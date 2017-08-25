package com.amwell.mapper.report;

import java.util.List;

import com.amwell.model.report.SimWaring;
import com.amwell.model.simcompany.SimCompanyQuery;
import com.amwell.model.simcompany.SimCompanyReport;
import com.amwell.util.MyMapper;

public interface SimCompanyReportMapper extends MyMapper<SimCompanyReport> {

	/**
	 * 按公司统计sim不同状态的数量
	 * @return
	 */
	public List<SimCompanyReport> querySimpanyReport(SimCompanyQuery query);
	
	/**
	 * sim卡状态统计
	 * @param query
	 * @return
	 */
	public List<SimCompanyReport> simStatusReport(SimCompanyQuery query);
	
	/**
	 * sim卡流量预警统计
	 * @param query
	 * @return
	 */
	public List<SimWaring> simWaringReport(SimCompanyQuery query);
	
	/**
	 * sim卡短信超量统计
	 * @param query
	 * @return
	 */
	//public List<SimCompanyReport> simExceedReport(SimCompanyQuery query);
}
