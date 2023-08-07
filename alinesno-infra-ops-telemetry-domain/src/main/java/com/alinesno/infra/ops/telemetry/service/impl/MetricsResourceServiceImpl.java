package com.alinesno.infra.ops.telemetry.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.MetricsResource;
import com.alinesno.infra.ops.telemetry.mapper.MetricsResourceMapper;
import com.alinesno.infra.ops.telemetry.service.IMetricsResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 资源 Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class MetricsResourceServiceImpl extends IBaseServiceImpl<MetricsResource, MetricsResourceMapper> implements IMetricsResourceService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(MetricsResourceServiceImpl.class);
}
