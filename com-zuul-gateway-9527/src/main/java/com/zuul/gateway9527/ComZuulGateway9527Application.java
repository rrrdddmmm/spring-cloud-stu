package com.zuul.gateway9527;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy // 启动zuul相关的代理作用
public class ComZuulGateway9527Application {
	public static void main(String[] args) {
		SpringApplication.run(ComZuulGateway9527Application.class, args);
	}
}
