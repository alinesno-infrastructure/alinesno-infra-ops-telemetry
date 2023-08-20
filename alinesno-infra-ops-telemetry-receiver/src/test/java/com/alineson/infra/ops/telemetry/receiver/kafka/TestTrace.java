package com.alineson.infra.ops.telemetry.receiver.kafka;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alineson.infra.ops.telemetry.receiver.kafka.bean.MetricsResourceTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestTrace {

    private Logger log = LoggerFactory.getLogger(TestTrace.class) ;

    @Test
    public void testTrace() throws IOException {
        log.info("--->>> testTrace");

        ClassPathResource resource = new ClassPathResource("json/trace.json");
        Path filePath = resource.getFile().toPath();

        // 使用Files工具类读取文件内容
        String fileContent = Files.readString(filePath);

        System.out.println(fileContent);

    }

    @Test
    public void testMetrics() throws IOException {
        log.info("--->>> testMetrics");

        ClassPathResource resource = new ClassPathResource("json/metrics.json");
        Path filePath = resource.getFile().toPath();

        // 使用Files工具类读取文件内容
        String fileContent = Files.readString(filePath);

        JSONArray jsonMetricsArr = JSONArray.parseArray(fileContent) ;
        for(int i = 0 ; i < jsonMetricsArr.size() ; i ++){

            JSONObject jsonMetrics = jsonMetricsArr.getJSONObject(i) ;

            MetricsResourceTest r = new MetricsResourceTest() ;
            JSONObject resource_ = jsonMetrics.getJSONObject("resource_") ;

            r.setResourceAttributes(resource_.getJSONArray("attributes_"));
            r.setResourceDroppedAttributesCount(resource_.getInteger("droppedAttributesCount_"));
            r.setResourceMemoizedHashCode(resource_.getInteger("memoizedIsInitialized"));
            r.setResourceUnknownFields(resource_.getJSONObject("unknownFields"));
            r.setResourceMemoizedSize(resource_.getInteger("memoizedSize"));
            r.setResourceMemoizedHashCode(resource_.getInteger("memoizedHashCode"));

            r.setSchemaUrl(jsonMetrics.getString("schemaUrl_"));
            r.setMemoizedIsInitialized(jsonMetrics.getInteger("memoizedIsInitialized"));
            r.setUnknownFields(jsonMetrics.getJSONObject("unknownFields"));
            r.setMemoizedSize(jsonMetrics.getInteger("memoizedSize"));
            r.setMemoizedHashCode(jsonMetrics.getInteger("memoizedHashCode"));

            System.out.println(JSONObject.toJSONString(r));

            JSONArray scopeMetricsArr = jsonMetrics.getJSONArray("scopeMetrics_")  ;

            for(int j = 0 ; j < scopeMetricsArr.size() ; j ++){
                JSONObject jsonMetrisObj = jsonMetricsArr.getJSONObject(i) ;
                System.out.println(jsonMetrisObj.toJSONString());
            }

        }

    }
}
