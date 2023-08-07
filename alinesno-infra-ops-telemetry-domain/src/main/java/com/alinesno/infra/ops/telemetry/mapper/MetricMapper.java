package com.alinesno.infra.ops.telemetry.mapper;

import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import com.alinesno.infra.ops.telemetry.entity.MetricBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * 指标 Mapper接口
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Mapper
public interface MetricMapper extends IBaseMapper<MetricBean> {
}
