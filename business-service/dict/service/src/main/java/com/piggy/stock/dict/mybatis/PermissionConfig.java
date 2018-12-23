package com.piggy.stock.dict.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PermissionConfig {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = new HashMap<>(16);

	/**
	 * 属性文件加载对象
	 */
	// private static PropertiesLoader loader = new
	// PropertiesLoader("permission.properties");

	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		// if (loader == null) {
		// logger.info("缺失配置文件 - permission.properties");
		// return null;
		// }
		// String value = map.get(key);
		// if (value == null) {
		// value = loader.getProperty(key);
		// map.put(key, value != null ? value : StringUtils.EMPTY);
		// }
		return null;
	}

}
