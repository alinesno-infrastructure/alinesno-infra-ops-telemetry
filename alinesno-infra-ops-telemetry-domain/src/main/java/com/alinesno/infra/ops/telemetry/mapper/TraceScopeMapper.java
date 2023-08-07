package com.alinesno.infra.ops.telemetry.mapper;

import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import com.alinesno.infra.ops.telemetry.entity.TraceScope;
import org.apache.ibatis.annotations.Mapper;

/**
 * 表示 TraceScope 的 Mapper接口
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Mapper
public interface TraceScopeMapper extends IBaseMapper<TraceScope> {
}
