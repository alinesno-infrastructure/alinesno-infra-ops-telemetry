package com.alinesno.infra.ops.telemetry.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.TraceScope;
import com.alinesno.infra.ops.telemetry.mapper.TraceScopeMapper;
import com.alinesno.infra.ops.telemetry.service.ITraceScopeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 表示 TraceScope 的 Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class TraceScopeServiceImpl extends IBaseServiceImpl<TraceScope, TraceScopeMapper> implements ITraceScopeService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(TraceScopeServiceImpl.class);
}
