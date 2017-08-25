package com.amwell.ex;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.amwell.util.HttpClientUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Extest {

	private static final String SERVER_NEW="http://119.147.23.108:89";
	private static final String REQ_URL=SERVER_NEW+"/gpsonline/GPSAPI";
	
	@Before
	public void init(){
		
	}
	@Test
	public void testGetRealTimeData() throws JsonProcessingException, IOException{
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("version", "1");
		paramMap.put("method", "vLoginSystem");
		paramMap.put("name", "863014530683340");
		paramMap.put("pwd", "000000");
		String res = HttpClientUtils.doPost(REQ_URL,paramMap);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode tree = mapper.readTree(res);
		String vid  = tree.get("vid").asText();
		String vkey = tree.get("vKey").asText();
		System.err.println("登录返回数据："+res);
		String rs = carlocation(vid,vkey);
		System.err.println("车辆最新位置数据："+rs);
	}
	
	private String carlocation(String vid,String vkey){
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("version", "1");
		paramMap.put("method", "loadLocation");
		paramMap.put("vid", vid);
		paramMap.put("vKey", vkey);
		String rs = HttpClientUtils.doPost(REQ_URL, paramMap);
		return rs;
	}
	
	
}
