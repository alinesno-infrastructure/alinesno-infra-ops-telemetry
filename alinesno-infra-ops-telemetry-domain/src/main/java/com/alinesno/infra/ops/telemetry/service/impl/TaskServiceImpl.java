package com.alinesno.infra.ops.telemetry.service.impl;

import com.alinesno.infra.ops.telemetry.entity.TaskEntity;
import com.alinesno.infra.ops.telemetry.mapper.TaskMapper;
import com.alinesno.infra.ops.telemetry.service.ITaskService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * TaskEntity的Service实现类。
 * 继承自IBaseServiceImpl类并实现ITaskService接口。
 *
 * @author luoxiaodong
 * @since 1.0.0
 */
@Service
public class TaskServiceImpl extends IBaseServiceImpl<TaskEntity, TaskMapper> implements ITaskService {

	// 日志记录
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

}
