package com.amwell.ecar.vo.result;

import java.io.Serializable;

public class ECarDeviceStatus implements Serializable {

	private static final long serialVersionUID = -8271860109593460371L;

	/**
	 * SIM卡iccid
	 */
	private String iccid;

	/**
	 * 0：关机 1：开机 2：未知
	 */
	private String deviceStatus;

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

}
