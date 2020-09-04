package com.java.concurrency.requesthandler;

import com.java.concurrency.entity.Response;

public interface RequestHandler {

	void init();
	void addToQueue(Response res);
	Response dequeue();
	void destroy();
}
