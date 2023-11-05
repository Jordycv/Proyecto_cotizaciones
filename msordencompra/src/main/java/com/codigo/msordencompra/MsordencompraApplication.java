package com.codigo.msordencompra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsordencompraApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsordencompraApplication.class, args);
	}

}
