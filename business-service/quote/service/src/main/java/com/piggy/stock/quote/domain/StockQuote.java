package com.piggy.stock.quote.domain;

import java.util.Date;

import com.piggy.stock.core.domain.Stock;

public class StockQuote extends Stock {

	private Double opening;

	private Double closing;

	private Double low;

	private Double high;

	private Date tradeTime;

	public Double getOpening() {
		return opening;
	}

	public void setOpening(Double opening) {
		this.opening = opening;
	}

	public Double getClosing() {
		return closing;
	}

	public void setClosing(Double closing) {
		this.closing = closing;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

}