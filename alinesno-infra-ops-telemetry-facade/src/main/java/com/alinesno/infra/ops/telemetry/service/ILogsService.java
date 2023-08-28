package com.alinesno.infra.ops.telemetry.service;

import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.telemetry.entity.Logs;

import java.util.List;

/**
 * 表示 Logs 的 Service 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
public interface ILogsService extends IBaseService<Logs> {

    /**
     * 保存日志信息
     * @param logList 日志列表
     */
    void saveLogs(List<String> logList);
}
