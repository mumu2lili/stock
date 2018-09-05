package com.piggy.stock.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 返回 map等不确定类型的对象，才可以用。
	 *
	 * @param s
	 * @return
	 */
	public static <T> T readValue(String s) {
		try {
			return mapper.readValue(s, new TypeReference<T>() {
			});

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static <T> T readValue(String s, Class<T> valueType) {
		try {
			return mapper.readValue(s, valueType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
