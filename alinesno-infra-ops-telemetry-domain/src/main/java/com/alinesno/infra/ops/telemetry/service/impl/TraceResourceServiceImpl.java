package com.alinesno.infra.ops.telemetry.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.TraceResource;
import com.alinesno.infra.ops.telemetry.mapper.TraceResourceMapper;
import com.alinesno.infra.ops.telemetry.service.ITraceResourceService;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 表示 Resource 的 Service业务层处理
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author luoxiaodong
 */
@Service
public class TraceResourceServiceImpl extends IBaseServiceImpl<TraceResource, TraceResourceMapper> implements ITraceResourceService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(TraceResourceServiceImpl.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void saveTrace(List<String> logList) {
        Connection connection = null;
        try {
            connection = sqlSessionTemplate.getConnection();

            // 关闭自动提交
            connection.setAutoCommit(false);

            // 提交事务
            connection.commit();
        } catch (SQLException e) {

            log.error("数据插入异常:{}" , e.getMessage());
            // 出现异常时回滚事务
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                log.error("数据插入回滚异常:{}" , e.getMessage());
            }
            throw new RuntimeException(e);
        }
    }
}
