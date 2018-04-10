package com.cooksys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableSpringDataWebSupport
@SpringBootApplication
public class EntityIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntityIntegrationApplication.class, args);
	}
}
