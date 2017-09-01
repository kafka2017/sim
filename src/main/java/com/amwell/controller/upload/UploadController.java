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
	
	/**
	 * 上传sim卡excel文件
	 * @param attachs
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/uploadExcel",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String uploadExcel(@RequestParam("file")MultipartFile[] attachs){
		List<ExcelSimSendRecord> l = new ArrayList<ExcelSimSendRecord>();
		try {
			System.err.println("----------------------------uploadExcel start-----------------------------------");
            Uploader up = new Uploader(5242880);
            int pre = (int) System.currentTimeMillis();
            if(attachs!=null && attachs.length>0){  
            	MultipartFile mf = attachs[0]; 
                up.setSavePath(EXCEL_URL);
                FTPUtils.upload(mf, up);
            }  
            int finaltime = (int) System.currentTimeMillis();  
            System.out.println("文件上传时间："+(finaltime - pre));
			System.err.println("----------------------------uploadExcel end-----------------------------------");
			System.err.println("----------------------------解析excel start-----------------------------------");
			//System.err.println("upload:"+JSONHelper.toString(up));
			if(up.getUrl()!=null && !up.getUrl().equals("")){
				ExcelUtil<ExcelSimSendRecord> eu = new ExcelUtil<ExcelSimSendRecord>();
				l = eu.readExcelTemplate2ObjByInputStream(attachs[0].getInputStream(), ExcelSimSendRecord.class, 2);
			}
			System.err.println("----------------------------解析excel end-----------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONHelper.toString(l);
	}
	
}
