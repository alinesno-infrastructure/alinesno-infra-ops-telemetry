package com.alinesno.infra.ops.telemetry.service;


import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.telemetry.entity.Trace;

import java.util.List;

/**
 * 表示 trace 的 Service接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
public interface ITraceService extends IBaseService<Trace> {

    /**
     * 保存链路跟踪信息
     * @param logList
     */
    void saveTrace(List<String> logList);
}
