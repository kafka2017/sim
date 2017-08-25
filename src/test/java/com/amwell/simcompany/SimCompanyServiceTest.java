package com.amwell.simcompany;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.amwell.config.AmwellSetConfig;
import com.amwell.model.simcompany.SimCompany;
import com.amwell.model.simcompany.SimCompanyQuery;
import com.amwell.model.simcompany.SimCompanyReport;
import com.amwell.model.simcompany.SimCompanyStatTreeNode;
import com.amwell.model.simcompany.SimCompanyTreeNode;
import com.amwell.service.simcompany.SimCompanyService;
import com.amwell.test.SpringBaseTestCase;
import com.amwell.util.JSONHelper;
import com.amwell.util.ResultJson;
import com.github.pagehelper.PageInfo;

public class SimCompanyServiceTest extends SpringBaseTestCase {

	@Autowired
	SimCompanyService simcompanyService;
	
	@Autowired
	AmwellSetConfig aset;
	
	@Test
	public void qppage(){
		SimCompanyQuery query = new SimCompanyQuery();
		query.setPageNum(1);
		query.setPageSize(10);
		PageInfo<SimCompany> pi = simcompanyService.querySimCompanyByPage(query);
		System.err.println(JSONHelper.toString(pi));
	}
	
	@Test
	public void add(){
//		SimCompany model = new SimCompany();
//		model.setAdminType(1);
//		model.setFullName("和合汽车33子公司");
//		model.setShortName("和合33子公司");
//		model.setParentId(5);
//		model.setUsername("admin33");
//		model.setPassword("admin33");
//		model.setFlag(1);
//		model.setRealName("管理员33");
//		ResultJson<Integer> rs = simcompanyService.addSimCompany(model);
//		System.err.println(JSONHelper.toString(rs));
	}
	
	@Test
	public void upd(){
		SimCompany model = new SimCompany();
		model.setAdminType(2);
		model.setFullName("和合汽车5吱吱");
		model.setShortName("和合5吱吱");
		model.setId(65);
		model.setParentId(1);
		model.setFlag(0);
		ResultJson<Integer> rs = simcompanyService.updSimCompany(model);
		System.err.println(JSONHelper.toString(rs));
		
	}
	
	@Test
	public void del(){
		ResultJson<Integer> rs = simcompanyService.delSimCompany(11);
		System.err.println(JSONHelper.toString(rs));
	}
	
	@Test
	public void qbyid(){
		SimCompany model = simcompanyService.queryById(5);
		System.out.println(JSONHelper.toString(model));
	}
	
	@Test
	public void q(){
		SimCompanyQuery query = new SimCompanyQuery();
		//query.setFullName("和合");
		//query.setParentId(5);
		query.setUserid(1);
		Map<String, Object> l;
		try {
			l = simcompanyService.querySimCompany(query);
			System.err.println(JSONHelper.toString(l));
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void rp(){
		SimCompanyQuery query = new SimCompanyQuery();
		//query.setType("silence");
		//query.setUserid(1);
		//query.setParentId(0);
		//query.setMessageTotal(aset.getMessageTotal());
		query.setUserid(1);
		query.setParentId(0);
		query.setType("exceed");
		//query.setPageNum(1);
		//query.setPageSize(10);
		List<SimCompanyReport> ll = simcompanyService.querySimCompanyReport(query);
		System.err.println(JSONHelper.toString(ll));
	}
	
	@Test
	public void testQuerySimCommpanyTree(){
		SimCompanyTreeNode result = this.simcompanyService.querySimCompanyTree(50);
		System.out.println(JSONHelper.toString(result));
	}
	
	@Test
	public void testQuerySimCompanyStatTree(){
		SimCompanyStatTreeNode result = this.simcompanyService.querySimCompanyStatTree(1,null);
		System.err.println(JSONHelper.toString(result));
	}
	
	@Test
	public void testQuerySimCompanyByShortName(){
		SimCompany company = this.simcompanyService.querySimCompanyByShortName("联和安业");
		System.out.println(JSONHelper.toString(company));
	}
}
