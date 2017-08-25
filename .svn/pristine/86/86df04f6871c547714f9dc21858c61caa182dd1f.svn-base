package com.amwell.credit.handler.base;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class CreditResponseHandler {

	public  <T> T  doResponse(HttpResponse response,Class<T> valueType){
		try {
			StatusLine statusLine = response.getStatusLine();
			HttpEntity entity = response.getEntity();
			if (statusLine.getStatusCode() == 200 && entity!=null) {
				Charset charset = Charset.forName("utf-8");
				Reader reader = new InputStreamReader(entity.getContent(), charset);
				ObjectMapper objectMapper = new ObjectMapper();
				//反序列时，如果Java对象中不包含json字符串的字段名称，则不予反序列化
				objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				return objectMapper.readValue(reader, valueType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
