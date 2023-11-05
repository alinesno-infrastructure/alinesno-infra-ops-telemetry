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
 * 表示 telemetry_metrics_exponential_histogram 实体。
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@TableName("telemetry_metrics_exponential_histogram")
@Data
public class MetricsExponentialHistogram extends InfraBaseEntity {

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
    @ColumnComment("作用域属性")
    private Map<String, String> scopeAttributes; // 作用域属性

    @TableField("ScopeDroppedAttrCount")
    @ColumnType(length = 10)
    @ColumnComment("作用域丢弃的属性计数")
    private Long scopeDroppedAttrCount; // 作用域丢弃的属性计数

    @TableField("ScopeSchemaUrl")
    @ColumnType(length = 255)
    @ColumnComment("scopeSchemaUrl")
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

    @TableField("Count")
    @ColumnType(length = 10)
    @ColumnComment("计数")
    private Long count; // 计数

    @TableField("Sum")
    @ColumnType(length = 10)
    @ColumnComment("sum")
    private Double sum; // 总和

    @TableField("Scale")
    @ColumnType(length = 2)
    @ColumnComment("规模")
    private Integer scale; // 缩放

    @TableField("ZeroCount")
    @ColumnType(length = 1)
    @ColumnComment("零计数")
    private Long zeroCount; // 零计数

    @TableField("PositiveOffset")
    @ColumnType(length = 255)
    @ColumnComment("正偏移量")
    private Integer positiveOffset; // 正偏移量

    @TableField("PositiveBucketCounts")
    @ColumnType(length = 10)
    @ColumnComment("正向桶计数")
    private List<Long> positiveBucketCounts; // 正桶计数

    @TableField("NegativeOffset")
    @ColumnType(length = 1)
    @ColumnComment("负偏移量")
    private Integer negativeOffset; // 负偏移量

    @TableField("NegativeBucketCounts")
    @ColumnType(length = 10)
    @ColumnComment("负面桶计数")
    private List<Long> negativeBucketCounts; // 负桶计数

    @TableField("Exemplars")
    @ColumnType(length = 255)
    @ColumnComment("样本")
    private List<Exemplar> exemplars; // 示范点

    @TableField("Flags")
    @ColumnType(length = 1)
    @ColumnComment("标志")
    private Long flags; // 标志

    @TableField("Min")
    @ColumnType(length = 4)
    @ColumnComment("最小值")
    private Double min; // 最小值

    @TableField("Max")
    @ColumnType(length = 255)
    @ColumnComment("最大值")
    private Double max; // 最大值

    // 省略 getter 和 setter 方法

    /**
     * 示范点实体类。
     */
    public static class Exemplar {
        @TableField("FilteredAttributes")
        @ColumnType(length = 255)
        @ColumnComment("过滤属性。")
        private Map<String, String> filteredAttributes; // 过滤属性

        @TableField("TimeUnix")
        @ColumnType(length = 10)
        @ColumnComment("时间戳")
        private Long timeUnix; // 时间（Unix 时间戳）

        @TableField("Value")
        @ColumnType(length = 255)
        @ColumnComment("value")
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
