package org.apache.ibatis.session;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.junit.Test;

import com.piggy.stock.dict.domain.StockDict;

public class SqlSessionTest {

	@Test
	public void test() throws IOException {
		String resouce = "mybatis/mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resouce);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();
			String sql = "com.piggy.stock.dict.dao.StockDictMapper.selectByPrimaryKey";
			StockDict dict = session.selectOne(sql, 1010000000000600000L);
			System.out.println(dict);

			sql = "com.piggy.stock.dict.dao.StockDictMapper.selectByPrimaryKey";
			dict = session.selectOne(sql, 1010000000000600001L);
			System.out.println(dict);

			sql = "com.piggy.stock.dict.dao.StockDictMapper.selectAutoAddTenant";
			Map<String, Object> params = new HashMap<>();
			params.put("id", 1010000000000600000L);
			List<StockDict> list = session.selectList(sql, params);
			System.out.println(list.size());
		} finally {
			session.close();
		}

		/**
		 * 第二种方式: 执行更清晰和类型安全的代码
		 */
		// UserDao userDao = session.getMapper(UserDao.class);
		// user = userDao.getById(1);
		// System.out.println(user);
	}

}
