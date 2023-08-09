package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 表示 Span 实体。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("trace_span")
public class TraceSpan extends InfraBaseEntity {

    @TableField("resource_id")
    private String resourceId; // 资源 ID

    @TableField("scope_id")
    private String scopeId; // Scope ID

    @TableField("trace_id")
    private String traceId; // 链路追踪 ID

    @TableField("span_id")
    private String spanId; // 跨度 ID

    @TableField("parent_span_id")
    private String parentSpanId; // 父跨度 ID

    @TableField("name")
    private String name; // 跨度名称

    @TableField("start_time_unix_nano")
    private long startTimeUnixNano; // 开始时间（纳秒）

    @TableField("end_time_unix_nano")
    private long endTimeUnixNano; // 结束时间（纳秒）

    @TableField("kind")
    private int kind; // 跨度类型

    @TableField("attribute")
    private List<String> attribute ;

    // 省略 getter 和 setter 方法


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getParentSpanId() {
        return parentSpanId;
    }

    public void setParentSpanId(String parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStartTimeUnixNano() {
        return startTimeUnixNano;
    }

    public void setStartTimeUnixNano(long startTimeUnixNano) {
        this.startTimeUnixNano = startTimeUnixNano;
    }

    public long getEndTimeUnixNano() {
        return endTimeUnixNano;
    }

    public void setEndTimeUnixNano(long endTimeUnixNano) {
        this.endTimeUnixNano = endTimeUnixNano;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public List<String> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<String> attribute) {
        this.attribute = attribute;
    }
}
