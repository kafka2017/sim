package com.amwell.service.impl.simbaseInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.amwell.common.ErrorCodeEnum;
import com.amwell.ecar.service.ECarInterfaceService;
import com.amwell.ecar.vo.result.ECarDeviceStatusWrapperResult;
import com.amwell.ecar.vo.result.ECarGprsStatusWrapperResult;
import com.amwell.mapper.simbaseInfo.SimBaseInfoMapper;
import com.amwell.mapper.simsendrecord.SimSendRecordMapper;
import com.amwell.model.simbaseInfo.SimBaseInfo;
import com.amwell.model.simbaseInfo.SimBaseInfoQuery;
import com.amwell.service.impl.BaseService;
import com.amwell.service.simbaseInfo.SimBaseInfoService;
import com.amwell.util.DateHelper;
import com.amwell.util.ResultJson;
import com.amwell.util.StatusCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import tk.mybatis.mapper.entity.Example;

@Service(value="simBaseInfoService")
public class SimBaseInfoServiceImpl extends BaseService<SimBaseInfo> implements SimBaseInfoService {

	@Resource
	private SimBaseInfoMapper simBaseInfoMapper;
	
	@Resource
	private SimSendRecordMapper simsendrecordMapper;
	
	@Autowired
	private ECarInterfaceService ecarInterfaceService;

	@Override
	public List<SimBaseInfo> queryAll() {
		Example example = new Example(SimBaseInfo.class);
		return super.selectByExample(example);
	}

	@Override
	public ResultJson<Integer> addSimBaseInfo(SimBaseInfo model) {
		if(model==null){
			return ResultJson.buildFailedMsg(StatusCode.SIMBASEINFO_NULL, "SimBaseInfo为空，添加失败");
		}
		
		model.setCreateTime(DateHelper.getDateTimeStr(new Date()));
		model.setLastUpdateTime(DateHelper.getDateTimeStr(new Date()));
		
		//验证
		Validator validator = new Validator(); 
	    List<ConstraintViolation> ret = validator.validate(model);
		if(!ret.isEmpty()){
			return ResultJson.buildFailedMsg(0, ret.get(0).getMessage());
		}
		
		Integer mcount = simBaseInfoMapper.isExisten("msisdn", model.getMsisdn(), null);
		if(mcount>0){
			return ResultJson.buildFailedMsg(StatusCode.SIMBASEINFO_MSISDN_NULL, "msisdn号已存在");
		}
		
		Integer icount = simBaseInfoMapper.isExisten("iccid", model.getIccid(), null);
		if(icount>0){
			return ResultJson.buildFailedMsg(StatusCode.SIMBASEINFO_ICCID_NULL, "iccid已存在");
		}
		
		//添加
		try {
			Integer i = super.save(model);
			if(i>0){
				return ResultJson.buildSuccessMsg(null,StatusCode.SUCCESS,"添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return ResultJson.buildFailedMsg(StatusCode.EXCEPTION, "添加失败");
	}

	@Override
	public ResultJson<Integer> updSimBaseInfo(SimBaseInfo model) {
		
		if(model==null){
			return ResultJson.buildFailedMsg(StatusCode.SIMBASEINFO_NULL, "SimBaseInfo为空，更新失败");
		}
		model.setLastUpdateTime(DateHelper.getDateTimeStr(new Date()));
		//验证
		Validator validator = new Validator(); 
	    List<ConstraintViolation> ret = validator.validate(model);
		if(!ret.isEmpty()){
			return ResultJson.buildFailedMsg(0, ret.get(0).getMessage());
		}
		
		Integer mcount = simBaseInfoMapper.isExisten("msisdn", model.getMsisdn(), model.getId());
		if(mcount>0){
			return ResultJson.buildFailedMsg(StatusCode.SIMBASEINFO_MSISDN_NULL, "msisdn号已存在");
		}
		
		Integer icount = simBaseInfoMapper.isExisten("iccid", model.getIccid(), model.getId());
		if(icount>0){
			return ResultJson.buildFailedMsg(StatusCode.SIMBASEINFO_ICCID_NULL, "iccid已存在");
		}
		
		//更新
		try {
			
			SimBaseInfo sb = super.selectByKey(model.getId());
			if(sb==null){
				return ResultJson.buildFailedMsg(StatusCode.SIMBASEINFO_NULL, "SimBaseInfo对象为空");
			}
			
			model.setVersion(sb.getVersion());//乐观锁并发控制
			
			Integer i = simBaseInfoMapper.updSimBaseInfoLock(model);
			
			if(i>0){
				return ResultJson.buildSuccessMsg(null,StatusCode.SUCCESS, "更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return ResultJson.buildFailedMsg(StatusCode.EXCEPTION, "更新失败");
	}

	@Override
	public SimBaseInfo querySimBaseInfoById(Long id) {
		
		if(id==null){
			return new SimBaseInfo();
		}
		
		SimBaseInfo s = simBaseInfoMapper.queryById(id);
//		if(s!=null){
//			SimSendRecordQuery query = new SimSendRecordQuery();
//			query.setIccid(s.getIccid());
//			List<SimSendRecordIccidCount> l = simsendrecordMapper.queryByiccid(query);
//			s.setSimIccidCount(l);
//		}
		
		return s;
	}

	@Override
	public PageInfo<SimBaseInfo> querySimBaseInfoByPage(SimBaseInfoQuery query) {
        //分页查询
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<SimBaseInfo> simbaseInfoList = simBaseInfoMapper.querySimBaseInfo(query);
        return new PageInfo<>(simbaseInfoList);
	}

	@Override
	public List<SimBaseInfo> querySimBaseInfo(SimBaseInfoQuery query) {
		query.setPageNum(null);
		query.setPageSize(null);
		List<SimBaseInfo> simbaseInfoList = simBaseInfoMapper.querySimBaseInfo(query);
		return simbaseInfoList;
	}

	@Override
	public ResultJson<SimBaseInfo> allocationSimCar(List<Long> ids, Integer companyId) {
		if(CollectionUtils.isNotEmpty(ids)&&null!= companyId&&companyId.intValue()>0){
			simBaseInfoMapper.allocationSimCar(ids,companyId);
			return ResultJson.buildSuccessMsg(null);
		}
		return ResultJson.buildFailedMsg(ErrorCodeEnum.illegal_para.getErrorCode(), ErrorCodeEnum.illegal_para.getErrorMsg());
	}

	@Override
	public ResultJson<String> deviceStatusInfo(String iccid) {
		
		if(iccid==null){
			return ResultJson.buildFailedMsg(null, ErrorCodeEnum.illegal_para.getErrorCode(), ErrorCodeEnum.illegal_para.getErrorMsg());
		}
		
		ECarDeviceStatusWrapperResult esr = ecarInterfaceService.getDeviceStatusInfo(iccid);
		
		if(esr.getSuccess().equals("false")){
			return ResultJson.buildFailedMsg(null,Integer.parseInt(esr.getErrorCode()), esr.getErrorMessage());
		}
		
		if(esr.getData()==null){
			return ResultJson.buildFailedMsg(null, Integer.parseInt(esr.getErrorCode()), esr.getErrorMessage());
		}
		String result = esr.getData().getDeviceStatus();
		try {
			String deviceStatus = "";
			if(result.equals("0")){
				deviceStatus = "关机";
			}else if(result.equals("1")){
				deviceStatus = "开机";
			}else if(result.equals("2")){
				deviceStatus = "未知";
			}
			
			Integer i = simBaseInfoMapper.updSimBaseInfoByiccid(esr.getData().getDeviceStatus(), iccid);
			if(i>0){
				return ResultJson.buildSuccessMsg(deviceStatus, ErrorCodeEnum.op_success.getErrorCode(), ErrorCodeEnum.op_success.getErrorMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	    return ResultJson.buildFailedMsg(null, ErrorCodeEnum.illegal_para.getErrorCode(), ErrorCodeEnum.illegal_para.getErrorMsg());
	}

	@Override
	public ResultJson<String> gprsStatusInfo(String iccid) {
		
		if(iccid==null){
			return ResultJson.buildFailedMsg(null, ErrorCodeEnum.illegal_para.getErrorCode(), ErrorCodeEnum.illegal_para.getErrorMsg());
		}
		
		ECarGprsStatusWrapperResult esr = ecarInterfaceService.getGprsStatusInfo(iccid);
		
		if(esr.getSuccess().equals("false")){
			return ResultJson.buildFailedMsg(null,Integer.parseInt(esr.getErrorCode()), esr.getErrorMessage());
		}
		
		if(esr.getData()==null){
			return ResultJson.buildFailedMsg(null, Integer.parseInt(esr.getErrorCode()), esr.getErrorMessage());
		}
		String result =  esr.getData().getGprsStatus();
		try {
			
			String gprsStatus = "";
			if(result.equals("00")){
				gprsStatus = "暂停";
			}else if(result.equals("01")){
				gprsStatus = "正常";
			}else{
				gprsStatus = "未知";
			}
			
			Integer i = simBaseInfoMapper.updSimBaseInfoGprsStateByiccid(result, iccid);
			if(i>0){
				return ResultJson.buildSuccessMsg(gprsStatus, ErrorCodeEnum.op_success.getErrorCode(), ErrorCodeEnum.op_success.getErrorMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	    return ResultJson.buildFailedMsg(null, ErrorCodeEnum.illegal_para.getErrorCode(), ErrorCodeEnum.illegal_para.getErrorMsg());
	}

	@Override
	public ResultJson<Integer> allocationSimCar(String startIccid, String endIccid,String startMsisdn,String endMsisdn, Integer companyId) {
		
		if(companyId==null){
			return ResultJson.buildFailedMsg(null, 0, "公司id不能为空");
		}
		
		if(StringUtils.isEmpty(startIccid)&& StringUtils.isEmpty(endIccid) && StringUtils.isEmpty(startMsisdn) && StringUtils.isEmpty(endMsisdn)){
			return ResultJson.buildFailedMsg(null, 0, "参数异常");
		}
		
		Integer updNum = simBaseInfoMapper.allocationSim(startIccid, endIccid, startMsisdn, endMsisdn, companyId);
		
		return ResultJson.buildSuccessMsg(null, StatusCode.SUCCESS, "操作成功，分配了"+updNum+"条");
	}
	
	
	
}
