package com.amwell.credit.handler.login;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import com.amwell.credit.handler.base.CreditResponseHandler;
import com.amwell.credit.result.LoginResult;

public class LoginResultHandler extends CreditResponseHandler  implements ResponseHandler<LoginResult>{

	@Override
	public LoginResult handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		return super.doResponse(response, LoginResult.class);
	}

}
