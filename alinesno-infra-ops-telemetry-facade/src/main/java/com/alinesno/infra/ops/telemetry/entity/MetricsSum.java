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
 * 表示 telemetry_metrics_sum 实体。
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@TableName("telemetry_metrics_sum")
@Data
public class MetricsSum extends InfraBaseEntity {

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
    @ColumnComment("作用域名称")
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
    @ColumnComment("作用域中已删除的属性数量")
    private Integer scopeDroppedAttrCount;

    @TableField("ScopeSchemaUrl")
    @ColumnType(length = 255)
    @ColumnComment("作用范围模式URL")
    private String scopeSchemaUrl;

    @TableField("MetricName")
    @ColumnType(length = 255)
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
    private Long startTimeUnix;

    @TableField("TimeUnix")
    @ColumnType(length = 255)
    @ColumnComment("时间戳")
    private Long timeUnix;

    @TableField("Value")
    @ColumnType(length = 255)
    @ColumnComment("value")
    private Double value;

    @TableField("Flags")
    @ColumnType(length = 1)
    @ColumnComment("标志")
    private Integer flags;

    @TableField(exist = false)
    @ColumnType(length = 255)
    @ColumnComment("示例")
    private List<Exemplar> exemplars;

    @TableField("AggTemp")
    @ColumnType(length = 255)
    @ColumnComment("聚合温度")
    private Integer aggTemp;

    @TableField("IsMonotonic")
    @ColumnType(length = 1)
    @ColumnComment("是否单调")
    private boolean isMonotonic;

    // 省略 getter 和 setter 方法...

    /**
     * 表示 Exemplar 实体。
     */
    public static class Exemplar {

        @TableField("FilteredAttributes")
        @ColumnType(length = 255)
        @ColumnComment("filteredAttributes")
        private Map<String, String> filteredAttributes;

        @TableField("TimeUnix")
        @ColumnType(length = 10)
        @ColumnComment("时间戳")
        private Long timeUnix;

        @TableField("Value")
        @ColumnType(length = 255)
        @ColumnComment("value")
        private Double value;

        @TableField("SpanId")
        @ColumnType(length = 64)
        @ColumnComment("跨度标识")
        private String spanId;

        @TableField("TraceId")
        @ColumnType(length = 36)
        @ColumnComment("追踪ID")
        private String traceId;
    }
}
