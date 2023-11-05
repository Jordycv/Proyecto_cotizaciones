package com.codigo.msrespuestacot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsrespuestacotApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsrespuestacotApplication.class, args);
	}

}
