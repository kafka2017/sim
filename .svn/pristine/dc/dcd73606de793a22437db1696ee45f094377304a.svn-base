package com.amwell.controller.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amwell.model.simsendrecord.ExcelSimSendRecord;
import com.amwell.util.JSONHelper;
import com.amwell.util.excel.ExcelUtil;
import com.amwell.util.ftp.FTPUtils;
import com.amwell.util.ftp.Uploader;

/**
 * 上传控制器类
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/up")
public class UploadController {
	
	private static final String EXCEL_URL="/excel";
	
	
	@RequestMapping(value="/uploadExcel",method=RequestMethod.POST)
	@ResponseBody
	public void uploadImage(@RequestParam("file")MultipartFile[] attachs,HttpServletRequest request,HttpServletResponse response){
		
		try {
			System.err.println("----------------------------uploadExcel start-----------------------------------");
            Uploader up = new Uploader(5242880);
            int pre = (int) System.currentTimeMillis();
            //System.err.println("attachs:"+JSONHelper.toString(attachs));
            if(attachs!=null && attachs.length>0){  
                //记录上传过程起始时的时间，用来计算上传时间  
                //取得上传文件  
            	MultipartFile mf = attachs[0]; 
                up.setSavePath(EXCEL_URL);
                FTPUtils.upload(mf, up);
            }  
            int finaltime = (int) System.currentTimeMillis();  
            System.out.println("文件上传时间："+(finaltime - pre));
			System.err.println("----------------------------uploadExcel end-----------------------------------");
			
			System.err.println("----------------------------解析excel start-----------------------------------");
			List<ExcelSimSendRecord> l = new ArrayList<ExcelSimSendRecord>();
			System.err.println("upload:"+JSONHelper.toString(up));
			if(up.getUrl()!=null && !up.getUrl().equals("")){
				ExcelUtil<ExcelSimSendRecord> eu = new ExcelUtil<ExcelSimSendRecord>();
				//File file = new File(up.getUrl());
				l = eu.readExcelTemplate2ObjByInputStream(attachs[0].getInputStream(), ExcelSimSendRecord.class, 2);
				//System.out.println(JSONHelper.toString(l));
			}
			System.err.println("----------------------------解析excel end-----------------------------------");
			
//			String result = "{\"name\":\""+ up.getFileName() +"\",\"savePath\":\""+ up.getSavePath() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";
//		    result = result.replaceAll( "\\\\", "\\\\" );
//		    result = result.replaceAll( "\\\\", "\\\\" );
//		    System.out.println("result:"+result);
		    response.setContentType("text/html; charset=UTF-8");
		    response.getWriter().print(JSONHelper.toString(l));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
