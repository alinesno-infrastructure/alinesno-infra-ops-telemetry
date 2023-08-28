package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;
import java.util.Map;

/**
 * 表示 telemetry_metrics_gauge 实体。
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@TableName("telemetry_metrics_gauge")
public class MetricsGauge extends InfraBaseEntity {

    @TableField("ResourceAttributes")
    private Map<String, String> resourceAttributes; // 资源属性
     
    @TableField("ResourceSchemaUrl")
    private String resourceSchemaUrl; // 资源模式 URL
     
    @TableField("ScopeName")
    private String scopeName; // 作用域名称
     
    @TableField("ScopeVersion")
    private String scopeVersion; // 作用域版本
     
    @TableField("ScopeAttributes")
    private Map<String, String> scopeAttributes; // 作用域属性
     
    @TableField("ScopeDroppedAttrCount")
    private Long scopeDroppedAttrCount; // 作用域丢弃的属性计数
     
    @TableField("ScopeSchemaUrl")
    private String scopeSchemaUrl; // 作用域模式 URL
     
    @TableField("MetricName")
    private String metricName; // 指标名称
     
    @TableField("MetricDescription")
    private String metricDescription; // 指标描述
     
    @TableField("MetricUnit")
    private String metricUnit; // 指标单位
     
    @TableField("Attributes")
    private Map<String, String> attributes; // 属性
     
    @TableField("StartTimeUnix")
    private Long startTimeUnix; // 起始时间（Unix 时间戳）
     
    @TableField("TimeUnix")
    private Long timeUnix; // 时间（Unix 时间戳）
     
    @TableField("Value")
    private Double value; // 值
     
    @TableField("Flags")
    private Long flags; // 标志
     
    @TableField("Exemplars")
    private List<Exemplar> exemplars; // 示范点
     
    // 省略 getter 和 setter 方法

    /**
     * 示范点实体类。
     */
    public static class Exemplar {
        @TableField("FilteredAttributes")
        private Map<String, String> filteredAttributes; // 过滤属性

        @TableField("TimeUnix")
        private Long timeUnix; // 时间（Unix 时间戳）

        @TableField("Value")
        private Double value; // 值

        @TableField("SpanId")
        private String spanId; // 跨度 ID

        @TableField("TraceId")
        private String traceId; // 追踪 ID

        // 省略 getter 和 setter 方法


        public Map<String, String> getFilteredAttributes() {
            return filteredAttributes;
        }

        public void setFilteredAttributes(Map<String, String> filteredAttributes) {
            this.filteredAttributes = filteredAttributes;
        }

        public Long getTimeUnix() {
            return timeUnix;
        }

        public void setTimeUnix(Long timeUnix) {
            this.timeUnix = timeUnix;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public String getSpanId() {
            return spanId;
        }

        public void setSpanId(String spanId) {
            this.spanId = spanId;
        }

        public String getTraceId() {
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }
    }

    public Map<String, String> getResourceAttributes() {
        return resourceAttributes;
    }

    public void setResourceAttributes(Map<String, String> resourceAttributes) {
        this.resourceAttributes = resourceAttributes;
    }

    public String getResourceSchemaUrl() {
        return resourceSchemaUrl;
    }

    public void setResourceSchemaUrl(String resourceSchemaUrl) {
        this.resourceSchemaUrl = resourceSchemaUrl;
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

    public Long getScopeDroppedAttrCount() {
        return scopeDroppedAttrCount;
    }

    public void setScopeDroppedAttrCount(Long scopeDroppedAttrCount) {
        this.scopeDroppedAttrCount = scopeDroppedAttrCount;
    }

    public String getScopeSchemaUrl() {
        return scopeSchemaUrl;
    }

    public void setScopeSchemaUrl(String scopeSchemaUrl) {
        this.scopeSchemaUrl = scopeSchemaUrl;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getMetricDescription() {
        return metricDescription;
    }

    public void setMetricDescription(String metricDescription) {
        this.metricDescription = metricDescription;
    }

    public String getMetricUnit() {
        return metricUnit;
    }

    public void setMetricUnit(String metricUnit) {
        this.metricUnit = metricUnit;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public Long getStartTimeUnix() {
        return startTimeUnix;
    }

    public void setStartTimeUnix(Long startTimeUnix) {
        this.startTimeUnix = startTimeUnix;
    }

    public Long getTimeUnix() {
        return timeUnix;
    }

    public void setTimeUnix(Long timeUnix) {
        this.timeUnix = timeUnix;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getFlags() {
        return flags;
    }

    public void setFlags(Long flags) {
        this.flags = flags;
    }

    public List<Exemplar> getExemplars() {
        return exemplars;
    }

    public void setExemplars(List<Exemplar> exemplars) {
        this.exemplars = exemplars;
    }
}
