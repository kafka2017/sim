package com.amwell.util.excel;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.util.StringUtils;

public class ExcelUtil<T> {

	private Workbook wb;
	
	private Sheet sheet;
	
	private Class<T> clz;
	
	private int startLine;
	
	// 返回结果集合
	private List<T> objs = new ArrayList<T>();
	
	// 模板类属性集合
	private List<ExcelHeader> ehs = new ArrayList<ExcelHeader>();
	
	private Map<Integer,CellStyle> styleMap = new HashMap<Integer,CellStyle>();
	
	/**
	 * 写
	 * @param string
	 * @param string2
	 * @param userList
	 * @param class1
	 * @param startLine
	 */
	public void exportObj2ExcelByTemplate(String template,
			List<T> objList, Class<T> clz,int startLine,OutputStream out) {
		
		this.clz = clz;
		this.startLine = startLine;
		
		// 读取模板指定行的样式
		readTemplateStyle(template, startLine);
		// 
		getTemplateClassFieldList();
		
		try {
			sheet = wb.getSheetAt(0);
			for(T t : objList)
			{
				Row row = sheet.createRow(startLine - 1 );
				int index = 0;
				for(ExcelHeader eh : ehs)
				{
					Cell cell = row.createCell(index);
					CellStyle cellStyle = styleMap.get(index);
					cell.setCellStyle(cellStyle);
					Field field = clz.getDeclaredField(eh.getFieldName());
					field.setAccessible(true);
					cell.setCellValue((String)field.get(t));
					index++;
				}
				startLine++;
			}
			wb.write(out);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}


	private void readTemplateStyle(String template, int startLine) {
		File file = new File(template);
		try {
			wb = WorkbookFactory.create(file);
			sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(startLine-1);
			int firstCellNum = row.getFirstCellNum();
			int lastCellNum = row.getLastCellNum();
			
			for(int i = firstCellNum;i<lastCellNum;i++){
				Cell cell = row.getCell(i);
				CellStyle cellStyle = cell.getCellStyle();
				styleMap.put(i, cellStyle);
			}
			
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<T> readExcelTemplate2ObjByInputStream(InputStream inputStream,Class<T> clz,int startLine){
		try {
			wb = WorkbookFactory.create(inputStream);
			return readExcelTemplate2Obj(clz,startLine);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param file
	 */
	public List<T> readExcelTemplate2ObjByFile(File file,Class<T> clz,int startLine){
		try {
			wb = WorkbookFactory.create(file);
			return readExcelTemplate2Obj(clz,startLine);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 
	 * @param clz
	 * @param startLine
	 * @return
	 */
	private List<T> readExcelTemplate2Obj(Class<T> clz, int startLine) {
		
		this.clz = clz;
		this.startLine = startLine;
		
		sheet = wb.getSheetAt(0);
		
		// 验证格式
		validateExcel();
		
		// 读取数据
		readExcelTemplate2Obj();
		
		return objs;
	}


	private void readExcelTemplate2Obj() {
		
		int lastRowNum = sheet.getLastRowNum();
		Row row = null;
		for(int i= (startLine -1); i<= lastRowNum;i++)
		{
			row = sheet.getRow(i);
			
			int firstCellNum = row.getFirstCellNum();
			int lastCellNum = row.getLastCellNum();
			int index = 0;
			T obj;
			try {
				obj = clz.newInstance();
				for(int j=firstCellNum;j<lastCellNum;j++)
				{
					Cell cell = row.getCell(j);
					String cellValue = getCellValue(cell);
					// 如果是空行就返回
					if(StringUtils.isEmpty(cellValue) && j == firstCellNum)
					{
						return;
					}
					ExcelHeader eh = ehs.get(index++);
					String fieldName = eh.getFieldName();
					Field field = clz.getDeclaredField(fieldName);
					field.setAccessible(true);
					field.set(obj, cellValue);
				}
				// 加入集合
				objs.add(obj);
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		
		
	}


	/**
	 * 验证excel的标题是否与模板要求一致
	 */
	private void validateExcel() {
		
		getTemplateClassFieldList();
		
		// 获取 Excel 的标题数据,由于excel的行下标是从0开始，输入的地方是内容的行号，故取标题这里-2
		Row row = sheet.getRow(startLine-2);
		int firstCellNum = row.getFirstCellNum();
		int lastCellNum = row.getLastCellNum();
		
		if(( lastCellNum - firstCellNum ) != ehs.size() )
		{
			throw new RuntimeException("excel标题列与模板类的属性个数不匹配,excel列数是:"+( lastCellNum - firstCellNum )+"模板类的属性个数是:"+ehs.size());
		}
		
		int index = 0;
		for(int i=firstCellNum; i< lastCellNum; i++)
		{
			Cell cell = row.getCell(i);
			String cellValue = getCellValue(cell);
			ExcelHeader eh = ehs.get(index++);
			if(!cellValue.equals(eh.getTitle()))
			{
				throw new RuntimeException("excel与模板类的属性注解的name不匹配,excel是:"+cellValue+",模板类里面的是:"+eh.getTitle());
			}
		}
		
	}

	/**
	 * 获取模板有 ExcelColumn 注解的属性
	 * @return
	 */
	private List<ExcelHeader> getTemplateClassFieldList() {
		Field[] fields = clz.getDeclaredFields();
		for(Field field : fields)
		{
			ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
			if(null == excelColumn)
			{
				continue;
			}
			
			// 
			String title = excelColumn.name();
			int order = excelColumn.order();
			String fieldName = field.getName();
			ehs.add(new ExcelHeader(title, order, fieldName));
		}
		
		if(ehs.isEmpty())
		{
			throw new RuntimeException("模板类没有注解 com.ExcelColumn .");
		}
		
		// 对 ExcelHeader 集合排序
		Collections.sort(ehs);
		
		return ehs;
	}
	
	
	/**
	 * 获取单元格的值
	 * 类型常量 == 具体的值对应关系
	 * CELL_TYPE_NUMERIC 数值型 0
	 * CELL_TYPE_STRING 字符串型 1
	 * CELL_TYPE_FORMULA 公式型 2
	 * CELL_TYPE_BLANK 空值 3
	 * CELL_TYPE_BOOLEAN 布尔型 4
	 * CELL_TYPE_ERROR 错误 5
	 * @param cell	单元格
	 * @return	单元格值
	 */
	private String getCellValue(Cell cell){
		String cellValue = "";
		
		if(null == cell)
		{
			return cellValue;
		}
		// 单元格类型
		int cellType = cell.getCellType();
		
		switch (cellType) {
			case Cell.CELL_TYPE_NUMERIC:
				DecimalFormat df = new DecimalFormat("0"); 
				cellValue =  df.format(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue =  cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue =  cell.getCellFormula();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue =  cell.getBooleanCellValue()+"";
				break;
			case Cell.CELL_TYPE_ERROR:
				cellValue =  cell.getErrorCellValue()+"";
				break;
			default:
				break;
		}
		return cellValue;
	}
	
	/**
     * 拼装数据
     * 
     * @param list 数据集
     * @param headMeta 表头
     * @param fields 对象或Map属性数组（注意：顺序要与表头标题顺序对应，如数据集为List<Object[]>，则该参数可以为null）
     * @return TableData
     */
	@SuppressWarnings("unchecked")
	public static TableData createTableData(List list,TableHeaderMetaData headMeta,String[] fields){

        TableData td = new TableData(headMeta);
        TableDataRow row = null;
        if(list != null && list.size()>0){
        	if(list.get(0).getClass().isArray()){//数组类型
                for (Object obj : list){
                	row = new TableDataRow(td);
            		for(Object o : (Object[])obj){
    					row.addCell(o);
    				}
                    td.addRow(row);
                }
        	}else{//JavaBean或Map类型
                for (Object obj : list){
                	row = new TableDataRow(td);
                	Map<String, Object> map = (obj instanceof Map)?(Map<String, Object>)obj:beanToMap(obj);
                    for(String key : fields){
                        row.addCell(map.get(key));
                    }
                    td.addRow(row);
                }
        	}
        }
        return td;
    }
	
	/**
	 * JavaBean转Map
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object obj) {  
        Map<String, Object> params = new HashMap<String, Object>(0);  
        try {  
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();  
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);  
            for (int i = 0; i < descriptors.length; i++) {  
                String name = descriptors[i].getName();  
                if (!equals(name, "class")) {  
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return params;  
    }
	
	/**
	 * 创建普通表头
	 * @param titls 表头名称数组
	 * @return
	 */
	public static TableHeaderMetaData createTableHeader(String[] titls){
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		for(String title : titls){
			TableColumn tc = new TableColumn();
			tc.setDisplay(title);
			tc.setGrouped(true);
			headMeta.addColumn(tc);
		}
		return headMeta;
	}
	
	public static void copyCellStyle(HSSFWorkbook destwb, HSSFCell dest,
			HSSFWorkbook srcwb, HSSFCell src) {
		if (src == null || dest == null)
			return;

		HSSFCellStyle nstyle = findStyle(src.getCellStyle(), srcwb, destwb);
		if (nstyle == null) {
			nstyle = destwb.createCellStyle();
			copyCellStyle(destwb, nstyle, srcwb, src.getCellStyle());
		}
		dest.setCellStyle(nstyle);
	}
	
	public static HSSFCellStyle findStyle(HSSFCellStyle style,
			HSSFWorkbook srcwb, HSSFWorkbook destwb) {
		HSSFPalette srcpalette = srcwb.getCustomPalette();
		HSSFPalette destpalette = destwb.getCustomPalette();

		for (short i = 0; i < destwb.getNumCellStyles(); i++) {
			HSSFCellStyle old = destwb.getCellStyleAt(i);
			if (old == null)
				continue;

			if (style.getAlignment() == old.getAlignment()
					&& style.getBorderBottom() == old.getBorderBottom()
					&& style.getBorderLeft() == old.getBorderLeft()
					&& style.getBorderRight() == old.getBorderRight()
					&& style.getBorderTop() == old.getBorderTop()
					&& isSameColor(style.getBottomBorderColor(), old
							.getBottomBorderColor(), srcpalette, destpalette)
					&& style.getDataFormat() == old.getDataFormat()
					&& isSameColor(style.getFillBackgroundColor(), old
							.getFillBackgroundColor(), srcpalette, destpalette)
					&& isSameColor(style.getFillForegroundColor(), old
							.getFillForegroundColor(), srcpalette, destpalette)
					&& style.getFillPattern() == old.getFillPattern()
					&& style.getHidden() == old.getHidden()
					&& style.getIndention() == old.getIndention()
					&& isSameColor(style.getLeftBorderColor(), old
							.getLeftBorderColor(), srcpalette, destpalette)
					&& style.getLocked() == old.getLocked()
					&& isSameColor(style.getRightBorderColor(), old
							.getRightBorderColor(), srcpalette, destpalette)
					&& style.getRotation() == old.getRotation()
					&& isSameColor(style.getTopBorderColor(), old
							.getTopBorderColor(), srcpalette, destpalette)
					&& style.getVerticalAlignment() == old
							.getVerticalAlignment()
					&& style.getWrapText() == old.getWrapText()) {

				HSSFFont oldfont = destwb.getFontAt(old.getFontIndex());
				HSSFFont font = srcwb.getFontAt(style.getFontIndex());
				if (oldfont.getBoldweight() == font.getBoldweight()
						&& oldfont.getItalic() == font.getItalic()
						&& oldfont.getStrikeout() == font.getStrikeout()
						&& oldfont.getCharSet() == font.getCharSet()
						&& isSameColor(oldfont.getColor(), font.getColor(),
								srcpalette, destpalette)
						&& oldfont.getFontHeight() == font.getFontHeight()
						&& oldfont.getFontName().equals(font.getFontName())
						&& oldfont.getTypeOffset() == font.getTypeOffset()
						&& oldfont.getUnderline() == font.getUnderline()) {
					return old;
				}
			}
		}
		return null;
	}
	
	public static void copyCellStyle(HSSFWorkbook destwb, HSSFCellStyle dest,
			HSSFWorkbook srcwb, HSSFCellStyle src) {
		if (src == null || dest == null)
			return;
		dest.setAlignment(src.getAlignment());
		dest.setBorderBottom(src.getBorderBottom());
		dest.setBorderLeft(src.getBorderLeft());
		dest.setBorderRight(src.getBorderRight());
		dest.setBorderTop(src.getBorderTop());
		dest.setBottomBorderColor(findColor(src.getBottomBorderColor(), srcwb,
				destwb));
		dest.setDataFormat(destwb.createDataFormat().getFormat(
				srcwb.createDataFormat().getFormat(src.getDataFormat())));
		dest.setFillPattern(src.getFillPattern());
		dest.setFillForegroundColor(findColor(src.getFillForegroundColor(),
				srcwb, destwb));
		dest.setFillBackgroundColor(findColor(src.getFillBackgroundColor(),
				srcwb, destwb));
		dest.setHidden(src.getHidden());
		dest.setIndention(src.getIndention());
		dest.setLeftBorderColor(findColor(src.getLeftBorderColor(), srcwb,
				destwb));
		dest.setLocked(src.getLocked());
		dest.setRightBorderColor(findColor(src.getRightBorderColor(), srcwb,
				destwb));
		dest.setRotation(src.getRotation());
		dest
				.setTopBorderColor(findColor(src.getTopBorderColor(), srcwb,
						destwb));
		dest.setVerticalAlignment(src.getVerticalAlignment());
		dest.setWrapText(src.getWrapText());

		HSSFFont f = srcwb.getFontAt(src.getFontIndex());
		HSSFFont nf = findFont(f, srcwb, destwb);
		if (nf == null) {
			nf = destwb.createFont();
			nf.setBoldweight(f.getBoldweight());
			nf.setCharSet(f.getCharSet());
			nf.setColor(findColor(f.getColor(), srcwb, destwb));
			nf.setFontHeight(f.getFontHeight());
			nf.setFontHeightInPoints(f.getFontHeightInPoints());
			nf.setFontName(f.getFontName());
			nf.setItalic(f.getItalic());
			nf.setStrikeout(f.getStrikeout());
			nf.setTypeOffset(f.getTypeOffset());
			nf.setUnderline(f.getUnderline());
		}
		dest.setFont(nf);
	}
	
	private static short findColor(short index, HSSFWorkbook srcwb,
			HSSFWorkbook destwb) {
		Integer id = new Integer(index);
		if (HSSFColor.getIndexHash().containsKey(id))
			return index;
		if (index == HSSFColor.AUTOMATIC.index)
			return index;
		HSSFColor color = srcwb.getCustomPalette().getColor(index);
		if (color == null) {
			return index;
		}

		HSSFColor ncolor = destwb.getCustomPalette().findColor(
				(byte) color.getTriplet()[0], (byte) color.getTriplet()[1],
				(byte) color.getTriplet()[2]);
		if (ncolor != null)
			return ncolor.getIndex();
		destwb.getCustomPalette().setColorAtIndex(index,
				(byte) color.getTriplet()[0], (byte) color.getTriplet()[1],
				(byte) color.getTriplet()[2]);
		return index;
	}
	
	private static boolean isSameColor(short a, short b, HSSFPalette apalette,
			HSSFPalette bpalette) {
		if (a == b)
			return true;
		HSSFColor acolor = apalette.getColor(a);
		HSSFColor bcolor = bpalette.getColor(b);
		if (acolor == null)
			return true;
		if (bcolor == null)
			return false;
		return acolor.getHexString().equals(bcolor.getHexString());
	}
	
	private static HSSFFont findFont(HSSFFont font, HSSFWorkbook src,
			HSSFWorkbook dest) {
		for (short i = 0; i < dest.getNumberOfFonts(); i++) {
			HSSFFont oldfont = dest.getFontAt(i);
			if (font.getBoldweight() == oldfont.getBoldweight()
					&& font.getItalic() == oldfont.getItalic()
					&& font.getStrikeout() == oldfont.getStrikeout()
					&& font.getCharSet() == oldfont.getCharSet()
					&& font.getColor() == oldfont.getColor()
					&& font.getFontHeight() == oldfont.getFontHeight()
					&& font.getFontName().equals(oldfont.getFontName())
					&& font.getTypeOffset() == oldfont.getTypeOffset()
					&& font.getUnderline() == oldfont.getUnderline()) {
				return oldfont;
			}
		}
		return null;
	}
	
	/**
	 * 创建普通表头
	 * @param titls 表头名称数组
	 * @param spanCount 需要行合并的列数。
	 * 		由第一列数据开始到指定列依次从左到右进行合并操作。
	 * 		如该值大于表头名称数组，则为全列合并
	 * @return
	 */
	public static TableHeaderMetaData createTableHeader(String[] titls,int spanCount){
		if(spanCount>titls.length)
			spanCount = titls.length;
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		for(int i=0;i<titls.length;i++){
			TableColumn tc = new TableColumn();
			tc.setDisplay(titls[i]);
			if(i<spanCount)
				tc.setGrouped(true);
			headMeta.addColumn(tc);
		}
		return headMeta;
	}
	
	/**
	 * 创建合并表头
	 * @param parents 父表头数组
	 * @param children 子表头数组
	 * @return
	 */
	public static TableHeaderMetaData createTableHeader(String[] parents,String[][] children){
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		TableColumn parentColumn = null;
		TableColumn sonColumn = null;
		for (int i = 0; i < parents.length; i++) {
			parentColumn = new TableColumn();
			parentColumn.setDisplay(parents[i]);
			if (children != null && children[i] != null) {
				for (int j = 0; j < children[i].length; j++) {
					sonColumn = new TableColumn();
					sonColumn.setDisplay(children[i][j]);
					parentColumn.addChild(sonColumn);
				}
			}
			headMeta.addColumn(parentColumn);
		}
		return headMeta;
	}
	
	public static boolean equals(String str1, String str2) {
		return str1 != null ? str1.equals(str2) : str2 == null;
	}
	
}
