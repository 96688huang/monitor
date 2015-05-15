package com.twogotrade.monitor.service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.twogotrade.monitor.app.JsoupUtil;
import com.twogotrade.monitor.app.MonitorConfig;

@Component
public class DmvcdMonitorService {

	private Logger logger = LoggerFactory.getLogger(DmvcdMonitorService.class);
	private Executor thread = Executors.newSingleThreadExecutor();

	public void start() {
		Runnable monitorTask = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Document doc = JsoupUtil.connect(MonitorConfig.DMVCD_DOMAIN, 5000, 3, 3000, "Dmvcd Monitor");
						String title = doc == null ? "" : doc.title();
						if (doc == null || title.startsWith("404") || title.startsWith("500")) {
							logger.info("dmvcd request error, title = {}, start to execute stop command...", title);
							Process process = Runtime.getRuntime().exec(MonitorConfig.DMVCD_STOP_CMD);
							process.waitFor();
							Thread.sleep(3000);

							logger.info("start to execute start command...");
							process = Runtime.getRuntime().exec(MonitorConfig.DMVCD_START_CMD);
							process.waitFor();

							Thread.sleep(3000);
						} else {
							doc = null;
							logger.info("dmvcd is health.");
						}
					} catch (Exception e) {
						logger.error("dmvcd monitor running fail.", e);
					}

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
}
