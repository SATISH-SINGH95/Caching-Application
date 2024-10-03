package com.chaching;

import org.springframework.boot.SpringApplication;
import org.slf4j.MDC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy
@Slf4j
public class CachingApplication {

	public static void main(String[] args) {
		MDC.put("pid", String.valueOf(ProcessHandle.current().pid()));
		SpringApplication.run(CachingApplication.class, args);
	}

}
