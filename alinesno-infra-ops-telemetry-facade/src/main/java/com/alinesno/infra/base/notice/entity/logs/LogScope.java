package com.alinesno.infra.base.notice.entity.logs;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 作用域实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("scopes")
public class LogScope {
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

}
