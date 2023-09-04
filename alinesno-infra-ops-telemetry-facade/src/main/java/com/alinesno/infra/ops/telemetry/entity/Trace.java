package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;
import java.util.Map;

/**
 * 表示 trace 实体。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("telemetry_trace")
public class Trace extends InfraBaseEntity {

    @TableField("Timestamp")
    private String timestamp; // 时间戳
     
    @TableField("TraceId")
    private String traceId; // 追踪 ID
     
    @TableField("SpanId")
    private String spanId; // 跨度 ID
     
    @TableField("ParentSpanId")
    private String parentSpanId; // 父跨度 ID
     
    @TableField("TraceState")
    private String traceState; // 追踪状态
     
    @TableField("SpanName")
    private String spanName; // 跨度名称
     
    @TableField("SpanKind")
    private String spanKind; // 跨度类型
     
    @TableField("ServiceName")
    private String serviceName; // 服务名称
     
    @TableField("ResourceAttributes")
    private Map<String, String> resourceAttributes; // 资源属性
     
    @TableField("ScopeName")
    private String scopeName; // 作用域名称
     
    @TableField("ScopeVersion")
    private String scopeVersion; // 作用域版本
     
    @TableField("SpanAttributes")
    private Map<String, String> spanAttributes; // 跨度属性

    @TableField("Duration")
    private Long duration; // 持续时间

    @TableField("StatusCode")
    private String statusCode; // 状态码

    @TableField("StatusMessage")
    private String statusMessage; // 状态信息

    @TableField("Events")
    private List<TelemetryEvent> events; // 事件列表

    @TableField("Links")
    private List<TelemetryLink> links; // 链接列表


    public static class TelemetryLink {

        @TableField("TraceId")
        private String traceId;
        @TableField("SpanId")
        private String spanId;
        @TableField("TraceState")
        private String traceState;
        @TableField("Attributes")
        private Map<String, String> attributes;

        public String getTraceId() {
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }

        public String getSpanId() {
            return spanId;
        }

        public void setSpanId(String spanId) {
            this.spanId = spanId;
        }

        public String getTraceState() {
            return traceState;
        }

        public void setTraceState(String traceState) {
            this.traceState = traceState;
        }

        public Map<String, String> getAttributes() {
            return attributes;
        }

        public void setAttributes(Map<String, String> attributes) {
            this.attributes = attributes;
        }
    }

    public static class TelemetryEvent {
        @TableField("Timestamp")
        private String timestamp;
        @TableField("Name")
        private String name;
        @TableField("Attributes")
        private Map<String, String> attributes;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, String> getAttributes() {
            return attributes;
        }

        public void setAttributes(Map<String, String> attributes) {
            this.attributes = attributes;
        }
    }


        // 省略 getter 和 setter 方法

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getParentSpanId() {
        return parentSpanId;
    }

    public void setParentSpanId(String parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public String getTraceState() {
        return traceState;
    }

    public void setTraceState(String traceState) {
        this.traceState = traceState;
    }

    public String getSpanName() {
        return spanName;
    }

    public void setSpanName(String spanName) {
        this.spanName = spanName;
    }

    public String getSpanKind() {
        return spanKind;
    }

    public void setSpanKind(String spanKind) {
        this.spanKind = spanKind;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Map<String, String> getResourceAttributes() {
        return resourceAttributes;
    }

    public void setResourceAttributes(Map<String, String> resourceAttributes) {
        this.resourceAttributes = resourceAttributes;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public String getScopeVersion() {
        return scopeVersion;
    }

    public void setScopeVersion(String scopeVersion) {
        this.scopeVersion = scopeVersion;
    }

    public Map<String, String> getSpanAttributes() {
        return spanAttributes;
    }

    public void setSpanAttributes(Map<String, String> spanAttributes) {
        this.spanAttributes = spanAttributes;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public List<TelemetryEvent> getEvents() {
        return events;
    }

    public void setEvents(List<TelemetryEvent> events) {
        this.events = events;
    }

    public List<TelemetryLink> getLinks() {
        return links;
    }

    public void setLinks(List<TelemetryLink> links) {
        this.links = links;
    }
}

