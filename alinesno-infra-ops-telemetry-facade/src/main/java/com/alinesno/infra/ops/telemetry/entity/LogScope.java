package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 作用域实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("log_scope")
public class LogScope extends InfraBaseEntity {
    /**
     * 作用域名称。
     */
    @JsonProperty("name")
    private String name;

    /**
     * 作用域版本。
     */
    @JsonProperty("version")
    private String version;

    /**
     * 属性列表。
     */
    @JsonProperty("attributes")
    @TableField(exist = false)
    private String attributes;

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
}
