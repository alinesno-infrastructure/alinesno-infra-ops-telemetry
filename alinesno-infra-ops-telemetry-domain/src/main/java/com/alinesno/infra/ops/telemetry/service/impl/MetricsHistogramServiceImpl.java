package com.alinesno.infra.ops.telemetry.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.MetricsHistogram;
import com.alinesno.infra.ops.telemetry.mapper.MetricsHistogramMapper;
import com.alinesno.infra.ops.telemetry.service.IMetricsHistogramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 直方图信息 Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class MetricsHistogramServiceImpl extends IBaseServiceImpl<MetricsHistogram, MetricsHistogramMapper> implements IMetricsHistogramService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(MetricsHistogramServiceImpl.class);
}
