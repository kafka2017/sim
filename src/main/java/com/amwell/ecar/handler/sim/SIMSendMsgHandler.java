package com.amwell.ecar.handler.sim;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import com.amwell.ecar.handler.base.BaseResponseHandler;
import com.amwell.ecar.vo.result.ECarSendMsgWrapperResult;


public class SIMSendMsgHandler extends BaseResponseHandler implements ResponseHandler<ECarSendMsgWrapperResult> {

	@Override
	public ECarSendMsgWrapperResult handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		return super.doResponse(response, ECarSendMsgWrapperResult.class);
	}

}
