package com.amwell.credit.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.amwell.credit.common.RequestUrlConstants;
import com.amwell.credit.service.CreditQueryService;
import com.amwell.util.HttpClientUtils;

@Service
public class CreditQueryServiceImpl implements CreditQueryService {

	@Override
	public String queryCreditInfo(Map<String, Object> paraMap) {
		return HttpClientUtils.doPost(RequestUrlConstants.INTERFACE_URL, paraMap);
	}

}
