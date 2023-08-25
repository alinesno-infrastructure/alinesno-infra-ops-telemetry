package com.alinesno.infra.ops.telemetry.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.TelemetryMetricsGauge;
import com.alinesno.infra.ops.telemetry.mapper.TelemetryMetricsGaugeMapper;
import com.alinesno.infra.ops.telemetry.service.ITelemetryMetricsGaugeService;
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
import java.util.Map;

/**
 * 表示 TelemetryMetricsGauge 的 Service 业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class TelemetryMetricsGaugeServiceImpl extends IBaseServiceImpl<TelemetryMetricsGauge, TelemetryMetricsGaugeMapper> implements ITelemetryMetricsGaugeService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(TelemetryMetricsGaugeServiceImpl.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void saveGauge(String logList) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = sqlSessionTemplate.getConnection();

            // 关闭自动提交
            connection.setAutoCommit(false);

//            String sql = "INSERT INTO telemetry_metrics_gauge (ResourceAttributes, ResourceSchemaUrl, ScopeName, ScopeVersion, " +
//                    "ScopeAttributes, ScopeDroppedAttrCount, ScopeSchemaUrl, MetricName, MetricDescription, MetricUnit, " +
//                    "Attributes, StartTimeUnix, TimeUnix, Value, Flags, Exemplars.FilteredAttributes, Exemplars.TimeUnix, Exemplars.Value, Exemplars.SpanId, Exemplars.TraceId) " +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            String sql = "INSERT INTO telemetry_metrics_gauge (ResourceAttributes, ResourceSchemaUrl, ScopeName, ScopeVersion, " +
                    "ScopeAttributes, ScopeDroppedAttrCount, ScopeSchemaUrl, MetricName, MetricDescription, MetricUnit, " +
                    "Attributes, StartTimeUnix, TimeUnix, Value, Flags) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            statement = connection.prepareStatement(sql);

            JSONArray jsonArray = JSON.parseArray(logList);
            for(Object obj: jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                // 验证解析是否正确
//                System.out.println(jsonObject.getString("metricName"));
//                System.out.println();

                JSONObject resourceAttributes = jsonObject.getJSONObject("resourceAttributes");
                Map<String, String> resAttr = new HashMap<>();
                for (String key : resourceAttributes.keySet()) {
                    String value = resourceAttributes.getString(key);
                    resAttr.put(key, value);
                }
                statement.setObject(1, resAttr);

                statement.setString(2, jsonObject.getString("resourceSchemaUrl"));
                statement.setString(3, jsonObject.getString("scopeName"));
                statement.setString(4, jsonObject.getString("scopeVersion"));

                JSONObject scopeAttributes = jsonObject.getJSONObject("scopeAttributes");
                Map<String, String> scoAttr = new HashMap<>();
                for (String key : scopeAttributes.keySet()) {
                    String value = scopeAttributes.getString(key);
                    scoAttr.put(key, value);
                }
                statement.setObject(5, scoAttr);

                statement.setLong(6, jsonObject.getLong("scopeDroppedAttrCount"));
                statement.setString(7, jsonObject.getString("scopeSchemaUrl"));
                statement.setString(8, jsonObject.getString("metricName"));
                statement.setString(9, jsonObject.getString("metricDescription"));
                statement.setString(10, jsonObject.getString("metricUnit"));

                JSONObject attributes = jsonObject.getJSONObject("attributes");
                Map<String, String> attr = new HashMap<>();
                for (String key : attributes.keySet()) {
                    String value = attributes.getString(key);
                    attr.put(key, value);
                }
                statement.setObject(11, attr);

                statement.setString(12, jsonObject.getString("startTimeUnix"));
                statement.setString(13, jsonObject.getString("timeUnix"));
                statement.setDouble(14, jsonObject.getDouble("value"));
                statement.setLong(15, jsonObject.getLong("flags"));

                // TODO
//                statement.setString(16, jsonObject.getString("exemplarsFilteredAttributes"));
//
//                statement.setObject(17, jsonObject.getString("exemplarsTimeUnix"));
//                statement.setString(18, jsonObject.getString("exemplarsValue"));
//                statement.setString(19, jsonObject.getString("exemplarsSpanId"));
//                statement.setString(20, jsonObject.getString("exemplarsTraceId"));

                statement.addBatch();
            }

            int[] result = statement.executeBatch();

            // 提交事务
            connection.commit();
        } catch (SQLException e) {
            log.error("数据插入异常: {}", e.getMessage());
            // 出现异常时回滚事务
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                log.error("数据插入回滚异常: {}", ex.getMessage());
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
