package com.amwell.model.simsendrecord;

public class SimSendRecordIccidCount {

	private String iccid;
	
	private Integer msgCount=0;//短信数
	
	private String msgRate="";//短信率

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public Integer getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(Integer msgCount) {
		this.msgCount = msgCount;
	}

	public String getMsgRate() {
		return msgRate;
	}

	public void setMsgRate(String msgRate) {
		this.msgRate = msgRate;
	}
	
}
