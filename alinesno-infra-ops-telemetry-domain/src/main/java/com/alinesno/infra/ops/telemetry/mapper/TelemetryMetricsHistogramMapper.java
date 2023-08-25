package com.alinesno.infra.ops.telemetry.mapper;

import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import com.alinesno.infra.ops.telemetry.entity.TelemetryMetricsHistogram;
import org.apache.ibatis.annotations.Mapper;

/**
 * 表示 TelemetryMetricsHistogram 的 Mapper 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Mapper
public interface TelemetryMetricsHistogramMapper extends IBaseMapper<TelemetryMetricsHistogram> {
}
