package com.amwell.role;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.amwell.model.RoleButton;
import com.amwell.service.RoleButtonService;
import com.amwell.test.SpringBaseTestCase;

public class RoleButtonTest extends SpringBaseTestCase{

	@Autowired
	private RoleButtonService roleButService;
	
	
	@Test
	public void q(){
		List<RoleButton> l = roleButService.queryRoleButton();
		for(RoleButton rb:l){
			System.out.println(rb.getbName());
		}
	}
}
