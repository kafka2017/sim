package com.amwell.controller.export;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amwell.model.report.SimBaseInfoSendTotal;
import com.amwell.model.simcompany.SimCompanyQuery;
import com.amwell.service.report.ReportService;
import com.amwell.util.excel.ExcelUtil;
import com.amwell.util.excel.JsGridReportBase;
import com.amwell.util.excel.TableData;

@Controller
@RequestMapping(value="/export")
public class ExportController {
	
	@Autowired
	private ReportService reportService;

	
	/**
	 * 导出sim基本信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/exportSimbase")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,SimCompanyQuery query){
	        response.setContentType("application/msexcel;charset=GBK");
//        Session session = SecurityUtils.getSubject().getSession();
//		User user = (User)session.getAttribute("userSession");
//		if(user!=null){
//			
//		}
		query.setPageNum(null);
		query.setPageSize(null);
		List<SimBaseInfoSendTotal> list = reportService.simMessageTotal(query);
	    
	    String	title = "sim卡基本信息列表";
	    String[] hearders = new String[] {
	 	    		"状态", "sim卡号", "iccid", "流量消耗(m)", "短信","开卡日期","使用日期", "预计停机日期","所属公司","套餐周期"};//表头数组
	    String[] fields = new String[] {
	 	    		"cardState", "msisdn", "iccid", "usedFlow", "sendTotal", 
	 	    		"registTime","serviceStartDate","serviceEndDate","fullName","flowCycle"};//对象属性数组
	   
	    TableData td = ExcelUtil.createTableData(list, ExcelUtil.createTableHeader(hearders),fields);
	    JsGridReportBase report;
		try {
			report = new JsGridReportBase(request, response);
			String agent = request.getHeader("User-Agent");
			report.exportToExcel(title, td,agent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   	    
	}
}
