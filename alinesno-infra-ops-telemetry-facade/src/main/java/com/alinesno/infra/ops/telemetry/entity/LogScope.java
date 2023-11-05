package com.alinesno.infra.ops.telemetry.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
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
@Data
public class LogScope extends InfraBaseEntity {
    /**
     * 作用域名称。
     */
    @JsonProperty("name")
	@ColumnType(length=255)
	@ColumnComment("作用域名称。")
	@TableField("name")
    private String name;

    /**
     * 作用域版本。
     */
    @JsonProperty("version")
	@ColumnType(length=2)
	@ColumnComment("作用域版本。")
	@TableField("version")
    private String version;

    /**
     * 属性列表。
     */
    @JsonProperty("attributes")
    @TableField(exist = false)
	@ColumnType(length=255)
	@ColumnComment("属性列表。")
    private String attributes;
}
