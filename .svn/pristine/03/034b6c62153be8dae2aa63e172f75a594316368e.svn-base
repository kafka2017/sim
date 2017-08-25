package com.amwell.ecar.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amwell.ecar.vo.result.ECarDeviceStatusWrapperResult;
import com.amwell.ecar.vo.result.ECarGprsStatusWrapperResult;
import com.amwell.ecar.vo.result.ECarRecvResult;
import com.amwell.ecar.vo.result.ECarSIMListWrapperResult;
import com.amwell.ecar.vo.result.ECarSendListWrapperResult;
import com.amwell.ecar.vo.result.ECarSendMsgWrapperResult;
import com.amwell.ecar.vo.result.ECarSimWrapperResult;
import com.amwell.model.simrecv.SimRecvResult;
import com.amwell.service.recv.RecvSimMessageService;
import com.amwell.test.SpringBaseTestCase;
import com.amwell.util.JSONHelper;
import com.amwell.util.ResultJson;

public class ECarInterfaceServiceTest extends SpringBaseTestCase{

	@Autowired
	private ECarInterfaceService eCarInterfaceService;
	
	@Autowired
	RecvSimMessageService recvSimMessageService;
	
	@Test
	public void testGetSimBaseInfo() {
		ECarSimWrapperResult result = eCarInterfaceService.getSimBaseInfo("898602B7101700082314");
		System.out.println(JSONHelper.toString(result));
	}
	
	@Test
	public void testBatchQuerySimBaseInfo(){
		ECarSIMListWrapperResult result = eCarInterfaceService.batchQuerySimBaseInfo(null, null, null, null, 1, 100);
		System.err.println("result="+JSONHelper.toString(result));
	}
	
	@Test
	public void testBatchQuerySimBaseInfo2(){
		ECarSIMListWrapperResult result = eCarInterfaceService.batchQuerySimBaseInfo(null, null, "2017-07-10 15:53:10", "2017-07-10 15:54:18", 1, 100);
		System.err.println("result="+JSONHelper.toString(result));
	}
	
	
	@Test
	public void testGetDeviceStatusInfo(){
		ECarDeviceStatusWrapperResult result = eCarInterfaceService.getDeviceStatusInfo("ABV");
		System.out.println(JSONHelper.toString(result));
	}

	@Test
	public void testGetGprsStatusInfo(){
		ECarGprsStatusWrapperResult result =eCarInterfaceService.getGprsStatusInfo("898602B7091701270158");
		System.out.println(JSONHelper.toString(result));
	}
	

	@Test
	public void testSendMsg(){
		ECarSendMsgWrapperResult result =eCarInterfaceService.sendMsg("1064873972316", "短信测试");
		System.out.println(JSONHelper.toString(result));
	}
	
	@Test
	public void testQuerySendList(){
		ECarSendListWrapperResult wrapperResult = this.eCarInterfaceService.querySendList("898602B7101700082722", null, null, null, 1, Integer.MAX_VALUE);
		System.out.println("wrapperResult="+JSONHelper.toString(wrapperResult));
		if(wrapperResult!=null&&"true".equals(wrapperResult.getSuccess())&&wrapperResult.getData().getTotal().longValue()>0&&wrapperResult.getData().getRows()!=null){
			List<ECarRecvResult> recvList = wrapperResult.getData().getRows();
			for(ECarRecvResult r : recvList){
				if(r!=null){
					recvSimMessageService.saveSimRecvResult(this.convert(r));
				}
			}
		}
		//ECarSendListWrapperResult result =eCarInterfaceService.querySendList("898602B7101700082722", null, null, null, 1, Integer.MAX_VALUE);
		//System.out.println(JSONHelper.toString(result));
	}
	
	private SimRecvResult convert(ECarRecvResult r) {
		SimRecvResult simRecvResult = new SimRecvResult();
		simRecvResult.setBusinessId(r.getBusinessId());
		simRecvResult.setCode(r.getCode());
		simRecvResult.setContent(r.getContent());
		if(r.getCreateDate()!=null){
			simRecvResult.setCreateDate(new Date(r.getCreateDate()));
		}
		simRecvResult.setIccid(r.getIccid());
		simRecvResult.setId(r.getId());
		simRecvResult.setMobile(r.getMobile());
		simRecvResult.setPushResult(r.getPushResult());
		simRecvResult.setReason(r.getReason());
		simRecvResult.setReceiveMobile(r.getReceiveMobile());
		simRecvResult.setSerialNumber(r.getSerialNumber());
		if(r.getSmsCreateDate()!=null){
			simRecvResult.setSmsCreateDate(new Date(r.getSmsCreateDate()));
		}
		simRecvResult.setSpNumber(r.getSpNumber());
		simRecvResult.setStatus(r.getStatus());
		return simRecvResult;
	}
}
