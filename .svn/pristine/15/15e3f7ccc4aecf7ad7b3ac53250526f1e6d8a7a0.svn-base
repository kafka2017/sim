package com.amwell.job;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.amwell.ecar.service.ECarInterfaceService;
import com.amwell.ecar.vo.result.ECarDeviceStatusWrapperResult;
import com.amwell.ecar.vo.result.ECarGprsStatusWrapperResult;
import com.amwell.ecar.vo.result.ECarSimWrapperResult;
import com.amwell.model.simbaseInfo.SimBaseInfo;
import com.amwell.model.simbaseInfo.SimBaseInfoQuery;
import com.amwell.service.simbaseInfo.SimBaseInfoService;
import com.amwell.util.DateHelper;
import com.github.pagehelper.PageInfo;


/**
 * 从翼卡数据接口拉取SIM卡状态
 * @author hxj
 */
@Component
public class PullSIMStatusJob {
	
	private static final Logger log = Logger.getLogger(PullSIMStatusJob.class);

	@Autowired
	private ECarInterfaceService eCarInterfaceService;
	
	@Autowired
	private SimBaseInfoService simBaseInfoService;
	
	/**
	 * 1分钟
	 */
	private static final long ONE_MINUTE=60000L;
	
	/**
	 * 10分钟
	 */
	private static final long TEN_MINUTE=600000L;
	
	/**
	 * 固定延迟10分钟（即后续任务调度将在上次执行完成后延迟10分钟执行，首次初化延迟值为1分钟）
	 */
	@Scheduled(fixedDelay=TEN_MINUTE,initialDelay=ONE_MINUTE)
	public void pullSimStatus(){
		long start = System.currentTimeMillis();
		log.info(Thread.currentThread().getId()+" pullSimStatus execute at:"+DateHelper.getNowDateTimeStr());
		int currPage=1;
		int pageSize=100;//此处设置为100是为了控制JVM内存
		try {
			SimBaseInfoQuery query = new SimBaseInfoQuery();
			query.setPageNum(currPage);
			query.setPageSize(pageSize);
			PageInfo<SimBaseInfo> page = simBaseInfoService.querySimBaseInfoByPage(query);
			while(page!=null&&page.getTotal()>0&&page.getList()!=null&&page.getTotal()>(currPage*pageSize)){
				log.info("start handle "+currPage+"批数据，totalCount:"+page.getTotal()+",currPage:"+currPage+",pageSize:"+pageSize);
				handleData(page.getList());
				query.setPageNum(++currPage);
				page=simBaseInfoService.querySimBaseInfoByPage(query);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		log.info(Thread.currentThread().getId()+" pullSimStatus execute take:"+(end-start)+"毫秒");
	
	}

	
	private void handleData(List<SimBaseInfo> simList) {
		if(CollectionUtils.isEmpty(simList)){
			return;
		}
		String iccid = null;
		ECarGprsStatusWrapperResult gprsStatusResult =null;
		ECarDeviceStatusWrapperResult deviceStatusResult =null;
		ECarSimWrapperResult simResult =null;
		String gprsStatus =null;
		String deviceStatus=null;
		String cardState=null;
		String usedFlow =null;
		//for开始
		for(SimBaseInfo simInfo : simList){
			iccid = simInfo.getIccid();
			gprsStatusResult = eCarInterfaceService.getGprsStatusInfo(iccid);
			deviceStatusResult = eCarInterfaceService.getDeviceStatusInfo(iccid);
			simResult = eCarInterfaceService.getSimBaseInfo(iccid);
			log.debug(iccid+"接口状态查询结果:");
			if(gprsStatusResult!=null&&"true".equals(gprsStatusResult.getSuccess())){
				gprsStatus = gprsStatusResult.getData().getGprsStatus();
				log.debug("gprsStatus:"+gprsStatus);
			}
			if(deviceStatusResult!=null&&"true".equals(deviceStatusResult.getSuccess())){
				deviceStatus = deviceStatusResult.getData().getDeviceStatus();
				log.debug("deviceStatus:"+deviceStatus);
			}
			if(simResult!=null&&"true".equals(simResult.getSuccess())){
				cardState = simResult.getData().getCardState();
				usedFlow = simResult.getData().getUsedFlow();
				log.debug("cardState:"+cardState);
				log.debug("usedFlow:"+usedFlow);
			}
			if(StringUtils.hasText(gprsStatus)||StringUtils.hasText(deviceStatus)&&StringUtils.hasText(cardState)||StringUtils.hasText(usedFlow)){
				SimBaseInfoQuery query = new SimBaseInfoQuery();
				query.setIccid(iccid);
				query.setPageNum(1);
				query.setPageSize(1);
				List<SimBaseInfo> list = simBaseInfoService.querySimBaseInfo(query);
				SimBaseInfo querySim = null;
				if(CollectionUtils.isNotEmpty(list)){
					querySim = list.get(0);
					querySim.setCardState(cardState);
					querySim.setDeviceStatus(deviceStatus);
					querySim.setGprsStatus(gprsStatus);
					if(StringUtils.hasText(usedFlow)){
						querySim.setUsedFlow(Double.valueOf(usedFlow));
					}
					querySim.setLastUpdateTime(DateHelper.getDateTimeStr(new Date()));
					simBaseInfoService.updSimBaseInfo(querySim);
				}
			}
		}
	  //for结束
	  simList=null;
	}
}
