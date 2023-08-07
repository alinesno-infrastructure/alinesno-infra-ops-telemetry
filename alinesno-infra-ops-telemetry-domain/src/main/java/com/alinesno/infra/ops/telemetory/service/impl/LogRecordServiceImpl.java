package com.alinesno.infra.ops.telemetory.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetory.entity.LogRecord;
import com.alinesno.infra.ops.telemetory.mapper.LogRecordMapper;
import com.alinesno.infra.ops.telemetory.service.ILogRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 日志记录Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class LogRecordServiceImpl extends IBaseServiceImpl<LogRecord, LogRecordMapper> implements ILogRecordService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(LogRecordServiceImpl.class);
}
