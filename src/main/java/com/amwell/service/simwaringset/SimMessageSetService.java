package com.amwell.service.simwaringset;

import org.springframework.transaction.annotation.Transactional;

import com.amwell.model.simwaringset.SimMessageSet;
import com.amwell.service.IService;
import com.amwell.util.ResultJson;

/**
 * 短信阈值设置接口
 * @author 番茄很忙
 *
 */
public interface SimMessageSetService extends IService<SimMessageSet> {

	/**
	 * 设置短信阈值
	 * @param message
	 * @return
	 */
	ResultJson<Integer> setSimMessageSet(SimMessageSet message);
	
	/**
	 * 根据公司id获取设置的值
	 * @param companyId
	 * @return
	 */
	SimMessageSet querySimMessageSet(Integer companyId);
}
