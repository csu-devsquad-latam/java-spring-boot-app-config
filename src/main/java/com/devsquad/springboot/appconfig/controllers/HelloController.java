package com.devsquad.springboot.appconfig.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class HelloController {

	@Autowired
	public HelloController() {
	}

	@GetMapping(value = "/hello", produces = "application/json")
	public ResponseEntity<String> getHelloMessage() {
		return ResponseEntity.ok().body("{\"hello\":\"world\"}");
	}
}
