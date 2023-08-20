package com.alinesno.infra.ops.telemetry.entity.meta;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 应用表
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("application")
public class ApplicationEntity extends InfraBaseEntity {

    /**
     * 应用名称
     */
    @TableField("name")
    private String name;

    /**
     * 接口密钥
     */
    @TableField("api_key")
    private String apiKey;

    /**
     * 应用logo标识
     */
    @TableField("banner")
    private String banner;

    /**
     * 应用码
     */
    @TableField("app_code")
    private String appCode;

    // 省略 getter 和 setter 方法

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}