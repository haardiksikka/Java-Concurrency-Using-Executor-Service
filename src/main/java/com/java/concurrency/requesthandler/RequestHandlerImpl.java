package com.java.concurrency.requesthandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.concurrency.entity.Response;
import com.java.concurrency.service.SampleSleepService;

@Service
public class RequestHandlerImpl implements RequestHandler {
	
	private BlockingQueue<Response> queue = new LinkedBlockingQueue<>();
	private boolean destroyFlag;
	private ExecutorService executorService;
	//private Stream<Response> stream;
	//private Integer counter = 0;
	//private Integer poolCount;
	@Autowired private SampleSleepService sleepService;
	
	@Override
	@PostConstruct
	public void init() {
		executorService = Executors.newFixedThreadPool(1);
		executorService.submit(()->{
			while(true) {
			System.out.println("inside executor");
			Response res = dequeue();
			System.out.println("Object ----> "+ res);
			sleepService.sleepService(res);
			}
		});
	}
	
	@Override
	public Response dequeue() {
		System.out.println("DEQ called");
		try {
			return queue.take();
		}
		catch(Exception e) {
		
			return null;
		}
	}
	
	@Override
	public void addToQueue(Response res) {
		if(!destroyFlag) {
			this.queue.add(res);
		}
		else {
			System.out.println("Shutdown!!");
		}
	}
	
	@Override
	public void destroy() {
		System.out.println("shutting down");
		destroyFlag = false;
		executorService.shutdownNow();
		//also save the queue in db
	}
	
}
