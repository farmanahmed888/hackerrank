package com.hackerrank.interviewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class InterviewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewerApplication.class, args);
	}

}
