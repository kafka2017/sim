package com.amwell.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amwell.service.IRedisService;
import com.amwell.test.SpringBaseTestCase;

public class RedisServiceTest extends SpringBaseTestCase {

	@Autowired
	IRedisService redisService;
	
	@Test
	public void set(){
		boolean b = redisService.set("t", "t");
		System.out.println(b);
	}
}
