package com.hab.bms.sys.common.config;

import java.util.HashMap;
import java.util.Map;

import com.hab.bms.sys.utils.PropertiesLoader;
import com.hab.bms.sys.utils.StringUtils;

public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = new HashMap<>();

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("bms.global.properties");

	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}

	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = loader.getProperty(key);
			map.put(key, StringUtils.trimToEmpty(value));
		}
		return value;
	}

}
