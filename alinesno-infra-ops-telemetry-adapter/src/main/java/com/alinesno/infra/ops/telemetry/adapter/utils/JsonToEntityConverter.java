package com.alinesno.infra.ops.telemetry.adapter.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.ops.telemetry.entity.LogResource;
import com.alinesno.infra.ops.telemetry.entity.MetricsResource;
import com.alinesno.infra.ops.telemetry.entity.TraceResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON转换为实体对象的工具类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class JsonToEntityConverter {

    private static final Logger log = LoggerFactory.getLogger(JsonToEntityConverter.class) ;

    /**
     * 将JSON转换为MetricsResource对象列表
     *
     * @param json JSON字符串
     * @return MetricsResource对象列表
     */
    public static List<MetricsResource> JsonToMetrics(String json) {
        List<MetricsResource> metricsResources = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(json);

        JSONArray resourceLogs = jsonObject.getJSONArray("resourceLogs");
        for (int i = 0; i < resourceLogs.size(); i++) {
            JSONObject resourceLog = resourceLogs.getJSONObject(i);
            JSONObject resource = resourceLog.getJSONObject("resource");
            JSONArray attributes = resource.getJSONArray("attributes");
            JSONObject serviceNameAttribute = attributes.getJSONObject(0);
            String serviceName = serviceNameAttribute.getJSONObject("value").getString("stringValue");

            MetricsResource metricsResource = new MetricsResource();
            metricsResources.add(metricsResource);
        }

        return metricsResources;
    }

    /**
     * 将JSON转换为TraceResource对象列表
     *
     * @param json JSON字符串
     * @return TraceResource对象列表
     */
    public static List<TraceResource> JsonToTrace(String json) {
        List<TraceResource> traceResources = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(json);

        JSONArray resourceLogs = jsonObject.getJSONArray("resourceLogs");
        for (int i = 0; i < resourceLogs.size(); i++) {
            JSONObject resourceLog = resourceLogs.getJSONObject(i);
            JSONObject scopeLog = resourceLog.getJSONArray("scopeLogs").getJSONObject(0);
            JSONObject scope = scopeLog.getJSONObject("scope");
            String scopeName = scope.getString("name");
            String scopeVersion = scope.getString("version");

            TraceResource traceResource = new TraceResource();
            traceResources.add(traceResource);
        }

        return traceResources;
    }

    /**
     * 将JSON转换为LogResource对象列表
     *
     * @param json JSON字符串
     * @return LogResource对象列表
     */
    public static List<LogResource> JsonToLog(String json) {
        List<LogResource> logResources = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(json);

        JSONArray resourceLogs = jsonObject.getJSONArray("resourceLogs");
        for (int i = 0; i < resourceLogs.size(); i++) {
            JSONObject resourceLog = resourceLogs.getJSONObject(i);
            JSONObject resource = resourceLog.getJSONObject("resource");
            JSONArray attributes = resource.getJSONArray("attributes");
            JSONObject serviceNameAttribute = attributes.getJSONObject(0);
            String serviceName = serviceNameAttribute.getJSONObject("value").getString("stringValue");

            LogResource logResource = new LogResource();
            logResource.setResourceName(serviceName);
            logResources.add(logResource);
        }

        return logResources;
    }

}
