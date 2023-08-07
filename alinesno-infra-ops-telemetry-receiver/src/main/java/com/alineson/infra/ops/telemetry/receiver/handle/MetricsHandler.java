package com.alineson.infra.ops.telemetry.receiver.handle;

import com.alineson.infra.ops.telemetry.receiver.enums.Constants;
import com.alineson.infra.ops.telemetry.receiver.kafka.TelemetryKafkaProducer;
import io.grpc.stub.StreamObserver;
import io.opentelemetry.proto.collector.metrics.v1.ExportMetricsServiceRequest;
import io.opentelemetry.proto.collector.metrics.v1.ExportMetricsServiceResponse;
import io.opentelemetry.proto.collector.metrics.v1.MetricsServiceGrpc;
import io.opentelemetry.proto.metrics.v1.Metric;
import io.opentelemetry.proto.metrics.v1.ResourceMetrics;
import io.opentelemetry.proto.metrics.v1.ScopeMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * 描述：该类为处理指标的 gRPC 服务实现类。
 * 类名：MetricsHandler
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class MetricsHandler extends MetricsServiceGrpc.MetricsServiceImplBase {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected TelemetryKafkaProducer kafkaProducer;

    /**
     * 处理指标导出请求，并打印指标信息。
     *
     * @param request          指标导出请求。
     * @param responseObserver 响应观察器。
     */
    @Override
    public void export(ExportMetricsServiceRequest request, StreamObserver<ExportMetricsServiceResponse> responseObserver) {

        System.out.println(this.getClass().getName());

        for (ResourceMetrics resourceMetrics : request.getResourceMetricsList()) {
            logger.debug("Resource: " + resourceMetrics.getResource().getAttributesList().stream().map(kv -> kv.getKey() + ":" + kv.getValue().getStringValue()).collect(Collectors.joining(",")));
            for (ScopeMetrics scopeMetrics : resourceMetrics.getScopeMetricsList()) {
                logger.debug("Scope: " + scopeMetrics.getScope().getName() + ":" + scopeMetrics.getScope().getVersion());
                for (Metric metric : scopeMetrics.getMetricsList()) {
                    logger.info("Metric: " + metric.getName() + ", unit=" + metric.getUnit());
                }
            }
        }
        responseObserver.onNext(ExportMetricsServiceResponse.newBuilder().build());
        responseObserver.onCompleted();

        // Send To Kafka
        kafkaProducer.sendMessage(Constants.MQ_METRICS_TOPIC, request.getResourceMetricsList());
    }
}
