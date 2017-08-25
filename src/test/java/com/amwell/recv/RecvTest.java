package com.amwell.recv;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amwell.model.simrecv.SimRecvResult;
import com.amwell.model.simrecv.SimRecvResultQuery;
import com.amwell.service.recv.RecvSimMessageService;
import com.amwell.test.SpringBaseTestCase;
import com.amwell.util.JSONHelper;
import com.amwell.util.ResultJson;
import com.github.pagehelper.PageInfo;

public class RecvTest extends SpringBaseTestCase {

	@Autowired
	RecvSimMessageService recvService;
	
	@Test
	public void add(){
		SimRecvResult model = new SimRecvResult();
		model.setId(1L);
		model.setBusinessId(24232L);
		model.setCode("AD");
		model.setContent("1");
		model.setCreateDate(new Date());
		model.setIccid("1ccds");
		model.setMobile("18777142423");
		model.setPushResult("2");
		model.setReason("2");
		model.setReceiveMobile("12312");
		model.setSerialNumber("232");
		model.setSmsCreateDate(new Date());
		model.setSpNumber("121");
		model.setStatus(1);
		ResultJson<Integer> rs = recvService.addSimRecvResult(model);
		System.out.println(JSONHelper.toString(rs));
	}
	
	@Test
	public void upd(){
		SimRecvResult model = new SimRecvResult();
		model.setId(1096L);
		model.setBusinessId(1617L);
		model.setCode(null);
		model.setContent("JT808-V5.13-OS-5 -D5.28-485@ Oct 30 2015 13:56:26,220.171.96.155,8889,UDP,,0,UDP,013832164474,CMMTM,GPRS,GPRS,,00,0000,0,2164474,70110,644");
		//model.setCreateDate(new Date());
		model.setIccid("898602B7101700082722");
		model.setMobile("1064873972724");
		model.setPushResult("上行成功");
		model.setReason(null);
		model.setReceiveMobile("12312");
		model.setSerialNumber("49436702624423226");
		model.setSmsCreateDate(null);
		model.setSpNumber("");
		model.setStatus(1);
		ResultJson<Integer> rs = recvService.saveSimRecvResult(model);
		System.out.println(JSONHelper.toString(rs));
	}
	
	@Test
	public void q(){
		SimRecvResultQuery query = new SimRecvResultQuery();
		query.setIccid("898602B7101700082737");
		PageInfo<SimRecvResult> p = recvService.queryByPage(query);
		System.out.println(JSONHelper.toString(p));
	}
}
