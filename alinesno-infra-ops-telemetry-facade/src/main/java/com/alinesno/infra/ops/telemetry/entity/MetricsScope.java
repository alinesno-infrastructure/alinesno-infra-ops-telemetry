package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 范围实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("metrics_scope")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
