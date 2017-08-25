package com.amwell.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.amwell.ecar.service.ECarInterfaceService;
import com.amwell.ecar.vo.result.ECarSIMListResult;
import com.amwell.ecar.vo.result.ECarSIMListWrapperResult;
import com.amwell.ecar.vo.result.ECarSimBaseInfo;
import com.amwell.model.simbaseInfo.SimBaseInfo;
import com.amwell.service.simbaseInfo.SimBaseInfoService;
import com.amwell.util.DateHelper;
import com.amwell.util.JSONHelper;

/**
 * 从翼卡数据接口拉取数据作业类
 * 
 * @author hxj
 *
 */
@Component
public class PullSIMDataJob {

	private static final int CURR_PAGE=1;
	
	private static final int PAGE_SIZE=3000;
	
	@Autowired
	private ECarInterfaceService eCarInterfaceService;
	
	@Autowired
	private SimBaseInfoService simBaseInfoService;
	
	/**
	 * 6点-23点，每隔10分钟执行一次数据扫描
	 */
	@Scheduled(cron="0 0/10 6-23 * * ?")
	public void executeWithFixedRate() {
		System.out.println(Thread.currentThread().getId()+" executeWithFixedRate execute at:"+DateHelper.getNowDateTimeStr());
		try {
			//查询过去12分钟内的数据
			ECarSIMListWrapperResult  result = eCarInterfaceService.batchQuerySimBaseInfo(null,null, DateHelper.getPassedMinute(-12), DateHelper.getNowDateTimeStr(), CURR_PAGE, PAGE_SIZE);
			handleData(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getId()+" executeWithFixedRate execute finished:"+DateHelper.getNowDateTimeStr());
	}
	
	/**
	 * 凌晨1点执行一次数据扫描，补全前一天的数据
	 */
	@Scheduled(cron="0 0 1 * * ?")
	public void executeWithCron(){
		System.out.println(Thread.currentThread().getId()+" executeWithCron execute at:"+DateHelper.getNowDateTimeStr());
		try {
			String yesterday = DateHelper.getYesterDateStr();
			String startDate = yesterday+" 00:00:00";
			ECarSIMListWrapperResult  result = eCarInterfaceService.batchQuerySimBaseInfo(null,null,startDate, DateHelper.getNowDateTimeStr(), CURR_PAGE, Integer.MAX_VALUE);
			handleData(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getId()+" executeWithCron execute finished:"+DateHelper.getNowDateTimeStr());
	}
	
	private void handleData(ECarSIMListWrapperResult result) {
		System.out.println(JSONHelper.toString(result));
		if(result!=null&&result.getSuccess().equals("true")&&result.getClass()!=null){
			ECarSIMListResult simListResult = result.getData();
			Long totalCount = simListResult.getTotalCount();
			System.out.println("totalCount="+totalCount);
			List<ECarSimBaseInfo> simList = simListResult.getList();
			for(ECarSimBaseInfo simInfo : simList){
				simBaseInfoService.addSimBaseInfo(convertModel(simInfo));
			}
		}
	}


	private SimBaseInfo convertModel(ECarSimBaseInfo simInfo) {
		//System.out.println("simInfo="+JSONHelper.toString(simInfo));
		SimBaseInfo model = new SimBaseInfo();
		if(StringUtils.hasText(simInfo.getActiveDate())){
			model.setActivationTime(DateHelper.getDateTimeStr(new Date(Long.valueOf(simInfo.getActiveDate()))));
		}
		if(StringUtils.hasText(simInfo.getDistributeDate())){
			model.setDistributeTime(DateHelper.getDateTimeStr(new Date(Long.valueOf(simInfo.getDistributeDate()))));
		}
		model.setFlowCycle(Integer.parseInt(simInfo.getFlowCycle()));
		model.setIccid(simInfo.getIccid());
		model.setMsisdn(simInfo.getMsisdn());
		if(StringUtils.hasText(simInfo.getServiceStartDate())){
			model.setServiceStartDate(DateHelper.getDateTimeStr(new Date(Long.valueOf(simInfo.getServiceStartDate()))));
		}
		if(StringUtils.hasText(simInfo.getServiceEndDate())){
			model.setServiceEndDate(DateHelper.getDateTimeStr(new Date(Long.valueOf(simInfo.getServiceEndDate()))));
		}
		if(StringUtils.hasText(simInfo.getTotalFlow())){
			model.setTotalFlow(Double.valueOf(simInfo.getTotalFlow()));
		}
		if(StringUtils.hasText(simInfo.getUsedFlow())){
			model.setUsedFlow(Double.valueOf(simInfo.getUsedFlow()));
		}
		if(StringUtils.hasText(simInfo.getCardState())){
			model.setCardState(simInfo.getCardState());
		}
		//System.out.println("model="+JSONHelper.toString(model));
		return model;
	}
}
