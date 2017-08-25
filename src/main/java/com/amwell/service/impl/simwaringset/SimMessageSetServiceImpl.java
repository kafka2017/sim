package com.amwell.service.impl.simwaringset;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amwell.model.simwaringset.SimMessageSet;
import com.amwell.service.impl.BaseService;
import com.amwell.service.simwaringset.SimMessageSetService;
import com.amwell.util.ResultJson;
import com.amwell.util.StatusCode;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service(value="simMessageSetService")
public class SimMessageSetServiceImpl extends BaseService<SimMessageSet> implements SimMessageSetService {

	@Override
	public ResultJson<Integer> setSimMessageSet(SimMessageSet message) {
		if(message==null){
			return ResultJson.buildFailedMsg(StatusCode.EXCEPTION, "SimMessageSet为空");
		}
		
		Validator validator = new Validator(); 
	    List<ConstraintViolation> ret = validator.validate(message);
		if(!ret.isEmpty()){
			return ResultJson.buildFailedMsg(0, ret.get(0).getMessage());
		}
		
		try {
			Example example = new Example(SimMessageSet.class);
			Criteria c = example.createCriteria();
			c.andEqualTo("companyId",message.getCompanyId());
			List<SimMessageSet> l = super.selectByExample(example);
			Integer i = 0;
			if(l!=null&&l.size()>0){
				message.setId(l.get(0).getId());
				message.setCompanyId(l.get(0).getCompanyId());
				i = super.updateAll(message);
			}else{
				i = super.save(message);
			}
			if(i>0){
				return ResultJson.buildSuccessMsg(null, StatusCode.SUCCESS, "设置成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return null;
	}

	@Override
	public SimMessageSet querySimMessageSet(Integer companyId) {
		if(companyId==null){
			return new SimMessageSet();
		}
		Example example = new Example(SimMessageSet.class);
		Criteria c = example.createCriteria();
		c.andEqualTo("companyId",companyId);
		List<SimMessageSet> l = super.selectByExample(example);
		if(l!=null&&l.size()>0){
			return l.get(0);
		}
		return null;
	}


}
