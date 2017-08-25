package com.amwell.ecar.vo.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ECarSimBaseInfo implements Serializable {

	private static final long serialVersionUID = -2832724179217207049L;

	/**
	 * 卡MSISDN号
	 */
	@JsonProperty("MSISDN")
	private String msisdn;

	/**
	 * 卡ICCID号
	 */
	@JsonProperty("ICCID")
	private String iccid;

	/**
	 * 服务开始时间
	 */
	@JsonProperty("ServiceStartDate")
	private String serviceStartDate;

	/**
	 * 服务结束时间
	 */
	@JsonProperty("ServiceEndDate")
	private String serviceEndDate;

	/**
	 * 激活时间
	 */
	@JsonProperty("ActiveDate")
	private String activeDate;

	/**
	 * 分发时间
	 */
	@JsonProperty("DistributeDate")
	private String distributeDate;

	/**
	 * 套餐流量（单位：兆）
	 */
	@JsonProperty("TotalFlow")
	private String totalFlow;

	/**
	 * 当前使用流量（单位：兆）
	 */
	@JsonProperty("UsedFlow")
	private String usedFlow;

	/**
	 * 套餐周期（单位：月）
	 */
	@JsonProperty("FlowCycle")
	private String flowCycle;

	/**
	 * 当前卡状态 (00：正常 01：未知 02：停机 07：待激活)
	 */
	@JsonProperty("CardState")
	private String cardState;

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

	public String getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(String activeDate) {
		this.activeDate = activeDate;
	}

	public String getDistributeDate() {
		return distributeDate;
	}

	public void setDistributeDate(String distributeDate) {
		this.distributeDate = distributeDate;
	}

	public String getTotalFlow() {
		return totalFlow;
	}

	public void setTotalFlow(String totalFlow) {
		this.totalFlow = totalFlow;
	}

	public String getUsedFlow() {
		return usedFlow;
	}

	public void setUsedFlow(String usedFlow) {
		this.usedFlow = usedFlow;
	}

	public String getFlowCycle() {
		return flowCycle;
	}

	public void setFlowCycle(String flowCycle) {
		this.flowCycle = flowCycle;
	}

	public String getCardState() {
		return cardState;
	}

	public void setCardState(String cardState) {
		this.cardState = cardState;
	}
}
