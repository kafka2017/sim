package com.amwell.util.ftp;

import java.io.Serializable;
import java.util.HashMap;

public class Uploader implements Serializable {
	
	private static final long serialVersionUID = 8219342951172552991L;
	
	// 输出文件地址
	private String url = "";
	
	// 上传文件名
	private String fileName = "";
	
	// 状态
	private String state = "";
	
	// 文件类型
	private String type = "";
	
	// 原始文件名
	private String originalName = "";
	
	// 文件大小
	private long size = 0;

	private String title = "";

	// 保存路径
	private String savePath;
	
	// 文件允许格式
	private String[] allowFiles = {".gif", ".png", ".jpg", ".jpeg", ".bmp","xls","xlsx" };
			
	// 文件大小限制，单位B
	private long maxSize = 5242880;

	private HashMap<String, String> errorInfo = new HashMap<String, String>();

	public Uploader(int maxSize) {
		HashMap<String, String> tmp = this.errorInfo;
		tmp.put("SUCCESS", "SUCCESS"); // 默认成功
		tmp.put("NOFILE", "未包含文件上传域");
		tmp.put("TYPE", "不允许的文件格式");
		tmp.put("SIZE", "文件大小超出限制");
		tmp.put("ENTYPE", "请求类型ENTYPE错误");
		tmp.put("REQUEST", "上传请求异常");
		tmp.put("IO", "IO异常");
		tmp.put("DIR", "目录创建失败");
		tmp.put("UNKNOWN", "未知错误");
		tmp.put("FTP","FTP异常");
		tmp.put("SAVEPATH", "远程服务器存储路径异常");
		this.maxSize=maxSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String[] getAllowFiles() {
		return allowFiles;
	}

	public void setAllowFiles(String[] allowFiles) {
		this.allowFiles = allowFiles;
	}

	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public HashMap<String, String> getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(HashMap<String, String> errorInfo) {
		this.errorInfo = errorInfo;
	}

}
