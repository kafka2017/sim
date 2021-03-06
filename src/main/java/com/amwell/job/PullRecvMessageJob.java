package com.amwell.job;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.amwell.ecar.service.ECarInterfaceService;
import com.amwell.ecar.vo.result.ECarRecvResult;
import com.amwell.ecar.vo.result.ECarSendListWrapperResult;
import com.amwell.model.simbaseInfo.SimBaseInfo;
import com.amwell.model.simbaseInfo.SimBaseInfoQuery;
import com.amwell.model.simrecv.SimRecvResult;
import com.amwell.service.recv.RecvSimMessageService;
import com.amwell.service.simbaseInfo.SimBaseInfoService;
import com.amwell.util.DateHelper;
import com.amwell.util.JSONHelper;
import com.github.pagehelper.PageInfo;

@Component
public class PullRecvMessageJob {

	private static final Logger log = Logger.getLogger(PullRecvMessageJob.class);
	
	@Autowired
	private ECarInterfaceService eCarInterfaceService;
	
	@Autowired
	private SimBaseInfoService simBaseInfoService;
	
	@Autowired
	private RecvSimMessageService recvSimMessageService;
	
	/**
	 * 1分钟
	 */
	private static final long ONE_MINUTE=60000L;
	
	/**
	 * 固定延迟10分钟（即后续任务调度将在上次执行完成后延迟10分钟执行，首次初化延迟值为1分钟）
	 */
	@Scheduled(fixedDelay=ONE_MINUTE,initialDelay=ONE_MINUTE)
	public void pullSimMessage(){
		long start = System.currentTimeMillis();
		log.info(Thread.currentThread().getId()+" pullSimMessage execute at:"+DateHelper.getNowDateTimeStr());
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
		log.info(Thread.currentThread().getId()+" pullSimMessage execute take:"+(end-start)+"毫秒");
	
	}

	
	private void handleData(List<SimBaseInfo> simList) {
		if(CollectionUtils.isEmpty(simList)){
			return;
		}
		String iccid = null;
		//for开始
		for(SimBaseInfo simInfo : simList){
			iccid = simInfo.getIccid();
			ECarSendListWrapperResult wrapperResult = this.eCarInterfaceService.querySendList(iccid, null, null, null, 1, Integer.MAX_VALUE);
			System.out.println("wrapperResult="+JSONHelper.toString(wrapperResult));
			if(wrapperResult!=null&&"true".equals(wrapperResult.getSuccess())&&wrapperResult.getData().getTotal().longValue()>0&&wrapperResult.getData().getRows()!=null){
				List<ECarRecvResult> recvList = wrapperResult.getData().getRows();
				for(ECarRecvResult r : recvList){
					if(r!=null){
						this.recvSimMessageService.addSimRecvResult(this.convert(r));
					}
				}
			}
		}
	  //for结束
	  simList=null;
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
