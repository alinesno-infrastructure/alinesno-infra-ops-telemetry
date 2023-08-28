package com.alinesno.infra.ops.telemetry.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.TraceResource;
import com.alinesno.infra.ops.telemetry.mapper.TraceResourceMapper;
import com.alinesno.infra.ops.telemetry.service.ITraceResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 表示 Resource 的 Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class TraceResourceServiceImpl extends IBaseServiceImpl<TraceResource, TraceResourceMapper> implements ITraceResourceService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(TraceResourceServiceImpl.class);


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void saveTrace(List<String> logList) {

    }

}
