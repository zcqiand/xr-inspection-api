package com.nanrong.inspection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zcitc.nanrong.controller", "com.nanrong.inspection.service",
		"com.nanrong.inspection.repository", "com.nanrong.inspection.model"})
public class InspectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(InspectionApplication.class, args);
	}

}
