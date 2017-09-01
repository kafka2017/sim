package com.amwell.service.report;

import java.util.List;

import com.amwell.model.report.SimBaseInfoSendTotal;
import com.amwell.model.report.SimWaring;
import com.amwell.model.simcompany.SimCompanyQuery;
import com.amwell.model.simcompany.SimCompanyReport;
import com.amwell.model.simsendrecord.SimSendRecordCount;
import com.amwell.model.simsendrecord.SimSendRecordQuery;
import com.amwell.util.ResultJson;

public interface ReportService  {
	/**
	 * sim卡状态统计
	 * @param query
	 * @return
	 * @author 番茄很忙
	 */
	public List<SimCompanyReport> simStatusReport(SimCompanyQuery query);
	
	/**
	 * sim卡流量阈值统计
	 * @param query
	 * @return
	 */
	public List<SimWaring> simWaringReport(SimCompanyQuery query);
	
	/**
	 * sim卡短信发送阈值统计
	 * @param query
	 * @return
	 */
	public ResultJson<SimSendRecordCount> simMessageRecord(SimSendRecordQuery query);
	
	/**
	 * sim卡短信发送数
	 * @param query
	 * @return
	 */
	public List<SimBaseInfoSendTotal> simMessageTotal(SimCompanyQuery query);	
	
}
