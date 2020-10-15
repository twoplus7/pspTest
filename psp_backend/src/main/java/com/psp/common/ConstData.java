package com.psp.common;

public class ConstData {

	private ConstData() {
		throw new IllegalStateException("Utility class");
	}

	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60;
	public static final Long ROLE_ADMIN = 1001L;
	public static final Long ROLE_INFLUENCER = 1002L;
	public static final Long ROLE_USER = 1004L;
	public static final Long ROLE_CUSTOMER = 1003L;
	public static final String TEMPLATE_PARTNERSHIP = "PartnershipDetails";
	public static final String TEMPLATE_INF_CONF = "influencerConfirmation";
}
