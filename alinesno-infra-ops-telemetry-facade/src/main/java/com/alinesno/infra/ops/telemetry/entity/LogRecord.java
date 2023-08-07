package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 日志记录实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("log_records")
public class LogRecord extends InfraBaseEntity {

    @TableField("scope_id")
    private String scopeId ;

    /**
     * Unix 纳秒时间戳。
     */
    @TableField("timeUnixNano")
    private long timeUnixNano;

    /**
     * 观察到的 Unix 纳秒时间戳。
     */
    @TableField("observedTimeUnixNano")
    private long observedTimeUnixNano;

    /**
     * 严重级别文本。
     */
    @TableField("severityText")
    private String severityText;

    /**
     * 追踪 ID。
     */
    @TableField("traceId")
    private String traceId;

    /**
     * 跨度 ID。
     */
    @TableField("spanId")
    private String spanId;

    /**
     * 日志内容。
     */
    @TableField("body")
    private String body;

    @TableField("parent_span_id")
    private String parentSpanId ;

    @TableField("start_time_unix_nano")
    private String startTimeUnixNano ; // ": 1544712660000000000,

    @TableField("end_time_unix_nano")
    private String endTimeUnixNano ; // ": 1544712661000000000,

    public String getParentSpanId() {
        return parentSpanId;
    }

    public void setParentSpanId(String parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public String getStartTimeUnixNano() {
        return startTimeUnixNano;
    }

    public void setStartTimeUnixNano(String startTimeUnixNano) {
        this.startTimeUnixNano = startTimeUnixNano;
    }

    public String getEndTimeUnixNano() {
        return endTimeUnixNano;
    }

    public void setEndTimeUnixNano(String endTimeUnixNano) {
        this.endTimeUnixNano = endTimeUnixNano;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public long getTimeUnixNano() {
        return timeUnixNano;
    }

    public void setTimeUnixNano(long timeUnixNano) {
        this.timeUnixNano = timeUnixNano;
    }

    public long getObservedTimeUnixNano() {
        return observedTimeUnixNano;
    }

    public void setObservedTimeUnixNano(long observedTimeUnixNano) {
        this.observedTimeUnixNano = observedTimeUnixNano;
    }

    public String getSeverityText() {
        return severityText;
    }

    public void setSeverityText(String severityText) {
        this.severityText = severityText;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
