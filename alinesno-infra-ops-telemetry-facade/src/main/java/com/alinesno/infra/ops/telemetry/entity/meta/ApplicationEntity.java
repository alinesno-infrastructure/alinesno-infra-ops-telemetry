package com.alinesno.infra.ops.telemetry.entity.meta;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;

/**
 * 应用表
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Data
@TableName("application")
public class ApplicationEntity extends InfraBaseEntity {

    /**
     * 应用名称
     */
    @ColumnType(length=255)
    @ColumnComment("应用名称")
    @TableField("name")
    private String name;

    /**
     * 接口密钥
     */
    @ColumnType(length=255)
    @ColumnComment("接口密钥")
    @TableField("api_key")
    private String apiKey;

    /**
     * 应用logo标识
     */
    @ColumnType(length=255)
    @ColumnComment("应用logo标识")
    @TableField("banner")
    private String banner;

    /**
     * 应用码
     */
    @ColumnType(length=255)
    @ColumnComment("应用码")
    @TableField("app_code")
    private String appCode;

}