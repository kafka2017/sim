package com.amwell.mapper.simbaseInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.amwell.model.simbaseInfo.SimBaseInfo;
import com.amwell.model.simbaseInfo.SimBaseInfoQuery;
import com.amwell.util.MyMapper;

public interface SimBaseInfoMapper extends MyMapper<SimBaseInfo>{

	//是否存在
	public Integer isExisten(@Param("col") String col,@Param("val") String val,@Param("id") Long id);
	
	/**
	 * 更新、乐观锁控制
	 * @param model
	 * @return
	 */
	public Integer updSimBaseInfoLock(SimBaseInfo model);
	
	public List<SimBaseInfo> querySimBaseInfo(SimBaseInfoQuery query);

	public SimBaseInfo queryById(Long id);

	public Integer allocationSimCar(@Param("ids") List<Long> ids, @Param("companyId") Integer companyId);
	
	public Integer updSimBaseInfoByiccid(@Param("deviceStatus")String deviceStatus,@Param("iccid")String iccid);
	
	public Integer updSimBaseInfoGprsStateByiccid(@Param("gprsStatus")String gprsStatus,@Param("iccid")String iccid);
	
	public Integer allocationSim(@Param("startIccid")String startIccid,@Param("endIccid")String endIccid,@Param("startMsisdn")String startMsisdn,@Param("endMsisdn")String endMsisdn,@Param("companyId")Integer companyId);
}
