package com.amwell.simbaseinfo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amwell.model.simbaseInfo.SimBaseInfo;
import com.amwell.model.simbaseInfo.SimBaseInfoQuery;
import com.amwell.service.simbaseInfo.SimBaseInfoService;
import com.amwell.test.SpringBaseTestCase;
import com.amwell.util.JSONHelper;
import com.amwell.util.ResultJson;
import com.github.pagehelper.PageInfo;

public class SimBaseInfoServiceTest extends SpringBaseTestCase {

	@Autowired
	private SimBaseInfoService simbaseInfoService;
	
	@Test
	public void q(){
		SimBaseInfoQuery query = new SimBaseInfoQuery();
		PageInfo<SimBaseInfo> l = simbaseInfoService.querySimBaseInfoByPage(query);
		System.out.println(JSONHelper.toString(l));
	}
	
//	@Test
//	public void add(){
//		SimBaseInfo model = new SimBaseInfo();
//		model.setMsisdn("ACD1002129");
//		model.setIccid("AC12201219");
//		model.setServiceStartDate("2017-07-21");
//		model.setServiceEndDate("2017-07-20");
//		model.setRegistTime("2017-07-22");
//		model.setDistributeTime("2017-07-23");
//		model.setActivationTime("2017-07-24");
//		model.setFlowCycle(1);
//		model.setTotalFlow(2D);
//		model.setUsedFlow(3D);
//		model.setGprsStatus("2");
//		model.setDeviceStatus("5");
//		model.setCompanyId(1);
//		model.setRemark("说明");
//		model.setVersion(1L);
//		ResultJson<Integer> i = simbaseInfoService.addSimBaseInfo(model);
//		System.err.println(JSONHelper.toString(i));
//	}
	
	@Test
	public void upd(){
		
		SimBaseInfo model = new SimBaseInfo();
		model.setMsisdn("CCC111");
		model.setIccid("CCC222");
		model.setServiceStartDate("2016-07-21");
		model.setServiceEndDate("2016-07-20");
		model.setRegistTime("2016-07-22");
		model.setDistributeTime("2016-07-23");
		model.setActivationTime("2016-07-24");
		model.setFlowCycle(1);
		model.setTotalFlow(2D);
		model.setUsedFlow(2D);
		model.setGprsStatus("2");
		model.setDeviceStatus("5");
		model.setCompanyId(1);
		model.setRemark("说明");
		model.setVersion(3L);
		
		model.setId(3L);
		ResultJson<Integer> rs = simbaseInfoService.updSimBaseInfo(model);
		System.err.println(JSONHelper.toString(rs));
	} 
	
	@Test
	public void qpage(){
		SimBaseInfoQuery query = new SimBaseInfoQuery();
		query.setPageNum(1);
		query.setPageSize(5);
		//query.setCompanyId(1);
		//query.setBeginDistributeTime("2017-05-25");
		//query.setEndDistributeTime("2017-05-28");
		//query.setBindState(1);
		query.setType("cancel");
		PageInfo<SimBaseInfo> page1 = simbaseInfoService.querySimBaseInfoByPage(query);
		System.err.println(JSONHelper.toString(page1));
	}
	
	@Test
	public void querybyid(){
		SimBaseInfo s = simbaseInfoService.querySimBaseInfoById(4732L);
		System.out.println(JSONHelper.toString(s));
	}
	
	@Test
	public void testAllocationSimCar(){
		SimBaseInfoQuery query = new SimBaseInfoQuery();
		query.setPageNum(1);
		query.setPageSize(100);
		PageInfo<SimBaseInfo> page= simbaseInfoService.querySimBaseInfoByPage(query);
		if(page!=null&&CollectionUtils.isNotEmpty(page.getList())){
			List<Long> ids = new ArrayList<Long>(page.getList().size());
			for(SimBaseInfo sm : page.getList()){
				ids.add(sm.getId());
			}
			Integer companyId = 1;
			ResultJson<SimBaseInfo> result = simbaseInfoService.allocationSimCar(ids, companyId);
			assertTrue(result.isSuccess());
		}
		
	}
	
	@Test
	public void deviceStatusInfo(){
		ResultJson<String> rs = simbaseInfoService.deviceStatusInfo("898602B7091701270018");
		System.out.println(JSONHelper.toString(rs));
	}
	
	@Test
	public void gprsstatusInfo(){
		ResultJson<String> rs = simbaseInfoService.gprsStatusInfo("898602B7091701270018");
		System.out.println(JSONHelper.toString(rs));
	}
	
	@Test
	public void all(){
		String startIccid = "898602B7091701270001";
		String endIccid = "898602B7091701270006";
		String startMsisdn = "";
		String endMsisdn = "";
		Integer companyId = 1;
		ResultJson<Integer> rs = simbaseInfoService.allocationSimCar(startIccid, endIccid,startMsisdn,endMsisdn, companyId);
		System.err.println(JSONHelper.toString(rs));
	}
}
