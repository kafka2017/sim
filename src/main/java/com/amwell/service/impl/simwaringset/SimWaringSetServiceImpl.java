package com.amwell.service.impl.simwaringset;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amwell.model.simwaringset.SimWaringSet;
import com.amwell.service.impl.BaseService;
import com.amwell.service.simwaringset.SimWaringSetService;
import com.amwell.util.ResultJson;
import com.amwell.util.StatusCode;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service(value="simwarginsetService")
public class SimWaringSetServiceImpl extends BaseService<SimWaringSet> implements SimWaringSetService {

	@Override
	public ResultJson<Integer> addSimWar(SimWaringSet model) {
		
		if(model==null){
			return ResultJson.buildFailedMsg(StatusCode.EXCEPTION, "model为空");
		}
		
		Validator validator = new Validator(); 
	    List<ConstraintViolation> ret = validator.validate(model);
		if(!ret.isEmpty()){
			return ResultJson.buildFailedMsg(0, ret.get(0).getMessage());
		}
		
		try {
			Example example = new Example(SimWaringSet.class);
			Criteria c = example.createCriteria();
			c.andEqualTo("companyId",model.getCompanyId());
			List<SimWaringSet> l = super.selectByExample(example);
			Integer i = 0;
			if(l!=null&&l.size()>0){
				model.setId(l.get(0).getId());
				model.setCompanyId(l.get(0).getCompanyId());
				i = super.updateAll(model);
			}else{
				i = super.save(model);
			}
			if(i>0){
				return ResultJson.buildSuccessMsg(null, StatusCode.SUCCESS, "设置成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return ResultJson.buildFailedMsg(StatusCode.EXCEPTION, "设置失败");
	}

	@Override
	public SimWaringSet querySimWaringSet(Integer companyId) {
		if(companyId==null){
			return new SimWaringSet();
		}
		Example example = new Example(SimWaringSet.class);
		Criteria c = example.createCriteria();
		c.andEqualTo("companyId",companyId);
		List<SimWaringSet> l = super.selectByExample(example);
		if(l!=null && l.size()>0){
			return l.get(0);
		}
		return null;
	}


}
