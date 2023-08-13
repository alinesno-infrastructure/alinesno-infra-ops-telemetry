package com.alinesno.infra.ops.telemetry.adapter;

import com.alinesno.infra.ops.telemetry.adapter.collect.KafkaTraceCollect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 日志采集启动组件
 */
@Component
@Order(100)
public class CollectStartBean implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(CollectStartBean.class) ;

    @Autowired
    private KafkaTraceCollect kafkaLogCollect ;

    /**
     * 在属性设置完成后启动日志采集
     *
     * @throws Exception 初始化异常
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            serverCollect();
        } catch (Exception e) {
            log.error("collect logback server starting failed!", e);
        }
    }

    /**
     * 启动日志采集
     */
    private void serverCollect() {
        kafkaLogCollect.kafkaStart();
    }
}
