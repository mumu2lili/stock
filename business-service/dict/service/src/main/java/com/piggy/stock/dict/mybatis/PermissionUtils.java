package com.piggy.stock.dict.mybatis;

import java.lang.reflect.Method;

import org.apache.ibatis.mapping.MappedStatement;

public class PermissionUtils {

	/**
	 * 根据 StatementHandler 获取 注解对象
	 *
	 * @author GaoYuan
	 * @date 2018/4/17 上午11:45
	 */
	public static TenantDataPerm getPermissionByDelegate(MappedStatement mappedStatement) {
		TenantDataPerm tenantDataPerm = null;
		try {
			String id = mappedStatement.getId();
			String className = id.substring(0, id.lastIndexOf("."));
			String methodName = id.substring(id.lastIndexOf(".") + 1, id.length());
			final Class cls = Class.forName(className);
			final Method[] method = cls.getMethods();
			for (Method me : method) {
				if (me.getName().equals(methodName) && me.isAnnotationPresent(TenantDataPerm.class)) {
					tenantDataPerm = me.getAnnotation(TenantDataPerm.class);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tenantDataPerm;
	}
}
