package com.amwell.model.simwaringset;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

@Table(name="sim_message_set")
public class SimMessageSet {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="公司不能为空")
	@NotEmpty(message="公司不能为空")
	@Column(name="companyId")
	private Integer companyId;
	
	@NotNull(message="短信阈值不能为空")
	@NotEmpty(message="短信阈值不能为空")
	@Column(name="messageTotal")
	private Integer messageTotal;
	
	@NotNull(message="创建人不能为空")
	@NotEmpty(message="创建人不能为空")
	@Column(name="createby")
	private Integer createby;
	
	@NotNull(message="创建时间不能为空")
	@NotEmpty(message="创建时间不能为空")
	@Column(name="createOn")
	private Date createOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getMessageTotal() {
		return messageTotal;
	}

	public void setMessageTotal(Integer messageTotal) {
		this.messageTotal = messageTotal;
	}

	public Integer getCreateby() {
		return createby;
	}

	public void setCreateby(Integer createby) {
		this.createby = createby;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
}
