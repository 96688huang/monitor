package com.twogotrade.monitor.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twogotrade.common.support.ServiceSupport;

public class ServiceExceptionHandler implements ServiceSupport.ExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ServiceExceptionHandler.class);

	public void onStartException(ServiceSupport service, Exception e) {
		logger.error(service.getName() + " failed to start, SYSTEM EXITING...");
		System.exit(-1);
	}

	public void onStopException(ServiceSupport service, Exception e) {
		logger.error(service.getName() + " failed to stop, SYSTEM EXITING...");
		System.exit(-1);
	}

}
