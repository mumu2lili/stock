package com.piggy.stock.core.util.stock;

import org.junit.Assert;
import org.junit.Test;

import com.piggy.stock.core.constant.StockCsts;

public class StockIdUtilsTest {

	@Test
	public void test_shanghai_main() {
		long id = StockIdUtils.calStockId(StockCsts.EXCHANGE_SHANGHAI, StockCsts.BOARD_MAIN, "000001");
		Assert.assertEquals(1010000000000000001L, id);
	}

	@Test
	public void test_shenzhen_main() {
		long id = StockIdUtils.calStockId(StockCsts.EXCHANGE_SHENZHEN, StockCsts.BOARD_MAIN, "000001");
		Assert.assertEquals(2010000000000000001L, id);
	}

}
