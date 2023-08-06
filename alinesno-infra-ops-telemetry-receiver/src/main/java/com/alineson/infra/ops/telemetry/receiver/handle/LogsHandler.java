package com.alineson.infra.ops.telemetry.receiver.handle;

import com.alineson.infra.ops.telemetry.receiver.kafka.TelemetryKafkaProducer;
import io.grpc.stub.StreamObserver;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceRequest;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceResponse;
import io.opentelemetry.proto.collector.logs.v1.LogsServiceGrpc;
import io.opentelemetry.proto.logs.v1.LogRecord;
import io.opentelemetry.proto.logs.v1.ResourceLogs;
import io.opentelemetry.proto.logs.v1.ScopeLogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述：该类为处理日志的 gRPC 服务实现类。
 * 类名：LogsHandler
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class LogsHandler extends LogsServiceGrpc.LogsServiceImplBase {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected TelemetryKafkaProducer kafkaProducer;

    /**
     * 处理日志导出请求，并打印日志记录的内容。
     *
     * @param request          日志导出请求。
     * @param responseObserver 响应观察器。
     */
    @Override
    public void export(ExportLogsServiceRequest request, StreamObserver<ExportLogsServiceResponse> responseObserver) {
        logger.debug("export");
        for (ResourceLogs resourceLogs : request.getResourceLogsList()) {
            for (ScopeLogs scopeLogs : resourceLogs.getScopeLogsList()) {
                for (LogRecord logRecord : scopeLogs.getLogRecordsList()) {


                    logger.info(logRecord.getBody().getStringValue());

                }
            }
        }
        responseObserver.onNext(ExportLogsServiceResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
