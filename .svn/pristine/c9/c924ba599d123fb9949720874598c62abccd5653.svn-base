package com.amwell.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmwellSetConfig {

	@Value("${amwell.flow}")
	private Double flow;//流量阈值
	
	@Value("${amwell.messageTotal}")
	private Integer messageTotal;//短信总数
	
	@Value("${amwell.messageFlow}")
	private Integer messageFlow;//短信阈值

	public Double getFlow() {
		return flow;
	}

	public void setFlow(Double flow) {
		this.flow = flow;
	}

	public Integer getMessageTotal() {
		return messageTotal;
	}

	public void setMessageTotal(Integer messageTotal) {
		this.messageTotal = messageTotal;
	}

	public Integer getMessageFlow() {
		return messageFlow;
	}

	public void setMessageFlow(Integer messageFlow) {
		this.messageFlow = messageFlow;
	}
	
}
