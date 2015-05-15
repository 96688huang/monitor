package com.twogotrade.monitor.service;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twogotrade.monitor.app.MonitorConfig;
import com.twogotrade.monitor.util.JsoupUtil;

@Component
public class DmvcdMonitorService {

	@Autowired
	private MonitorEmailService monitorEmailService;

	private Logger logger = LoggerFactory.getLogger(DmvcdMonitorService.class);
	private Executor thread = Executors.newSingleThreadExecutor();
	private Date today;
	private int totalRestartTime;
	private long emailLastSendTime;

	public void start() {
		Runnable monitorTask = new Runnable() {
			@Override
			public void run() {
				today = new Date();

				while (true) {
					try {
						Document doc = JsoupUtil.connect(MonitorConfig.DMVCD_DOMAIN, 5000, 3, 3000, "Dmvcd Monitor");
						String title = doc == null ? "" : doc.title();
						if (doc == null || title.startsWith("404") || title.startsWith("500")) {
							totalRestartTime = DateUtils.isSameDay(today, new Date()) ? totalRestartTime : 0;
							totalRestartTime++;

							logger.info("dmvcd request error, title = {}, execute stop command : {}", title,
									MonitorConfig.DMVCD_STOP_CMD);
							executeCmd(MonitorConfig.DMVCD_STOP_CMD);
							Thread.sleep(3000);

							logger.info("execute start command : {}", MonitorConfig.DMVCD_START_CMD);
							executeCmd(MonitorConfig.DMVCD_START_CMD);
							Thread.sleep(3000);

							// check again
							try {
								doc = JsoupUtil.connect(MonitorConfig.DMVCD_DOMAIN, 5000, 3, 3000, "Dmvcd Monitor");
							} catch (Exception e) {
								doc = null;
							}
							title = doc == null ? "" : doc.title();
							String emailSubject = "";
							String emailMsg = "";
							if (doc == null || title.startsWith("404") || title.startsWith("500")) {
								emailSubject = "Dmvcd restart Fail";
								emailMsg = "Dmvcd restart FAIL, please check. today totalRestartTime = " + totalRestartTime;
								logger.error("restart dmvcd fail.");
							} else {
								emailSubject = "Dmvcd restart Success";
								emailMsg = "Dmvcd restart SUCC. today totalRestartTime = " + totalRestartTime;
								logger.info("restart dmvcd success.");
							}

							// 超过指定的时间, 才发送email
							if (System.currentTimeMillis() - emailLastSendTime >= MonitorConfig.EMAIL_SEND_INTERVAL) {
								monitorEmailService.sendEmail(emailSubject, emailMsg);
								emailLastSendTime = System.currentTimeMillis();
							}
						} else {
							doc = null;
							logger.info("dmvcd is health.");
						}
					} catch (Exception e) {
						logger.error("dmvcd monitor running fail.", e);
					}

					System.gc(); // release
					sleepQuiet();
				}
			}

			private void sleepQuiet() {
				try {
					Thread.sleep(MonitorConfig.DMVCD_CHECK_INTERVAL);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		};
		thread.execute(monitorTask);
	}

	/**
	 * 当命令中有空格时, 建议按空格组装成数组, 再执行命令.
	 * @param command
	 */
	private void executeCmd(String command) {
		try {
			Process process = Runtime.getRuntime().exec(StringUtils.splitByWholeSeparator(command, " "));
			process.waitFor();
		} catch (Exception e) {
			logger.error("execute command \"" + command + "\" fail.");
		}
	}
}
