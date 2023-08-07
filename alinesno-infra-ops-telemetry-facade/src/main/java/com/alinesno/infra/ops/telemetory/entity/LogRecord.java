package com.alinesno.infra.ops.telemetory.entity;

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

}
