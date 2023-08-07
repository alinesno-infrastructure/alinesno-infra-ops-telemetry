package com.alinesno.infra.ops.telemetory.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetory.entity.MetricsGauge;
import com.alinesno.infra.ops.telemetory.mapper.MetricsGaugeMapper;
import com.alinesno.infra.ops.telemetory.service.IMetricsGaugeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 测量信息 Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class MetricsGaugeServiceImpl extends IBaseServiceImpl<MetricsGauge, MetricsGaugeMapper> implements IMetricsGaugeService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(MetricsGaugeServiceImpl.class);
}
