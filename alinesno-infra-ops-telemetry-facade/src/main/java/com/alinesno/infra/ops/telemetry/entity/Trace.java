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
 * 表示 trace 实体。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("telemetry_trace")
@Data
public class Trace extends InfraBaseEntity {

    @TableField("Timestamp")
    @ColumnType(length = 20)
    @ColumnComment("时间戳")
    private String timestamp; // 时间戳

    @TableField("TraceId")
    @ColumnType(length = 128)
    @ColumnComment("追踪ID")
    private String traceId; // 追踪 ID

    @TableField("SpanId")
    @ColumnType(length = 64)
    @ColumnComment("跨度标识")
    private String spanId; // 跨度 ID

    @TableField("ParentSpanId")
    @ColumnType(length = 64)
    @ColumnComment("父级跨度ID")
    private String parentSpanId; // 父跨度 ID

    @TableField("TraceState")
    @ColumnType(length = 2)
    @ColumnComment("追踪状态")
    private String traceState; // 追踪状态

    @TableField("SpanName")
    @ColumnType(length = 255)
    @ColumnComment("跨度名称")
    private String spanName; // 跨度名称

    @TableField("SpanKind")
    @ColumnType(length = 20)
    @ColumnComment("跨度类型")
    private String spanKind; // 跨度类型

    @TableField("ServiceName")
    @ColumnType(length = 50)
    @ColumnComment("服务名称")
    private String serviceName; // 服务名称

    @TableField("ResourceAttributes")
    @ColumnType(length = 255)
    @ColumnComment("资源属性")
    private Map<String, String> resourceAttributes; // 资源属性

    @TableField("ScopeName")
    @ColumnType(length = 50)
    @ColumnComment("作用域名称")
    private String scopeName; // 作用域名称

    @TableField("ScopeVersion")
    @ColumnType(length = 10)
    @ColumnComment("作用域版本")
    private String scopeVersion; // 作用域版本

    @TableField("SpanAttributes")
    @ColumnType(length = 255)
    @ColumnComment("跨度属性")
    private Map<String, String> spanAttributes; // 跨度属性

    @TableField("Duration")
    @ColumnType(length = 8)
    @ColumnComment("持续时间")
    private Long duration; // 持续时间

    @TableField("StatusCode")
    @ColumnType(length = 2)
    @ColumnComment("状态码")
    private String statusCode; // 状态码

    @TableField("StatusMessage")
    @ColumnType(length = 255)
    @ColumnComment("状态消息")
    private String statusMessage; // 状态信息

    @TableField("Events")
    @ColumnType(length = 255)
    @ColumnComment("事件")
    private List<TelemetryEvent> events; // 事件列表

    @TableField("Links")
    @ColumnType(length = 255)
    @ColumnComment("链接")
    private List<TelemetryLink> links; // 链接列表


    public static class TelemetryLink {

        @TableField("TraceId")
        @ColumnType(length = 36)
        @ColumnComment("追踪ID")
        private String traceId;
        @TableField("SpanId")
        @ColumnType(length = 64)
        @ColumnComment("跨度标识")
        private String spanId;
        @TableField("TraceState")
        @ColumnType(length = 2)
        @ColumnComment("追踪状态")
        private String traceState;
        @TableField("Attributes")
        @ColumnType(length = 255)
        @ColumnComment("attributes")
        private Map<String, String> attributes;

    }

    public static class TelemetryEvent {
        @TableField("Timestamp")
        @ColumnType(length = 20)
        @ColumnComment("时间戳")
        private String timestamp;
        @TableField("Name")
        @ColumnType(length = 255)
        @ColumnComment("姓名")
        private String name;
        @TableField("Attributes")
        @ColumnType(length = 255)
        @ColumnComment("attributes")
        private Map<String, String> attributes;
    }
}

