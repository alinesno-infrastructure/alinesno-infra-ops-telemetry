package com.alinesno.infra.ops.telemetry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 集成一个Java开发示例工具
 * @author LuoAnDong
 * @since 2023年8月3日 上午6:23:43
 */
@EnableAsync
@EnableScheduling
@MapperScan("com.alinesno.infra.ops.telemetry.mapper")
@SpringBootApplication
public class OpsTelemetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpsTelemetryApplication.class, args);
	}

}