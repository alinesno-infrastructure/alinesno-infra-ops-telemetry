package com.alinesno.infra.ops.telemetry.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.LogAttribute;
import com.alinesno.infra.ops.telemetry.mapper.LogAttributeMapper;
import com.alinesno.infra.ops.telemetry.service.ILogAttributeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class LogAttributeServiceImpl extends IBaseServiceImpl<LogAttribute, LogAttributeMapper> implements ILogAttributeService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(LogAttributeServiceImpl.class);
}
