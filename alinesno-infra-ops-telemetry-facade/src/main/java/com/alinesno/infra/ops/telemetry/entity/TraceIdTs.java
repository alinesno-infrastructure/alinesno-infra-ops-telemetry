package com.alinesno.infra.ops.telemetry.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
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
@Data
public class TraceIdTs extends InfraBaseEntity {

    @TableField("TraceId")
	@ColumnType(length=36)
	@ColumnComment("追踪ID")
    private String traceId; // 跟踪ID

    @TableField("Start")
	@ColumnType(length=50)
	@ColumnComment("跟踪开始时间")
    private LocalDateTime start; // 跟踪开始时间

    @TableField("End")
	@ColumnType(length=255)
	@ColumnComment("跟踪结束时间")
    private LocalDateTime end; // 跟踪结束时间
}
