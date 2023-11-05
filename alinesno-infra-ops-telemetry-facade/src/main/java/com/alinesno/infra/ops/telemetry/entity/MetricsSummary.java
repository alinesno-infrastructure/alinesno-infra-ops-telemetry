package com.alinesno.infra.ops.telemetry.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
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
@Data
public class MetricsSummary extends InfraBaseEntity {

    @TableField("ResourceAttributes")
    @ColumnType(length = 255)
    @ColumnComment("resourceAttributes")
    private Map<String, String> resourceAttributes;

    @TableField("ResourceSchemaUrl")
    @ColumnType(length = 255)
    @ColumnComment("资源模式URL")
    private String resourceSchemaUrl;

    @TableField("ScopeName")
    @ColumnType(length = 50)
    @ColumnComment("作用范围名称")
    private String scopeName;

    @TableField("ScopeVersion")
    @ColumnType(length = 10)
    @ColumnComment("作用域版本")
    private String scopeVersion;

    @TableField("ScopeAttributes")
    @ColumnType(length = 255)
    @ColumnComment("scopeAttributes")
    private Map<String, String> scopeAttributes;

    @TableField("ScopeDroppedAttrCount")
    @ColumnType(length = 10)
    @ColumnComment("作用域丢弃属性计数")
    private Long scopeDroppedAttrCount;

    @TableField("ScopeSchemaUrl")
    @ColumnType(length = 255)
    @ColumnComment("作用范围模式URL")
    private String scopeSchemaUrl;

    @TableField("MetricName")
    @ColumnType(length = 50)
    @ColumnComment("指标名称")
    private String metricName;

    @TableField("MetricDescription")
    @ColumnType(length = 255)
    @ColumnComment("指标描述")
    private String metricDescription;

    @TableField("MetricUnit")
    @ColumnType(length = 20)
    @ColumnComment("度量单位")
    private String metricUnit;

    @TableField("Attributes")
    @ColumnType(length = 255)
    @ColumnComment("attributes")
    private Map<String, String> attributes;

    @TableField("StartTimeUnix")
    @ColumnType(length = 10)
    @ColumnComment("开始时间的Unix时间戳")
    private String startTimeUnix;

    @TableField("TimeUnix")
    @ColumnType(length = 10)
    @ColumnComment("时间戳")
    private String timeUnix;

    @TableField("Count")
    @ColumnType(length = 10)
    @ColumnComment("计数")
    private Long count;

    @TableField("Sum")
    @ColumnType(length = 10)
    @ColumnComment("求和")
    private Double sum;

    @TableField("ValueAtQuantiles")
    @ColumnType(length = 8)
    @ColumnComment("分位数值")
    private List<ValueAtQuantiles> valueAtQuantiles;

    @TableField("Flags")
    @ColumnType(length = 1)
    @ColumnComment("标志")
    private Long flags;

    public static class ValueAtQuantiles {
        @TableField("Quantile")
        @ColumnType(length = 8)
        @ColumnComment("分位数")
        private Double Quantile;
        @TableField("Value")
        @ColumnType(length = 255)
        @ColumnComment("值")
        private Double Value;
    }
}
