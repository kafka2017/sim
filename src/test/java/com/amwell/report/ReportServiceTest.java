package com.amwell.report;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amwell.model.report.SimWaring;
import com.amwell.model.simcompany.SimCompanyQuery;
import com.amwell.model.simcompany.SimCompanyReport;
import com.amwell.model.simsendrecord.SimSendRecordCount;
import com.amwell.model.simsendrecord.SimSendRecordQuery;
import com.amwell.service.report.ReportService;
import com.amwell.test.SpringBaseTestCase;
import com.amwell.util.JSONHelper;
import com.amwell.util.ResultJson;

public class ReportServiceTest extends SpringBaseTestCase {

	@Autowired
	private ReportService reportService;
	
	@Test
	public void simstatuc(){
		SimCompanyQuery query = new SimCompanyQuery();
		query.setCompanyId(1);
		List<SimCompanyReport> l = reportService.simStatusReport(query);
		System.err.println(JSONHelper.toString(l));
	}
	
	@Test
	public void simWaring(){
		SimCompanyQuery query = new SimCompanyQuery();
		query.setCompanyId(1);
		List<SimWaring> l = reportService.simWaringReport(query);
		System.err.println(JSONHelper.toString(l));
	}
	
	@Test
	public void simMessageRecord(){
		SimSendRecordQuery query = new SimSendRecordQuery();
		query.setCompanyId(1);
		ResultJson<SimSendRecordCount> simsend = reportService.simMessageRecord(query);
		System.err.println(JSONHelper.toString(simsend));
	}
}
