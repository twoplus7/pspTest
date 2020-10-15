package com.psp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.psp.common.ResponseData;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PspException.class)
	public ResponseEntity<ResponseData> getException(PspException gaException) {
		ResponseData errorResponse = new ResponseData(gaException.getHttpStatus().getStatus(),
				gaException.getHttpStatus().getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.OK);
	}

//	@ExceptionHandler(Exception.class)
//	public ResponseBean handleException(Exception ex, WebRequest req) {
//		return new ResponseBean(com.psp.enums.HttpStatus.SOMETHING_WRONG.getStatus(), ex.getMessage(),
//				req.getDescription(false));
//
//	}

}
