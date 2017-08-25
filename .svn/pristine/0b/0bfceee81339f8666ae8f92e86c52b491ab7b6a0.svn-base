package com.amwell.sendMsg;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amwell.model.simsendrecord.SimSendRecord;
import com.amwell.model.simsendrecord.SimSendRecordQuery;
import com.amwell.service.simsendrecord.SimSendRecordService;
import com.amwell.test.SpringBaseTestCase;
import com.amwell.util.JSONHelper;
import com.amwell.util.ResultJson;
import com.github.pagehelper.PageInfo;

public class SendMsg extends SpringBaseTestCase {

	@Autowired
	SimSendRecordService simsendrecordservice;
	
	@Test
	public void sendmsg(){
		SimSendRecord model = new SimSendRecord();
		model.setContent("短信测试4");
		model.setMsisdn("1064873972316");
		model.setIccid("898602B7091701270016");
		model.setCreateBy(1);
		ResultJson<Integer> i = simsendrecordservice.sendMessage(model);
		System.err.println(JSONHelper.toString(i));
	}
	
	@Test
	public void qbypage(){
		SimSendRecordQuery query = new SimSendRecordQuery();
		PageInfo<SimSendRecord> pi = simsendrecordservice.queryByPage(query);
		System.err.println(JSONHelper.toString(pi));
	}
	
}
