package com.alineson.infra.ops.telemetry.receiver.handle;

import com.alibaba.fastjson.JSONObject;
import com.alineson.infra.ops.telemetry.receiver.constants.Constants;
import com.alineson.infra.ops.telemetry.receiver.kafka.TelemetryKafkaProducer;
import com.alineson.infra.ops.telemetry.receiver.utils.HelperUtils;
import io.grpc.stub.StreamObserver;
import io.opentelemetry.proto.collector.metrics.v1.ExportMetricsServiceRequest;
import io.opentelemetry.proto.collector.metrics.v1.ExportMetricsServiceResponse;
import io.opentelemetry.proto.collector.metrics.v1.MetricsServiceGrpc;
import io.opentelemetry.proto.common.v1.InstrumentationScope;
import io.opentelemetry.proto.metrics.v1.*;
import io.opentelemetry.proto.resource.v1.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：该类为处理指标的 gRPC 服务实现类。
 * 类名：MetricsHandler
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class MetricsHandler extends MetricsServiceGrpc.MetricsServiceImplBase {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 处理指标导出请求，并打印指标信息。
     *
     * @param request          指标导出请求。
     * @param responseObserver 响应观察器。
     */
    @Override
    public void export(ExportMetricsServiceRequest request, StreamObserver<ExportMetricsServiceResponse> responseObserver) {

        List<Map<String , Object>> list = handlePush(request.getResourceMetricsList()) ;

        responseObserver.onNext(ExportMetricsServiceResponse.newBuilder().build());
        responseObserver.onCompleted();

        log.debug("MetricsHandler list >>>>>>>>>>>>> \r\n {} " , JSONObject.toJSON(list));

        // Send To Kafka
        TelemetryKafkaProducer.getInstance().sendMessage(Constants.MQ_METRICS_TOPIC, JSONObject.toJSON(list));
    }

    private List<Map<String, Object>> handlePush(List<ResourceMetrics> resourceMetricsList) {
        // 创建一个空的 JSON 对象列表
        List<Map<String , Object>> list = new ArrayList<>();

        for (ResourceMetrics resourceMetrics : resourceMetricsList) {

            Resource res = resourceMetrics.getResource();
            Map<String, JSONObject> resAttr = HelperUtils.attributesToMap(res.getAttributesList());

            for (ScopeMetrics scopeMetrics : resourceMetrics.getScopeMetricsList()) {

                for (Metric metrics : scopeMetrics.getMetricsList()) {
                    Metric.DataCase dataCase = metrics.getDataCase() ;

                    log.debug("data case = {}" , dataCase.getNumber());

                    switch (dataCase.getNumber()) {
                        case Metric.GAUGE_FIELD_NUMBER -> buildGaugeMessage(list, metrics , scopeMetrics , resAttr);
                        case Metric.SUM_FIELD_NUMBER -> buildSumMessage(list, metrics , scopeMetrics , resAttr);
                        case Metric.HISTOGRAM_FIELD_NUMBER -> buildHistogramMessage(list, metrics , scopeMetrics , resAttr);
                        case Metric.EXPONENTIAL_HISTOGRAM_FIELD_NUMBER -> buildExponentialHistogramMessage(list, metrics , scopeMetrics , resAttr);
                        case Metric.SUMMARY_FIELD_NUMBER -> buildSummaryMessage(list, metrics , scopeMetrics , resAttr);
                        case 0 -> {
                        }
                    }

                }
            }
        }

        return list ;
    }

    private void buildSummaryMessage(List<Map<String, Object>> list, Metric metrics, ScopeMetrics scopeMetrics, Map<String, JSONObject> resAttr) {

        InstrumentationScope scopeInstr = scopeMetrics.getScope() ;
        List<SummaryDataPoint> dataPoints = metrics.getSummary().getDataPointsList() ;

        for(SummaryDataPoint dp : dataPoints){

            String resourceSchemaUrl = scopeMetrics.getSchemaUrl() ;
            String scopeName = scopeInstr.getName() ;
            String scopeVersion = scopeInstr.getVersion() ;
            Map<String, JSONObject> scopeAttributes = HelperUtils.attributesToMap(scopeInstr.getAttributesList()) ;
            int scopeDroppedAttrCount = scopeInstr.getDroppedAttributesCount() ;
            String scopeSchemaUrl = scopeMetrics.getSchemaUrl() ;
            String metricName = metrics.getName() ;
            String metricDescription = metrics.getDescription()  ;
            String metricUnit = metrics.getUnit() ;

            Map<String, JSONObject> attributes = HelperUtils.attributesToMap(dp.getAttributesList()) ;
            long startTimeUnix = dp.getStartTimeUnixNano() ;
            long timeUnix = dp.getTimeUnixNano() ;
            long count = dp.getCount() ;
            double sum = dp.getSum() ;

            List<Double> valueAtQuantilesQuantile = HelperUtils.convertQuantiles(dp.getQuantileValuesList());
            List<Double> valueAtQuantilesValue = HelperUtils.convertValue(dp.getQuantileValuesList());
            int flags = dp.getFlags() ;

            Map<String, Object> map = new HashMap<>();

            map.put("resourceAttributes", resAttr) ;
            map.put("resourceSchemaUrl",resourceSchemaUrl) ;
            map.put("scopeName", scopeName) ;
            map.put("scopeVersion", scopeVersion) ;
            map.put("scopeAttributes", scopeAttributes) ;
            map.put("scopeDroppedAttrCount", scopeDroppedAttrCount) ;
            map.put("scopeSchemaUrl", scopeSchemaUrl) ;
            map.put("metricDescription", metricDescription) ;
            map.put("metricName", metricName) ;
            map.put("metricUnit", metricUnit);

            map.put("attributes", attributes) ;
            map.put("startTimeUnix", startTimeUnix) ;
            map.put("timeUnix", timeUnix) ;
            map.put("count", count) ;
            map.put("sum", sum) ;

            map.put("valueAtQuantilesQuantile", valueAtQuantilesQuantile) ;
            map.put("valueAtQuantilesValue", valueAtQuantilesValue) ;
            map.put("flags", flags) ;

            list.add(map) ;
        }

    }

    private void buildExponentialHistogramMessage(List<Map<String, Object>> list, Metric metrics, ScopeMetrics scopeMetrics, Map<String, JSONObject> resAttr) {

        InstrumentationScope scopeInstr = scopeMetrics.getScope() ;
        List<ExponentialHistogramDataPoint> dataPoints = metrics.getExponentialHistogram().getDataPointsList() ;

        for(ExponentialHistogramDataPoint dp : dataPoints){


            String resourceSchemaUrl = scopeMetrics.getSchemaUrl() ;
            String scopeName = scopeInstr.getName();
            String scopeVersion = scopeInstr.getVersion() ;
            Map<String, JSONObject> scopeAttributes = HelperUtils.attributesToMap(scopeInstr.getAttributesList());
            int scopeDroppedAttrCount = scopeInstr.getDroppedAttributesCount() ;
            String scopeSchemaUrl = scopeMetrics.getSchemaUrl() ;
            String metricName = metrics.getName() ;
            String metricDescription = metrics.getDescription() ;
            String metricUnit = metrics.getUnit(); ;

            Map<String, JSONObject> exemplarsFilteredAttributes = HelperUtils.convertExemplarAttribute(dp.getExemplarsList()) ;
            List<Long> exemplarsTimeUnix  = HelperUtils.convertExemplarTimeUnit(dp.getExemplarsList())  ;
            List<Double> exemplarsValue  = HelperUtils.convertExemplarValue(dp.getExemplarsList())  ;
            List<String> exemplarsSpanId  = HelperUtils.convertExemplarSpanId(dp.getExemplarsList())  ;
            List<String> exemplarsTraceId  = HelperUtils.convertExemplarTraceId(dp.getExemplarsList())  ;

            int flag = dp.getFlags() ;
            Map<String, JSONObject> attributes = HelperUtils.attributesToMap(dp.getAttributesList()) ;
            long startTimeUnix = dp.getStartTimeUnixNano();
            long timeUnix = dp.getTimeUnixNano() ;
            long count = dp.getCount() ;
            double sum = dp.getSum();
            double Min = dp.getMin();
            double Max = dp.getMax();

            int scale = dp.getScale() ;
            long zeroCount = dp.getZeroCount() ;
            int positiveOffset = dp.getPositive().getOffset()  ;
            List<Long> positiveBucketCounts = dp.getPositive().getBucketCountsList() ;
            int negativeOffset = dp.getNegative().getOffset() ;
            List<Long> negativeBucketCounts = dp.getNegative().getBucketCountsList() ;

            Map<String, Object> map = new HashMap<>();

            map.put("resourceAttributes", resAttr);
            map.put("resourceSchemaUrl", resourceSchemaUrl);
            map.put("scopeName", scopeName);
            map.put("scopeVersion", scopeVersion);
            map.put("scopeAttributes", scopeAttributes);
            map.put("scopeDroppedAttrCount", scopeDroppedAttrCount);
            map.put("scopeSchemaUrl", scopeSchemaUrl);
            map.put("metricName", metricName);
            map.put("metricDescription", metricDescription);
            map.put("metricUnit", metricUnit);
            map.put("exemplarsFilteredAttributes", exemplarsFilteredAttributes);
            map.put("exemplarsTimeUnix", exemplarsTimeUnix);
            map.put("exemplarsValue", exemplarsValue);
            map.put("exemplarsSpanId", exemplarsSpanId);
            map.put("exemplarsTraceId", exemplarsTraceId);
            map.put("flag", flag);
            map.put("attributes", attributes);
            map.put("startTimeUnix", startTimeUnix);
            map.put("timeUnix", timeUnix);
            map.put("count", count);
            map.put("sum", sum);
            map.put("Min", Min);
            map.put("Max", Max);
            map.put("scale", scale);
            map.put("zeroCount", zeroCount);
            map.put("positiveOffset", positiveOffset);
            map.put("positiveBucketCounts", positiveBucketCounts);
            map.put("negativeOffset", negativeOffset);
            map.put("negativeBucketCounts", negativeBucketCounts);

            list.add(map) ;
        }
    }

    private void buildHistogramMessage(List<Map<String, Object>> list, Metric metrics, ScopeMetrics scopeMetrics, Map<String, JSONObject> resAttr) {

        InstrumentationScope scopeInstr = scopeMetrics.getScope() ;
        List<HistogramDataPoint> dataPoints = metrics.getHistogram().getDataPointsList() ;

        for(HistogramDataPoint dp : dataPoints){

            String resourceSchemaUrl = scopeMetrics.getSchemaUrl() ;
            String scopeName = scopeInstr.getName();
            String scopeVersion = scopeInstr.getVersion() ;
            Map<String, JSONObject> scopeAttributes = HelperUtils.attributesToMap(scopeInstr.getAttributesList());
            int scopeDroppedAttrCount = scopeInstr.getDroppedAttributesCount() ;
            String scopeSchemaUrl = scopeMetrics.getSchemaUrl() ;
            String metricName = metrics.getName() ;
            String metricDescription = metrics.getDescription() ;
            String metricUnit = metrics.getUnit(); ;

            Map<String, JSONObject> exemplarsFilteredAttributes = HelperUtils.convertExemplarAttribute(dp.getExemplarsList()) ;
            List<Long> exemplarsTimeUnix  = HelperUtils.convertExemplarTimeUnit(dp.getExemplarsList())  ;
            List<Double> exemplarsValue  = HelperUtils.convertExemplarValue(dp.getExemplarsList())  ;
            List<String> exemplarsSpanId  = HelperUtils.convertExemplarSpanId(dp.getExemplarsList())  ;
            List<String> exemplarsTraceId  = HelperUtils.convertExemplarTraceId(dp.getExemplarsList())  ;

            int flag = dp.getFlags() ;
            Map<String, JSONObject> attributes = HelperUtils.attributesToMap(dp.getAttributesList()) ;
            long startTimeUnix = dp.getStartTimeUnixNano();
            long timeUnix = dp.getTimeUnixNano() ;
            long count = dp.getCount() ;
            double sum = dp.getSum();
            List<Long> bucketCounts = dp.getBucketCountsList() ;
            List<Double> explicitBounds = dp.getExplicitBoundsList() ;
            double Min = dp.getMin();
            double Max = dp.getMax();

            Map<String, Object> map = new HashMap<>();

            map.put("resourceAttributes", resAttr);
            map.put("resourceSchemaUrl", resourceSchemaUrl);
            map.put("scopeName", scopeName);
            map.put("scopeVersion", scopeVersion);
            map.put("scopeAttributes", scopeAttributes);
            map.put("scopeDroppedAttrCount", scopeDroppedAttrCount);
            map.put("scopeSchemaUrl", scopeSchemaUrl);
            map.put("metricName", metricName);
            map.put("metricDescription", metricDescription);
            map.put("metricUnit", metricUnit);
            map.put("exemplarsFilteredAttributes", exemplarsFilteredAttributes);
            map.put("exemplarsTimeUnix", exemplarsTimeUnix);
            map.put("exemplarsValue", exemplarsValue);
            map.put("exemplarsSpanId", exemplarsSpanId);
            map.put("exemplarsTraceId", exemplarsTraceId);
            map.put("flag", flag);
            map.put("attributes", attributes);
            map.put("startTimeUnix", startTimeUnix);
            map.put("timeUnix", timeUnix);
            map.put("count", count);
            map.put("sum", sum);
            map.put("bucketCounts", bucketCounts);
            map.put("explicitBounds", explicitBounds);
            map.put("Min", Min);
            map.put("Max", Max);

            list.add(map) ;
        }

    }

    private void buildSumMessage(List<Map<String, Object>> list, Metric metrics, ScopeMetrics scopeMetrics, Map<String, JSONObject> resAttr) {

        InstrumentationScope scopeInstr = scopeMetrics.getScope() ;
        List<NumberDataPoint> dataPoints = metrics.getSum().getDataPointsList() ;

        for(NumberDataPoint dp : dataPoints){

            String resourceSchemaUrl = scopeMetrics.getSchemaUrl() ;
            String scopeName = scopeInstr.getName();
            String scopeVersion = scopeInstr.getVersion() ;
            Map<String, JSONObject> scopeAttributes = HelperUtils.attributesToMap(scopeInstr.getAttributesList());
            int scopeDroppedAttrCount = scopeInstr.getDroppedAttributesCount() ;
            String scopeSchemaUrl = scopeMetrics.getSchemaUrl() ;
            String metricName = metrics.getName() ;
            String metricDescription = metrics.getDescription() ;
            String metricUnit = metrics.getUnit(); ;

            Map<String, JSONObject> attributes = HelperUtils.attributesToMap(dp.getAttributesList()) ;
            double startTimeUnix = dp.getStartTimeUnixNano() ;
            double timeUnix = dp.getTimeUnixNano() ;
            double value = HelperUtils.getValue(dp.getAsInt() , dp.getAsDouble() , dp.getParserForType()) ;
            double flags = dp.getFlags() ;

            Map<String, JSONObject> exemplarsFilteredAttributes = HelperUtils.convertExemplarAttribute(dp.getExemplarsList()) ;
            List<Long> exemplarsTimeUnix  = HelperUtils.convertExemplarTimeUnit(dp.getExemplarsList())  ;
            List<Double> exemplarsValue  = HelperUtils.convertExemplarValue(dp.getExemplarsList())  ;
            List<String> exemplarsSpanId  = HelperUtils.convertExemplarSpanId(dp.getExemplarsList())  ;
            List<String> exemplarsTraceId  = HelperUtils.convertExemplarTraceId(dp.getExemplarsList())  ;

            AggregationTemporality aggTemp = metrics.getSum().getAggregationTemporality() ;
            boolean isMonotonic = metrics.getSum().getIsMonotonic() ;

            Map<String, Object> map = new HashMap<>();

            map.put("resourceAttributes", resAttr);
            map.put("resourceSchemaUrl", resourceSchemaUrl);
            map.put("scopeName", scopeName);
            map.put("scopeVersion", scopeVersion);
            map.put("scopeAttributes", scopeAttributes);
            map.put("scopeDroppedAttrCount", scopeDroppedAttrCount);
            map.put("scopeSchemaUrl", scopeSchemaUrl);
            map.put("metricName", metricName);
            map.put("metricDescription", metricDescription);
            map.put("metricUnit", metricUnit);
            map.put("attributes", attributes);
            map.put("startTimeUnix", startTimeUnix);
            map.put("timeUnix", timeUnix);
            map.put("value", value);
            map.put("flags", flags);
            map.put("exemplarsFilteredAttributes", exemplarsFilteredAttributes);
            map.put("exemplarsTimeUnix", exemplarsTimeUnix);
            map.put("exemplarsValue", exemplarsValue);
            map.put("exemplarsSpanId", exemplarsSpanId);
            map.put("exemplarsTraceId", exemplarsTraceId);
            map.put("aggTemp", aggTemp);
            map.put("isMonotonic", isMonotonic);

            list.add(map) ;
        }

    }

    private void buildGaugeMessage(List<Map<String, Object>> list, Metric metrics, ScopeMetrics scopeMetrics, Map<String, JSONObject> resAttr) {

        InstrumentationScope scopeInstr = scopeMetrics.getScope() ;
        List<NumberDataPoint> dataPoints = metrics.getGauge().getDataPointsList() ;

        for(NumberDataPoint dp : dataPoints){

            String resourceSchemaUrl = scopeMetrics.getSchemaUrl() ;
            String scopeName = scopeInstr.getName();
            String scopeVersion = scopeInstr.getVersion() ;
            Map<String, JSONObject> scopeAttributes = HelperUtils.attributesToMap(scopeInstr.getAttributesList());
            int scopeDroppedAttrCount = scopeInstr.getDroppedAttributesCount() ;
            String scopeSchemaUrl = scopeMetrics.getSchemaUrl() ;
            String metricName = metrics.getName() ;
            String metricDescription = metrics.getDescription() ;
            String metricUnit = metrics.getUnit(); ;

            Map<String, JSONObject> attributes = HelperUtils.attributesToMap(dp.getAttributesList()) ;
            double startTimeUnix = dp.getStartTimeUnixNano() ;
            double timeUnix = dp.getTimeUnixNano() ;
            double value = HelperUtils.getValue(dp.getAsInt() , dp.getAsDouble() , dp.getParserForType()) ;
            double flags = dp.getFlags() ;

            Map<String, JSONObject> exemplarsFilteredAttributes = HelperUtils.convertExemplarAttribute(dp.getExemplarsList()) ;
            List<Long> exemplarsTimeUnix  = HelperUtils.convertExemplarTimeUnit(dp.getExemplarsList())  ;
            List<Double> exemplarsValue  = HelperUtils.convertExemplarValue(dp.getExemplarsList())  ;
            List<String> exemplarsSpanId  = HelperUtils.convertExemplarSpanId(dp.getExemplarsList())  ;
            List<String> exemplarsTraceId  = HelperUtils.convertExemplarTraceId(dp.getExemplarsList())  ;

            Map<String, Object> map = new HashMap<>();

            map.put("resourceAttributes", resAttr);
            map.put("resourceSchemaUrl", resourceSchemaUrl);
            map.put("scopeName", scopeName);
            map.put("scopeVersion", scopeVersion);
            map.put("scopeAttributes", scopeAttributes);
            map.put("scopeDroppedAttrCount", scopeDroppedAttrCount);
            map.put("scopeSchemaUrl", scopeSchemaUrl);
            map.put("metricName", metricName);
            map.put("metricDescription", metricDescription);
            map.put("metricUnit", metricUnit);
            map.put("attributes", attributes);
            map.put("startTimeUnix", startTimeUnix);
            map.put("timeUnix", timeUnix);
            map.put("value", value);
            map.put("flags", flags);
            map.put("exemplarsFilteredAttributes", exemplarsFilteredAttributes);
            map.put("exemplarsTimeUnix", exemplarsTimeUnix);
            map.put("exemplarsValue", exemplarsValue);
            map.put("exemplarsSpanId", exemplarsSpanId);
            map.put("exemplarsTraceId", exemplarsTraceId);

            list.add(map) ;
        }
    }
}
