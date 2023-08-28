package com.alinesno.infra.ops.telemetry.service;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.telemetry.entity.MetricsExponentialHistogram;

/**
 * 表示 MetricsExponentialHistogram 的 Service 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
public interface IMetricsExponentialHistogramService extends IBaseService<MetricsExponentialHistogram> {

    /**
     * 保存指数直方图信息
     * @param logList 日志列表
     */
    void saveHistogram(String logList);
}
