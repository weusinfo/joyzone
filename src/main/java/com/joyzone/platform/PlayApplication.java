package com.joyzone.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class},scanBasePackages="com.joyzone.platform")
@MapperScan(basePackages={"com.joyzone.platform.core.mapper"})
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
public class PlayApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PlayApplication.class, args);
	}
}
