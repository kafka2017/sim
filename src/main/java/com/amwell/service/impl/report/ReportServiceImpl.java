package com.amwell.service.impl.report;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amwell.common.ErrorCodeEnum;
import com.amwell.config.AmwellSetConfig;
import com.amwell.mapper.report.SimCompanyReportMapper;
import com.amwell.mapper.simsendrecord.SimSendRecordMapper;
import com.amwell.mapper.simwaringset.SimMessageMapper;
import com.amwell.model.report.SimWaring;
import com.amwell.model.simcompany.SimCompanyQuery;
import com.amwell.model.simcompany.SimCompanyReport;
import com.amwell.model.simsendrecord.SimSendRecordCount;
import com.amwell.model.simsendrecord.SimSendRecordQuery;
import com.amwell.service.report.ReportService;
import com.amwell.util.ResultJson;

@Service(value="reportService")
public class ReportServiceImpl  implements ReportService {

	@Resource
	private SimCompanyReportMapper simcompanyReportMapper;
	
	@Resource
	private SimSendRecordMapper simsendRecordMapper;
	
	@Resource
	private SimMessageMapper messageMapper;
	
	@Autowired
	private AmwellSetConfig aset;

	@Override
	public List<SimCompanyReport> simStatusReport(SimCompanyQuery query) {
		return simcompanyReportMapper.simStatusReport(query);
	}

	@Override
	public List<SimWaring> simWaringReport(SimCompanyQuery query) {
		query.setSetVal(aset.getFlow());
		return simcompanyReportMapper.simWaringReport(query);
	}

	@Override
	public ResultJson<SimSendRecordCount> simMessageRecord(SimSendRecordQuery query) {
		
		if(query==null){
			return ResultJson.buildFailedMsg(ErrorCodeEnum.illegal_para.getErrorCode(), "参数不能为空");
		}
		
		if(query.getCompanyId()==null){
			return ResultJson.buildFailedMsg(ErrorCodeEnum.illegal_para.getErrorCode(), "公司id不能为空");
		}
		
		query.setMessageFlow(aset.getMessageFlow());
		query.setMessageTotal(aset.getMessageTotal());
		SimSendRecordCount src = simsendRecordMapper.queryMessageByCompany(query);
		
		if(src==null){
			src = new SimSendRecordCount();
		}
		
		return ResultJson.buildSuccessMsg(src);
	}

}
