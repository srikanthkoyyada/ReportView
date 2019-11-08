package com.report.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DtjReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(DtjReportApplication.class, args);
	}

}
