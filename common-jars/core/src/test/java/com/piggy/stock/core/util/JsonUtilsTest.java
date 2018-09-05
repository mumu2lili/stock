package com.piggy.stock.core.util;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class JsonUtilsTest {

	@Test
	public void testReadValue() {
		String s = "{\"a\":1, \"b\":\"2\"}";
		Map<String, Object> map = JsonUtils.readValue(s);

		Assert.assertEquals(map.get("a"), 1);
		Assert.assertEquals(map.get("b"), "2");
	}

}
