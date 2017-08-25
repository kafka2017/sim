//package com.amwell.controller.security;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.amwell.model.simbaseInfo.SimBaseInfo;
//import com.amwell.service.simbaseInfo.SimBaseInfoService;
//import com.amwell.util.JSONHelper;
//
//@RestController
//public class Endpoint {
//	
//	@Autowired
//	private SimBaseInfoService simbaseInfoService;
//
//	@GetMapping(value="/simbase")
//	public String getSimbaseInfo(){
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<SimBaseInfo> list = simbaseInfoService.queryAll();
//		return JSONHelper.toString(list);
//	}
//}
