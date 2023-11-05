package com.alinesno.infra.ops.telemetry.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;

/**
 * 发送任务列表
 * @author luoxiaodong
 * @since 1.0.0
 */
@TableName("send_task") // 指定数据库表名
@Data
public class TaskEntity extends InfraBaseEntity {

    // 任务名称
    @TableField("task_name")
	@ColumnType(length=255)
	@ColumnComment("任务名称")
    private String taskName;

    // 定时CRON表达式
    @TableField("cron_expression")
	@ColumnType(length=255)
	@ColumnComment("定时CRON表达式")
    private String cronExpression;

    // 是否运行
    @TableField("is_running")
	@ColumnType(length=1)
	@ColumnComment("是否运行")
    private Boolean running;

    // 运行状态
    @TableField("status")
	@ColumnType(length=255)
	@ColumnComment("运行状态")
    private String status;
}
