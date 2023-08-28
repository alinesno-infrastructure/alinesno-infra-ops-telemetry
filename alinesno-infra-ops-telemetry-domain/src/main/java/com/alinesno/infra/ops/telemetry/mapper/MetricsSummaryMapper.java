package com.alinesno.infra.ops.telemetry.mapper;

import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import com.alinesno.infra.ops.telemetry.entity.MetricsSummary;
import org.apache.ibatis.annotations.Mapper;

/**
 * 表示 MetricsSummary 的 Mapper 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Mapper
public interface MetricsSummaryMapper extends IBaseMapper<MetricsSummary> {
}
