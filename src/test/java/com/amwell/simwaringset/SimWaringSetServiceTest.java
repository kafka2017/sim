package com.amwell.simwaringset;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amwell.model.simwaringset.SimWaringSet;
import com.amwell.service.simwaringset.SimWaringSetService;
import com.amwell.test.SpringBaseTestCase;
import com.amwell.util.JSONHelper;

public class SimWaringSetServiceTest extends SpringBaseTestCase {

	@Autowired
	SimWaringSetService simwaringservice;
	
	@Test
	public void q(){
		SimWaringSet l = simwaringservice.selectByKey(1);
		System.err.println(JSONHelper.toString(l));
	}
}
