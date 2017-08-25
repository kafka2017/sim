package com.amwell.util.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel导入注解
 * @author hushuang
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.FIELD)
public @interface ExcelColumn {
	
	/**
	 * 列名
	 * @return
	 */
	String name();
	/**
	 * 排序
	 * @return
	 */
	int order();
}
