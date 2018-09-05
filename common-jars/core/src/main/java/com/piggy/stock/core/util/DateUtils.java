package com.piggy.stock.core.util;

import java.text.ParseException;
import java.util.Date;

public class DateUtils {

	public static Date parseDate(String str, String[] formats) {
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(str, formats);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
