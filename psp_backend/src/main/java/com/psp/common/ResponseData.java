package com.psp.common;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseData {
	private Integer status;
	private String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object data;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String current_page_no;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String total_page_no;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String total_element;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String token;

	public ResponseData(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public ResponseData(Integer status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public ResponseData(Integer status, String message, Object data, String token) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.token = token;
	}

	public ResponseData(Integer status, String message, Object data, String token, int pageNo, int totalPage,
			long totalElement) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.token = token;
		this.current_page_no = String.valueOf(pageNo);
		this.total_page_no = String.valueOf(totalPage);
		this.total_element = String.valueOf(totalElement);
	}

	public String getCurrent_page_no() {
		return current_page_no;
	}

	public void setCurrent_page_no(String current_page_no) {
		this.current_page_no = current_page_no;
	}

	public String getTotal_page_no() {
		return total_page_no;
	}

	public void setTotal_page_no(String total_page_no) {
		this.total_page_no = total_page_no;
	}

	public String getTotal_element() {
		return total_element;
	}

	public void setTotal_element(String total_element) {
		this.total_element = total_element;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
