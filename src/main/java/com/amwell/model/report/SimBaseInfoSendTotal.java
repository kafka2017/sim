package com.amwell.model.report;

public class SimBaseInfoSendTotal {

	private Long id;//id
	
	private String msisdn;//MSISDN号
	
	private String iccid;//ICCID
	
	private String serviceStartDate;//服务开始时间
	
	private String serviceEndDate;//服务结束时间
	
	private String registTime;//注册时间
	
	private Integer flowCycle;//套餐周期(单位：月)
	
	private Double totalFlow;//总流量(单位M)
	
	private Double usedFlow;//已使用流量(单位M)
	
	private String cardState;//卡状态 当(00：正常（移动卡：已激活） 01： 未知 02： 停机 03:销号 07：待激活  Activated:已激活(联通卡))
	
	private String fullName;
	
	private Integer sendTotal;//短信发送数

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public String getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}

	public Integer getFlowCycle() {
		return flowCycle;
	}

	public void setFlowCycle(Integer flowCycle) {
		this.flowCycle = flowCycle;
	}

	public Double getTotalFlow() {
		return totalFlow;
	}

	public void setTotalFlow(Double totalFlow) {
		this.totalFlow = totalFlow;
	}

	public Double getUsedFlow() {
		return usedFlow;
	}

	public void setUsedFlow(Double usedFlow) {
		this.usedFlow = usedFlow;
	}

	public String getCardState() {
		return cardState;
	}

	public void setCardState(String cardState) {
		this.cardState = cardState;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getSendTotal() {
		return sendTotal;
	}

	public void setSendTotal(Integer sendTotal) {
		this.sendTotal = sendTotal;
	}
	
}
