package com.alineson.infra.ops.telemetry.receiver.kafka.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

// Inner class for MetricsData
public class MetricsDataTest {
    /**
     * 数据点数组
     */
    private JSONArray dataPoints_;

    /**
     * 聚合时序性
     */
    private int aggregationTemporality_;

    /**
     * 是否单调递增
     */
    private boolean isMonotonic_;

    /**
     * 数据的初始化状态缓存
     */
    private int memoizedIsInitialized;

    /**
     * 数据的未知字段
     */
    private JSONObject unknownFields;

    /**
     * 数据的缓存大小
     */
    private int memoizedSize;

    /**
     * 数据的缓存哈希码
     */
    private int memoizedHashCode;

    // Getters and setters

    @Override
    public String toString() {
        return "MetricsData{" +
                "dataPoints_=" + dataPoints_ +
                ", aggregationTemporality_=" + aggregationTemporality_ +
                ", isMonotonic_=" + isMonotonic_ +
                ", memoizedIsInitialized=" + memoizedIsInitialized +
                ", unknownFields=" + unknownFields +
                ", memoizedSize=" + memoizedSize +
                ", memoizedHashCode=" + memoizedHashCode +
                '}';
    }
}

