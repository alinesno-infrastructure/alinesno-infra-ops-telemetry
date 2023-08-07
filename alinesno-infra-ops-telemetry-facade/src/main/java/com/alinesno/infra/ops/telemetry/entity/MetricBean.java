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
@TableName("metrics")
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

}
