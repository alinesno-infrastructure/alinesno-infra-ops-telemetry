package com.alinesno.infra.ops.telemetry.mapper;

import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import com.alinesno.infra.ops.telemetry.entity.MetricsGauge;
import org.apache.ibatis.annotations.Mapper;

/**
 * 测量信息 Mapper接口
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Mapper
public interface MetricsGaugeMapper extends IBaseMapper<MetricsGauge> {
}
