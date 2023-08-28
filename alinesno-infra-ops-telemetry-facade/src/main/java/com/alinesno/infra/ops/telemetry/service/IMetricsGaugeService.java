package com.alinesno.infra.ops.telemetry.service;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.telemetry.entity.MetricsGauge;

/**
 * 表示 MetricsGauge 的 Service 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
public interface IMetricsGaugeService extends IBaseService<MetricsGauge> {

    /**
     * 保存 Gauge 信息
     * @param logList 日志列表
     */
    void saveGauge(String logList);
}
