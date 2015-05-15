package com.twogotrade.monitor.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

public class Base64Util {
	public static String encode(String msg) {
		try {
			if (StringUtils.isBlank(msg)) {
				return null;
			}
			return Base64.encodeBase64String(msg.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static String decode(String base64String) throws UnsupportedEncodingException {
		if (StringUtils.isBlank(base64String)) {
			return null;
		}
		return new String(Base64.decodeBase64(base64String), "UTF-8");
	}

	public static String decode(byte[] base64Bytes) throws UnsupportedEncodingException {
		return new String(Base64.decodeBase64(base64Bytes), "UTF-8");
	}
}
