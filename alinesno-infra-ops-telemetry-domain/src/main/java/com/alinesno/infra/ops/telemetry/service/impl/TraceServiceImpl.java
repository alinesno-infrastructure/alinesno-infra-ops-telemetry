package com.alinesno.infra.ops.telemetry.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.Trace;
import com.alinesno.infra.ops.telemetry.mapper.TraceMapper;
import com.alinesno.infra.ops.telemetry.service.ITraceService;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alinesno.infra.ops.telemetry.enums.ClickhouseSqls.traceSql;

/**
 * 表示 Trace 的 Service业务层处理
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Service
public class TraceServiceImpl extends IBaseServiceImpl<Trace, TraceMapper> implements ITraceService {
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

            statement = connection.prepareStatement(traceSql);

            for(String traceObj: logList) {
                JSONArray jsonArray = JSON.parseArray(traceObj);

                for(Object obj : jsonArray) {
                    JSONObject jsonObject = (JSONObject) obj;

                    // 验证解析是否正确
                    log.debug(jsonObject.getString("startTimeUnixNano"));

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
                    statement.setString(16, jsonObject.getString("eventTimes"));
                    statement.setString(17, jsonObject.getString("eventNames"));

                    // TODO eventAttrs 数据为 Map 类型，但是数据库对应字段类型为 Array，调整 eventAttrs 数据以正确插入数据库中
//                    statement.setString(18, jsonObject.getString("eventAttrs"));

                    statement.setString(19, jsonObject.getString("linksTraceIDs"));
                    statement.setString(20, jsonObject.getString("linksSpanIDs"));
                    statement.setString(21, jsonObject.getString("linksTraceStates"));

                    // TODO linkAttributes 数据为 Map 类型，但是数据库对应字段类型为 Array，调整 linkAttributes 数据以正确插入数据库中
//                    statement.setString(22, jsonObject.getString("linkAttributes"));


                    statement.addBatch();
                }

                int[] result = statement.executeBatch();

                // 提交事务
                connection.commit();
            }

            statement.close();
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
        }
    }
}
