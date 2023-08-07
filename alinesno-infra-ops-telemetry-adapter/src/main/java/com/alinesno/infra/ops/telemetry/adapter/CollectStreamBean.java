package com.alinesno.infra.ops.telemetry.adapter;

import com.alinesno.infra.ops.telemetry.adapter.collect.KafkaTraceCollect;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(100)
public class CollectStreamBean implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(CollectStreamBean.class) ;

    @Autowired(required = false)
    private KafkaConsumer<String , String> kafkaConsumer;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private void serverStart() {

        kafkaConsumer = KafkaConsumerClient.getInstance("192.168.101.18:9092" , "1" , 500).getKafkaConsumer();
        log.debug("kafkaConsumer = {}" , kafkaConsumer);

        KafkaTraceCollect kafkaLogCollect = new KafkaTraceCollect(kafkaConsumer, applicationEventPublisher) ;
        kafkaLogCollect.kafkaStart();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            serverStart();
        } catch (Exception e) {
            log.debug("收集服务启动失败.");
        }
    }
}