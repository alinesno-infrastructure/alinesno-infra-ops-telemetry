package com.alinesno.infra.ops.telemetry.service;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.telemetry.entity.MetricsHistogram;

/**
 * 表示 MetricsHistogram 的 Service 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
public interface IMetricsHistogramService extends IBaseService<MetricsHistogram> {

    /**
     * 保存直方图信息
     * @param logList 日志列表
     */
    void saveHistogram(String logList);
}
