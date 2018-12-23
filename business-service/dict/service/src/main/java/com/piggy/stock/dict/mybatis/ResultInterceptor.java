package com.piggy.stock.dict.mybatis;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
@Component
public class ResultInterceptor implements Interceptor {
	/** 日志 */
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if (log.isInfoEnabled()) {
			log.info("进入 ResultInterceptor 拦截器...");
		}

		// DefaultResultSetHandler handler = (DefaultResultSetHandler)
		// invocation.getTarget();
		// ParameterHandler parameterHandler =
		// (ParameterHandler)ReflectUtil.getFieldValue(handler, "parameterHandler");
		// Object parameterObj = parameterHandler.getParameterObject();

		ResultSetHandler resultSetHandler1 = (ResultSetHandler) invocation.getTarget();
		// 通过java反射获得mappedStatement属性值
		// 可以获得mybatis里的resultype
		MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(resultSetHandler1,
				"mappedStatement");
		// 获取切面对象
		TenantDataPerm tenantDataPerm = PermissionUtils.getPermissionByDelegate(mappedStatement);

		// 执行请求方法，并将所得结果保存到result中
		Object result = invocation.proceed();
		if (tenantDataPerm != null) {
			if (result instanceof ArrayList) {
				ArrayList resultList = (ArrayList) result;
				for (int i = 0; i < resultList.size(); i++) {
					Object oi = resultList.get(i);
					Class c = oi.getClass();
					Class[] types = { String.class };
					Method method = c.getMethod("setRegionCd", types);
					// 调用obj对象的 method 方法
					method.invoke(oi, "");
					if (log.isInfoEnabled()) {
						log.info("数据权限处理【过滤结果】...");
					}
				}
			}
		}
		return result;
	}

}
