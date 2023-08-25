package com.alinesno.infra.ops.telemetry.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.TelemetryMetricsSummary;
import com.alinesno.infra.ops.telemetry.mapper.TelemetryMetricsSummaryMapper;
import com.alinesno.infra.ops.telemetry.service.ITelemetryMetricsSummaryService;
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
 * 表示 TelemetryMetricsSummary 的 Service 业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class TelemetryMetricsSummaryServiceImpl extends IBaseServiceImpl<TelemetryMetricsSummary, TelemetryMetricsSummaryMapper> implements ITelemetryMetricsSummaryService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(TelemetryMetricsSummaryServiceImpl.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void saveSummary(String logList) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = sqlSessionTemplate.getConnection();

            // 关闭自动提交
            connection.setAutoCommit(false);

            String sql = "INSERT INTO telemetry_metrics_summary " +
                    "(ResourceAttributes, ResourceSchemaUrl, ScopeName, ScopeVersion, ScopeAttributes, " +
                    "ScopeDroppedAttrCount, ScopeSchemaUrl, MetricName, MetricDescription, MetricUnit, " +
                    "Attributes, StartTimeUnix, TimeUnix, Count, Sum, ValueAtQuantiles.Quantile, ValueAtQuantiles.Value, Flags) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            statement = connection.prepareStatement(sql);

            JSONArray jsonArray = JSON.parseArray(logList);
            for(Object obj: jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

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

                statement.setString(6, jsonObject.getString("scopeDroppedAttrCount"));
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

                statement.setLong(12, jsonObject.getLong("startTimeUnix"));
                statement.setLong(13, jsonObject.getLong("timeUnix"));
                statement.setLong(14, jsonObject.getLong("count"));
                statement.setDouble(15, jsonObject.getDouble("sum"));
                statement.setString(16, jsonObject.getString("valueAtQuantilesQuantile"));
                statement.setString(17, jsonObject.getString("valueAtQuantilesValue"));
                statement.setLong(18, jsonObject.getLong("flags"));

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
