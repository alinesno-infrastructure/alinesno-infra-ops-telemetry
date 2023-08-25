package com.alinesno.infra.ops.telemetry.mapper;

import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import com.alinesno.infra.ops.telemetry.entity.TelemetryMetricsSum;
import org.apache.ibatis.annotations.Mapper;

/**
 * 表示 TelemetryMetricsSum 的 Mapper 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Mapper
public interface TelemetryMetricsSumMapper extends IBaseMapper<TelemetryMetricsSum> {
}
