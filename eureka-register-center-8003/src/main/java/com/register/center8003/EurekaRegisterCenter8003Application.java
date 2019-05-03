package com.register.center8003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaRegisterCenter8003Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaRegisterCenter8003Application.class, args);
	}

}
