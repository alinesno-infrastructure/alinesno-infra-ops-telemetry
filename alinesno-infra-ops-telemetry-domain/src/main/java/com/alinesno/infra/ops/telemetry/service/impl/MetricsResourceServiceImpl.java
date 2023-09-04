package com.alinesno.infra.ops.telemetry.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.MetricsResource;
import com.alinesno.infra.ops.telemetry.mapper.MetricsResourceMapper;
import com.alinesno.infra.ops.telemetry.service.IMetricsResourceService;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资源 Service业务层处理
 *
 * @author luoxiaodong
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class MetricsResourceServiceImpl extends IBaseServiceImpl<MetricsResource, MetricsResourceMapper> implements IMetricsResourceService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(MetricsResourceServiceImpl.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void saveMetrics(List<String> logList) {

    }

}
