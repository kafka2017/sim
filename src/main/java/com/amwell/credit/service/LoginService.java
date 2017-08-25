package com.amwell.credit.service;

import com.amwell.credit.result.LoginResult;

public interface LoginService {

	/**
	 * 登录
	 * @param org_id
	 * @param user_id
	 * @param password
	 * @return
	 */
	public LoginResult login(String org_id,String user_id,String password);
}
