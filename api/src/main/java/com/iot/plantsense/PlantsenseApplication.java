package com.iot.plantsense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PlantsenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantsenseApplication.class, args);
	}

}
