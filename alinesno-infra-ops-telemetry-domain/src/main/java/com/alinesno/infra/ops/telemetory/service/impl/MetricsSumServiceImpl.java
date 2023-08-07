package com.alinesno.infra.ops.telemetory.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetory.entity.MetricsSum;
import com.alinesno.infra.ops.telemetory.mapper.MetricsSumMapper;
import com.alinesno.infra.ops.telemetory.service.IMetricsSumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 总和信息 Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class MetricsSumServiceImpl extends IBaseServiceImpl<MetricsSum, MetricsSumMapper> implements IMetricsSumService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(MetricsSumServiceImpl.class);
}
