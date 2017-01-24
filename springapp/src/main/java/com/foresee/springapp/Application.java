package com.foresee.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample Spring Boot application - To be deployed as Docker container.
 * 
 * @author abdel.dridi
 *
 */
@SpringBootApplication
@RestController
public class Application {

	@RequestMapping("/")
	public String home() {
		return "Hello from Mesos Docker-SpringBoot World!";
	}

	@RequestMapping("/health")
	public String health() {
		return "200 OK!";
	}

	@RequestMapping("/ping")
	public String ping() {
		return "pong!";
	}

	@RequestMapping("/error")
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "SERVICE Not found")
	public String error() {
		return "40X Error!";
	}

	@RequestMapping("/exception")
	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "SERVICE UNAVAILABLE")
	public String exception() {
		return "50X Error!";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
