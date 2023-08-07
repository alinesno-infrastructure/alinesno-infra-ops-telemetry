package com.alinesno.infra.base.notice.entity.metrics;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 直方图信息实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("histograms")
public class MetricsHistogram {
    /**
     * 直方图桶列表。
     */
    @JsonProperty("buckets")
    private String buckets;

}
