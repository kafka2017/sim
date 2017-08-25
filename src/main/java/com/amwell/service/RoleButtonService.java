package com.amwell.service;

import java.util.List;

import com.amwell.model.RoleButton;

public interface RoleButtonService extends IService<RoleButton> {

	public List<RoleButton> queryRoleButton();
}
