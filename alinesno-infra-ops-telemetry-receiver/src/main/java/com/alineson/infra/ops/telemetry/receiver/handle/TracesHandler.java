package com.alineson.infra.ops.telemetry.receiver.handle;

import com.alibaba.fastjson.JSONObject;
import com.alineson.infra.ops.telemetry.receiver.constants.Constants;
import com.alineson.infra.ops.telemetry.receiver.kafka.TelemetryKafkaProducer;
import com.alineson.infra.ops.telemetry.receiver.utils.HelperUtils;
import io.grpc.stub.StreamObserver;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceRequest;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceResponse;
import io.opentelemetry.proto.collector.trace.v1.TraceServiceGrpc;
import io.opentelemetry.proto.resource.v1.Resource;
import io.opentelemetry.proto.trace.v1.ResourceSpans;
import io.opentelemetry.proto.trace.v1.ScopeSpans;
import io.opentelemetry.proto.trace.v1.Span;
import io.opentelemetry.proto.trace.v1.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：该类为处理跟踪数据的 gRPC 服务实现类。
 * 此处代码参考: <a href=https://github.com/open-telemetry/opentelemetry-collector-contrib/blob/main/exporter/clickhouseexporter/exporter_traces.go>exporter_traces.go</a>
 * 类名：TracesHandler
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class TracesHandler extends TraceServiceGrpc.TraceServiceImplBase {
    private final Logger log = LoggerFactory.getLogger(getClass());

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

        List<Map<String , Object>> list = handlePush(request.getResourceSpansList()) ;

        responseObserver.onNext(ExportTraceServiceResponse.newBuilder().build());
        responseObserver.onCompleted();

        log.debug("TracesHandler list >>>>>>>>>>>>> \r\n {} " , JSONObject.toJSON(list));

        // Send To Kafka
        TelemetryKafkaProducer.getInstance().sendMessage(Constants.MQ_TRACE_TOPIC, JSONObject.toJSON(list));
    }

    /**
     * 处理推送数据，并将数据转换为包含 JSON 对象的列表。
     *
     * @param resourceSpansList 资源跟踪数据列表
     * @return 包含 JSON 对象的列表
     */
    private List<Map<String , Object>> handlePush(List<ResourceSpans> resourceSpansList) {
        // 创建一个空的 JSON 对象列表
        List<Map<String , Object>> list = new ArrayList<>();

        // 遍历资源跟踪数据列表
        for (ResourceSpans resourceSpans : resourceSpansList) {
            // 获取资源信息
            Resource res = resourceSpans.getResource();
            Map<String, JSONObject> resAttr = HelperUtils.attributesToMap(res.getAttributesList());
            JSONObject serviceName = resAttr.get(Constants.AttributeServiceName);

            // 遍历作用域跟踪数据列表
            for (ScopeSpans scopeSpans : resourceSpans.getScopeSpansList()) {
                // 获取作用域信息
                String scopeName = scopeSpans.getScope().getName();
                String scopeVersion = scopeSpans.getScope().getVersion();

                // 遍历跟踪数据列表
                for (Span span : scopeSpans.getSpansList()) {

                    // 获取跟踪信息
                    Map<String, JSONObject> spanAttr = HelperUtils.attributesToMap(span.getAttributesList());
                    Status status = span.getStatus();
                    List<String> eventTimes = HelperUtils.getEventTimes(span.getEventsList());
                    List<String> eventNames = HelperUtils.getEventNames(span.getEventsList());
                    Map<String, JSONObject> eventAttrs = HelperUtils.getEventAttributes(span.getEventsList());
                    List<String> linksTraceIDs = HelperUtils.getLinksTraceIDs(span.getLinksList());
                    List<String> linksSpanIDs = HelperUtils.getLinksSpanIDs(span.getLinksList());
                    List<String> linksTraceStates = HelperUtils.getLinksTraceStates(span.getLinksList());
                    Map<String, JSONObject> linksAttrs = HelperUtils.getElinksAttrs(span.getLinksList());
                    long startTimeUnixNano = span.getStartTimeUnixNano();
                    String traceId = HelperUtils.toHexOrEmptyString(span.getTraceId());
                    String spanId = HelperUtils.toHexOrEmptyString(span.getSpanId());
                    String parentSpanId = HelperUtils.toHexOrEmptyString(span.getParentSpanId());
                    String traceState = span.getTraceState();
                    String name = span.getName();
                    String kind = HelperUtils.spanKindToStr(span.getKind());
                    long endTimeUnixNano = span.getEndTimeUnixNano();
                    long nanoseconds = span.getEndTimeUnixNano() - span.getStartTimeUnixNano();
                    String statusCode = HelperUtils.getStatusCodeStr(status.getCode());
                    String message = status.getMessage();

                    // 创建一个空的 JSON 对象
                    Map<String , Object> json = new HashMap<>();

                    // 添加数据到 JSON 对象
                    json.put("startTimeUnixNano", startTimeUnixNano);
                    json.put("traceId", traceId);
                    json.put("spanId", spanId);
                    json.put("parentSpanId", parentSpanId);
                    json.put("traceState", traceState);
                    json.put("name", name);
                    json.put("kind", kind);
                    json.put("serviceName", serviceName);
                    json.put("resAttr", resAttr);
                    json.put("scopeName", scopeName);
                    json.put("scopeVersion", scopeVersion);
                    json.put("spanAttr", spanAttr);
                    json.put("endTimeUnixNano", endTimeUnixNano);
                    json.put("nanoseconds", nanoseconds);
                    json.put("statusCode", statusCode);
                    json.put("message", message);
                    json.put("eventTimes", eventTimes);
                    json.put("eventNames", eventNames);
                    json.put("eventAttrs", eventAttrs);
                    json.put("linksTraceIDs", linksTraceIDs);
                    json.put("linksSpanIDs", linksSpanIDs);
                    json.put("linksTraceStates", linksTraceStates);
                    json.put("linksAttrs", linksAttrs);

                    // 将 JSON 对象添加到列表中
                    list.add(json);
                }
            }
        }

        // 返回 JSON 对象列表
        return list;
    }
}
