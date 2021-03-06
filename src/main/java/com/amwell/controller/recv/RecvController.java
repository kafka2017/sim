package com.amwell.controller.recv;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amwell.common.ErrorCodeEnum;
import com.amwell.ecar.service.ECarInterfaceService;
import com.amwell.ecar.vo.result.ECarRecvResult;
import com.amwell.ecar.vo.result.ECarSendListWrapperResult;
import com.amwell.model.User;
import com.amwell.model.simrecv.SimRecvResult;
import com.amwell.model.simrecv.SimRecvResultQuery;
import com.amwell.service.recv.RecvSimMessageService;
import com.amwell.util.JSONHelper;
import com.amwell.util.ResultJson;
import com.amwell.util.StatusCode;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/recv")
public class RecvController {

	
	@Autowired
	RecvSimMessageService recvSimMessageService;
	
	@Autowired
	private ECarInterfaceService eCarInterfaceService;
	
	/**
	 * 分页查询
	 * @return
	 */
	@RequestMapping(value="/queryByPage")
	public ResultJson<PageInfo<SimRecvResult>> queryByPage(SimRecvResultQuery query){
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User)session.getAttribute("userSession");
		if(user==null){
			return ResultJson.buildFailedMsg(StatusCode.LOGIN_OUT, "登录超时");
		}
		PageInfo<SimRecvResult> pi = recvSimMessageService.queryByPage(query);
		
		return ResultJson.buildSuccessMsg(pi, StatusCode.SUCCESS, "查询成功");
	}
	
	@RequestMapping(value="flushSimRecvData")
	public ResultJson<SimRecvResult> flushSimRecvData(String iccid){
		try {
			ECarSendListWrapperResult wrapperResult = this.eCarInterfaceService.querySendList(iccid, null, null, null, 1, Integer.MAX_VALUE);
			System.out.println("wrapperResult="+JSONHelper.toString(wrapperResult));
			if(wrapperResult!=null&&"true".equals(wrapperResult.getSuccess())&&wrapperResult.getData().getTotal().longValue()>0&&wrapperResult.getData().getRows()!=null){
				List<ECarRecvResult> recvList = wrapperResult.getData().getRows();
				for(ECarRecvResult r : recvList){
					if(r!=null){
						this.recvSimMessageService.saveSimRecvResult(this.convert(r));
					}
				}
				return ResultJson.buildSuccessMsg(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultJson.buildFailedMsg(ErrorCodeEnum.illegal_para.getErrorCode(), ErrorCodeEnum.illegal_para.getErrorMsg());
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
