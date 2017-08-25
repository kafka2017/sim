package com.amwell.ecar.vo.result;

import com.amwell.ecar.vo.base.ECarCommonResult;

public class ECarSendListWrapperResult extends ECarCommonResult {

	private static final long serialVersionUID = 2639633419626271280L;

	private ECarSendListResult data;

	public ECarSendListResult getData() {
		return data;
	}

	public void setData(ECarSendListResult data) {
		this.data = data;
	}

}
