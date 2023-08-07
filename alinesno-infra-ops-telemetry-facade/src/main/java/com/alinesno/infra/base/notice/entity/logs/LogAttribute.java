package com.alinesno.infra.base.notice.entity.logs;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 属性实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("attributes")
public class LogAttribute {

    @TableField("record_id")
    private String recordId ;

    /**
     * 属性键。
     */
    @TableField("key")
    private String key;

    /**
     * 属性值。
     */
    @TableField("value")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
