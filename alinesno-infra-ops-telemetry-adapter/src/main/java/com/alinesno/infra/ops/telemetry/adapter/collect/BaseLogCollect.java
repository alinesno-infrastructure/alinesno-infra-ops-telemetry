package com.alinesno.infra.ops.telemetry.adapter.collect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.ops.telemetry.adapter.utils.ThreadPoolUtil;
import com.alinesno.infra.ops.telemetry.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 采集日志基础类
 * BaseLogCollect 是一个采集日志的基础类。
 *
 * 该类提供了处理日志数据的常用方法，例如保存日志、测量执行时间和打印日志统计信息。
 * 它使用线程池执行器进行并发处理，并使用定时线程池执行器进行周期性任务。
 * ApplicationEventPublisher 用于发布与日志采集相关的事件。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class BaseLogCollect {

	private final Logger log = LoggerFactory.getLogger(BaseLogCollect.class);

	public ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.getPool();

	protected ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
	protected ApplicationEventPublisher applicationEventPublisher;

	// 日志服务
	@Autowired
	private ILogsService logsService;


	// 链路跟踪服务
	@Autowired
	private ITraceService traceService;


	// 监控服务 metricsGauge
	@Autowired
	private IMetricsGaugeService metricsGaugeService;

	// 监控服务 sum
	@Autowired
	private IMetricsSumService metricsSumService;

	//监控服务 histogram
	@Autowired
	private IMetricsHistogramService metricsHistogramService;

	// 监控服务 exponentialHistogram
	@Autowired
	private IMetricsExponentialHistogramService metricsExponentialHistogramService;

	// 监控服务 summary
	@Autowired
	private IMetricsSummaryService metricsSummaryService;

	
	/**
	 * 处理跟踪日志。
	 *
	 * @param logList 要处理的跟踪日志列表
	 */
	protected void handleTrace(List<String> logList) {
		long startTime = System.currentTimeMillis();

		traceService.saveTrace(logList) ;

		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		log.info("方法执行时间：" + elapsedTime + " 毫秒 , 插入数据:"+ logList.size() +" 条");
	}

	/**
	 * 处理指标日志。
	 *
	 * @param logList 要处理的指标日志列表
	 */
	protected void handleMetrics(List<String> logList) {
		long startTime = System.currentTimeMillis();

		for(String log: logList) {
			JSONObject jsonObject = JSON.parseObject(log);

			metricsGaugeService.saveGauge(jsonObject.getString("metricsGauge"));
			metricsSumService.saveSum(jsonObject.getString("metricsSum"));
			metricsHistogramService.saveHistogram(jsonObject.getString("metricsHistogram"));
			metricsExponentialHistogramService.saveHistogram(jsonObject.getString("metricsExponentialHistogram"));
			metricsSummaryService.saveSummary(jsonObject.getString("metricsSummary"));
		}

		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		log.info("方法执行时间：" + elapsedTime + " 毫秒 , 插入数据:"+ logList.size() +" 条");
	}

	/**
	 * 处理普通日志。
	 *
	 * @param logList 要处理的普通日志列表
	 */
	protected void handleLog(List<String> logList) {
		long startTime = System.currentTimeMillis();

		logsService.saveLogs(logList) ;

		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		log.info("方法执行时间：" + elapsedTime + " 毫秒 , 插入数据:"+ logList.size() +" 条");
	}
}
