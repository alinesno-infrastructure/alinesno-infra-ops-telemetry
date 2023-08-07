package com.alinesno.infra.base.notice.entity.metrics;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 数据点实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("data_points")
public class MetricsDataPoint {

    @TableField("sum_id")
    private String sumId ;

    @TableField("gauge_id")
    private String gaugeId ;

    @TableField("histogram_id")
    private String histogramId ;

    @TableField("resource_id")
    private String resourceId; // 资源 ID

    @TableField("scope_id")
    private String scopeId ; // 资源 ID

    /**
     * 开始时间戳（纳秒）。
     */
    @TableField("start_time_unix_nano")
    private long startTimeUnixNano;

    /**
     * 时间戳（纳秒）。
     */
    @TableField("time_unix_nano")
    private long timeUnixNano;

    /**
     * 计数。
     */
    @TableField("count")
    private int count;

    /**
     * 总和。
     */
    @TableField("sum")
    private int sum;

    /**
     * 桶计数列表。
     */
    @TableField("bucket_counts")
    private List<Integer> bucketCounts;

    /**
     * 显式边界列表。
     */
    @TableField("explicit_bounds")
    private List<Integer> explicitBounds;

    /**
     * 最小值。
     */
    @TableField("min")
    private int min;

    /**
     * 最大值。
     */
    @TableField("max")
    private int max;

    /**
     * 属性列表。
     */
    @TableField("attributes")
    private String attributes;

}
