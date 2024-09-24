package com.y.gui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RedissonAutoConfiguration.class})
@EnableAsync
@MapperScan("com.y.gui.dao")
@SpringBootApplication
public class GuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuiApplication.class, args);
	}

}
