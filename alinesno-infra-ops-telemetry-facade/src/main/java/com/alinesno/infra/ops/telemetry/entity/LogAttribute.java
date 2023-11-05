package com.alinesno.infra.ops.telemetry.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 属性实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("log_attribute")
@Data
public class LogAttribute extends InfraBaseEntity {

    @TableField("record_id")
	@ColumnType(length=255)
	@ColumnComment("recordId")
    private String recordId ;

    /**
     * 属性键。
     */
    @TableField("key")
	@ColumnType(length=255)
	@ColumnComment("属性键。")
    private String key;

    /**
     * 属性值。
     */
    @TableField("value")
	@ColumnType(length=255)
	@ColumnComment("属性值。")
    private String value;
}
