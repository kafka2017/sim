package com.amwell.model.simsendrecord;

public class SimSendRecordCount {

	private Integer companyId;//公司id
	
	private String shortName;//公司简称
	
	private Integer normalCount=0;//正常
	
	private Integer alarmCount=0;//阈警
	
	private Integer exceedCount=0;//短信超出数

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getNormalCount() {
		return normalCount;
	}

	public void setNormalCount(Integer normalCount) {
		this.normalCount = normalCount;
	}

	public Integer getAlarmCount() {
		return alarmCount;
	}

	public void setAlarmCount(Integer alarmCount) {
		this.alarmCount = alarmCount;
	}

	public Integer getExceedCount() {
		return exceedCount;
	}

	public void setExceedCount(Integer exceedCount) {
		this.exceedCount = exceedCount;
	}
	
}
