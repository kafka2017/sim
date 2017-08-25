package com.amwell.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amwell.model.User;
import com.amwell.service.UserService;
import com.amwell.test.SpringBaseTestCase;
import com.amwell.util.JSONHelper;

public class UserServiceTest extends SpringBaseTestCase {

	@Autowired
	UserService userservice;
	
	@Test
	public void qbyname(){
		User u = userservice.selectByUsername("admin");
		System.err.println(JSONHelper.toString(u));
	}
	
	@Test
	public void qbyid(){
		User u = userservice.queryUser(1);
		System.err.println(JSONHelper.toString(u));
	}
}
