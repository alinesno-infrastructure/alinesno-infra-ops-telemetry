package com.alineson.infra.ops.telemetry.receiver.utils;

import com.alibaba.fastjson.JSONObject;
import com.alineson.infra.ops.telemetry.receiver.constants.KindConstants;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import io.opentelemetry.proto.common.v1.KeyValue;
import io.opentelemetry.proto.trace.v1.Span;
import io.opentelemetry.proto.trace.v1.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HelperUtils类提供了一些辅助方法，用于处理与跟踪和事件相关的操作。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class HelperUtils {

    private static final Gson gson = new Gson() ;

    /**
     * 将KeyValue列表转换为Map形式的属性。
     *
     * @param attributes KeyValue列表
     * @return 转换后的属性Map
     */
    public static Map<String, JSONObject> attributesToMap(List<KeyValue> attributes) {
        Map<String, JSONObject> newAttrMap = new HashMap<>();

        for (KeyValue entry : attributes) {
            String k = entry.getKey();
            Object v = entry.getValue();

            JSONObject jsonV = JSONObject.parseObject(gson.toJson(v)) ;
            newAttrMap.put(k, jsonV);
        }

        return newAttrMap;
    }

    /**
     * 获取事件列表中的时间字符串列表。
     *
     * @param events 事件列表
     * @return 时间字符串列表
     */
    public static List<String> getEventTimes(List<Span.Event> events) {
        List<String> times = new ArrayList<>();

        for (int i = 0; i < events.size(); i++) {
            Span.Event event = events.get(i);
            times.add(event.getTimeUnixNano() + "");
        }

        return times;
    }

    /**
     * 获取事件列表中的事件名称列表。
     *
     * @param events 事件列表
     * @return 事件名称列表
     */
    public static List<String> getEventNames(List<Span.Event> events) {
        List<String> names = new ArrayList<>();

        for (int i = 0; i < events.size(); i++) {
            Span.Event event = events.get(i);
            names.add(event.getName());
        }

        return names;
    }

    /**
     * 获取事件列表中的事件属性。
     *
     * @param events 事件列表
     * @return 事件属性Map
     */
    public static Map<String, JSONObject> getEventAttributes(List<Span.Event> events) {
        Map<String, JSONObject> attrs = new HashMap<>();

        for (int i = 0; i < events.size(); i++) {
            Span.Event event = events.get(i);

            Map<String, JSONObject> resAttr = HelperUtils.attributesToMap(event.getAttributesList());
            attrs.putAll(resAttr);
        }

        return attrs;
    }

    /**
     * 获取链接列表中的跟踪ID列表。
     *
     * @param linksList 链接列表
     * @return 跟踪ID列表
     */
    public static List<String> getLinksTraceIDs(List<Span.Link> linksList) {
        List<String> traceIDs = new ArrayList<>();

        for (Span.Link link : linksList) {
            traceIDs.add(HelperUtils.toHexOrEmptyString(link.getTraceId()));
        }

        return traceIDs;
    }

    /**
     * 获取链接列表中的Span ID列表。
     *
     * @param linksList 链接列表
     * @return Span ID列表
     */
    public static List<String> getLinksSpanIDs(List<Span.Link> linksList) {
        List<String> traceIDs = new ArrayList<>();

        for (Span.Link link : linksList) {
            traceIDs.add(HelperUtils.toHexOrEmptyString(link.getSpanId()));
        }

        return traceIDs;
    }

    /**
     * 获取链接列表中的跟踪状态列表。
     *
     * @param linksList 链接列表
     * @return 跟踪状态列表
     */
    public static List<String> getLinksTraceStates(List<Span.Link> linksList) {
        List<String> traceIDs = new ArrayList<>();

        for (Span.Link link : linksList) {
            traceIDs.add(HelperUtils.toHexOrEmptyString(link.getTraceStateBytes()));
        }

        return traceIDs;
    }

    /**
     * 获取链接列表中的链接属性。
     *
     * @param linksList 链接列表
     * @return 链接属性Map
     */
    public static Map<String, JSONObject> getElinksAttrs(List<Span.Link> linksList) {
        Map<String, JSONObject> attrs = new HashMap<>();

        for (Span.Link link : linksList) {
            Map<String, JSONObject> resAttr = HelperUtils.attributesToMap(link.getAttributesList());
            attrs.putAll(resAttr);
        }

        return attrs;
    }

    /**
     * 将ByteString转换为十六进制字符串，如果为空则返回空字符串。
     *
     * @param id ByteString对象
     * @return 十六进制字符串
     */
    public static String toHexOrEmptyString(ByteString id) {
        if (id.isEmpty()) {
            return "";
        }
        return bytesToHex(id.toByteArray());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * 将Span.SpanKind转换为对应的字符串表示。
     *
     * @param sk Span.SpanKind对象
     * @return Span.Kind的字符串表示
     */
    public static String spanKindToStr(Span.SpanKind sk) {
        return switch (sk.name()) {
            case KindConstants.SpanKindUnspecified -> "SPAN_KIND_UNSPECIFIED";
            case KindConstants.SpanKindInternal -> "SPAN_KIND_INTERNAL";
            case KindConstants.SpanKindServer -> "SPAN_KIND_SERVER";
            case KindConstants.SpanKindClient -> "SPAN_KIND_CLIENT";
            case KindConstants.SpanKindProducer -> "SPAN_KIND_PRODUCER";
            case KindConstants.SpanKindConsumer -> "SPAN_KIND_CONSUMER";
            default -> "";
        };
    }

    /**
     * 将Status.StatusCode转换为对应的字符串表示。
     *
     * @param sk Status.StatusCode对象
     * @return Status.StatusCode的字符串表示
     */
    public static String getStatusCodeStr(Status.StatusCode sk) {
        return switch (sk.name()) {
            case KindConstants.StatusCodeUnset -> "STATUS_CODE_UNSET";
            case KindConstants.StatusCodeOk -> "STATUS_CODE_OK";
            case KindConstants.StatusCodeError -> "STATUS_CODE_ERROR";
            default -> "";
        };
    }
}