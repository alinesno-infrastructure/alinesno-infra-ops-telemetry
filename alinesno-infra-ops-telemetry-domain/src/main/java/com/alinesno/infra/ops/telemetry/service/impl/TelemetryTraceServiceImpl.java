package com.alinesno.infra.ops.telemetry.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.TelemetryTrace;
import com.alinesno.infra.ops.telemetry.mapper.TelemetryTraceMapper;
import com.alinesno.infra.ops.telemetry.service.ITelemetryTraceService;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表示 Trace 的 Service业务层处理
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Service
public class TelemetryTraceServiceImpl extends IBaseServiceImpl<TelemetryTrace, TelemetryTraceMapper> implements ITelemetryTraceService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(TraceResourceServiceImpl.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void saveTrace(List<String> logList) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = sqlSessionTemplate.getConnection();

            // 关闭自动提交
            connection.setAutoCommit(false);

//            String sql = "INSERT INTO telemetry_trace (Timestamp, TraceId, SpanId, ParentSpanId, TraceState, SpanName, " +
//                    "SpanKind, ServiceName, ResourceAttributes, ScopeName, ScopeVersion, SpanAttributes, Duration, " +
//                    "StatusCode, StatusMessage, Events.Timestamp, Events.Name, Events.Attributes, Links.TraceId, Links.SpanId, Links.TraceState, Links.Attributes) " +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            String sql = "INSERT INTO telemetry_trace (Timestamp, TraceId, SpanId, ParentSpanId, TraceState, SpanName, " +
                    "SpanKind, ServiceName, ResourceAttributes, ScopeName, ScopeVersion, SpanAttributes, Duration, " +
                    "StatusCode, StatusMessage) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


            statement = connection.prepareStatement(sql);

            for(String log: logList) {
                JSONArray jsonArray = JSON.parseArray(log);

                for(Object obj: jsonArray) {
                    JSONObject jsonObject = (JSONObject) obj;

                    // 验证解析是否正确
//                    System.out.println(jsonObject.getString("traceId"));
//                    System.out.println();

                    // 设置数据
                    statement.setString(1, jsonObject.getString("startTimeUnixNano"));
                    statement.setString(2, jsonObject.getString("traceId"));
                    statement.setString(3, jsonObject.getString("spanId"));
                    statement.setString(4, jsonObject.getString("parentSpanId"));
                    statement.setString(5, jsonObject.getString("traceState"));
                    statement.setString(6, jsonObject.getString("spanName"));
                    statement.setString(7, jsonObject.getString("spanKind"));
                    statement.setString(8, jsonObject.getString("serviceName"));

                    JSONObject resAttr = jsonObject.getJSONObject("resAttr");
                    Map<String, String> resourceAttributes = new HashMap<>();
                    for (String key : resAttr.keySet()) {
                        String value = resAttr.getString(key);
                        resourceAttributes.put(key, value);
                    }
                    statement.setObject(9, resourceAttributes);

                    statement.setString(10, jsonObject.getString("scopeName"));
                    statement.setString(11, jsonObject.getString("scopeVersion"));

                    JSONObject spanAttr = jsonObject.getJSONObject("spanAttr");
                    Map<String, String> spanAttributes = new HashMap<>();
                    for (String key : spanAttr.keySet()) {
                        String value = spanAttr.getString(key);
                        spanAttributes.put(key, value);
                    }
                    statement.setObject(12, spanAttributes);

                    statement.setLong(13, jsonObject.getLong("nanoseconds"));
                    statement.setString(14, jsonObject.getString("statusCode"));
                    statement.setString(15, jsonObject.getString("statusMessage"));

                    // TODO
                    // eventTimes 和 eventNames 都是Array，但是eventAttrs是object，存进去的时候会报错
//                    statement.setString(16, jsonObject.getString("eventTimes"));
//                    statement.setString(17, jsonObject.getString("eventNames"));

                    // Column 17,  name: Events.Attributes,  type: Array(Map(LowCardinality(String), String)), parsed text: <EMPTY>ERROR
                    // Code: 130. DB::Exception: Array does not start with '[' character. (CANNOT_READ_ARRAY_FROM_TEXT) (version 23.7.4.5 (official build))
//                    JSONObject eventAttrs = jsonObject.getJSONObject("eventAttrs");
//                    Map<String, String> eventAttributes = new HashMap<>();
//                    for (String key : eventAttrs.keySet()) {
//                        String value = eventAttrs.getString(key);
//                        eventAttributes.put(key, value);
//                    }
//                    statement.setObject(18, eventAttributes);

                    // 报错：Exception: Elements 'Events.Timestamp' and 'Events.Attributes' of Nested data structure 'Events' (Array columns) have different array sizes.
//                    String eventAttrs = "[" + jsonObject.getString("eventAttrs") + "]";
//                    statement.setString(18, eventAttrs);

                    // TODO
                    // 跟上一个一样的问题
//                    statement.setString(19, jsonObject.getString("linksTraceIDs"));
//                    statement.setString(20, jsonObject.getString("linksSpanIDs"));
//                    statement.setString(21, jsonObject.getString("linksTraceStates"));
//                    statement.setObject(22, jsonObject.getString("linkAttributes"));


                    statement.addBatch();

                }
            }

            int[] result = statement.executeBatch();

            // 提交事务
            connection.commit();
        } catch (SQLException e) {

            log.error("数据插入异常:{}", e.getMessage());
            // 出现异常时回滚事务
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                log.error("数据插入回滚异常:{}", e.getMessage());
            }
            throw new RuntimeException(e);
        } finally {
            // 关闭资源
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    log.error("关闭PreparedStatement异常: {}", e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("关闭Connection异常: {}", e.getMessage());
                }
            }
        }
    }
}
