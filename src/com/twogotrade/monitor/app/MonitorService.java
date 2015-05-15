package com.twogotrade.monitor.app;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twogotrade.common.support.ServiceSupport;
import com.twogotrade.monitor.service.DmvcdMonitorService;

@Component
public class MonitorService extends ServiceSupport {

	private static final Logger logger = LoggerFactory.getLogger(MonitorService.class);

	@Autowired
	private DmvcdMonitorService dmvcdMonitorService;

	@Override
	protected void _start() throws Exception {
		startService();
	}

	public void startService() throws IOException {
		dmvcdMonitorService.start();
	}

	@Override
	protected void _stop() throws Exception {
		stopService();
	}

	public void stopService() {
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public String getName() {
		return "Monitor";
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
