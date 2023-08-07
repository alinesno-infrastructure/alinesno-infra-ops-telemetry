package com.alinesno.infra.ops.telemetory.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetory.entity.LogScope;
import com.alinesno.infra.ops.telemetory.mapper.LogScopeMapper;
import com.alinesno.infra.ops.telemetory.service.ILogScopeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 作用域 Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class LogScopeServiceImpl extends IBaseServiceImpl<LogScope, LogScopeMapper> implements ILogScopeService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(LogScopeServiceImpl.class);
}
