package com.amwell.model.simcompany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimCompanyTreeNode implements Serializable {

	private static final long serialVersionUID = -2564582019048346901L;

	private Integer id;//公司ID

	private String fullName;//公司全称

	private String shortName;//公司简称

	private List<SimCompanyTreeNode> children = new ArrayList<SimCompanyTreeNode>();//子公司数据

	public SimCompanyTreeNode(){
		
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

	public List<SimCompanyTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<SimCompanyTreeNode> children) {
		this.children = children;
	}

}
