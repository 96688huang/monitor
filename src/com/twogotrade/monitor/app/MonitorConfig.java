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

	// email server config
	public static String EMAIL_SERVER_HOST = config.getString("email", "email_server_host");
	public static String EMAIL_AUTH_USER = config.getString("email", "email_auth_user");
	public static String EMAIL_AUTH_PWD = config.getString("email", "email_auth_pwd");
	public static String EMAIL_TO = config.getString("email", "email_to");
	public static String EMAIL_FROM = config.getString("email", "email_from");
	public static long EMAIL_SEND_INTERVAL = config.getInt("email", "email_send_interval") * 60 * 1000;
}
