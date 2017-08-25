package com.amwell.model.simcompany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimCompanyStatTreeNode implements Serializable {

	private static final long serialVersionUID = -2564582019048346901L;

	private Integer id;// 公司ID

	private String fullName;// 公司全称

	private String shortName;// 公司简称

	// 启
	private Integer countEnable = 0;

	// 未知
	private Integer countUnknown = 0;

	// 停
	private Integer countDisable = 0;

	// 待激活
	private Integer countSilence = 0;

	// 销号
	private Integer countCancel = 0;

	// 流量预警
	private Integer countWarn = 0;

	// 超流量
	private Integer countExceed = 0;

	private List<SimCompanyStatTreeNode> children = new ArrayList<SimCompanyStatTreeNode>();// 子公司数据

	public SimCompanyStatTreeNode() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	public Integer getCountCancel() {
		return countCancel;
	}

	public void setCountCancel(Integer countCancel) {
		this.countCancel = countCancel;
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

	public List<SimCompanyStatTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<SimCompanyStatTreeNode> children) {
		this.children = children;
	}

}
