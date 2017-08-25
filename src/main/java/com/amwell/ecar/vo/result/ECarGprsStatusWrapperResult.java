package com.amwell.ecar.vo.result;

import com.amwell.ecar.vo.base.ECarCommonResult;

/**
 * SIM卡GPRS状态
 * @author hxj
 */
public class ECarGprsStatusWrapperResult extends ECarCommonResult {


	private static final long serialVersionUID = -6377233154036900377L;
	
	private ECarGprsStatus data;

	public ECarGprsStatus getData() {
		return data;
	}

	public void setData(ECarGprsStatus data) {
		this.data = data;
	}

}
