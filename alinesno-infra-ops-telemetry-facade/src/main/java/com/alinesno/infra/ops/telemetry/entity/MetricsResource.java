package com.alinesno.infra.ops.telemetry.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 资源实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("metrics_resource")
@Data
public class MetricsResource extends InfraBaseEntity {

    @TableField("resource_id")
	@ColumnType(length=50)
	@ColumnComment("资源ID")
    private String resourceId; // 资源 ID

    @TableField("resource_name")
	@ColumnType(length=255)
	@ColumnComment("资源名称")
    private String resourceName; // 资源名称

    @TableField("attribute")
	@ColumnType(length=255)
	@ColumnComment("属性")
    private String attribute ;
}
