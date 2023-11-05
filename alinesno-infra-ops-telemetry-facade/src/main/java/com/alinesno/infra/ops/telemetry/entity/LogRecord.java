package com.alinesno.infra.ops.telemetry.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
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
@Data
public class LogRecord extends InfraBaseEntity {

    @TableField("scope_id")
	@ColumnType(length=255)
	@ColumnComment("scopeId")
    private String scopeId ;

    /**
     * Unix 纳秒时间戳。
     */
    @TableField("timeUnixNano")
	@ColumnType(length=19)
	@ColumnComment("纳秒时间戳")
    private long timeUnixNano;

    /**
     * 观察到的 Unix 纳秒时间戳。
     */
    @TableField("observedTimeUnixNano")
	@ColumnType(length=64)
	@ColumnComment("观察到的 Unix 纳秒时间戳。")
    private long observedTimeUnixNano;

    /**
     * 严重级别文本。
     */
    @TableField("severityText")
	@ColumnType(length=50)
	@ColumnComment("严重级别文本。")
    private String severityText;

    /**
     * 追踪 ID。
     */
    @TableField("traceId")
	@ColumnType(length=36)
	@ColumnComment("追踪ID")
    private String traceId;

    /**
     * 跨度 ID。
     */
    @TableField("spanId")
	@ColumnType(length=16)
	@ColumnComment("跨度ID")
    private String spanId;

    /**
     * 日志内容。
     */
    @TableField("body")
	@ColumnType(length=255)
	@ColumnComment("日志内容。")
    private String body;

    @TableField("parent_span_id")
	@ColumnType(length=64)
	@ColumnComment("父级跨度ID")
    private String parentSpanId ;

    @TableField("start_time_unix_nano")
	@ColumnType(length=20)
	@ColumnComment("开始时间的Unix纳秒表示")
    private String startTimeUnixNano ; // ": 1544712660000000000,

    @TableField("end_time_unix_nano")
	@ColumnType(length=20)
	@ColumnComment("结束时间的Unix纳秒表示")
    private String endTimeUnixNano ; // ": 1544712661000000000,
}
