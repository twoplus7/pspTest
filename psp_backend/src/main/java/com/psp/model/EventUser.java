/**
 * 
 */
package com.psp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author us
 * 
 */

@Entity
public class EventUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eventUserId;

	private String fullName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private Integer zipCode;

	public Long getEventUserId() {
		return eventUserId;
	}

	public void setEventUserId(Long eventUserId) {
		this.eventUserId = eventUserId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
}
