package com.alineson.infra.ops.telemetry.receiver;

import com.alineson.infra.ops.telemetry.receiver.handle.LogsHandler;
import com.alineson.infra.ops.telemetry.receiver.handle.MetricsHandler;
import com.alineson.infra.ops.telemetry.receiver.handle.TracesHandler;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * OpenTelemetry统一服务管理
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class TelemetryServer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Server server;

    /**
     * 启动OTLP OpenTelemetry服务器。
     *
     * @throws IOException 如果发生I/O错误。
     */
    public void start() throws IOException {
        this.server = ServerBuilder
                .forPort(4316)
                .addService(new TracesHandler())
                .addService(new LogsHandler())
                .addService(new MetricsHandler())
                .build();
        server.start();

        logger.info("OTLP服务器已启动，端口号：" + server.getPort());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // 由于JVM关闭钩子可能重置日志记录器，因此在此处使用stderr。
            logger.debug("*** JVM正在关闭，停止gRPC服务器");
            try {
                stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            logger.info("*** 服务器已关闭");
        }));
    }

    /**
     * 停止OTLP OpenTelemetry服务器。
     *
     * @throws InterruptedException 如果执行被中断。
     */
    protected void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * 阻塞主线程，直到服务器关闭。
     *
     * @throws InterruptedException 如果执行被中断。
     */
    protected void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
