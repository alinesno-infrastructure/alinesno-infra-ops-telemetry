package com.alinesno.infra.ops.telemetory.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 范围实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("scopes")
public class MetricsScope extends InfraBaseEntity{
    /**
     * 范围名称。
     */
    @TableField("name")
    private String name;

    /**
     * 范围版本。
     */
    @TableField("version")
    private String version;

    /**
     * 属性列表。
     */
    @TableField("attributes")
    private String attributes;

    @TableField("resource_id")
    private String resourceId; // 资源 ID

}
