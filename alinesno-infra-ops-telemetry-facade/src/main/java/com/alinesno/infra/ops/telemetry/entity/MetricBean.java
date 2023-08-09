package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 指标实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("metrics_bean")
public class MetricBean extends InfraBaseEntity {

    @TableField("span_id")
    private String spanId ; // 资源 ID

    @TableField("scope_id")
    private String scopeId ; // 资源 ID

    @TableField("resource_id")
    private String resourceId; // 资源 ID

    /**
     * 指标名称。
     */
    @JsonProperty("name")
    @TableField("metric_name")
    private String name;

    /**
     * 指标单位。
     */
    @JsonProperty("unit")
    private String unit;

    /**
     * 指标描述。
     */
    @JsonProperty("description")
    private String description;

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
