package com.commroid.util;

public class StringUtil {

	/**
	 * 检测字符串是否为空
	 * 
	 * @param String
	 * @return if String==null return true
	 */
	public static boolean isNull(String s) {
		if (s == null) {
			return true;
		}
		return false;
	}

	/**
	 * 检测字符串长度是否满足
	 * 
	 * @param String
	 * @size 需要的长度
	 * @return
	 */
	public static boolean checkLength(String s, int size) {
		if (s == null||s.length() < size) {
			return false;
		}
		return true;
	}

}
