package com.joyzone.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages= {"com.joyzone.platform.config.datasource"})
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan(basePackages={"com.joyzone.platform.mapper"})
@EnableTransactionManagement
@EnableScheduling
public class PlayApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(PlayApplication.class).bannerMode(Mode.OFF).run(args);
	}

}
