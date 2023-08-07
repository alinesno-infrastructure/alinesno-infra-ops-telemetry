package com.alinesno.infra.ops.telemetory.mapper;

import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import com.alinesno.infra.ops.telemetory.entity.MetricsHistogram;
import org.apache.ibatis.annotations.Mapper;

/**
 * 直方图信息 Mapper接口
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Mapper
public interface MetricsHistogramMapper extends IBaseMapper<MetricsHistogram> {
}
