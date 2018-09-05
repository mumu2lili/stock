package com.piggy.stock.zuul.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * 防脚本注入攻击包装。在获取请求参数时过滤掉或者转换掉特殊字符。
 * 
 * @author jiangzhongxiang
 * @version 1.0, 2018-1-19
 * @since 1.0, 2018-1-19
 */
public class InjectionAttackWrapper extends HttpServletRequestWrapper {

	boolean filterXSS = true;
	boolean filterSQL = true;

	public InjectionAttackWrapper(HttpServletRequest request, boolean filterXSS, boolean filterSQL) {

		super(request);
		this.filterXSS = filterXSS;
		this.filterSQL = filterSQL;
	}

	public InjectionAttackWrapper(HttpServletRequest request) {

		this(request, true, true);
	}

	@Override
	public String getHeader(String name) {

		String value = super.getHeader(name);
		return filterParamString(value);

	}

	@Override
	public String getParameter(String name) {

		String value = super.getParameter(name);
		return filterParamString(value);
	}

	@Override
	public Map<String, String[]> getParameterMap() {

		Map<String, String[]> rawMap = super.getParameterMap();
		Map<String, String[]> filteredMap = new HashMap<String, String[]>(rawMap.size());
		Set<String> keys = rawMap.keySet();
		for (String key : keys) {
			String[] rawValue = rawMap.get(key);
			String[] filteredValue = filterStringArray(rawValue);
			filteredMap.put(key, filteredValue);
		}
		return filteredMap;
	}

	@Override
	public String[] getParameterValues(String name) {

		String[] rawValues = super.getParameterValues(name);
		if (rawValues == null) {
			return null;
		}

		return filterStringArray(rawValues);
	}

	@Override
	public Cookie[] getCookies() {

		Cookie[] existingCookies = super.getCookies();
		if (existingCookies != null) {
			for (int i = 0; i < existingCookies.length; ++i) {
				Cookie cookie = existingCookies[i];
				cookie.setValue(filterParamString(cookie.getValue()));
			}
		}
		return existingCookies;
	}

	@Override
	public String getQueryString() {

		String rawValue = super.getQueryString();
		if (rawValue == null) {
			return null;
		}
		String[] arr = rawValue.split("&");
		StringBuilder sb = new StringBuilder();
		for (String str : arr) {
			sb.append(filterParamString(str)).append("&");
		}
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();
	}

	protected String[] filterStringArray(String[] rawValue) {

		String[] filteredArray = new String[rawValue.length];
		for (int i = 0; i < rawValue.length; i++) {
			filteredArray[i] = filterParamString(rawValue[i]);
		}
		return filteredArray;
	}

	/**
	 * 过滤请求参数。进行html,javascript,sql等脚本过滤。
	 * 
	 * @param rawValue
	 *            请求参数
	 * @return 过滤后的参数
	 */
	protected String filterParamString(String rawValue) {

		if (rawValue == null) {
			return null;
		}
		if (filterXSS()) {
			rawValue = filterXss(rawValue);
		}
		if (filterSQL()) {
			rawValue = filterSql(rawValue);
		}
		return rawValue;
	}

	public static String filterXss(String rawValue) {

		rawValue = filterXssDetail(rawValue);// 采用删除的方式过滤
		rawValue = StringEscapeUtils.escapeHtml(rawValue); // 采用转换特殊字符的方式过滤
		return rawValue;
	}

	/**
	 * 采用删除的方式过滤。 根据实际需要增减。
	 * 
	 * @param rawValue
	 * @return
	 */
	private static String filterXssDetail(String rawValue) {

		// 删除 <script>(.*?)</script>
		Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
		rawValue = scriptPattern.matcher(rawValue).replaceAll("");

		// Avoid anything in a src='...' type of e­xpression
		scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		rawValue = scriptPattern.matcher(rawValue).replaceAll("");

		scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		rawValue = scriptPattern.matcher(rawValue).replaceAll("");

		// 删除 </script>
		scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
		rawValue = scriptPattern.matcher(rawValue).replaceAll("");

		// 删除 <script ...>
		scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		rawValue = scriptPattern.matcher(rawValue).replaceAll("");

		// Avoid eval(...) e­xpressions
		scriptPattern = Pattern.compile("eval\\((.*?)\\)",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		rawValue = scriptPattern.matcher(rawValue).replaceAll("");

		// Avoid e­xpression(...) e­xpressions
		scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		rawValue = scriptPattern.matcher(rawValue).replaceAll("");

		// Avoid javascript:... e­xpressions
		scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
		rawValue = scriptPattern.matcher(rawValue).replaceAll("");

		// Avoid alert:... e­xpressions
		scriptPattern = Pattern.compile("alert", Pattern.CASE_INSENSITIVE);
		rawValue = scriptPattern.matcher(rawValue).replaceAll("");

		// Avoid onload= e­xpressions
		scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		rawValue = scriptPattern.matcher(rawValue).replaceAll("");
		scriptPattern = Pattern.compile("vbscript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
		rawValue = scriptPattern.matcher(rawValue).replaceAll("");

		return rawValue;
	}

	public static String filterSql(String rawValue) {

		rawValue = StringEscapeUtils.escapeSql(rawValue);
		return rawValue;
	}

	protected boolean filterXSS() {

		return filterXSS;
	}

	protected boolean filterSQL() {

		return filterSQL;
	}

}