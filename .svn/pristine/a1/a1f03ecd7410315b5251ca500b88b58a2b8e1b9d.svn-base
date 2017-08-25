package com.amwell.service;

import com.amwell.model.User;
import com.amwell.util.ResultJson;
import com.github.pagehelper.PageInfo;

public interface UserService extends IService<User> {

	PageInfo<User> selectByPage(User user, int start, int length);

	/**
	 * 查询有效的账户
	 * @param username
	 * @return
	 */
	User selectByUsername(String username);

	void delUser(Integer userid);

	/**
	 * 修改用户密码
	 * @param id 主键
	 * @param oldPassword 原密码，若传值，则会验证原密码
	 * @param newPassword 新密码
	 * @return
	 */
	ResultJson<User> updateUserPassword(Integer id,String oldPassword,String newPassword);

	/**
	 * 修改用户密码
	 * @param username 登录名
	 * @param oldPassword 原密码，若传值，则会验证原密码
	 * @param newPassword 新密码
	 * @return
	 */
	ResultJson<User> updateUserPassword(String username,String oldPassword,String newPassword);
	
	/**
	 * 根据id获取用户信息，包含所属公司
	 * @param id 用户id
	 * @return
	 */
	User queryUser(Integer id);
}
