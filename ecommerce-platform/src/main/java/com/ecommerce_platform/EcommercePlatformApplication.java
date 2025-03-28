package com.ecommerce_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.ecommerce_platform")

public class EcommercePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommercePlatformApplication.class, args);
	}

}
