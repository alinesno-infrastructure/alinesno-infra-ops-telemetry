package com.alinesno.infra.ops.telemetory.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetory.entity.MetricBean;
import com.alinesno.infra.ops.telemetory.mapper.MetricMapper;
import com.alinesno.infra.ops.telemetory.service.IMetricService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 指标 Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class MetricServiceImpl extends IBaseServiceImpl<MetricBean, MetricMapper> implements IMetricService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(MetricServiceImpl.class);
}
