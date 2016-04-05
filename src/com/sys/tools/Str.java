package com.sys.tools;

public final class Str {
	
	/**
	 * 		判断字符串是否为null
	 * @param str 要进行判断的字符串
	 * @return Boolean 字符串为 null 时返回 true
	 */
	public static Boolean is_null(String str) {
		return str == null;
	}

	/**
	 * 		判断字符串是否为空
	 * @描述 当字符串为 null 时, 此方法返回 false, 只有当字符串为空串 [""] 时, 才返回 true
	 * 
	 * @param str 要进行判断的字符串
	 * @return Boolean 
	 */
	public static Boolean is_empty(String str) {
		return str != null && str.length() <= 0;
	}
	
	/**
	 * 		判断字符串是否无效 [字符串为空或为 null 时, forfreej将视为此字符串无效]
	 * @param str 要进行判断的字符串
	 * @return Boolean 符串为空或为 null 时返回 true
	 */
	public static Boolean is_invalid(String str) {
		return str == null || str.length() <= 0;
	}
	

	/**
	 * 		字符串中单个字符转大写
	 * @param s 待转换字符串
	 * @param index 待转换字符在字符串中的索引位置, 首字符索引为 0
	 * @return 转换完成后的字符串
	 */
	public static String toUpper(String s, int index) {
		char[] arr = s.toCharArray();       
		if (arr[index] >= 'a' && arr[index] <= 'z') {
		    arr[index] -= 32;
		}
		return new String(arr);
	}
}