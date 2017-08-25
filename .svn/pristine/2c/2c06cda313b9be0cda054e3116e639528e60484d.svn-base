package com.amwell.ecar.handler.sim;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import com.amwell.ecar.handler.base.BaseResponseHandler;
import com.amwell.ecar.vo.result.ECarGprsStatusWrapperResult;


public class SIMGprsStatusHandler extends BaseResponseHandler implements ResponseHandler<ECarGprsStatusWrapperResult> {

	@Override
	public ECarGprsStatusWrapperResult handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		return super.doResponse(response, ECarGprsStatusWrapperResult.class);
	}

}
