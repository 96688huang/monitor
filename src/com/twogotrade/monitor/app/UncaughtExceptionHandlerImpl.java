package com.twogotrade.monitor.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 用于处理线程中未捕获的异常
 *
 * @author Scofield
 * @version 1.0
 */
@Component
public class UncaughtExceptionHandlerImpl implements Thread.UncaughtExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(UncaughtExceptionHandlerImpl.class);

	private Thread errorThread;
	private Throwable cause;

	public void uncaughtException(Thread t, Throwable e) {
		errorThread = t;
		cause = e;
		logger.error("Uncaught exception is thrown from thread: " + t.getName(), e);
	}

	public Thread getErrorThread() {
		return errorThread;
	}

	public Throwable getCause() {
		return cause;
	}

}
