package com.alineson.infra.ops.telemetry.receiver.kafka;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Kafka生产者用于发送遥测数据到Kafka。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class TelemetryKafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(TelemetryKafkaProducer.class) ;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final Gson gson = new Gson();

    /**
     * 构造函数，初始化Kafka配置和KafkaTemplate。
     */
    public TelemetryKafkaProducer( KafkaTemplate<String, String> kafkaTemplat) {
        this.kafkaTemplate = kafkaTemplat ;
    }

    private static TelemetryKafkaProducer telemetryKafkaProducer ;

    public static TelemetryKafkaProducer getInstance() {

        if(telemetryKafkaProducer == null){
            // Kafka配置
            Map<String, Object> kafkaConfig = new HashMap<>();

            // Kafka服务器的地址和端口
            kafkaConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.1:9092");
            // 键的序列化器类
            kafkaConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            // 值的序列化器类
            kafkaConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

            // 批量发送的大小（字节）
            kafkaConfig.put(ProducerConfig.BATCH_SIZE_CONFIG, "16384");

            kafkaConfig.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "5242880");
            // 发送缓冲区的大小（字节）
            kafkaConfig.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");
            // 确认模式（0：不等待确认，1：等待leader的确认，all：等待所有副本的确认）
            kafkaConfig.put(ProducerConfig.ACKS_CONFIG, "1");
            // 发送失败时的重试次数
            kafkaConfig.put(ProducerConfig.RETRIES_CONFIG, "3");
            // 压缩类型（none：不压缩，gzip：GZIP压缩，snappy：Snappy压缩）
            kafkaConfig.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");

            // 创建ProducerFactory
            ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(kafkaConfig);

            KafkaTemplate<String, String> kafkaTemplat = new KafkaTemplate<>(producerFactory); ;

            telemetryKafkaProducer = new TelemetryKafkaProducer(kafkaTemplat) ;
        }

        return telemetryKafkaProducer ;
    }

    /**
     * 发送消息到指定的Kafka主题。
     *
     * @param topic   Kafka主题
     * @param message 消息内容
     */
    public void sendMessage(String topic, String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        boolean isDone = future.isDone() ;

        future.thenAccept(result -> {
            // 处理发送成功的情况
            System.out.println("消息发送成功: " + result.getRecordMetadata().toString());
        }).exceptionally(ex -> {
            // 处理发送失败的情况
            System.err.println("消息发送失败: " + ex.getMessage());
            return null;
        });

        log.debug("isDone =" + isDone);
    }

    /**
     * 将对象转换为JSON格式的字符串，并发送到指定的Kafka主题。
     *
     * @param topic  Kafka主题
     * @param object 要发送的对象
     */
    public void sendMessage(String topic, Object object) {
        String message = gson.toJson(object);
        kafkaTemplate.send(topic, message);
    }
}
