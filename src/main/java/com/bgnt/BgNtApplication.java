package com.bgnt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bgnt"})
@EnableTransactionManagement
@MapperScan("com.bgnt")
public class BgNtApplication {

	public static void main(String[] args) {
	    // 开启热启动
        System.setProperty("spring.devtools.restart.enabled", "true");
	    SpringApplication.run(BgNtApplication.class, args);
	}
}
