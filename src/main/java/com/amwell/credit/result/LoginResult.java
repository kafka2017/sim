package com.amwell.credit.result;

import com.amwell.credit.common.BaseEntity;

/**
 * 登录返回结果
 * 
 * @author hxj
 */
public class LoginResult extends BaseEntity{

	private static final long serialVersionUID = -3894020908186175376L;

	/**
	 * 接口返回状态码,正常为200
	 */
	private String status;

	/**
	 * 状态描述
	 */
	private String status_desc;

	/**
	 * token 字符串
	 */
	private String token;

	/**
	 * Token 有效期 单位（小时）默认有效期为 2 小时
	 */
	private Integer active_date;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_desc() {
		return status_desc;
	}

	public void setStatus_desc(String status_desc) {
		this.status_desc = status_desc;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getActive_date() {
		return active_date;
	}

	public void setActive_date(Integer active_date) {
		this.active_date = active_date;
	}
	
}
