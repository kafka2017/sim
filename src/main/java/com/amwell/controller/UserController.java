package com.amwell.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amwell.model.User;
import com.amwell.model.UserRole;
import com.amwell.service.UserRoleService;
import com.amwell.service.UserService;
import com.amwell.util.PasswordHelper;
import com.amwell.util.ResultJson;
import com.amwell.util.StatusCode;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/users")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private UserRoleService userRoleService;
	
	/**
	 * 根据id修改密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@RequestMapping(value="/updateUserPassword",method = RequestMethod.POST)
	public ResultJson<User> updateUserPassword(String oldPassword,String newPassword){
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User)session.getAttribute("userSession");
		if(user==null){
			return ResultJson.buildFailedMsg(StatusCode.LOGIN_OUT, "登录超时");
		}
		return userService.updateUserPassword(user.getId(), oldPassword, newPassword);
	}
	
	/**
	 * 根据登录名修改密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@RequestMapping(value="/updateUserPasswordByname",method = RequestMethod.POST)
	public ResultJson<User> updateUserPasswordByname(String username,String oldPassword,String newPassword){
		return userService.updateUserPassword(username, oldPassword, newPassword);
	}

	@RequestMapping
	public Map<String, Object> getAll(User user, String draw,
			@RequestParam(required = false, defaultValue = "1") int start,
			@RequestParam(required = false, defaultValue = "10") int length) {
		Map<String, Object> map = new HashMap<>();
		PageInfo<User> pageInfo = userService.selectByPage(user, start, length);
		System.out.println("pageInfo.getTotal():" + pageInfo.getTotal());
		map.put("draw", draw);
		map.put("recordsTotal", pageInfo.getTotal());
		map.put("recordsFiltered", pageInfo.getTotal());
		map.put("data", pageInfo.getList());
		return map;
	}
	
	/**
	 * 获取用户信息
	 * @return
	 */
	@RequestMapping(value="queryUser")
	public ResultJson<User> queryUser(){
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User)session.getAttribute("userSession");
		if(user==null){
			return ResultJson.buildFailedMsg(StatusCode.LOGIN_OUT, "登录超时");
		}
		User us = userService.queryUser(user.getId());
		return ResultJson.buildSuccessMsg(us, StatusCode.SUCCESS, "查询成功");
	}

	/**
	 * 保存用户角色
	 * 
	 * @param userRole
	 *            用户角色 此处获取的参数的角色id是以 “,” 分隔的字符串
	 * @return
	 */
	@RequestMapping("/saveUserRoles")
	public String saveUserRoles(UserRole userRole) {
		if (StringUtils.isEmpty(userRole.getUserid()))
			return "error";
		try {
			userRoleService.addUserRole(userRole);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@RequestMapping(value = "/add")
	public String add(User user) {
		User u = userService.selectByUsername(user.getUsername());
		if (u != null)
			return "error";
		try {
			user.setEnable(1);
			user.setPassword(PasswordHelper.getEncryptPassword(user.getUsername(),user.getPassword()));
			userService.save(user);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@RequestMapping(value = "/delete")
	public String delete(Integer id) {
		try {
			userService.delUser(id);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

}
