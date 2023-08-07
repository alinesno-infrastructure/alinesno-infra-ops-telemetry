package com.alinesno.infra.ops.telemetory.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 表示 Scope 实体。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("scopes")
public class TraceScope extends InfraBaseEntity {

    @TableField("name")
    private String name; // 作用域名称

    @TableField("version")
    private String version; // 版本号

    @TableField("resource_id")
    private String resourceId; // 资源 ID

    @TableField("attribute")
    private String attribute ;

    // 省略 getter 和 setter 方法

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }


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

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
