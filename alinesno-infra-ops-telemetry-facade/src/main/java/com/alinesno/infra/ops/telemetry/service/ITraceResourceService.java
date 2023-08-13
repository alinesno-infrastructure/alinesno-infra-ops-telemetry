package com.alinesno.infra.ops.telemetry.service;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.telemetry.entity.TraceResource;

import java.util.List;

/**
 * 表示 Resource 的 Service接口
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
public interface ITraceResourceService extends IBaseService<TraceResource> {

    /**
     * 保存链路跟踪信息
     * @param logList
     */
    void saveTrace(List<String> logList);

}
