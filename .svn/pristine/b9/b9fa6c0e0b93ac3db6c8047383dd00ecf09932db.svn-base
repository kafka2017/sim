package com.amwell.ecar.handler.sim;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import com.amwell.ecar.handler.base.BaseResponseHandler;
import com.amwell.ecar.vo.result.ECarSendListWrapperResult;

public class SIMSendListHandler extends BaseResponseHandler implements ResponseHandler<ECarSendListWrapperResult>{

	@Override
	public ECarSendListWrapperResult handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		return super.doResponse(response, ECarSendListWrapperResult.class);
	}

}
