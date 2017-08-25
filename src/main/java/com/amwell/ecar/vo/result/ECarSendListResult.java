package com.amwell.ecar.vo.result;

import java.io.Serializable;
import java.util.List;

public class ECarSendListResult implements Serializable {

	private static final long serialVersionUID = 3256162710710381665L;

	private Long total;

	private List<ECarRecvResult> rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<ECarRecvResult> getRows() {
		return rows;
	}

	public void setRows(List<ECarRecvResult> rows) {
		this.rows = rows;
	}

}
