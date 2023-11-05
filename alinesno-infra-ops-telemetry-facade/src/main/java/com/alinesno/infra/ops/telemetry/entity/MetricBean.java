package com.alinesno.infra.ops.telemetry.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
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
@Data
public class MetricBean extends InfraBaseEntity {

    @TableField("span_id")
	@ColumnType(length=255)
	@ColumnComment("资源 ID")
    private String spanId ; // 资源 ID

    @TableField("scope_id")
	@ColumnType(length=255)
	@ColumnComment("资源 ID")
    private String scopeId ; // 资源 ID

    @TableField("resource_id")
	@ColumnType(length=255)
	@ColumnComment("资源ID")
    private String resourceId; // 资源 ID

    /**
     * 指标名称。
     */
    @JsonProperty("name")
    @TableField("metric_name")
	@ColumnType(length=255)
	@ColumnComment("指标名称。")
    private String name;

    /**
     * 指标单位。
     */
    @JsonProperty("unit")
	@ColumnType(length=50)
	@ColumnComment("指标单位。")
	@TableField("unit")
    private String unit;

    /**
     * 指标描述。
     */
    @JsonProperty("description")
	@ColumnType(length=255)
	@ColumnComment("指标描述。")
	@TableField("description")
    private String description;
}
