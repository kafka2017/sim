package com.amwell.controller.simwaringset;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amwell.model.User;
import com.amwell.model.simwaringset.SimMessageSet;
import com.amwell.service.simwaringset.SimMessageSetService;
import com.amwell.util.ResultJson;
import com.amwell.util.StatusCode;

@RestController
@RequestMapping("/simmessagegset")
public class SimMessageSetController {
	
	@Autowired
	SimMessageSetService simmessagesetService;
	
	/**
	 * 按公司短信流量阈值
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/setSimmessage",method = RequestMethod.POST)
	public ResultJson<Integer> setSimmessage(SimMessageSet entity){
		Session session = SecurityUtils.getSubject().getSession();
		
		User user = (User)session.getAttribute("userSession");
		
		if(user==null){
			return ResultJson.buildFailedMsg(StatusCode.LOGIN_OUT, "登录超时");
		}
		
		entity.setCreateby(user.getId());
		entity.setCreateOn(new Date());
		
		return simmessagesetService.setSimMessageSet(entity);
	}
	
	/**
	 * 根据公司id查询设置的值
	 * @param companyId 公司id
	 * @return
	 */
	@RequestMapping(value="querySimMessageSet")
	public SimMessageSet querySimMessageSet(Integer companyId){
		return simmessagesetService.querySimMessageSet(companyId);
	}
}
