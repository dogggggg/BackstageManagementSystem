package com.hab.bms.sys.utils;

public class CollectionUtil {

	/**
	 * 数组是否为空
	 * 
	 * @param array
	 *            数组
	 * @return 是否为空
	 */
	public static <T> boolean isEmpty(T[] array) {
		return array == null || array.length <= 0;
	}

}
