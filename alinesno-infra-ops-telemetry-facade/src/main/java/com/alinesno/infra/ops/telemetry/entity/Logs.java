package com.alinesno.infra.ops.telemetry.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 表示 telemetry_logs 实体。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("telemetry_logs")
@Data
public class Logs extends InfraBaseEntity {

    @TableField("Timestamp")
	@ColumnType(length=20)
	@ColumnComment("时间戳")
    private LocalDateTime timestamp; // 时间戳

    @TableField("TraceId")
	@ColumnType(length=36)
	@ColumnComment("追踪ID")
    private String traceId; // 跟踪ID

    @TableField("SpanId")
	@ColumnType(length=64)
	@ColumnComment("跨度标识")
    private String spanId; // 跨度ID

    @TableField("TraceFlags")
	@ColumnType(length=1)
	@ColumnComment("跟踪标志")
    private Long traceFlags; // 跟踪标志

    @TableField("SeverityText")
	@ColumnType(length=50)
	@ColumnComment("严重程度文本")
    private String severityText; // 严重性文本

    @TableField("SeverityNumber")
	@ColumnType(length=255)
	@ColumnComment("严重性数字")
    private Integer severityNumber; // 严重性数字

    @TableField("ServiceName")
	@ColumnType(length=50)
	@ColumnComment("服务名称")
    private String serviceName; // 服务名称

    @TableField("Body")
	@ColumnType(length=255)
	@ColumnComment("日志内容")
    private String body; // 日志内容

    @TableField("ResourceSchemaUrl")
	@ColumnType(length=255)
	@ColumnComment("资源模式URL")
    private String resourceSchemaUrl; // 资源模式URL

    @TableField("ResourceAttributes")
	@ColumnType(length=255)
	@ColumnComment("资源属性")
    private Map<String, String> resourceAttributes; // 资源属性

    @TableField("ScopeSchemaUrl")
	@ColumnType(length=255)
	@ColumnComment("作用范围模式URL")
    private String scopeSchemaUrl; // 作用域模式URL

    @TableField("ScopeName")
	@ColumnType(length=50)
	@ColumnComment("作用域名称")
    private String scopeName; // 作用域名称

    @TableField("ScopeVersion")
	@ColumnType(length=10)
	@ColumnComment("作用域版本")
    private String scopeVersion; // 作用域版本

    @TableField("ScopeAttributes")
	@ColumnType(length=255)
	@ColumnComment("作用域属性")
    private Map<String, String> scopeAttributes; // 作用域属性

    @TableField("LogAttributes")
	@ColumnType(length=255)
	@ColumnComment("日志属性")
    private Map<String, String> logAttributes; // 日志属性
}
