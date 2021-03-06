package com.amwell.service.simsendrecord;

import java.util.List;

import com.amwell.model.simsendrecord.SimSendRecord;
import com.amwell.model.simsendrecord.SimSendRecordIccidCount;
import com.amwell.model.simsendrecord.SimSendRecordQuery;
import com.amwell.service.IService;
import com.amwell.util.ResultJson;
import com.github.pagehelper.PageInfo;

/**
 * 短信发送接口
 * @author 番茄很忙
 *
 */
public interface SimSendRecordService extends IService<SimSendRecord> {

	/**
	 * 发送短信
	 * @param model
	 * @return
	 */
	public ResultJson<Integer> sendMessage(SimSendRecord model);
	
	
	/**
	 * 分页查询发送的短信记录
	 * @param query
	 * @return
	 */
	public PageInfo<SimSendRecord> queryByPage(SimSendRecordQuery query);
	
	/**
	 * 按iccid统计短信发送数
	 * @param query
	 * @return
	 */
	public List<SimSendRecordIccidCount> querySendIccidCount(SimSendRecordQuery query);
	
	/**
	 * 按iccid统计短信发送数
	 * @param iccid
	 * @return
	 */
	public SimSendRecordIccidCount queryById(String iccid,Integer companyId);
	
	
}
