package com.alinesno.infra.ops.telemetry.adapter.collect;

import com.alinesno.infra.ops.telemetry.enums.Constants;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Kafka日志采集，保存到clickhouse当中
 * KafkaTraceCollect 是一个将 Kafka 日志采集并保存到 ClickHouse 的类。
 *
 * 该类继承自 BaseLogCollect 类，提供了 Kafka 日志采集的功能。
 * 它使用 KafkaConsumer 来消费 Kafka 消息，将不同类型的日志分别保存到对应的列表中。
 * 然后调用父类的 handleTrace、handleMetrics 和 handleLog 方法来处理不同类型的日志。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Component
public class KafkaTraceCollect extends BaseLogCollect {

	private final Logger log = LoggerFactory.getLogger(KafkaTraceCollect.class);

	@Autowired
	private KafkaConsumer<String, String> kafkaConsumer;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	/**
	 * 启动 Kafka 日志采集
	 */
	public void kafkaStart() {

		log.debug("kafkaConsumer = {}", kafkaConsumer);

		// 监听的topic
		this.kafkaConsumer.subscribe(Arrays.asList(
				Constants.MQ_METRICS_TOPIC,
				Constants.MQ_METRICS_TOPIC,
				Constants.MQ_METRICS_TOPIC
		));

		super.applicationEventPublisher = applicationEventPublisher;
		log.info("kafkaConsumer subscribe ready!");
		log.info("sending log ready!");

		threadPoolExecutor.execute(this::collectRuningLog);
		log.info("KafkaLogCollect is starting!");
	}

	/**
	 * 执行日志采集
	 */
	public void collectRuningLog() {
		while (true) {

			List<String> logList = new ArrayList<>();
			List<String> metricsList = new ArrayList<>();
			List<String> traceList = new ArrayList<>();

			log.debug("collect running log!!");

			try {
				ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(1000));

				records.forEach(record -> {

					log.debug("topic = {}" , record.topic());
					log.debug("message = {}" , record.value());

					if (record.topic().equals(Constants.MQ_METRICS_TOPIC)) {

						String logString = record.value();

						metricsList.add(logString);
					}else if (record.topic().equals(Constants.MQ_LOG_TOPIC)) {

						String logString = record.value();

						logList.add(logString);
					}else if (record.topic().equals(Constants.MQ_TRACE_TOPIC)) {

						String logString = record.value();
						// RunLogMessage runLogMessage = JSON.parseObject(logString, RunLogMessage.class);

						traceList.add(logString);
					}
				});
			} catch (Exception e) {
				log.error("get logs from kafka failed! ", e);
			}

			// 链路跟踪记录
			if (!traceList.isEmpty()) {
				super.handleTrace(logList) ;
			}

			// 监控记录
			if (!metricsList.isEmpty()) {
				super.handleMetrics(logList) ;
			}

			// 日志记录
			if (!logList.isEmpty()) {
				super.handleLog(logList) ;
			}
		}
	}

}
