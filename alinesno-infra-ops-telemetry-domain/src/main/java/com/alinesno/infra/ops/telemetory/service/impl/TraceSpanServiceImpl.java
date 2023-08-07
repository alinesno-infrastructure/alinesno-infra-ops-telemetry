package com.alinesno.infra.ops.telemetory.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetory.entity.TraceSpan;
import com.alinesno.infra.ops.telemetory.mapper.TraceSpanMapper;
import com.alinesno.infra.ops.telemetory.service.ITraceSpanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 表示 TraceSpan 的 Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class TraceSpanServiceImpl extends IBaseServiceImpl<TraceSpan, TraceSpanMapper> implements ITraceSpanService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(TraceSpanServiceImpl.class);
}
