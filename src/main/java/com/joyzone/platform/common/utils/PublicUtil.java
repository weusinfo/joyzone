package com.joyzone.platform.common.utils;

import java.util.Collection;
import java.util.Map;

public class PublicUtil {
	
	private static byte[] lock = new byte[0];
	
	private final static long position = 10000000;

	public static boolean isEmpty(Object pObj) {
		if (pObj == null) {
			return true;
		}
		if (pObj == "") {
			return true;
		}
		if (pObj instanceof String) {
			return ((String) pObj).length() == 0;
		} else if (pObj instanceof Collection) {
			return ((Collection) pObj).isEmpty();
		} else if (pObj instanceof Map) {
			return ((Map) pObj).size() == 0;
		}
		return false;
	}

	public static boolean isNotEmpty(Object pObj) {
		if (pObj == null) {
			return false;
		}
		if (pObj == "") {
			return false;
		}
		if (pObj instanceof String) {
			return ((String) pObj).trim().length() != 0;
		} else if (pObj instanceof Collection) {
			return !((Collection) pObj).isEmpty();
		} else if (pObj instanceof Map) {
			return ((Map) pObj).size() != 0;
		}
		return true;
	}
	
	public static String generateID(Long id) {
		long r = (long) ((Math.random() + 1) * position);
		String defValue = String.valueOf(r);
		return defValue.substring(0, defValue.length() - id.toString().length()) + id.toString();
	}

}
