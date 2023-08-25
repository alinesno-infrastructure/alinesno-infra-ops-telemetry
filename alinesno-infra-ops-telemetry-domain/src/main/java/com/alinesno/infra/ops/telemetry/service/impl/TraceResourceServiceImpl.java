package com.alinesno.infra.ops.telemetry.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.TelemetryTrace;
import com.alinesno.infra.ops.telemetry.entity.TraceResource;
import com.alinesno.infra.ops.telemetry.mapper.TraceResourceMapper;
import com.alinesno.infra.ops.telemetry.service.ITraceResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 表示 Resource 的 Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class TraceResourceServiceImpl extends IBaseServiceImpl<TraceResource, TraceResourceMapper> implements ITraceResourceService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(TraceResourceServiceImpl.class);

//    @Autowired
//    private DriverManager driverManager;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    private String sql = "INSERT INTO telemetry_trace (Timestamp, TraceId, SpanId, ParentSpanId, TraceState, SpanName, SpanKind, ServiceName, ResourceAttributes, ScopeName, ScopeVersion, SpanAttributes, Duration, StatusCode, StatusMessage, Events, Links) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void saveTrace(List<String> logList) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("连接 ClickHouse 数据库成功！！！！！");

            // 关闭自动提交
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(sql);

//            for(String log: logList) {
//                JSONArray jsonArray = JSON.parseArray(log);
//
//                for(Object obj : jsonArray){
//                    JSONObject jsonObject = (JSONObject) obj;
//
//                    TelemetryTrace telemetryTrace = jsonToTrace(jsonObject);
//
//
//                    statement.setString(1, telemetryTrace.getTimestamp());
//                    statement.setString(2, telemetryTrace.getTraceId());
//                    statement.setString(3, telemetryTrace.getSpanId());
//                    statement.setString(4, telemetryTrace.getParentSpanId());
//                    statement.setString(5, telemetryTrace.getTraceState());
//                    statement.setString(6, telemetryTrace.getSpanName());
//                    statement.setString(7, telemetryTrace.getSpanKind());
//                    statement.setString(8, telemetryTrace.getServiceName());
//                    statement.setObject(9, telemetryTrace.getResourceAttributes());
//                    statement.setString(10, telemetryTrace.getScopeName());
//                    statement.setString(11, telemetryTrace.getScopeVersion());
//                    statement.setObject(12, telemetryTrace.getSpanAttributes());
//                    statement.setLong(13, telemetryTrace.getDuration());
//                    statement.setString(14, telemetryTrace.getStatusCode());
//                    statement.setString(15, telemetryTrace.getStatusMessage());
//                    statement.setObject(16, telemetryTrace.getEvents());
//                    statement.setObject(17, telemetryTrace.getLinks());
//
//                    statement.executeUpdate();
//
//                }
//            }


            // 提交事务
            connection.commit();
            System.out.println("数据插入成功！！！！！");
        } catch (SQLException e) {
            log.error("数据插入异常: {}", e.getMessage());

            // 出现异常时回滚事务
            try {
                if (connection != null) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("数据插入回滚异常: {}", ex.getMessage());
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("关闭数据库连接异常: {}", e.getMessage());
            }
        }
    }


    public static TelemetryTrace jsonToTrace(JSONObject jsonObject) {
        TelemetryTrace telemetryTrace = new TelemetryTrace();

        // 获取键值
        String timestamp = jsonObject.getString("startTimeUnixNano");
        String traceId = jsonObject.getString("traceId");
        String spanId = jsonObject.getString("spanId");
        String parentSpanId = jsonObject.getString("parentSpanId");
        String traceState = jsonObject.getString("traceState");
        String spanName = jsonObject.getString("name");
        String spanKind = jsonObject.getString("kind");
        String serviceName = jsonObject.getString("serviceName");
        JSONObject resourceAttributes = jsonObject.getJSONObject("resAttr");
        String scopeName = jsonObject.getString("scopeName");
        String scopeVersion = jsonObject.getString("scopeVersion");
        JSONObject spanAttributes = jsonObject.getJSONObject("spanAttr");
        Long duration = jsonObject.getLong("nanoseconds");
        String statusCode = jsonObject.getString("statusCode");

        JSONArray eventTimes = jsonObject.getJSONArray("eventTimes");
        JSONArray eventNames = jsonObject.getJSONArray("eventNames");
        JSONObject eventAttrs = jsonObject.getJSONObject("eventAttrs");

        JSONArray linksTraceIDs = jsonObject.getJSONArray("linksTraceIDs");
        JSONArray linksTraceSpanIDs = jsonObject.getJSONArray("linksTraceSpanIDs");
        JSONArray linksTraceStates = jsonObject.getJSONArray("linksTraceStates");
        JSONObject linksAttrs = jsonObject.getJSONObject("linksAttrs");

        // 赋值到实体类对象
//        telemetryTrace.setTimestamp(timestamp);
//        telemetryTrace.setTraceId(traceId);
//        telemetryTrace.setSpanId(spanId);
//        telemetryTrace.setParentSpanId(parentSpanId);
//        telemetryTrace.setTraceState(traceState);
//        telemetryTrace.setSpanName(spanName);
//        telemetryTrace.setSpanKind(spanKind);
//        telemetryTrace.setServiceName(serviceName);
//        telemetryTrace.setResourceAttributes(resourceAttributes);
//        telemetryTrace.setScopeName(scopeName);
//        telemetryTrace.setScopeVersion(scopeVersion);
//        telemetryTrace.setSpanAttributes(spanAttributes);
//        telemetryTrace.setDuration(duration);
//        telemetryTrace.setStatusCode(statusCode);
//
//        TelemetryEvent telemetryEvent = new TelemetryEvent();
//        telemetryEvent.setTimestamp(eventTimes);



        return telemetryTrace;
    }
}
