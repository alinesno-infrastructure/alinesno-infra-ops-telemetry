package com.alineson.infra.ops.telemetry.receiver.constants;

/**
 * KindConstants类定义了一些与跟踪和事件相关的常量。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class KindConstants {
    /**
     * 表示跟踪的种类未指定。
     */
    public static final String SpanKindUnspecified = "Unspecified";

    /**
     * 表示跟踪的种类为内部跟踪。
     */
    public static final String SpanKindInternal = "Internal";

    /**
     * 表示跟踪的种类为服务端跟踪。
     */
    public static final String SpanKindServer = "Server";

    /**
     * 表示跟踪的种类为客户端跟踪。
     */
    public static final String SpanKindClient = "Client";

    /**
     * 表示跟踪的种类为生产者跟踪。
     */
    public static final String SpanKindProducer = "Producer";

    /**
     * 表示跟踪的种类为消费者跟踪。
     */
    public static final String SpanKindConsumer = "Consumer";

    /**
     * 表示状态码为成功。
     */
    public static final String StatusCodeOk = "OK";

    /**
     * 表示状态码未设置。
     */
    public static final String StatusCodeUnset = "UNSET";

    /**
     * 表示状态码为错误。
     */
    public static final String StatusCodeError = "ERROR";
}
