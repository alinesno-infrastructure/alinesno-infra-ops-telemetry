package com.alineson.infra.ops.telemetry.receiver.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TelemetryKafkaProducer {
    protected final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public TelemetryKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
