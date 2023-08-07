package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 属性实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("attributes")
public class LogAttribute extends InfraBaseEntity {

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

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
