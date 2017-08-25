package com.amwell.model.simwaringset;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

@Entity
@Table(name="sim_waring_set")
public class SimWaringSet {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="公司不能为空")
	@NotEmpty(message="公司不能为空")
	@Column(name="companyId")
	private Integer companyId;
	
	@NotNull(message="值不能为空")
	@NotEmpty(message="值不能为空")
	@Column(name="setVal")
	private Double setVal;
	
	@Column(name="createby")
	private Integer createby;
	
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

	public Double getSetVal() {
		return setVal;
	}

	public void setSetVal(Double setVal) {
		this.setVal = setVal;
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
