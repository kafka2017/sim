package com.amwell.credit.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amwell.credit.common.CreditAccountConstants;
import com.amwell.credit.result.LoginResult;
import com.amwell.test.SpringBaseTestCase;

public class LoginServiceTest  extends SpringBaseTestCase{

	@Autowired
	private LoginService loginService;
	
	
	@Test
	public void testLogin() {
		LoginResult  result = loginService.login(CreditAccountConstants.ORG_ID, CreditAccountConstants.USER_ID, CreditAccountConstants.PASSWORD);
		System.err.println(result);
	}

}
