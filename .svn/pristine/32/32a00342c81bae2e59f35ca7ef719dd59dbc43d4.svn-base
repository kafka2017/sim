package com.amwell.credit.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.amwell.credit.common.RequestUrlConstants;
import com.amwell.credit.handler.login.LoginResultHandler;
import com.amwell.credit.result.LoginResult;
import com.amwell.credit.service.LoginService;
import com.amwell.util.HttpClientUtils;

@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public LoginResult login(String org_id, String user_id, String password) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("org_id", org_id);
		paraMap.put("user_id", user_id);
		paraMap.put("password", password);
		return HttpClientUtils.doPost(RequestUrlConstants.LOGIN_URL, paraMap,new LoginResultHandler());
	}

	
}
