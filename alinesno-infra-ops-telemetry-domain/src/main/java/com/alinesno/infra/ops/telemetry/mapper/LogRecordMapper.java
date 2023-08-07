package com.alinesno.infra.ops.telemetry.mapper;

import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import com.alinesno.infra.ops.telemetry.entity.LogRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志记录Mapper接口
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Mapper
public interface LogRecordMapper extends IBaseMapper<LogRecord> {
}
