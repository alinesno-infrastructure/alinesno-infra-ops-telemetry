package com.alinesno.infra.base.notice.entity.metrics;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 资源实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("resources")
public class MetricsResource {

    @TableField("resource_id")
    private String resourceId; // 资源 ID

    @TableField("resource_name")
    private String resourceName; // 资源名称

    @TableField("attribute")
    private String attribute ;

}
