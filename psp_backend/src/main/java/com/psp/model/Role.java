package com.psp.model;

import javax.persistence.*;

import com.psp.enums.ROLE;

@Entity
public class Role {

	@Id
	private Long roleId;
	@Enumerated(EnumType.STRING)
	private ROLE role;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public ROLE getRole() {
		return role;
	}

	public void setRole(ROLE role) {
		this.role = role;
	}

}
