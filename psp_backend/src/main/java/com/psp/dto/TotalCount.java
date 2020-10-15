package com.psp.dto;

public class TotalCount {

	private String createdBy;
	private Long imageCount;
	private Long videoCount;

	public TotalCount(String createdBy, Long imageCount, Long videoCount) {
		this.createdBy = createdBy;
		this.imageCount = imageCount;
		this.videoCount = videoCount;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getImageCount() {
		return imageCount;
	}

	public void setImageCount(Long imageCount) {
		this.imageCount = imageCount;
	}

	public Long getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(Long videoCount) {
		this.videoCount = videoCount;
	}

}
