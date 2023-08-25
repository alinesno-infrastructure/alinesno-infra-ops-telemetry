package com.alinesno.infra.ops.telemetry.service;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.telemetry.entity.TelemetryMetricsGauge;

/**
 * 表示 TelemetryMetricsGauge 的 Service 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
public interface ITelemetryMetricsGaugeService extends IBaseService<TelemetryMetricsGauge> {

    /**
     * 保存 Gauge 信息
     * @param logList 日志列表
     */
    void saveGauge(String logList);
}
