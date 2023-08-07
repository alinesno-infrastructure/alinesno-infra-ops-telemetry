package com.alinesno.infra.ops.telemetry.adapter;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

/**
 * Kafka 客户端
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class KafkaConsumerClient {
    private static volatile KafkaConsumerClient instance = null;
    private final KafkaConsumer<String , String> kafkaConsumer;

    private KafkaConsumerClient(String hosts, String groupName, int maxPullSize) {
        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, hosts);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPullSize);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupName);

        kafkaConsumer = new KafkaConsumer<>(props);
    }

    public static KafkaConsumerClient getInstance(String hosts, String groupName, int maxPullSize) {
        if (instance == null) {
            synchronized (KafkaConsumerClient.class) {
                if (instance == null) {
                    instance = new KafkaConsumerClient(hosts, groupName, maxPullSize);
                }
            }
        }
        return instance;
    }

    public KafkaConsumer<String ,String> getKafkaConsumer() {
        return kafkaConsumer;
    }

}
