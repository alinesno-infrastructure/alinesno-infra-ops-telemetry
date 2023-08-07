package com.alinesno.infra.ops.telemetry.adapter.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类，用于创建 ThreadPoolExecutor 实例。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class ThreadPoolUtil {

    /**
     * 创建具有默认设置的 ThreadPoolExecutor。
     *
     * @return 创建的 ThreadPoolExecutor 实例。
     */
    public static ThreadPoolExecutor getPool() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                10, 10, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        return threadPool;
    }

    /**
     * 创建具有自定义设置的 ThreadPoolExecutor。
     *
     * @param corePoolSize 线程池中保留的线程数，即使它们是空闲的。
     * @param maxPoolSize 线程池允许的最大线程数。
     * @param capacity 工作队列的容量。
     * @return 创建的 ThreadPoolExecutor 实例。
     */
    public static ThreadPoolExecutor getPool(int corePoolSize, int maxPoolSize, int capacity) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                corePoolSize, maxPoolSize, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(capacity),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        return threadPool;
    }

}
