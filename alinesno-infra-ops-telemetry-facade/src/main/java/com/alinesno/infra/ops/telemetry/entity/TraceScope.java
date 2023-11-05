package com.alinesno.infra.ops.telemetry.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 表示 Scope 实体。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("trace_scope")
@Data
public class TraceScope extends InfraBaseEntity {

    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("作用域名称")
    private String name; // 作用域名称

    @TableField("version")
	@ColumnType(length=4)
	@ColumnComment("版本号")
    private String version; // 版本号

    @TableField("resource_id")
	@ColumnType(length=50)
	@ColumnComment("资源ID")
    private String resourceId; // 资源 ID

    @TableField("attribute")
	@ColumnType(length=255)
	@ColumnComment("属性")
    private String attribute ;
}
