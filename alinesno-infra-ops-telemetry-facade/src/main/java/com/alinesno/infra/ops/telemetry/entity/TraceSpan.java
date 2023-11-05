package com.alinesno.infra.ops.telemetry.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
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
@Data
public class TraceSpan extends InfraBaseEntity {

    @TableField("resource_id")
	@ColumnType(length=50)
	@ColumnComment("资源ID")
    private String resourceId; // 资源 ID

    @TableField("scope_id")
	@ColumnType(length=50)
	@ColumnComment("作用域ID")
    private String scopeId; // Scope ID

    @TableField("trace_id")
	@ColumnType(length=36)
	@ColumnComment("追踪ID")
    private String traceId; // 链路追踪 ID

    @TableField("span_id")
	@ColumnType(length=64)
	@ColumnComment("跨度标识")
    private String spanId; // 跨度 ID

    @TableField("parent_span_id")
	@ColumnType(length=255)
	@ColumnComment("父级跨度ID")
    private String parentSpanId; // 父跨度 ID

    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("姓名")
    private String name; // 跨度名称

    @TableField("start_time_unix_nano")
	@ColumnType(length=19)
	@ColumnComment("开始时间的纳秒级时间戳")
    private long startTimeUnixNano; // 开始时间（纳秒）

    @TableField("end_time_unix_nano")
	@ColumnType(length=20)
	@ColumnComment("结束时间的Unix纳秒表示")
    private long endTimeUnixNano; // 结束时间（纳秒）

    @TableField("kind")
	@ColumnType(length=20)
	@ColumnComment("种类")
    private int kind; // 跨度类型

    @TableField("attribute")
	@ColumnType(length=255)
	@ColumnComment("属性")
    private List<String> attribute ;
}
