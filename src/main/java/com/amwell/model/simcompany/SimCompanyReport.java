package com.amwell.model.simcompany;

public class SimCompanyReport {

	//公司id
	private Integer id;
	
	//公司名称
	private String shortName;
	
	//父id
	private Integer parentId = 0;
	
	//启
	private Integer countEnable = 0;
	
	//未知
	private Integer countUnknown = 0;
	
	//停
	private Integer countDisable = 0;
	
	//待激活
	private Integer countSilence = 0;
	
	//已激活
	private Integer activityCount=0;
	
	//销号
	private Integer countCancel = 0;
	
	//警
	private Integer countWarn = 0;
	
	//超
	private Integer countExceed = 0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getCountEnable() {
		return countEnable;
	}

	public void setCountEnable(Integer countEnable) {
		this.countEnable = countEnable;
	}

	public Integer getCountUnknown() {
		return countUnknown;
	}

	public void setCountUnknown(Integer countUnknown) {
		this.countUnknown = countUnknown;
	}

	public Integer getCountDisable() {
		return countDisable;
	}

	public void setCountDisable(Integer countDisable) {
		this.countDisable = countDisable;
	}

	public Integer getCountSilence() {
		return countSilence;
	}

	public void setCountSilence(Integer countSilence) {
		this.countSilence = countSilence;
	}

	public Integer getCountWarn() {
		return countWarn;
	}

	public void setCountWarn(Integer countWarn) {
		this.countWarn = countWarn;
	}

	public Integer getCountExceed() {
		return countExceed;
	}

	public void setCountExceed(Integer countExceed) {
		this.countExceed = countExceed;
	}

	public Integer getCountCancel() {
		return countCancel;
	}

	public void setCountCancel(Integer countCancel) {
		this.countCancel = countCancel;
	}

	public Integer getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(Integer activityCount) {
		this.activityCount = activityCount;
	}
	
}
