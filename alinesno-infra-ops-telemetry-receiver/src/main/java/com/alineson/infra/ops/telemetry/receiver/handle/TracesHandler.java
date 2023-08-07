package com.alineson.infra.ops.telemetry.receiver.handle;

import com.alineson.infra.ops.telemetry.receiver.constants.Constants;
import com.alineson.infra.ops.telemetry.receiver.kafka.TelemetryKafkaProducer;
import com.google.gson.Gson;
import io.grpc.stub.StreamObserver;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceRequest;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceResponse;
import io.opentelemetry.proto.collector.trace.v1.TraceServiceGrpc;
import io.opentelemetry.proto.trace.v1.ResourceSpans;
import io.opentelemetry.proto.trace.v1.ScopeSpans;
import io.opentelemetry.proto.trace.v1.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * 描述：该类为处理跟踪数据的 gRPC 服务实现类。
 * 类名：TracesHandler
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class TracesHandler extends TraceServiceGrpc.TraceServiceImplBase {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected TelemetryKafkaProducer kafkaProducer;

    /**
     * 处理跟踪数据导出请求，并打印跟踪数据信息。
     *
     * @param request          跟踪数据导出请求。
     * @param responseObserver 响应观察器。
     */
    @Override
    public void export(ExportTraceServiceRequest request, StreamObserver<ExportTraceServiceResponse> responseObserver) {

        logger.debug("metrics ===>>> {}" , new Gson().toJson(request.getResourceSpansList()));

        for (ResourceSpans resourceSpans : request.getResourceSpansList()) {
            logger.debug("Resource: " + resourceSpans.getResource().getAttributesList().stream().map(kv -> kv.getKey() + ":" + kv.getValue().getStringValue()).collect(Collectors.joining(",")));
            for (ScopeSpans scopeSpans : resourceSpans.getScopeSpansList()) {
                logger.debug("Scope: " + scopeSpans.getScope().getName() + ":" + scopeSpans.getScope().getVersion());
                for (Span span : scopeSpans.getSpansList()) {
                    logger.info("Span: " + span.getName() + ", starts=" + span.getStartTimeUnixNano());
                }
            }
        }
        responseObserver.onNext(ExportTraceServiceResponse.newBuilder().build());
        responseObserver.onCompleted();

        // Send To Kafka
        TelemetryKafkaProducer.getInstance().sendMessage(Constants.MQ_TRACE_TOPIC, request.getResourceSpansList());
    }
}
