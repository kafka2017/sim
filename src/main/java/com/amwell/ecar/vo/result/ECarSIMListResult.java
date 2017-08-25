package com.amwell.ecar.vo.result;

import java.io.Serializable;
import java.util.List;

public class ECarSIMListResult implements Serializable {

	private static final long serialVersionUID = 302677535810137717L;

	private Long totalCount;

	private List<ECarSimBaseInfo> list;

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public List<ECarSimBaseInfo> getList() {
		return list;
	}

	public void setList(List<ECarSimBaseInfo> list) {
		this.list = list;
	}

}
