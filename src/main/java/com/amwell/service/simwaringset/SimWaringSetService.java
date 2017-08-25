package com.amwell.service.simwaringset;

import org.springframework.transaction.annotation.Transactional;

import com.amwell.model.simwaringset.SimWaringSet;
import com.amwell.service.IService;
import com.amwell.util.ResultJson;

/**
 * 流量阈值设置接口
 * @author 番茄很忙
 *
 */
public interface SimWaringSetService extends IService<SimWaringSet> {

	/**
	 * 设置流量阈值
	 * @param model
	 * @return
	 */
	ResultJson<Integer> addSimWar(SimWaringSet model);
	
	/**
	 * 根据公司查询流量设置值
	 * @param companyId
	 * @return
	 */
	SimWaringSet querySimWaringSet(Integer companyId);
}
