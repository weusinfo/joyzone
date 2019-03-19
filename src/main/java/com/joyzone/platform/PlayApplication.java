package com.joyzone.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages= {"com.joyzone.platform.config.datasource"})
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan(basePackages={"com.joyzone.platform.mapper"})
@EnableTransactionManagement
public class PlayApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PlayApplication.class, args);
	}

}
