package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 测量信息实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("metrics_gauge")
public class MetricsGauge extends InfraBaseEntity {

    @TableField("span_id")
    private String spanId ; // 资源 ID

    @TableField("scope_id")
    private String scopeId ; // 资源 ID

    @TableField("resource_id")
    private String resourceId; // 资源 ID

    /**
     * 测量值。
     */
    @JsonProperty("value")
    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

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
}
