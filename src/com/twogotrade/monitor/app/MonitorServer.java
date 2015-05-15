package com.twogotrade.monitor.app;

import org.apache.commons.lang.SystemUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.twogotrade.common.support.ServiceSupport;

public class MonitorServer {

	// init log4j
	static {
		DOMConfigurator.configure(SystemUtils.USER_DIR + "/conf/log4j.xml");
	}

	/** 使用的spring配置文件列表 */
	private static final String[] CONFIG_FILES = new String[] { "conf/application_context.xml" };

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext(CONFIG_FILES);
		setUncaughtExceptionHandler(context);

		ServiceSupport[] services = new ServiceSupport[] { (MonitorService) context.getBean("monitorService") };

		ServiceExceptionHandler exceptionHandler = new ServiceExceptionHandler();

		for (ServiceSupport service : services) {
			service.setExceptionHandler(exceptionHandler);
			service.start();
		}
	}

	private static void setUncaughtExceptionHandler(ApplicationContext context) {
		UncaughtExceptionHandlerImpl handler = (UncaughtExceptionHandlerImpl) context
				.getBean("uncaughtExceptionHandlerImpl");
		Thread.setDefaultUncaughtExceptionHandler(handler);
	}

}
