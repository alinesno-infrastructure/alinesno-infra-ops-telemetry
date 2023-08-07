package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 直方图信息实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("histograms")
public class MetricsHistogram extends InfraBaseEntity {
    /**
     * 直方图桶列表。
     */
    @JsonProperty("buckets")
    private String buckets;

}
