package com.psp.enums;

public enum HttpStatus {
	// User / Customer/ Admin
	EMAIL_PASSSWORD_INCORRECT(301, "Jwt token expired , email or password incorrect"),
	YOU_ARE_ALREADY_REGISTERED(302, "This user already exist"), USER_NOT_FOUND(303, "User not found"),
	USER_NOT_DELETED(304, "Failed to delete user"), USER_LIST_NOT_FOUND(305, "User list not found"),
	USER_ALREADY_PRESENT(306, "Email address already exist"),
	YOUR_ACCOUNT_IS_INACTIVE(307, "Your account is inactivated"),
	EMAIL_ID_INVALID(308, "Your email id is not registered"), TOKEN_EXPIRED(309, "Token is expired"),
	INVALID_PWD(310, "Please enter valid password"),

	OK(200, "Transaction successfull"), FAIL(201, "Transaction fail"), SOMETHING_WRONG(203, "Something went wrong..!"),
	ACCESS_DENIED(204, "You are not Authorization."), ID_NOT_VALID(205, "Entered Id is Invalid"),
	NULL_NOT_ALLOWED(206, "Null or Zero values are not allowed"), NO_DATA(207, "No Data Available"),

	// Seat
	SEAT_IS_NOT_AVAILABLE(801, "Seat is not available"), SEAT_IS_NUMBER_IS_EXCEEDED(802, "No more seat available");

	private Integer status;
	private String message;

	private HttpStatus(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
