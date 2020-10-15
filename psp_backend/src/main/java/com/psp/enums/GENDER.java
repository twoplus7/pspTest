package com.psp.enums;

public enum GENDER {
	MALE("Male"), 
	FEMALE("Female"), 
	OTHER("Other");

	private String influencerGender;

	private GENDER(String gender) {
		this.influencerGender = gender;
	}

	public String getInfluencerGender() {
		return influencerGender;
	}
}
