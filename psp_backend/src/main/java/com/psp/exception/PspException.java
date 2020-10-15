package com.psp.exception;

import com.psp.enums.HttpStatus;

@SuppressWarnings("serial")
public class PspException extends Exception {
	private HttpStatus httpStatus;
	private Exception ex = null;
	public String message = null;

	public PspException(HttpStatus httpStatus, Exception ex) {

		this.httpStatus = httpStatus;
		this.ex = ex;
	}

	public PspException(HttpStatus httpStatus, String message) {

		this.httpStatus = httpStatus;
		this.message = message;
	}

	public PspException(HttpStatus httpStatus) {

		this.httpStatus = httpStatus;
		this.message = ex.getMessage();
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String setMessage(String msg) {
		return this.message = msg;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public PspException(Throwable cause) {
		super(cause);
	}

	@Override
	public String toString() {
		return "PspException [httpStatus=" + httpStatus + ", ex=" + ex + ", message=" + message + "]";
	}

}
