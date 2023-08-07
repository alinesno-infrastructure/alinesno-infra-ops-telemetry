package com.alinesno.infra.ops.telemetry.adapter.collect;

import com.alinesno.infra.base.notice.enums.Constants;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Kafka日志采集，保存到clickhouse当中
 * @author luoxiaodong
 * @version 1.0.0
 */
public class KafkaLogCollect extends BaseLogCollect {
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(KafkaLogCollect.class);

	int count = 0 ; 
	
	private final KafkaConsumer<String, String> kafkaConsumer;

	public KafkaLogCollect( KafkaConsumer<String , String> kafkaConsumer,  ApplicationEventPublisher applicationEventPublisher) {

		this.kafkaConsumer = kafkaConsumer;
		logger.debug("kafkaConsumer = {}", kafkaConsumer);

		// 监听的topic
		this.kafkaConsumer.subscribe(Arrays.asList(
				Constants.MQ_METRICS_TOPIC,
				Constants.MQ_METRICS_TOPIC,
				Constants.MQ_METRICS_TOPIC
		));
		
		super.applicationEventPublisher = applicationEventPublisher;
		logger.info("kafkaConsumer subscribe ready!");
		logger.info("sending log ready!");
	}

	public void kafkaStart() {
		threadPoolExecutor.execute(this::collectRuningLog);
		logger.info("KafkaLogCollect is starting!");
	}

	public void collectRuningLog() {
		while (true) {
			List<String> logList = new ArrayList<String>();

			try {
				ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(1000));

				records.forEach(record -> {
						
					if (logger.isDebugEnabled()) {
						logger.debug("get log:" + record.value() + "  logType:" + record.topic());
						logger.debug("count = {}" , count++) ;
					}

					if (record.topic().equals(Constants.MQ_METRICS_TOPIC)) {

						String logString = record.value();
						// RunLogMessage runLogMessage = JSON.parseObject(logString, RunLogMessage.class);

						logList.add(logString);
					}else if (record.topic().equals(Constants.MQ_LOG_TOPIC)) {

						String logString = record.value();
						// RunLogMessage runLogMessage = JSON.parseObject(logString, RunLogMessage.class);

						logList.add(logString);
					}else if (record.topic().equals(Constants.MQ_TRACE_TOPIC)) {

						String logString = record.value();
						// RunLogMessage runLogMessage = JSON.parseObject(logString, RunLogMessage.class);

						logList.add(logString);
					}
				});
			} catch (Exception e) {
				logger.error("get logs from kafka failed! ", e);
			}

		}
	}

}
