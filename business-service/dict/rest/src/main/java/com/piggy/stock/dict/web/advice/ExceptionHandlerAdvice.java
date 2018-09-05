package com.piggy.stock.dict.web.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.piggy.stock.core.vo.ErrorMsg;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	private Logger log = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMsg> exception(Exception ex) {
		ResponseEntity<ErrorMsg> res = new ResponseEntity<ErrorMsg>(new ErrorMsg(ex.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);

		log.error(ex.getMessage(), ex);

		return res;
	}

}
