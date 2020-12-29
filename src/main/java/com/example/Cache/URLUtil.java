package com.example.Cache;

public abstract class URLUtil {

	public static final String maskURL(String url) {
		String[] splits = url.split("/");
		StringBuilder sb = new StringBuilder();
		sb.append("/");
		for (int i = 0; i < splits.length; i++) {
			if (isInteger(splits[i])) {
				sb.append("{id}");
			} else {
				sb.append(splits[i]);
			}
			sb.append("/");
		}
		return sb.toString();
	}

	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

}
