package com.twogotrade.monitor.service;

import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.twogotrade.monitor.app.MonitorConfig;

@Component
public class MonitorEmailService {

	private static final Logger logger = LoggerFactory.getLogger(MonitorEmailService.class);

	public void sendEmail(String emailSubject, String emailMsg) {
		try {
			SimpleEmail email = new SimpleEmail();
			email.setHostName(MonitorConfig.EMAIL_SERVER_HOST);
			email.setAuthentication(MonitorConfig.EMAIL_AUTH_USER, MonitorConfig.EMAIL_AUTH_PWD);
			email.setCharset("UTF-8");
			email.addTo(MonitorConfig.EMAIL_TO);
			email.setFrom(MonitorConfig.EMAIL_FROM);
			email.setSubject(emailSubject);
			email.setMsg(emailMsg);
			email.send();
			logger.info("send email success. emailSubject={}, emailMsg={}", emailSubject, emailMsg);
		} catch (Exception e) {
			logger.error("send email fail. emailSubject=" + emailSubject + ", emailMsg=" + emailMsg, e);
		}
	}

	/**
	 * for testing
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.163.com");
		email.setAuthentication("96688huang@163.com", "jaeycyaafafqmykh");
		email.setCharset("UTF-8");
		email.addTo("460256497@qq.com");
		email.setFrom("96688huang@163.com");
		email.setSubject("for testing");
		email.setMsg("just for testing");
		email.send();
		System.out.println("done");
	}
}
