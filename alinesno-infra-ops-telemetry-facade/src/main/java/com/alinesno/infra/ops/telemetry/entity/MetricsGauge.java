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
@TableName("gauges")
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
}
