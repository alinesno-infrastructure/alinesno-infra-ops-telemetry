package com.alinesno.infra.ops.telemetry.service;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.telemetry.entity.TelemetryMetricsSummary;

/**
 * 表示 TelemetryMetricsSummary 的 Service 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
public interface ITelemetryMetricsSummaryService extends IBaseService<TelemetryMetricsSummary> {

    /**
     * 保存 Summary 信息
     * @param logList 日志列表
     */
    void saveSummary(String logList);
}
