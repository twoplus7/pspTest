package com.psp.controller;

public class UserControllerUrl {

	private UserControllerUrl() {
		throw new IllegalStateException("Utility class");
	}

	public static final String FIND_ALL = "/users";
	public static final String FIND_BY_ID = "/users/{id}";
	public static final String SIGNUP = "/signup";
	public static final String CREATE_USER = "/create-user";
	public static final String CREATE_MANAGER = "/create-manager";
	public static final String CREATE_INFLUENCER = "/create-influencer";

	public static final String DELETE_USER = "/delete-influencer";
	public static final String GET_USER_BY_ID = "/get-user-by-id";
	public static final String LIST_ALL_INFLUENCER = "/list_all_influencer";
	public static final String LOGIN = "/login";
	public static final String PASSWORD_GENERATOR = "/passwordgenerator";
	public static final String GET_ALL_MANAGES_PROFILE = "/get_all_manages_profile";// list_influencers
	public static final String GET_ALL_ADMIN_PROFILE = "/get_all_admin_profile";// list_influencers
	public static final String UPDATE_STATUS = "/update_status";
	public static final String UPDATE_STATUS_EMAIL = "/update-status-email-verification";
	public static final String UPDATE_PROFILE_STATUS = "/update_profile_status";
	public static final String FORGET_PASSWORD = "/forget_password";
	public static final String CHANGE_PASSWORD = "/change_password";
	public static final String UPDATE_USER = "/update-user";

}
