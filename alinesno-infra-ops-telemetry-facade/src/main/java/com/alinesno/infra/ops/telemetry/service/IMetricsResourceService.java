package com.alinesno.infra.ops.telemetry.service;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.telemetry.entity.MetricsResource;

import java.util.List;

/**
 * 资源 Service接口
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
public interface IMetricsResourceService extends IBaseService<MetricsResource> {

    /**
     * 保存监控信息
     * @param logList
     */
    void saveMetrics(List<String> logList);

}
