package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.BaseEntity;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 表示 telemetry_logs 实体。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("telemetry_logs")
public class TelemetryLogs extends InfraBaseEntity {

    @TableField("Timestamp")
    private LocalDateTime timestamp; // 时间戳

    @TableField("TraceId")
    private String traceId; // 跟踪ID

    @TableField("SpanId")
    private String spanId; // 跨度ID

    @TableField("TraceFlags")
    private Long traceFlags; // 跟踪标志

    @TableField("SeverityText")
    private String severityText; // 严重性文本

    @TableField("SeverityNumber")
    private Integer severityNumber; // 严重性数字

    @TableField("ServiceName")
    private String serviceName; // 服务名称

    @TableField("Body")
    private String body; // 日志内容

    @TableField("ResourceSchemaUrl")
    private String resourceSchemaUrl; // 资源模式URL

    @TableField("ResourceAttributes")
    private Map<String, String> resourceAttributes; // 资源属性

    @TableField("ScopeSchemaUrl")
    private String scopeSchemaUrl; // 作用域模式URL

    @TableField("ScopeName")
    private String scopeName; // 作用域名称

    @TableField("ScopeVersion")
    private String scopeVersion; // 作用域版本

    @TableField("ScopeAttributes")
    private Map<String, String> scopeAttributes; // 作用域属性

    @TableField("LogAttributes")
    private Map<String, String> logAttributes; // 日志属性

    // 省略 getter 和 setter 方法

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
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

    public Long getTraceFlags() {
        return traceFlags;
    }

    public void setTraceFlags(Long traceFlags) {
        this.traceFlags = traceFlags;
    }

    public String getSeverityText() {
        return severityText;
    }

    public void setSeverityText(String severityText) {
        this.severityText = severityText;
    }

    public Integer getSeverityNumber() {
        return severityNumber;
    }

    public void setSeverityNumber(Integer severityNumber) {
        this.severityNumber = severityNumber;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getResourceSchemaUrl() {
        return resourceSchemaUrl;
    }

    public void setResourceSchemaUrl(String resourceSchemaUrl) {
        this.resourceSchemaUrl = resourceSchemaUrl;
    }

    public Map<String, String> getResourceAttributes() {
        return resourceAttributes;
    }

    public void setResourceAttributes(Map<String, String> resourceAttributes) {
        this.resourceAttributes = resourceAttributes;
    }

    public String getScopeSchemaUrl() {
        return scopeSchemaUrl;
    }

    public void setScopeSchemaUrl(String scopeSchemaUrl) {
        this.scopeSchemaUrl = scopeSchemaUrl;
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

    public Map<String, String> getScopeAttributes() {
        return scopeAttributes;
    }

    public void setScopeAttributes(Map<String, String> scopeAttributes) {
        this.scopeAttributes = scopeAttributes;
    }

    public Map<String, String> getLogAttributes() {
        return logAttributes;
    }

    public void setLogAttributes(Map<String, String> logAttributes) {
        this.logAttributes = logAttributes;
    }
}
