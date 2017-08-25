package com.amwell.controller.simwaringset;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amwell.model.User;
import com.amwell.model.simwaringset.SimWaringSet;
import com.amwell.service.simwaringset.SimWaringSetService;
import com.amwell.util.ResultJson;
import com.amwell.util.StatusCode;

@RestController
@RequestMapping("/simwaringset")
public class SimWaringSetController {
	
	@Autowired
	SimWaringSetService simwaringsetService;
	
	/**
	 * 按公司设置流量阈值
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/setSimwaring",method = RequestMethod.POST)
	public ResultJson<Integer> setSimwaring(SimWaringSet entity){
		Session session = SecurityUtils.getSubject().getSession();
		
		User user = (User)session.getAttribute("userSession");
		
		if(user==null){
			return ResultJson.buildFailedMsg(StatusCode.LOGIN_OUT, "登录超时");
		}
		
		entity.setCreateby(user.getId());
		entity.setCreateOn(new Date());
		
		return simwaringsetService.addSimWar(entity);
	}
	
	/**
	 * 
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value="querySimWaringSet")
	public SimWaringSet querySimWaringSet(Integer companyId){
		return simwaringsetService.querySimWaringSet(companyId);
	}
}
