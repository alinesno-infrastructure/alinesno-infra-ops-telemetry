package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 表示 telemetry_trace_id_ts 实体。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("telemetry_trace_id_ts")
public class TraceIdTs extends InfraBaseEntity {

    @TableField("TraceId")
    private String traceId; // 跟踪ID

    @TableField("Start")
    private LocalDateTime start; // 跟踪开始时间

    @TableField("End")
    private LocalDateTime end; // 跟踪结束时间

    // 省略 getter 和 setter 方法

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
