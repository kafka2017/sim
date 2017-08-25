package com.amwell.ecar.vo.base;

import java.io.Serializable;

import com.amwell.util.JSONHelper;

public class ECarCommonResult implements Serializable{

	private static final long serialVersionUID = -2338638596754334733L;

	/**
	 * 错误码
	 */
	protected String errorCode;

	/**
	 * 是否成功（是true，否false）
	 */
	protected String success;

	/**
	 * 错误原因
	 */
	protected String errorMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String toString(){
		return JSONHelper.toString(this);
	}
	
}
