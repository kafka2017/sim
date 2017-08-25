package com.amwell.credit.common;

import java.io.Serializable;

import com.amwell.util.JSONHelper;

public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 2383978096335114568L;

	public String toString() {
		return JSONHelper.toString(this);
	}
}
