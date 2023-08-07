package com.alineson.infra.ops.telemetry.receiver.handle;

import com.alineson.infra.ops.telemetry.receiver.constants.Constants;
import com.alineson.infra.ops.telemetry.receiver.kafka.TelemetryKafkaProducer;
import com.google.gson.Gson;
import io.grpc.stub.StreamObserver;
import io.opentelemetry.proto.collector.metrics.v1.ExportMetricsServiceRequest;
import io.opentelemetry.proto.collector.metrics.v1.ExportMetricsServiceResponse;
import io.opentelemetry.proto.collector.metrics.v1.MetricsServiceGrpc;
import io.opentelemetry.proto.metrics.v1.Metric;
import io.opentelemetry.proto.metrics.v1.ResourceMetrics;
import io.opentelemetry.proto.metrics.v1.ScopeMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    /**
     * 处理指标导出请求，并打印指标信息。
     *
     * @param request          指标导出请求。
     * @param responseObserver 响应观察器。
     */
    @Override
    public void export(ExportMetricsServiceRequest request, StreamObserver<ExportMetricsServiceResponse> responseObserver) {

        logger.debug("metrics ===>>> {}" , new Gson().toJson(request.getResourceMetricsList()));

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
        TelemetryKafkaProducer.getInstance().sendMessage(Constants.MQ_METRICS_TOPIC, request.getResourceMetricsList());
    }
}
