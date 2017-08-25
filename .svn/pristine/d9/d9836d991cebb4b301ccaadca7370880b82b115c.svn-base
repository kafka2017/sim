package com.amwell.model.simrecv;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="sim_recv_record")
public class SimRecvResult implements Serializable {

	private static final long serialVersionUID = -5099376528410625874L;

	@Id
	private Long id;//编号

	@Column(name="businessId")
	private Long businessId;//商户编号

	@Column(name="iccid")
	private String iccid;//iccid

	@Column(name="mobile")
	private String mobile;//发送手机号码

	@Column(name="receiveMobile")
	private String receiveMobile;//接收手机号码

	@Column(name="status")
	private Integer status;//状态:0:失败;1:成功
	
	@Transient
	private String status_msg;

	@Column(name="content")
	private String content;//上行短信内容

	@Column(name="reason")
	private String reason;//原因

	@Column(name="code")
	private String code;//编码

	@Column(name="smsCreateDate")
	private Date smsCreateDate;//发送短信时间(物联网)
	
	@Transient
	private String smsCreateDate_s;

	@Column(name="serialNumber")
	private String serialNumber;//流水号(唯一标识)

	@Column(name="pushResult")
	private String pushResult;//推送成功标识

	@Column(name="spNumber")
	private String spNumber;//短信发送接入号

	@Column(name="createDate")
	private Date createDate;//创建时间
	
	@Transient
	private String createDate_s;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getReceiveMobile() {
		return receiveMobile;
	}

	public void setReceiveMobile(String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getSmsCreateDate() {
		return smsCreateDate;
	}

	public void setSmsCreateDate(Date smsCreateDate) {
		this.smsCreateDate = smsCreateDate;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getPushResult() {
		return pushResult;
	}

	public void setPushResult(String pushResult) {
		this.pushResult = pushResult;
	}

	public String getSpNumber() {
		return spNumber;
	}

	public void setSpNumber(String spNumber) {
		this.spNumber = spNumber;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus_msg() {
		return status_msg;
	}

	public void setStatus_msg(String status_msg) {
		this.status_msg = status_msg;
	}

	public String getSmsCreateDate_s() {
		return smsCreateDate_s;
	}

	public void setSmsCreateDate_s(String smsCreateDate_s) {
		this.smsCreateDate_s = smsCreateDate_s;
	}

	public String getCreateDate_s() {
		return createDate_s;
	}

	public void setCreateDate_s(String createDate_s) {
		this.createDate_s = createDate_s;
	}
	
}
