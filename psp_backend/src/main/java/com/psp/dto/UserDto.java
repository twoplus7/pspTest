package com.psp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psp.enums.Status;
import com.psp.model.Role;

public class UserDto {

	private Long userId;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private Status status;
	private Long mediaId;

	@JsonIgnore
	private Role role;

	@JsonIgnore
	private Boolean userRole = false;

	@JsonIgnore
	private Boolean influencerRole = false;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public Boolean getUserRole() {
		return userRole;
	}

	public void setUserRole(Boolean userRole) {
		this.userRole = userRole;
	}

	public Boolean getInfluencerRole() {
		return influencerRole;
	}

	public void setInfluencerRole(Boolean influencerRole) {
		this.influencerRole = influencerRole;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
