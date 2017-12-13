package com.prav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
//Property source can be added here and application.properties can be placesd in jboss config directory
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}