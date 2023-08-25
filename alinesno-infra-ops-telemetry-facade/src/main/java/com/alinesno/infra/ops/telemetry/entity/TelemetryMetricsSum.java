package com.alinesno.infra.ops.telemetry.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;
import java.util.Map;

/**
 * 表示 telemetry_metrics_sum 实体。
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@TableName("telemetry_metrics_sum")
public class TelemetryMetricsSum extends InfraBaseEntity {

    @TableField("ResourceAttributes")
    private Map<String, String> resourceAttributes;

    @TableField("ResourceSchemaUrl")
    private String resourceSchemaUrl;

    @TableField("ScopeName")
    private String scopeName;

    @TableField("ScopeVersion")
    private String scopeVersion;

    @TableField("ScopeAttributes")
    private Map<String, String> scopeAttributes;

    @TableField("ScopeDroppedAttrCount")
    private Integer scopeDroppedAttrCount;

    @TableField("ScopeSchemaUrl")
    private String scopeSchemaUrl;

    @TableField("MetricName")
    private String metricName;

    @TableField("MetricDescription")
    private String metricDescription;

    @TableField("MetricUnit")
    private String metricUnit;

    @TableField("Attributes")
    private Map<String, String> attributes;

    @TableField("StartTimeUnix")
    private Long startTimeUnix;

    @TableField("TimeUnix")
    private Long timeUnix;

    @TableField("Value")
    private Double value;

    @TableField("Flags")
    private Integer flags;

    @TableField(exist = false)
    private List<Exemplar> exemplars;

    @TableField("AggTemp")
    private Integer aggTemp;

    @TableField("IsMonotonic")
    private boolean isMonotonic;

    // 省略 getter 和 setter 方法...

    /**
     * 表示 Exemplar 实体。
     */
    public static class Exemplar {

        @TableField("FilteredAttributes")
        private Map<String, String> filteredAttributes;

        @TableField("TimeUnix")
        private Long timeUnix;

        @TableField("Value")
        private Double value;

        @TableField("SpanId")
        private String spanId;

        @TableField("TraceId")
        private String traceId;

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

    public Integer getScopeDroppedAttrCount() {
        return scopeDroppedAttrCount;
    }

    public void setScopeDroppedAttrCount(Integer scopeDroppedAttrCount) {
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

    public Integer getFlags() {
        return flags;
    }

    public void setFlags(Integer flags) {
        this.flags = flags;
    }

    public List<Exemplar> getExemplars() {
        return exemplars;
    }

    public void setExemplars(List<Exemplar> exemplars) {
        this.exemplars = exemplars;
    }

    public Integer getAggTemp() {
        return aggTemp;
    }

    public void setAggTemp(Integer aggTemp) {
        this.aggTemp = aggTemp;
    }

    public boolean isMonotonic() {
        return isMonotonic;
    }

    public void setMonotonic(boolean monotonic) {
        isMonotonic = monotonic;
    }
}
