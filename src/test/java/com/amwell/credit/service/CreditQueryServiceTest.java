package com.amwell.credit.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amwell.credit.common.CreditAccountConstants;
import com.amwell.credit.result.LoginResult;
import com.amwell.test.SpringBaseTestCase;

public class CreditQueryServiceTest extends SpringBaseTestCase{
	
	@Autowired
	private CreditQueryService creditQueryService;
	
	@Autowired
	private LoginService loginService;

	@Test
	public void testQueryCreditInfo() {
		String token = this.getToken();
		String result = null;
		Map<String, Object> paraMap = new HashMap<String,Object>();
		paraMap.put("token", token);
		paraMap.put("city", "深圳");
		paraMap.put("name", "张向国");
		paraMap.put("id_type", 1);
		paraMap.put("id_no", "310109195607025254");
		paraMap.put("mobile_no", "13701897727");
		result = this.creditQueryService.queryCreditInfo(paraMap);
		System.out.println(result);
		
	}

	private String getToken(){
		String token = "7a8c7d01-36ed-406e-b0e6-3faeaf0612ef";
		if(token==null){
			LoginResult  loginResult = loginService.login(CreditAccountConstants.ORG_ID, CreditAccountConstants.USER_ID, CreditAccountConstants.PASSWORD);
			if(loginResult.getStatus().equals("200")){
				token = loginResult.getToken();
			}
		}
		System.out.println("token="+token);
		return token;
	}
}
