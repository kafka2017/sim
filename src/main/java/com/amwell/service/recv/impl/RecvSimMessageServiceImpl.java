package com.amwell.service.recv.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.amwell.mapper.recv.RecvSimResultMapper;
import com.amwell.model.simrecv.SimRecvResult;
import com.amwell.model.simrecv.SimRecvResultQuery;
import com.amwell.service.impl.BaseService;
import com.amwell.service.recv.RecvSimMessageService;
import com.amwell.util.ResultJson;
import com.amwell.util.StatusCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RecvSimMessageServiceImpl extends BaseService<SimRecvResult> implements RecvSimMessageService{
	
	@Resource
	private RecvSimResultMapper recvSimResultMapper;

	@Override
	public ResultJson<Integer> addSimRecvResult(SimRecvResult model) {
		
		SimRecvResult sr = super.selectByKey(model.getId());
		if(sr!=null){
			return ResultJson.buildFailedMsg(null, StatusCode.EXCEPTION, "数据已存在");
		}
		
		Integer i = super.save(model);
		
		if(i>0){
			return ResultJson.buildSuccessMsg(null, StatusCode.SUCCESS, "添加成功");
		}
		
		return ResultJson.buildFailedMsg(null, StatusCode.EXCEPTION, "添加失败");
	}

	@Override
	public PageInfo<SimRecvResult> queryByPage(SimRecvResultQuery query) {
//		Example example = new Example(SimRecvResult.class);
//        Criteria criteria = example.createCriteria();
//        
//        if (query.getIccid() != null) {
//            criteria.andEqualTo("iccid", query.getIccid());
//        }
        //分页查询
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<SimRecvResult> userList = recvSimResultMapper.querySimRecvResult(query);
        return new PageInfo<>(userList);
	}

	@Override
	public ResultJson<Integer> saveSimRecvResult(SimRecvResult model) {
		SimRecvResult sr = super.selectByKey(model.getId());
		if(sr!=null){
			Integer u = super.updateNotNull(model);
			if(u>0){
				return ResultJson.buildSuccessMsg(null, StatusCode.SUCCESS, "更新成功");
			}
		}else{
			Integer i = super.save(model);
			
			if(i>0){
				return ResultJson.buildSuccessMsg(null, StatusCode.SUCCESS, "添加成功");
			}
		}
		return ResultJson.buildFailedMsg(null, StatusCode.EXCEPTION, "添加失败");
	}

}
