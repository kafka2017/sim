package com.amwell.controller.message;

import java.util.Optional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amwell.model.User;
import com.amwell.model.simsendrecord.SimSendRecord;
import com.amwell.model.simsendrecord.SimSendRecordQuery;
import com.amwell.service.simsendrecord.SimSendRecordService;
import com.amwell.util.ResultJson;
import com.amwell.util.StatusCode;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/msg")
public class SendMsgController {

	@Autowired
	SimSendRecordService simsendmsgService;
	
	/**
	 * 发送短信
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/sendMsg",method=RequestMethod.POST)
	public ResultJson<Integer> sendMsg(SimSendRecord model){
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User)session.getAttribute("userSession");
		if(user==null){
			return ResultJson.buildFailedMsg(StatusCode.LOGIN_OUT, "登录超时");
		}
		model.setCreateBy(user.getId());
		return simsendmsgService.sendMessage(model);
	}
	
	/**
	 * 群发短信
	 * @param model
	 * @return
	 */
	@PostMapping(value="/sendMassMsg")
	public ResultJson<Integer> sendMassMsg(SimSendRecord model){
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User)session.getAttribute("userSession");
		if(user==null){
			return ResultJson.buildFailedMsg(StatusCode.LOGIN_OUT, "登录超时");
		}
		model.setCreateBy(user.getId());
		
		Optional<?> op = Optional.ofNullable(model.getArrMsisdn());
		
		if(op.isPresent()){
			String arr[] = model.getArrMsisdn().split(",");
			Integer index = 0;
			for(Integer i=0;i<arr.length;i++){
				model.setMsisdn(arr[i]);
				ResultJson<Integer> rs = simsendmsgService.sendMessage(model);
				index++;
			}
			return ResultJson.buildSuccessMsg(index);
		}
		return ResultJson.buildFailedMsg(StatusCode.EXCEPTION, "短信群发失败");
	}
	
	/**
	 * 分页查询发送的短信
	 * @param query
	 * @return
	 */
	@RequestMapping("/queryByPage")
	public ResultJson<PageInfo<SimSendRecord>> queryByPage(SimSendRecordQuery query){
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User)session.getAttribute("userSession");
		if(user==null){
			return ResultJson.buildFailedMsg(StatusCode.LOGIN_OUT, "登录超时");
		}
		query.setCompanyId(user.getSimcompanyId());
		PageInfo<SimSendRecord> list = simsendmsgService.queryByPage(query);
		return ResultJson.buildSuccessMsg(list, StatusCode.SUCCESS, "查询成功");
	}
	
}
