package com.alinesno.infra.ops.telemetry.mapper;

import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import com.alinesno.infra.ops.telemetry.entity.Logs;
import org.apache.ibatis.annotations.Mapper;

/**
 * 表示 Logs 的 Mapper 接口
 *
 * @version 1.0.0
 * @author luoxiaodng
 */
@Mapper
public interface LogsMapper extends IBaseMapper<Logs> {
}
