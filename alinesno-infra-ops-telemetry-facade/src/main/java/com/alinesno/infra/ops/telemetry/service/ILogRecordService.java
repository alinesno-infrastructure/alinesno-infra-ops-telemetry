package com.alinesno.infra.ops.telemetry.service;


import com.alinesno.infra.common.facade.services.IBaseService;
import com.alinesno.infra.ops.telemetry.entity.LogRecord;

import java.util.List;

/**
 * 日志记录Service接口
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
public interface ILogRecordService extends IBaseService<LogRecord> {

    /**
     * 保存日志信息
     * @param logList
     */
    void saveLog(List<String> logList);

}
