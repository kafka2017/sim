package com.amwell.controller.simcompany;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amwell.common.ErrorCodeEnum;
import com.amwell.model.User;
import com.amwell.model.simcompany.SimCompany;
import com.amwell.model.simcompany.SimCompanyQuery;
import com.amwell.model.simcompany.SimCompanyReport;
import com.amwell.model.simcompany.SimCompanyStatTreeNode;
import com.amwell.model.simcompany.SimCompanyTreeNode;
import com.amwell.service.simcompany.SimCompanyService;
import com.amwell.util.JSONUtil;
import com.amwell.util.ResultJson;
import com.amwell.util.StatusCode;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 分公司账户
 * @author 番茄很忙
 *
 */
@RestController
@RequestMapping("/simcompany")
public class SimCompanyController {
	
	@Autowired
	private SimCompanyService simcompanyService;
	
	/**
	 * 统计父级公司的sim数量
	 * @param query
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping(value="/querySimCompanyReport")
	public Map<String,Object> querySimCompanyReport(SimCompanyQuery query) throws JSONException{
		
		Session session = SecurityUtils.getSubject().getSession();
		
		User user = (User)session.getAttribute("userSession");
		Map<String,Object> map = new HashMap<String,Object>();
		if(user==null){
			map.put("code", StatusCode.LOGIN_OUT);
			map.put("msg", "登录超时");
			return map;
		}
		
		query.setUserid(user.getId());
		
		query.setParentId(0);//父级
		
		List<SimCompanyReport> l = simcompanyService.querySimCompanyReport(query);
		
		JSONArray tree = new JSONArray();
		//迭代所有的权限集合
		for(int i=0;i<l.size();i++){
			//取出这个对象
			SimCompanyReport simcompany = l.get(i);
			JSONObject one = new JSONObject();
			one.put("id", simcompany.getId());//设置权限ID
			one.put("pId", simcompany.getParentId());//设置父权限ID
			one.put("name", simcompany.getShortName());//设置权限的名称
			one.put("open", "true");//设置菜单出事话是否展开
			
			one.put("countEnable", simcompany.getCountEnable());
			one.put("countUnknown", simcompany.getCountUnknown());
			one.put("countDisable", simcompany.getCountDisable());
			one.put("countSilence", simcompany.getCountSilence());
			one.put("countExceed", simcompany.getCountExceed());
			one.put("countWarn", simcompany.getCountWarn());
			tree.put(one);
		}
		map.put("tree", JSONUtil.toJSONString(tree));
		return map;
	}
	
	/**
	 * 不带统计数据的公司树
	 * @return
	 */
	@RequestMapping(value="/querySimCompanyTree")
	public ResultJson<SimCompanyTreeNode> querySimCompanyTree(){
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User)session.getAttribute("userSession");
		if(user!=null&&user.getSimcompanyId()!=null){
			SimCompanyTreeNode result =  this.simcompanyService.querySimCompanyTree(user.getSimcompanyId());
			return ResultJson.buildSuccessMsg(result);
		}
		return ResultJson.buildFailedMsg(ErrorCodeEnum.login_timeout.getErrorCode(), ErrorCodeEnum.login_timeout.getErrorMsg());
	}
	
	/**
	 * 带统计数据的公司树
	 * @return
	 */
	@RequestMapping(value="/querySimCompanyStatTree")
	public ResultJson<SimCompanyStatTreeNode> querySimCompanyStatTree(String type){
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User)session.getAttribute("userSession");
		if(user!=null&&user.getSimcompanyId()!=null){
			SimCompanyStatTreeNode result =  this.simcompanyService.querySimCompanyStatTree(user.getSimcompanyId(),type);
			return ResultJson.buildSuccessMsg(result);
		}
		return ResultJson.buildFailedMsg(ErrorCodeEnum.login_timeout.getErrorCode(), ErrorCodeEnum.login_timeout.getErrorMsg());
	}
	
	/**
	 * 统计下级公司不同的sim数量
	 * @param query
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping(value="/querySimCompanyReportBypid")
	public Map<String,Object> querySimCompanyReportBypid(Integer pid) throws JSONException{
		
		SimCompanyQuery query = new SimCompanyQuery();
		query.setParentId(pid);//父级
		
		List<SimCompanyReport> l = simcompanyService.querySimCompanyReport(query);
		Map<String,Object> map = new HashMap<String,Object>();
		JSONArray tree = new JSONArray();
		//迭代所有的权限集合
		for(int i=0;i<l.size();i++){
			//取出这个对象
			SimCompanyReport simcompany = l.get(i);
			JSONObject one = new JSONObject();
			one.put("id", simcompany.getId());//设置权限ID
			one.put("pId", simcompany.getParentId());//设置父权限ID
			one.put("name", simcompany.getShortName());//设置权限的名称
			one.put("open", "flase");//设置菜单出事话是否展开
			
			one.put("countEnable", simcompany.getCountEnable());
			one.put("countUnknown", simcompany.getCountUnknown());
			one.put("countDisable", simcompany.getCountDisable());
			one.put("countSilence", simcompany.getCountSilence());
			one.put("countExceed", simcompany.getCountExceed());
			one.put("countWarn", simcompany.getCountWarn());
			tree.put(one);
		}
		map.put("tree", JSONUtil.toJSONString(tree));
		return map;
	}

	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value="分公司账户分页查询", notes="根据SimCompanyQuery对象查询分公司账户")
    @ApiImplicitParam(name = "SimCompanyQuery", value = "分页查询条件", required = false, dataType = "SimCompanyQuery")
	@RequestMapping(value="/queryByPage")
	public ResultJson<PageInfo<SimCompany>> queryByPage(SimCompanyQuery query){
		
		Session session = SecurityUtils.getSubject().getSession();
		
		User user = (User)session.getAttribute("userSession");
		
		if(user==null){
			return ResultJson.buildFailedMsg(null, StatusCode.LOGIN_OUT, "登录超时");
		}
		
		query.setUserid(user.getId());
		PageInfo<SimCompany> list = simcompanyService.querySimCompanyByPage(query);
		return ResultJson.buildSuccessMsg(list, StatusCode.SUCCESS, "查询成功");
	}
	
	/**
	 * 创建分公司
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addSimCompany",method=RequestMethod.POST)
	public ResultJson<Integer> addSimCompany(SimCompany model){
		Session session = SecurityUtils.getSubject().getSession();
		
		User user = (User)session.getAttribute("userSession");
		
		if(user==null){
			return ResultJson.buildFailedMsg(null, StatusCode.LOGIN_OUT, "登录超时");
		}
		
		//model.setParentId(user.getSimcompanyId());//公司id
		
		return simcompanyService.addSimCompany(model);
	}
	
	/**
	 * 修改
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updSimCompany",method=RequestMethod.POST)
	public ResultJson<Integer> updSimCompany(SimCompany model){
		Session session = SecurityUtils.getSubject().getSession();
		
		User user = (User)session.getAttribute("userSession");
		
		if(user==null){
			return ResultJson.buildFailedMsg(null, StatusCode.LOGIN_OUT, "登录超时");
		}
		return simcompanyService.updSimCompany(model);
	}
	
	/**
	 * 获取单条
	 * @param id 公司id
	 * @return
	 */
	@RequestMapping(value="/queryById")
	public SimCompany queryById(Integer id){
		Session session = SecurityUtils.getSubject().getSession();
		
		User user = (User)session.getAttribute("userSession");
		
		if(user==null){
			return new SimCompany();
		}
		
		return simcompanyService.queryById(id);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delSimCompany")
	public ResultJson<Integer> delSimCompany(Integer id){
		Session session = SecurityUtils.getSubject().getSession();
		
		User user = (User)session.getAttribute("userSession");
		
		if(user==null){
			return ResultJson.buildFailedMsg(null, StatusCode.LOGIN_OUT, "登录超时");
		}
		return simcompanyService.delSimCompany(id);
	}
	
	/**
	 * 查询父公司
	 * @param query
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping(value="/querySimCompany")
	public Map<String,Object> querySimCompany(SimCompanyQuery query) throws JSONException{
		Session session = SecurityUtils.getSubject().getSession();
		
		User user = (User)session.getAttribute("userSession");
		Map<String,Object> map = new HashMap<String,Object>();
		if(user==null){
			map.put("code", StatusCode.LOGIN_OUT);
			map.put("msg", "登录超时");
			return map;
		}
		
//		if(query.getParentId()==null){
//			query.setParentId(0);
//		}
		
		query.setUserid(user.getId());
	    map = simcompanyService.querySimCompany(query);
		return map;
	}
	
	/**
	 * 查询子公司
	 * @param query
	 * @return
	 */
	@RequestMapping(value="/querySimCompanyChild")
	public Map<String,Object> querySimCompanyChild(SimCompanyQuery query) throws JSONException{
		Session session = SecurityUtils.getSubject().getSession();
		
		User user = (User)session.getAttribute("userSession");
		Map<String,Object> map = new HashMap<String,Object>();
		if(user==null){
			map.put("code", StatusCode.LOGIN_OUT);
			map.put("msg", "登录超时");
			return map;
		}
		query.setParentId(user.getSimcompanyId());
		
		//query.setUserid(user.getId());
	    map = simcompanyService.querySimCompany(query);
		return map;
	}
	
	/**
	 * 根据pid获取菜单
	 * @param query
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping(value="/querySimCompanyBypId")
	public Map<String,Object> querySimCompanyBypId(Integer pid) throws JSONException{
		SimCompanyQuery query = new SimCompanyQuery();
		query.setParentId(pid);
		Map<String,Object>  map = simcompanyService.querySimCompany(query);
		return map;
	}
	
	/**
	 * 根据公司简称获取公司名称
	 * @param shortName 公司简称
	 * @return
	 */
	@RequestMapping(value="/querySimCompanyByShortName")
	public SimCompany querySimCompanyByShortName(String shortName){
		return simcompanyService.querySimCompanyByShortName(shortName);
	}
	
}
