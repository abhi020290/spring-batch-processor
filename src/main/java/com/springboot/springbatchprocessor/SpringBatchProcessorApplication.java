package com.springboot.springbatchprocessor;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.springboot.springbatchprocessor.entity"})
public class SpringBatchProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchProcessorApplication.class, args);
	}

}
