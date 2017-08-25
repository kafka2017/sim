package com.amwell.mapper.simcompany;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.amwell.model.simcompany.SimCompany;
import com.amwell.model.simcompany.SimCompanyQuery;
import com.amwell.model.simcompany.SimCompanyStatTreeNode;
import com.amwell.model.simcompany.SimCompanyTreeNode;
import com.amwell.util.MyMapper;

public interface SimCompanyMapper extends MyMapper<SimCompany>{

	/**
	 * 查询是否存在
	 * @param col 字段名
	 * @param val 字段值
	 * @param id  主键，为null，添加使用，否更新使用
	 * @return
	 */
	public Integer isExisten(@Param("col") String col,@Param("val") String val,@Param("id") Integer id);
	
	/**
	 * 更新，乐观锁控制
	 * @param simcompany
	 * @return
	 */
	public Integer updSimCompanyLock(SimCompany simcompany);
	
	/**
	 * 查询公司列表
	 * @param query
	 * @return
	 */
	public List<SimCompany> querySimCompany(SimCompanyQuery query);

	/**
	 * 查询公司详情
	 * @param id 公司id
	 * @return
	 */
	public List<SimCompany> queryById(Integer id);

	/**
	 * 通过函数来查询所有当前公司及其子公司信息
	 * @param companyId
	 * @return
	 */
	public List<SimCompany> queryTreeById(Integer companyId);

	/**
	 * 获取当前公司信息
	 * @param id
	 * @return
	 */
	public SimCompanyTreeNode queryTreeRootNodeById(Integer id);

	/**
	 * 获取传入公司的直接子公司信息
	 * @param parentId
	 * @return
	 */
	public List<SimCompanyTreeNode> queryTreeChildNodesByParentId(Integer parentId);

	public SimCompanyStatTreeNode queryStatTreeRootNodeById(Integer id);

	public List<SimCompanyStatTreeNode> queryStatTreeChildNodesByParentId(Integer parentId);

	public SimCompanyStatTreeNode queryNodeStatData(@Param("id")Integer id,@Param("flow")Double flow);

	public SimCompany querySimCompanyByShortName(String shortName);
	
}
