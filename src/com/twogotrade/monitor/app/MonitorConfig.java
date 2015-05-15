package com.twogotrade.monitor.app;

import org.apache.commons.lang.SystemUtils;

import com.twogotrade.common.utils.IniReader;

public class MonitorConfig {

	private static IniReader config = new IniReader(SystemUtils.USER_DIR + "/conf/monitor.ini");
	public static String DMVCD_STOP_CMD = config.getString("dmvcd", "dmvcd_stop_cmd");
	public static String[] DMVCD_STOP_CMD_ITEMS = DMVCD_STOP_CMD.split(" ");

	public static String DMVCD_START_CMD = config.getString("dmvcd", "dmvcd_start_cmd");
	public static String[] DMVCD_START_CMD_ITEMS = DMVCD_START_CMD.split(" ");

	public static String DMVCD_DOMAIN = config.getString("dmvcd", "dmvcd_domain");
	public static long DMVCD_CHECK_INTERVAL = config.getInt("dmvcd", "dmvcd_check_interval");
}
