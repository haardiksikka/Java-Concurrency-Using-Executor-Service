package com.java.concurrency.service;

import org.springframework.stereotype.Service;

import com.java.concurrency.entity.Response;

@Service	
public class SampleSleepService {
	
	public void sleepService(Response res) throws InterruptedException {
		System.out.println("Sleep service called");
		//do processing
		Thread.sleep(100000);
		System.out.println("Servicing of Object completed ======>"+ res);
	}
}
