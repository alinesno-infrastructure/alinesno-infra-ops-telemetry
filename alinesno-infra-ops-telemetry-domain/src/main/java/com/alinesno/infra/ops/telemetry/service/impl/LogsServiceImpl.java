package com.alinesno.infra.ops.telemetry.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.Logs;
import com.alinesno.infra.ops.telemetry.mapper.LogsMapper;
import com.alinesno.infra.ops.telemetry.service.ILogsService;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alinesno.infra.ops.telemetry.enums.ClickhouseSqls.logsSql;

/**
 * 表示 Logs 的 Service 实现类
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class LogsServiceImpl extends IBaseServiceImpl<Logs, LogsMapper> implements ILogsService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(LogsServiceImpl.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void saveLogs(List<String> logList) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = sqlSessionTemplate.getConnection();

            // 关闭自动提交
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(logsSql);

            for(String logsObj: logList) {
                JSONArray jsonArray = JSON.parseArray(logsObj);

                for(Object obj: jsonArray) {
                    JSONObject jsonObject = (JSONObject) obj;

                    statement.setLong(1, jsonObject.getLong("timestamp"));
                    statement.setString(2, jsonObject.getString("traceId"));
                    statement.setString(3, jsonObject.getString("spanId"));
                    statement.setLong(4, jsonObject.getLong("traceFlags"));
                    statement.setString(5, jsonObject.getString("severityText"));
                    statement.setLong(6, jsonObject.getLong("severityNumber"));
                    statement.setString(7, jsonObject.getString("serviceName"));
                    statement.setString(8, jsonObject.getString("body"));
                    statement.setString(9, jsonObject.getString("resourceSchemaUrl"));
                    JSONObject resourceAttributes = jsonObject.getJSONObject("resourceAttributes");
                    Map<String, String> resAttr = new HashMap<>();
                    for (String key : resourceAttributes.keySet()) {
                        String value = resourceAttributes.getString(key);
                        resAttr.put(key, value);
                    }
                    statement.setObject(10, resAttr);
                    statement.setString(11, jsonObject.getString("scopeSchemaUrl"));
                    statement.setString(12, jsonObject.getString("scopeName"));
                    statement.setString(13, jsonObject.getString("scopeVersion"));
                    JSONObject scopeAttributes = jsonObject.getJSONObject("scopeAttributes");
                    Map<String, String> scoAttr = new HashMap<>();
                    for (String key : scopeAttributes.keySet()) {
                        String value = scopeAttributes.getString(key);
                        scoAttr.put(key, value);
                    }
                    statement.setObject(14, scoAttr);

                    JSONObject logAttributes = jsonObject.getJSONObject("logAttributes");
                    Map<String, String> logAttr = new HashMap<>();
                    for (String key : logAttributes.keySet()) {
                        String value = logAttributes.getString(key);
                        logAttr.put(key, value);
                    }
                    statement.setObject(15, logAttr);

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
