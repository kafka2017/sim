package com.amwell.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.amwell.mapper.RoleButtonMapper;
import com.amwell.model.RoleButton;
import com.amwell.service.RoleButtonService;

@Service(value="roleButtonService")
public class RoleButtonServiceImpl extends BaseService<RoleButton> implements RoleButtonService {


	@Resource
	private RoleButtonMapper rolebuttonMapper;
	
	@Override
	public List<RoleButton> queryRoleButton() {
		return rolebuttonMapper.queryRoleButton();
	}

	

}
