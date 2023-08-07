package com.alinesno.infra.ops.telemetry.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.MetricsScope;
import com.alinesno.infra.ops.telemetry.mapper.MetricsScopeMapper;
import com.alinesno.infra.ops.telemetry.service.IMetricsScopeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 范围 Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class MetricsScopeServiceImpl extends IBaseServiceImpl<MetricsScope, MetricsScopeMapper> implements IMetricsScopeService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(MetricsScopeServiceImpl.class);
}
