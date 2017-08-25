package com.amwell.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientUtils {

	private static final int connectTimeout = 10000;

	private static final int socketTimeout = 60000;

	public static String doPost(String url, Map<String, Object> paramMap) {
		String result = null;
		long start = System.currentTimeMillis();
		try {
			UrlEncodedFormEntity formEntity = buildUrlEncodedFormEntity(paramMap);
			result = Request.Post(url).useExpectContinue().connectTimeout(connectTimeout).socketTimeout(socketTimeout)
					.body(formEntity).execute().returnContent().asString(Charset.forName("utf-8"));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		if((end-start)>30000){
			System.err.println(url+" take "+(end-start)/1000+" 秒");
		}
		return result;
	}

	public static <T> T doPost(String url, Map<String, Object> paramMap, ResponseHandler<T> handler) {
		try {
			UrlEncodedFormEntity formEntity = buildUrlEncodedFormEntity(paramMap);
			return Request.Post(url).useExpectContinue().connectTimeout(connectTimeout).socketTimeout(socketTimeout)
					.body(formEntity).execute().handleResponse(handler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static UrlEncodedFormEntity buildUrlEncodedFormEntity(Map<String, Object> paramMap) {
		if (paramMap != null) {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			for (String s : paramMap.keySet()) {
				if (paramMap.get(s).getClass().isArray()) {
					Object[] arr = (Object[]) paramMap.get(s);
					for (Object o : arr) {
						parameters.add(new BasicNameValuePair(s, o.toString()));
					}
				} else {
					parameters.add(new BasicNameValuePair(s, paramMap.get(s).toString()));
				}
			}
			// 构建一个form表单式的实体
			return new UrlEncodedFormEntity(parameters, Charset.forName("UTF-8"));
		}
		return null;
	}

private static final String SERVER_NEW="http://vip4.exlive.cn";
	
	private static final String REQ_CAR_LOGIN=SERVER_NEW+"/gpsonline/GPSAPI";
	
	public static void main(String[] args) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("version", "1");
		paramMap.put("method", "vLoginSystem");
		paramMap.put("name", "粤E055G3");
		paramMap.put("pwd", "000000");
		String res = HttpClientUtils.doPost(REQ_CAR_LOGIN,paramMap);
		System.err.println(res);
	}
}
