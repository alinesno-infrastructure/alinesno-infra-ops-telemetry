package com.alinesno.infra.ops.telemetry.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.telemetry.entity.MetricsResource;
import com.alinesno.infra.ops.telemetry.mapper.MetricsResourceMapper;
import com.alinesno.infra.ops.telemetry.service.IMetricsResourceService;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 资源 Service业务层处理
 *
 * @author luoxiaodong
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class MetricsResourceServiceImpl extends IBaseServiceImpl<MetricsResource, MetricsResourceMapper> implements IMetricsResourceService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(MetricsResourceServiceImpl.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void saveMetrics(List<String> logList) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("连接 ClickHouse 数据库成功！！！！！");

            // 关闭自动提交
            connection.setAutoCommit(false);


            // 提交事务
            connection.commit();
            System.out.println("数据插入成功！！！！！");
        } catch (SQLException e) {
            log.error("数据插入异常: {}", e.getMessage());

            // 出现异常时回滚事务
            try {
                if (connection != null) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("数据插入回滚异常: {}", ex.getMessage());
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("关闭数据库连接异常: {}", e.getMessage());
            }
        }
    }

}
