package com.amwell.model.simbaseInfo;

import com.amwell.model.BaseModel;

public class SimBaseInfoQuery extends BaseModel{

	private Integer companyId;//公司id
	
	private String msisdn;
	
	private String iccid;
	
	private String shortName;//公司简称
	
	private String beginDistributeTime;//SIM卡分发开始时间
	
	private String endDistributeTime;//SIM卡分发结束时间
	
	private String gprsStatus;//gprs状态 00：离线，01:在线，      02:未知
	
	private String deviceStatus;//设备状态 0：关机 1：开机  2：未知
	
	private String cardState;//当前卡状态（00：正常 01 未知 02 停机 07 待激活）
	
	private Integer bindState;//绑定状态 1 绑定 0 未绑定
	
	private String type;//enable:启，unknown:未知，disable:停,silence:待激活,warn:警,exceed:超
	
	private String startIccid;//开始iccid
	
	private String endIccid;//结束iccid
	
	private String startMsisdn;
	
	private String endMsisdn;

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getBeginDistributeTime() {
		return beginDistributeTime;
	}

	public void setBeginDistributeTime(String beginDistributeTime) {
		this.beginDistributeTime = beginDistributeTime;
	}

	public String getEndDistributeTime() {
		return endDistributeTime;
	}

	public void setEndDistributeTime(String endDistributeTime) {
		this.endDistributeTime = endDistributeTime;
	}

	public String getGprsStatus() {
		return gprsStatus;
	}

	public void setGprsStatus(String gprsStatus) {
		this.gprsStatus = gprsStatus;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getCardState() {
		return cardState;
	}

	public void setCardState(String cardState) {
		this.cardState = cardState;
	}

	public Integer getBindState() {
		return bindState;
	}

	public void setBindState(Integer bindState) {
		this.bindState = bindState;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getStartIccid() {
		return startIccid;
	}

	public void setStartIccid(String startIccid) {
		this.startIccid = startIccid;
	}

	public String getEndIccid() {
		return endIccid;
	}

	public void setEndIccid(String endIccid) {
		this.endIccid = endIccid;
	}

	public String getStartMsisdn() {
		return startMsisdn;
	}

	public void setStartMsisdn(String startMsisdn) {
		this.startMsisdn = startMsisdn;
	}

	public String getEndMsisdn() {
		return endMsisdn;
	}

	public void setEndMsisdn(String endMsisdn) {
		this.endMsisdn = endMsisdn;
	}
	
}
