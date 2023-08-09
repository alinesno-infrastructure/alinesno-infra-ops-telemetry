package com.alinesno.infra.ops.telemetry.entity;

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
public class MetricsResource extends InfraBaseEntity {

    @TableField("resource_id")
    private String resourceId; // 资源 ID

    @TableField("resource_name")
    private String resourceName; // 资源名称

    @TableField("attribute")
    private String attribute ;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
