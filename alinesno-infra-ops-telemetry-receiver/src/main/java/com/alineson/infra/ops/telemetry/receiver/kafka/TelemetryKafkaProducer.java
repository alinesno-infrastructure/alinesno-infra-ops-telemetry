package com.alineson.infra.ops.telemetry.receiver.kafka;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TelemetryKafkaProducer {
    protected final KafkaTemplate<String, String> kafkaTemplate;

    private static final Gson gson = new Gson() ;

    @Autowired
    public TelemetryKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void sendMessage(String topic, Object object) {

        String message = gson.toJson(object) ;

        kafkaTemplate.send(topic, message);
    }
}
