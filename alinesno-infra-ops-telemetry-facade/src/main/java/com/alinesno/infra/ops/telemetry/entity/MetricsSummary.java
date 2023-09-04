package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;
import java.util.Map;


/**
 * 表示 metrics_summary 实体。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("telemetry_metrics_summary")
public class MetricsSummary extends InfraBaseEntity {

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
    private Long scopeDroppedAttrCount;

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
    private String startTimeUnix;

    @TableField("TimeUnix")
    private String timeUnix;

    @TableField("Count")
    private Long count;

    @TableField("Sum")
    private Double sum;

    @TableField("ValueAtQuantiles")
    private List<ValueAtQuantiles> valueAtQuantiles;

    @TableField("Flags")
    private Long flags;

    public static class ValueAtQuantiles {
        @TableField("Quantile")
        private Double Quantile;
        @TableField("Value")
        private Double Value;

        public Double getQuantile() {
            return Quantile;
        }

        public void setQuantile(Double quantile) {
            Quantile = quantile;
        }

        public Double getValue() {
            return Value;
        }

        public void setValue(Double value) {
            Value = value;
        }
    }

    // 省略 getter 和 setter 方法


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

    public String getStartTimeUnix() {
        return startTimeUnix;
    }

    public void setStartTimeUnix(String startTimeUnix) {
        this.startTimeUnix = startTimeUnix;
    }

    public String getTimeUnix() {
        return timeUnix;
    }

    public void setTimeUnix(String timeUnix) {
        this.timeUnix = timeUnix;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public List<ValueAtQuantiles> getValueAtQuantiles() {
        return valueAtQuantiles;
    }

    public void setValueAtQuantiles(List<ValueAtQuantiles> valueAtQuantiles) {
        this.valueAtQuantiles = valueAtQuantiles;
    }

    public Long getFlags() {
        return flags;
    }

    public void setFlags(Long flags) {
        this.flags = flags;
    }
}