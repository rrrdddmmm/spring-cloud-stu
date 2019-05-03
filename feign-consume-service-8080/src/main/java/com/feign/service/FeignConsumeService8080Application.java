package com.feign.service;

import com.feign.rules.CustomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient // 注册服务发现
@RibbonClient(name="DICTSERVICE-PROVIDER-USER", configuration = CustomRule.class)
public class FeignConsumeService8080Application {

	public static void main(String[] args) {
		SpringApplication.run(FeignConsumeService8080Application.class, args);
	}

}
