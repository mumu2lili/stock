package com.piggy.stock.core.util.stock;

import com.piggy.stock.core.constant.StockCsts;
import com.piggy.stock.core.domain.Stock;

public abstract class StockIdUtils {

	private static final long EXCHANGE_FACTOR = 1000L * 1000L * 1000L * 1000L * 1000L * 1000L;
	private static final long BOARD_FACTOR = 1000L * 1000L * 1000L * 1000L * 1000L * 10L;

	public static long calStockId(String exchange, String board, String code) {
		long id = 0;
		if (StockCsts.EXCHANGE_SHANGHAI.equals(exchange)) {
			id = 1 * EXCHANGE_FACTOR;
		} else if (StockCsts.EXCHANGE_SHENZHEN.equals(exchange)) {
			id = 2 * EXCHANGE_FACTOR;
		} else {
			throw new IllegalArgumentException("unknown exchange " + exchange);
		}

		if (StockCsts.BOARD_MAIN.equals(board)) {
			id += 1 * BOARD_FACTOR;
		} else if (StockCsts.BOARD_GEM.equals(board)) {
			id += 2 * BOARD_FACTOR;
		} else {
			throw new IllegalArgumentException("unknown board " + board);
		}

		id += Long.parseLong(code);

		return id;
	}

	public static long calStockId(Stock stock) {
		return calStockId(stock.getExchange(), stock.getBoard(), stock.getCode());
	}

}
