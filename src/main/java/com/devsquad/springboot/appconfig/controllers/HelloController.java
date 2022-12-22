package com.devsquad.springboot.appconfig.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

@RestController
public class HelloController {
	@Value("${hello-controller.message}")
	private String message;

	@Value("${hello-controller.another-message}")
	private String anotherMessage;

	@Autowired
	public HelloController() {
	}

	@GetMapping(value = "/hello", produces = "application/json")
	public ResponseEntity<String> getHelloMessage() {
		return ResponseEntity.ok().body(String.format("{\"hello\":\"%s - %s\"}", message, anotherMessage));
	}
}
