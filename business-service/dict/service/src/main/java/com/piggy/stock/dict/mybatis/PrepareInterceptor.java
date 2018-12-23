package com.piggy.stock.dict.mybatis;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * mybatis数据权限拦截器 - prepare
 *
 * @author GaoYuan
 * @date 2018/4/17 上午9:52
 */
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
@Component
public class PrepareInterceptor implements Interceptor {
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
			log.info("进入 PrepareInterceptor 拦截器...");
		}
		if (invocation.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
			StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
			// 通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
			MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
			// 千万不能用下面注释的这个方法，会造成对象丢失，以致转换失败
			// MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
			TenantDataPerm tenantDataPerm = PermissionUtils.getPermissionByDelegate(mappedStatement);
			if (tenantDataPerm == null) {
				if (log.isInfoEnabled()) {
					log.info("数据权限放行...");
				}
				return invocation.proceed();
			}
			if (log.isInfoEnabled()) {
				log.info("数据权限处理【拼接SQL】...");
			}
			BoundSql boundSql = delegate.getBoundSql();
			ReflectUtil.setFieldValue(boundSql, "sql", permissionSql(boundSql.getSql()));
		}
		return invocation.proceed();
	}

	/**
	 * 权限sql包装
	 *
	 * @author GaoYuan
	 * @date 2018/4/17 上午9:51
	 */
	protected String permissionSql(String sql) {
		StringBuilder sbSql = new StringBuilder(sql);

		sbSql = new StringBuilder("select * from (").append(sbSql).append(" ) s where 1 = 1 ");

		return sbSql.toString();
	}

	protected String permissionSql_0(String sql) {
		StringBuilder sbSql = new StringBuilder(sql);
		String userMethodPath = PermissionConfig.getConfig("permission.client.userid.method");
		// 当前登录人
		String userId = (String) ReflectUtil.reflectByPath(userMethodPath);
		// 如果用户为 1 则只能查询第一条
		if ("1".equals(userId)) {
			// sbSql = sbSql.append(" limit 1 ");
			// 如果有动态参数 regionCd
			if (true) {
				String premission_param = "regionCd";
				// select * from (select id,name,region_cd from sys_exam ) where region_cd like
				// '${}%'
				String methodPath = PermissionConfig.getConfig("permission.client.params." + premission_param);
				String regionCd = (String) ReflectUtil.reflectByPath(methodPath);
				sbSql = new StringBuilder("select * from (").append(sbSql)
						.append(" ) s where s.regionCd like concat(" + regionCd + ",'%')  ");
			}

		}
		return sbSql.toString();
	}
}