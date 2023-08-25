package com.alinesno.infra.ops.telemetry.service;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.telemetry.entity.TelemetryMetricsSum;

/**
 * 表示 TelemetryMetricsSum 的 Service 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
public interface ITelemetryMetricsSumService extends IBaseService<TelemetryMetricsSum> {

    /**
     * 保存 Sum 信息
     * @param logList 日志列表
     */
    void saveSum(String logList);
}
