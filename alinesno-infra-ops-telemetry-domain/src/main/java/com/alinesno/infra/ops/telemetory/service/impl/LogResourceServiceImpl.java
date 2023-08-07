package com.alinesno.infra.ops.telemetory.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetory.entity.LogResource;
import com.alinesno.infra.ops.telemetory.mapper.LogResourceMapper;
import com.alinesno.infra.ops.telemetory.service.ILogResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Resource Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class LogResourceServiceImpl extends IBaseServiceImpl<LogResource, LogResourceMapper> implements ILogResourceService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(LogResourceServiceImpl.class);
}
