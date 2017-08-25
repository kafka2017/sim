package com.amwell.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "role_button")
public class RoleButton {

	@Id
    @Column(name = "roleButtonId")
	private Integer roleButtonId;
	
	@Id
    @Column(name = "resourcesId")
	private Integer resourcesId;
	
	@Column(name="bName")
	private String bName;
	
	@Column(name="enable")
	private Integer enable;

	public Integer getRoleButtonId() {
		return roleButtonId;
	}

	public void setRoleButtonId(Integer roleButtonId) {
		this.roleButtonId = roleButtonId;
	}

	public Integer getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(Integer resourcesId) {
		this.resourcesId = resourcesId;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	
}
