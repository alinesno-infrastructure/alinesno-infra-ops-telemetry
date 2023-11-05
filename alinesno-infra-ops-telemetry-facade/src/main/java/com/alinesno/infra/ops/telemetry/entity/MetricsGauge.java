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
 * 表示 telemetry_metrics_gauge 实体。
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@TableName("telemetry_metrics_gauge")
@Data
public class MetricsGauge extends InfraBaseEntity {

    @TableField("ResourceAttributes")
    @ColumnType(length = 255)
    @ColumnComment("资源属性")
    private Map<String, String> resourceAttributes; // 资源属性

    @TableField("ResourceSchemaUrl")
    @ColumnType(length = 255)
    @ColumnComment("资源模式URL")
    private String resourceSchemaUrl; // 资源模式 URL

    @TableField("ScopeName")
    @ColumnType(length = 50)
    @ColumnComment("作用域名称")
    private String scopeName; // 作用域名称

    @TableField("ScopeVersion")
    @ColumnType(length = 10)
    @ColumnComment("作用域版本")
    private String scopeVersion; // 作用域版本

    @TableField("ScopeAttributes")
    @ColumnType(length = 255)
    @ColumnComment("字符串")
    private Map<String, String> scopeAttributes; // 作用域属性

    @TableField("ScopeDroppedAttrCount")
    @ColumnType(length = 10)
    @ColumnComment("作用域丢弃属性计数")
    private Long scopeDroppedAttrCount; // 作用域丢弃的属性计数

    @TableField("ScopeSchemaUrl")
    @ColumnType(length = 255)
    @ColumnComment("作用域模式 URL")
    private String scopeSchemaUrl; // 作用域模式 URL

    @TableField("MetricName")
    @ColumnType(length = 255)
    @ColumnComment("指标名称")
    private String metricName; // 指标名称

    @TableField("MetricDescription")
    @ColumnType(length = 255)
    @ColumnComment("指标描述")
    private String metricDescription; // 指标描述

    @TableField("MetricUnit")
    @ColumnType(length = 20)
    @ColumnComment("度量单位")
    private String metricUnit; // 指标单位

    @TableField("Attributes")
    @ColumnType(length = 255)
    @ColumnComment("属性")
    private Map<String, String> attributes; // 属性

    @TableField("StartTimeUnix")
    @ColumnType(length = 10)
    @ColumnComment("开始时间的Unix时间戳")
    private Long startTimeUnix; // 起始时间（Unix 时间戳）

    @TableField("TimeUnix")
    @ColumnType(length = 10)
    @ColumnComment("时间戳")
    private Long timeUnix; // 时间（Unix 时间戳）

    @TableField("Value")
    @ColumnType(length = 255)
    @ColumnComment("value")
    private Double value; // 值

    @TableField("Flags")
    @ColumnType(length = 255)
    @ColumnComment("标志")
    private Long flags; // 标志

    @TableField("Exemplars")
    @ColumnType(length = 255)
    @ColumnComment("示例")
    private List<Exemplar> exemplars; // 示范点

    // 省略 getter 和 setter 方法

    /**
     * 示范点实体类。
     */
    public static class Exemplar {
        @TableField("FilteredAttributes")
        @ColumnType(length = 255)
        @ColumnComment("示范点实体类。")
        private Map<String, String> filteredAttributes; // 过滤属性

        @TableField("TimeUnix")
        @ColumnType(length = 255)
        @ColumnComment("时间戳")
        private Long timeUnix; // 时间（Unix 时间戳）

        @TableField("Value")
        @ColumnType(length = 255)
        @ColumnComment("原数据库字段名")
        private Double value; // 值

        @TableField("SpanId")
        @ColumnType(length = 64)
        @ColumnComment("跨度标识")
        private String spanId; // 跨度 ID

        @TableField("TraceId")
        @ColumnType(length = 36)
        @ColumnComment("追踪ID")
        private String traceId; // 追踪 ID
    }
}
