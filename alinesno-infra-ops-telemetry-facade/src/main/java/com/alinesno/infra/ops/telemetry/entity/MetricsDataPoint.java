package com.alinesno.infra.ops.telemetry.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 数据点实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("metrics_data_point")
public class MetricsDataPoint extends InfraBaseEntity {

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

    public String getSumId() {
        return sumId;
    }

    public void setSumId(String sumId) {
        this.sumId = sumId;
    }

    public String getGaugeId() {
        return gaugeId;
    }

    public void setGaugeId(String gaugeId) {
        this.gaugeId = gaugeId;
    }

    public String getHistogramId() {
        return histogramId;
    }

    public void setHistogramId(String histogramId) {
        this.histogramId = histogramId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public long getStartTimeUnixNano() {
        return startTimeUnixNano;
    }

    public void setStartTimeUnixNano(long startTimeUnixNano) {
        this.startTimeUnixNano = startTimeUnixNano;
    }

    public long getTimeUnixNano() {
        return timeUnixNano;
    }

    public void setTimeUnixNano(long timeUnixNano) {
        this.timeUnixNano = timeUnixNano;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public List<Integer> getBucketCounts() {
        return bucketCounts;
    }

    public void setBucketCounts(List<Integer> bucketCounts) {
        this.bucketCounts = bucketCounts;
    }

    public List<Integer> getExplicitBounds() {
        return explicitBounds;
    }

    public void setExplicitBounds(List<Integer> explicitBounds) {
        this.explicitBounds = explicitBounds;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
}
