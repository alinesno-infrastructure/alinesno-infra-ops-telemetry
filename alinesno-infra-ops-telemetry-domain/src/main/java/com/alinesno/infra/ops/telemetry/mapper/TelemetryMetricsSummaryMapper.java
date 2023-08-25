package com.alinesno.infra.ops.telemetry.mapper;

import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import com.alinesno.infra.ops.telemetry.entity.TelemetryMetricsSummary;
import org.apache.ibatis.annotations.Mapper;

/**
 * 表示 TelemetryMetricsSummary 的 Mapper 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Mapper
public interface TelemetryMetricsSummaryMapper extends IBaseMapper<TelemetryMetricsSummary> {
}
