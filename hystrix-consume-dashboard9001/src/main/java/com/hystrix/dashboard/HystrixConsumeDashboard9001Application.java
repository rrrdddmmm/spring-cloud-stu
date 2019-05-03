package com.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixConsumeDashboard9001Application {

	public static void main(String[] args) {
		SpringApplication.run(HystrixConsumeDashboard9001Application.class, args);
	}

}
