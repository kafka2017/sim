package com.amwell.excel;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.amwell.model.simsendrecord.ExcelSimSendRecord;
import com.amwell.util.JSONHelper;
import com.amwell.util.excel.ExcelUtil;

public class ExcelTest {

	
	@Test
	public void readExcel(){
		ExcelUtil<ExcelSimSendRecord> eu = new ExcelUtil<ExcelSimSendRecord>();
		File file = new File("http://120.24.153.199:39571/excel/3078808589417.xlsx");
		//File file = new File("E:/store/t.xlsx");
		List<ExcelSimSendRecord> l = eu.readExcelTemplate2ObjByFile(file, ExcelSimSendRecord.class, 2);
		System.out.println(JSONHelper.toString(l));
	}
}
