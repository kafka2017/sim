package com.amwell.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONHelper {

	private JSONHelper() {

	}

	public static String toString(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
		try 
		{
			result = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) 
		{
			e.printStackTrace();
		} catch (JsonMappingException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public static <T> T toObject(String s,Class<?> T){
		ObjectMapper mapper = new ObjectMapper();
		T obj = null;
		try {
			obj = (T) mapper.readValue(s, T);
		} catch (JsonParseException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 获取泛型的Collection Type
	 * @param collectionClass 泛型的Collection
	 * @param elementClasses 元素类
	 * @return
	 * @author 番茄很忙
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
	    ObjectMapper mapper = new ObjectMapper();
	    JavaType jt =  mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
		return jt;   
	}
	
}
