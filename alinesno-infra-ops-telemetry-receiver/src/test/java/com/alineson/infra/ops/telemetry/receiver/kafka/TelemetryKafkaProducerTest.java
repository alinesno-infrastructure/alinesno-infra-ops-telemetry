//package com.alineson.infra.ops.telemetry.receiver.kafka;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.kafka.core.KafkaTemplate;
//
//import static org.mockito.Mockito.*;
//
//public class TelemetryKafkaProducerTest {
//
//    private TelemetryKafkaProducer telemetryKafkaProducer;
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    @BeforeEach
//    public void setup() {
//        kafkaTemplate = mock(KafkaTemplate.class);
//        telemetryKafkaProducer = new TelemetryKafkaProducer();
//    }
//
//    @Test
//    public void testSendMessage() {
//        String topic = "test" ; // Constants.MQ_TRACE_TOPIC;
//        String message = "Hello, Kafka!";
//
//        telemetryKafkaProducer.sendMessage(topic, message);
//
//        verify(kafkaTemplate, times(60)).send(topic, message);
//    }
//
//    @Test
//    public void testSendMessageWithObject() {
//        String topic = "test-topic";
//        TestObject testObject = new TestObject("example", 123);
//
//        telemetryKafkaProducer.sendMessage(topic, testObject);
//
//        String expectedMessage = "{\"name\":\"example\",\"value\":123}";
//        verify(kafkaTemplate, times(1)).send(topic, expectedMessage);
//    }
//
//    private static class TestObject {
//        private String name;
//        private int value;
//
//        public TestObject(String name, int value) {
//            this.name = name;
//            this.value = value;
//        }
//
//        // Getters and setters
//    }
//}
