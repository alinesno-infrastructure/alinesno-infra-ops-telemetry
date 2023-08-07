package com.alinesno.infra.ops.telemetory.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetory.entity.TraceResource;
import com.alinesno.infra.ops.telemetory.mapper.TraceResourceMapper;
import com.alinesno.infra.ops.telemetory.service.ITraceResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
}
