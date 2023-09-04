package com.alinesno.infra.ops.telemetry.enums;

import io.opentelemetry.proto.metrics.v1.Sum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class ClickhouseSqls {

    // 日志服务
    public static final String logsSql = "INSERT INTO telemetry_logs (" +
            "Timestamp," +
            "TraceId," +
            "SpanId," +
            "TraceFlags," +
            "SeverityText," +
            "SeverityNumber, " +
            "ServiceName," +
            "Body," +
            "ResourceSchemaUrl," +
            "ResourceAttributes," +
            "ScopeSchemaUrl," +
            "ScopeName, " +
            "ScopeVersion," +
            "ScopeAttributes," +
            "LogAttributes" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    // 链路跟踪服务
    public static final String traceSql = "INSERT INTO telemetry_trace (" +
            "Timestamp," +
            "TraceId," +
            "SpanId," +
            "ParentSpanId," +
            "TraceState," +
            "SpanName," +
            "SpanKind," +
            "ServiceName," +
            "ResourceAttributes," +
            "ScopeName," +
            "ScopeVersion," +
            "SpanAttributes," +
            "Duration," +
            "StatusCode," +
            "StatusMessage," +
            "Events.Timestamp," +
            "Events.Name," +
            "Events.Attributes," +
            "Links.TraceId," +
            "Links.SpanId," +
            "Links.TraceState," +
            "Links.Attributes" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    // 监控服务 histogram
    public static final String metricsExponentialHistogramSql = "INSERT INTO telemetry_metrics_exponential_histogram (" +
            "ResourceAttributes," +
            "ResourceSchemaUrl," +
            "ScopeName," +
            "ScopeVersion," +
            "ScopeAttributes," +
            "ScopeDroppedAttrCount," +
            "ScopeSchemaUrl," +
            "MetricName," +
            "MetricDescription," +
            "MetricUnit," +
            "Attributes," +
            "StartTimeUnix," +
            "TimeUnix," +
            "Count," +
            "Sum," +
            "Scale," +
            "ZeroCount," +
            "PositiveOffset," +
            "PositiveBucketCounts," +
            "NegativeOffset," +
            "NegativeBucketCounts," +
            "Exemplars.FilteredAttributes," +
            "Exemplars.TimeUnix," +
            "Exemplars.Value," +
            "Exemplars.SpanId," +
            "Exemplars.TraceId," +
            "Flags," +
            "Min," +
            "Max" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // 监控服务 gauge
    public static final String metricsGaugeSql = "INSERT INTO telemetry_metrics_gauge (" +
            "ResourceAttributes," +
            "ResourceSchemaUrl," +
            "ScopeName," +
            "ScopeVersion," +
            "ScopeAttributes," +
            "ScopeDroppedAttrCount," +
            "ScopeSchemaUrl," +
            "MetricName," +
            "MetricDescription," +
            "MetricUnit," +
            "Attributes," +
            "StartTimeUnix," +
            "TimeUnix," +
            "Value," +
            "Flags," +
            "Exemplars.FilteredAttributes," +
            "Exemplars.TimeUnix," +
            "Exemplars.Value," +
            "Exemplars.SpanId," +
            "Exemplars.TraceId" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // 监控服务 histogram
    public static final String metricsHistogramSql = "INSERT INTO telemetry_metrics_histogram (" +
            "ResourceAttributes," +
            "ResourceSchemaUrl," +
            "ScopeName," +
            "ScopeVersion," +
            "ScopeAttributes," +
            "ScopeDroppedAttrCount," +
            "ScopeSchemaUrl," +
            "MetricName," +
            "MetricDescription," +
            "MetricUnit," +
            "Attributes," +
            "StartTimeUnix," +
            "TimeUnix," +
            "Count," +
            "Sum," +
            "BucketCounts," +
            "ExplicitBounds," +
            "Exemplars.FilteredAttributes," +
            "Exemplars.TimeUnix," +
            "Exemplars.Value," +
            "Exemplars.SpanId," +
            "Exemplars.TraceId" +
            "Flags," +
            "Min," +
            "Max" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // 监控服务 summary
    public static final String metricsSummarySql = "INSERT INTO telemetry_metrics_summary (" +
            "ResourceAttributes," +
            "ResourceSchemaUrl," +
            "ScopeName," +
            "ScopeVersion," +
            "ScopeAttributes," +
            "ScopeDroppedAttrCount," +
            "ScopeSchemaUrl," +
            "MetricName," +
            "MetricDescription," +
            "MetricUnit," +
            "Attributes," +
            "StartTimeUnix," +
            "TimeUnix," +
            "Count," +
            "Sum," +
            "ValueAtQuantiles.Quantile," +
            "ValueAtQuantiles.Value," +
            "Flags" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // 监控服务 sum
    public static final String metricsSumSql = "INSERT INTO telemetry_metrics_sum (" +
            "ResourceAttributes," +
            "ResourceSchemaUrl," +
            "ScopeName," +
            "ScopeVersion," +
            "ScopeAttributes," +
            "ScopeDroppedAttrCount," +
            "ScopeSchemaUrl," +
            "MetricName," +
            "MetricDescription," +
            "MetricUnit," +
            "Attributes," +
            "StartTimeUnix," +
            "TimeUnix," +
            "Value," +
            "Flags," +
            "Exemplars.FilteredAttributes," +
            "Exemplars.TimeUnix," +
            "Exemplars.Value," +
            "Exemplars.SpanId," +
            "Exemplars.TraceId," +
            "AggTemp," +
            "IsMonotonic" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";



}
