package com.amwell.ecar.vo.result;

import java.io.Serializable;

public class ECarGprsStatus implements Serializable {

	private static final long serialVersionUID = -8271860109593460371L;

	/**
	 * SIM卡iccid
	 */
	private String iccid;

	/**
	 * 00：离线，01:在线,02:未知
	 */
	private String gprsStatus;

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getGprsStatus() {
		return gprsStatus;
	}

	public void setGprsStatus(String gprsStatus) {
		this.gprsStatus = gprsStatus;
	}

}
