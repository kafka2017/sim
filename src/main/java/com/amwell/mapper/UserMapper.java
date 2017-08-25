package com.amwell.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.amwell.model.User;
import com.amwell.util.MyMapper;

public interface UserMapper extends MyMapper<User> {
	
	/**
	 * 查询是否存在
	 * @param col 字段名
	 * @param val 字段值
	 * @param id  主键，为null，添加使用，否更新使用
	 * @return
	 */
	public Integer isExisten(@Param("col") String col,@Param("val") String val,@Param("id") Integer id);

	/**
	 * 更新用户密码
	 * @param queryUser 必传字段id,password
	 * @return
	 */
	public Integer updateUserPassword(User queryUser);

	/**
	 * 根据username查询用户
	 * @param username
	 * @return
	 */
	public User selectByUsername(@Param("username")String username);
	
	/**
	 * 包含所属公司
	 * @param id
	 * @return
	 */
	public User queryUser(Integer id);
	
	/**
	 * 更新用户状态
	 * @param companyId
	 * @return
	 */
	public Integer updateUserFlag(@Param("flag")Integer flag,@Param("companyId")Integer companyId);
}