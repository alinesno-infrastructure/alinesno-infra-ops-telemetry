package com.alineson.infra.ops.telemetry.receiver.kafka.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MetricsResourceTest {
    /**
     * 资源属性
     */
    private JSONArray resourceAttributes;

    /**
     * 资源中被丢弃的属性数量
     */
    private int resourceDroppedAttributesCount;

    /**
     * 资源的初始化状态缓存
     */
    private int resourceMemoizedIsInitialized;

    /**
     * 资源中的未知字段
     */
    private JSONObject resourceUnknownFields;

    /**
     * 资源的缓存大小
     */
    private int resourceMemoizedSize;

    /**
     * 资源的缓存哈希码
     */
    private int resourceMemoizedHashCode;

    /**
     * 模式URL
     */
    private String schemaUrl;

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

    public JSONArray getResourceAttributes() {
        return resourceAttributes;
    }

    public void setResourceAttributes(JSONArray resourceAttributes) {
        this.resourceAttributes = resourceAttributes;
    }

    public int getResourceDroppedAttributesCount() {
        return resourceDroppedAttributesCount;
    }

    public void setResourceDroppedAttributesCount(int resourceDroppedAttributesCount) {
        this.resourceDroppedAttributesCount = resourceDroppedAttributesCount;
    }

    public int getResourceMemoizedIsInitialized() {
        return resourceMemoizedIsInitialized;
    }

    public void setResourceMemoizedIsInitialized(int resourceMemoizedIsInitialized) {
        this.resourceMemoizedIsInitialized = resourceMemoizedIsInitialized;
    }

    public JSONObject getResourceUnknownFields() {
        return resourceUnknownFields;
    }

    public void setResourceUnknownFields(JSONObject resourceUnknownFields) {
        this.resourceUnknownFields = resourceUnknownFields;
    }

    public int getResourceMemoizedSize() {
        return resourceMemoizedSize;
    }

    public void setResourceMemoizedSize(int resourceMemoizedSize) {
        this.resourceMemoizedSize = resourceMemoizedSize;
    }

    public int getResourceMemoizedHashCode() {
        return resourceMemoizedHashCode;
    }

    public void setResourceMemoizedHashCode(int resourceMemoizedHashCode) {
        this.resourceMemoizedHashCode = resourceMemoizedHashCode;
    }

    public String getSchemaUrl() {
        return schemaUrl;
    }

    public void setSchemaUrl(String schemaUrl) {
        this.schemaUrl = schemaUrl;
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
