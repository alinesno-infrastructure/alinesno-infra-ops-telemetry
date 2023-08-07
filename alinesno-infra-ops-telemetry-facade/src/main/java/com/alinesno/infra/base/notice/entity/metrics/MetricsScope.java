package com.alinesno.infra.base.notice.entity.metrics;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 范围实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("scopes")
public class MetricsScope {
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
