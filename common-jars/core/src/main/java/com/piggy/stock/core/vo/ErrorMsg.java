package com.piggy.stock.core.vo;

public class ErrorMsg {

	// @JsonProperty("message")
	private String message;

	public ErrorMsg(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
