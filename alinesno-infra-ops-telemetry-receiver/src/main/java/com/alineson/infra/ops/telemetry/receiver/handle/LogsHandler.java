package com.alineson.infra.ops.telemetry.receiver.handle;

import com.alibaba.fastjson.JSONObject;
import com.alineson.infra.ops.telemetry.receiver.constants.Constants;
import com.alineson.infra.ops.telemetry.receiver.kafka.TelemetryKafkaProducer;
import com.alineson.infra.ops.telemetry.receiver.utils.HelperUtils;
import io.grpc.stub.StreamObserver;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceRequest;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceResponse;
import io.opentelemetry.proto.collector.logs.v1.LogsServiceGrpc;
import io.opentelemetry.proto.logs.v1.LogRecord;
import io.opentelemetry.proto.logs.v1.ResourceLogs;
import io.opentelemetry.proto.logs.v1.ScopeLogs;
import io.opentelemetry.proto.resource.v1.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：该类为处理日志的 gRPC 服务实现类。
 * 类名：LogsHandler
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class LogsHandler extends LogsServiceGrpc.LogsServiceImplBase {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 处理日志导出请求，并打印日志记录的内容。
     *
     * @param request          日志导出请求。
     * @param responseObserver 响应观察器。
     */
    @Override
    public void export(ExportLogsServiceRequest request, StreamObserver<ExportLogsServiceResponse> responseObserver) {

        List<Map<String , Object>> list = handlePush(request.getResourceLogsList()) ;

        log.debug("LogsHandler list >>>>>>>>>>>>> \r\n {} " , JSONObject.toJSON(list));

        responseObserver.onNext(ExportLogsServiceResponse.newBuilder().build());
        responseObserver.onCompleted();

        // Send To Kafka
        TelemetryKafkaProducer.getInstance().sendMessage(Constants.MQ_LOG_TOPIC, JSONObject.toJSON(list));

    }

    private List<Map<String, Object>> handlePush(List<ResourceLogs> resourceLogsList) {

        // 创建一个空的 JSON 对象列表
        List<Map<String , Object>> list = new ArrayList<>();

        for (ResourceLogs resourceLogs : resourceLogsList) {

            Resource res = resourceLogs.getResource() ;
            String resURL = resourceLogs.getSchemaUrl() ;
            Map<String, JSONObject> resAttr = HelperUtils.attributesToMap(res.getAttributesList()) ;

            JSONObject serviceName = resAttr.get(Constants.AttributeServiceName) ;

            for (ScopeLogs scopeLogs : resourceLogs.getScopeLogsList()) {

                String scopeURL = scopeLogs.getSchemaUrl() ;
                String scopeName = scopeLogs.getScope().getName() ;
                String scopeVersion = scopeLogs.getScope().getName() ;

                Map<String, JSONObject> scopeAttr = HelperUtils.attributesToMap(scopeLogs.getScope().getAttributesList()) ;

                for (LogRecord r : scopeLogs.getLogRecordsList()) {

                    long timestamp = r.getTimeUnixNano() ;  ;
                    String traceId = HelperUtils.toHexOrEmptyString(r.getTraceId()) ;
                    String spanId = HelperUtils.toHexOrEmptyString(r.getSpanId()) ;
                    int traceFlags = r.getFlags() ;
                    String severityText = r.getSeverityText() ;
                    int severityNumber = r.getSeverityNumber().getNumber() ;
                    String body = r.getBody().getStringValue() ;

                    Map<String, JSONObject> logAttributes = HelperUtils.attributesToMap(r.getAttributesList()) ;

                    Map<String , Object> map = new HashMap<>();
                    map.put("timestamp", timestamp);
                    map.put("traceId", traceId);
                    map.put("spanId", spanId);
                    map.put("traceFlags", traceFlags);
                    map.put("severityText", severityText);
                    map.put("severityNumber", severityNumber);
                    map.put("serviceName", serviceName);
                    map.put("body", body);
                    map.put("resourceSchemaUrl", resURL);
                    map.put("resourceAttributes", resAttr);
                    map.put("scopeSchemaUrl", scopeURL);
                    map.put("scopeName", scopeName);
                    map.put("scopeVersion", scopeVersion);
                    map.put("scopeAttributes", scopeAttr);
                    map.put("logAttributes", logAttributes);

                    list.add(map) ;
                }
            }
        }

        return list ;
    }
}
