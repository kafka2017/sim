package com.amwell.mapper;

import java.util.List;

import com.amwell.model.RoleButton;

import tk.mybatis.mapper.common.Mapper;

public interface RoleButtonMapper extends Mapper<RoleButton> {

	List<RoleButton> queryRoleButton();

}
