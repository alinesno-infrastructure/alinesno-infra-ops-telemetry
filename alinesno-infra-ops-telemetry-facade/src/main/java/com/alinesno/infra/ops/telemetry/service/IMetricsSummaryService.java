package com.alinesno.infra.ops.telemetry.service;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.telemetry.entity.MetricsSummary;

/**
 * 表示 MetricsSummary 的 Service 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
public interface IMetricsSummaryService extends IBaseService<MetricsSummary> {

    /**
     * 保存 Summary 信息
     * @param logList 日志列表
     */
    void saveSummary(String logList);
}
