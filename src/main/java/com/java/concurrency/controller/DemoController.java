package com.java.concurrency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.concurrency.entity.Response;
import com.java.concurrency.requesthandler.RequestHandlerImpl;



@RestController
public class DemoController {
	
	@Autowired private RequestHandlerImpl requestHandler;
	
	@GetMapping("/demo")
	public String demo() {
		return "OK";
	}
	
	@PostMapping("/test")
	public String test(@RequestBody Response res) {
		System.out.println("Request Received =======>" +res);
		requestHandler.addToQueue(res);
		System.out.println("Exiting...");
		return "OK";
	}

}
