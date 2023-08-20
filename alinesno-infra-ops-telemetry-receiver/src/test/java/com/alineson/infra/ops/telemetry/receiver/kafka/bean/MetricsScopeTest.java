package com.alineson.infra.ops.telemetry.receiver.kafka.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MetricsScopeTest {
    /**
     * 作用域名称
     */
    private String scope_name_;

    /**
     * 作用域版本
     */
    private String scope_version_;

    /**
     * 作用域属性
     */
    private JSONArray scope_attributes_;

    /**
     * 在作用域中被丢弃的属性数量
     */
    private int scope_droppedAttributesCount_;

    /**
     * 作用域的初始化状态缓存
     */
    private int scope_memoizedIsInitialized;

    /**
     * 作用域中的未知字段
     */
    private JSONObject scope_unknownFields;

    /**
     * 作用域的缓存大小
     */
    private int scope_memoizedSize;

    /**
     * 作用域的缓存哈希码
     */
    private int scope_memoizedHashCode;

    /**
     * 指标数据类型
     */
    private int metrics_dataCase_;

    /**
     * 指标数据
     */
    private MetricsDataTest metrics_data_;

    /**
     * 指标名称
     */
    private String metrics_name_;

    /**
     * 指标描述
     */
    private String metrics_description_;

    /**
     * 指标单位
     */
    private String metrics_unit_;

    /**
     * 指标的初始化状态缓存
     */
    private int metrics_memoizedIsInitialized;

    /**
     * 指标的未知字段
     */
    private Object metrics_unknownFields;

    /**
     * 指标的缓存大小
     */
    private int metrics_memoizedSize;

    /**
     * 指标的缓存哈希码
     */
    private int metrics_memoizedHashCode;

    /**
     * 模式URL
     */
    private String schemaUrl_;

    /**
     * 初始化状态缓存
     */
    private int memoizedIsInitialized;

    /**
     * 未知字段
     */
    private JSONObject unknownFields;

    /**
     * 缓存大小
     */
    private int memoizedSize;

    /**
     * 缓存哈希码
     */
    private int memoizedHashCode;

    // Getters and setters

    public String getScope_name_() {
        return scope_name_;
    }

    public void setScope_name_(String scope_name_) {
        this.scope_name_ = scope_name_;
    }

    public String getScope_version_() {
        return scope_version_;
    }

    public void setScope_version_(String scope_version_) {
        this.scope_version_ = scope_version_;
    }

    public JSONArray getScope_attributes_() {
        return scope_attributes_;
    }

    public void setScope_attributes_(JSONArray scope_attributes_) {
        this.scope_attributes_ = scope_attributes_;
    }

    public int getScope_droppedAttributesCount_() {
        return scope_droppedAttributesCount_;
    }

    public void setScope_droppedAttributesCount_(int scope_droppedAttributesCount_) {
        this.scope_droppedAttributesCount_ = scope_droppedAttributesCount_;
    }

    public int getScope_memoizedIsInitialized() {
        return scope_memoizedIsInitialized;
    }

    public void setScope_memoizedIsInitialized(int scope_memoizedIsInitialized) {
        this.scope_memoizedIsInitialized = scope_memoizedIsInitialized;
    }

    public JSONObject getScope_unknownFields() {
        return scope_unknownFields;
    }

    public void setScope_unknownFields(JSONObject scope_unknownFields) {
        this.scope_unknownFields = scope_unknownFields;
    }

    public int getScope_memoizedSize() {
        return scope_memoizedSize;
    }

    public void setScope_memoizedSize(int scope_memoizedSize) {
        this.scope_memoizedSize = scope_memoizedSize;
    }

    public int getScope_memoizedHashCode() {
        return scope_memoizedHashCode;
    }

    public void setScope_memoizedHashCode(int scope_memoizedHashCode) {
        this.scope_memoizedHashCode = scope_memoizedHashCode;
    }

    public int getMetrics_dataCase_() {
        return metrics_dataCase_;
    }

    public void setMetrics_dataCase_(int metrics_dataCase_) {
        this.metrics_dataCase_ = metrics_dataCase_;
    }

    public MetricsDataTest getMetrics_data_() {
        return metrics_data_;
    }

    public void setMetrics_data_(MetricsDataTest metrics_data_) {
        this.metrics_data_ = metrics_data_;
    }

    public String getMetrics_name_() {
        return metrics_name_;
    }

    public void setMetrics_name_(String metrics_name_) {
        this.metrics_name_ = metrics_name_;
    }

    public String getMetrics_description_() {
        return metrics_description_;
    }

    public void setMetrics_description_(String metrics_description_) {
        this.metrics_description_ = metrics_description_;
    }

    public String getMetrics_unit_() {
        return metrics_unit_;
    }

    public void setMetrics_unit_(String metrics_unit_) {
        this.metrics_unit_ = metrics_unit_;
    }

    public int getMetrics_memoizedIsInitialized() {
        return metrics_memoizedIsInitialized;
    }

    public void setMetrics_memoizedIsInitialized(int metrics_memoizedIsInitialized) {
        this.metrics_memoizedIsInitialized = metrics_memoizedIsInitialized;
    }

    public Object getMetrics_unknownFields() {
        return metrics_unknownFields;
    }

    public void setMetrics_unknownFields(Object metrics_unknownFields) {
        this.metrics_unknownFields = metrics_unknownFields;
    }

    public int getMetrics_memoizedSize() {
        return metrics_memoizedSize;
    }

    public void setMetrics_memoizedSize(int metrics_memoizedSize) {
        this.metrics_memoizedSize = metrics_memoizedSize;
    }

    public int getMetrics_memoizedHashCode() {
        return metrics_memoizedHashCode;
    }

    public void setMetrics_memoizedHashCode(int metrics_memoizedHashCode) {
        this.metrics_memoizedHashCode = metrics_memoizedHashCode;
    }

    public String getSchemaUrl_() {
        return schemaUrl_;
    }

    public void setSchemaUrl_(String schemaUrl_) {
        this.schemaUrl_ = schemaUrl_;
    }

    public int getMemoizedIsInitialized() {
        return memoizedIsInitialized;
    }

    public void setMemoizedIsInitialized(int memoizedIsInitialized) {
        this.memoizedIsInitialized = memoizedIsInitialized;
    }

    public JSONObject getUnknownFields() {
        return unknownFields;
    }

    public void setUnknownFields(JSONObject unknownFields) {
        this.unknownFields = unknownFields;
    }

    public int getMemoizedSize() {
        return memoizedSize;
    }

    public void setMemoizedSize(int memoizedSize) {
        this.memoizedSize = memoizedSize;
    }

    public int getMemoizedHashCode() {
        return memoizedHashCode;
    }

    public void setMemoizedHashCode(int memoizedHashCode) {
        this.memoizedHashCode = memoizedHashCode;
    }
}
