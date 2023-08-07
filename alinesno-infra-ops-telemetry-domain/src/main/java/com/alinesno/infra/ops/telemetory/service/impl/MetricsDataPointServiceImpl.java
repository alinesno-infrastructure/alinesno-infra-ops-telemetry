package com.alinesno.infra.ops.telemetory.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetory.entity.MetricsDataPoint;
import com.alinesno.infra.ops.telemetory.mapper.MetricsDataPointMapper;
import com.alinesno.infra.ops.telemetory.service.IMetricsDataPointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 数据点 Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class MetricsDataPointServiceImpl extends IBaseServiceImpl<MetricsDataPoint, MetricsDataPointMapper> implements IMetricsDataPointService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(MetricsDataPointServiceImpl.class);
}
