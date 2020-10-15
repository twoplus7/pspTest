package com.psp.enums;

public enum AdminStatus {
	APPROVED("Approved"), REJECTED("Rejected"), NOT_APPROVED("Unapproved");
	private String mediaStatus;

	private AdminStatus(String mediaStatus) {
		this.mediaStatus = mediaStatus;
	}

	public String getMediaStatus() {
		return mediaStatus;
	}
}
