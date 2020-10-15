package com.psp.dto;

import java.io.Serializable;

import com.psp.enums.Status;

public class ProductDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer productId;
	private String productName;
	private String productUrl;
	private String assignTag;
	private Integer measurementId;
	private Double hips;
	private Double bust;
	private Double weight;
	private Double height;
	private Double waist;
	private Status status;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getAssignTag() {
		return assignTag;
	}

	public void setAssignTag(String assignTag) {
		this.assignTag = assignTag;
	}

	public Integer getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(Integer measurementId) {
		this.measurementId = measurementId;
	}

	public Double getHips() {
		return hips;
	}

	public void setHips(Double hips) {
		this.hips = hips;
	}

	public Double getBust() {
		return bust;
	}

	public void setBust(Double bust) {
		this.bust = bust;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWaist() {
		return waist;
	}

	public void setWaist(Double waist) {
		this.waist = waist;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
