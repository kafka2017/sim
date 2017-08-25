package com.amwell.util.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.sauronsoftware.ftp4j.FTPClient;

/**
 * FTP上传工具类
 * @author Administrator
 *
 */
public class FTPUtils {

	public static void upload(MultipartFile mf, Uploader uploader) {
		if (mf != null && uploader != null) {
			uploader.setOriginalName(mf.getOriginalFilename());
			uploader.setSize(mf.getSize());
			if(!StringUtils.hasText(uploader.getSavePath())){
				uploader.setState(uploader.getErrorInfo().get("SAVEPATH"));
				return;
			}
			// 验证文件大小
			long size = mf.getSize();
			System.out.println("uploadSize="+size);
			System.out.println("maxSize="+uploader.getMaxSize());
			if (size > uploader.getMaxSize()) {
				uploader.setState(uploader.getErrorInfo().get("SIZE"));
				return;
			}
			System.out.println("after check size...");
			// 处理上传
			try {
				handleUpload(mf.getInputStream(), uploader);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			uploader.setState(uploader.getErrorInfo().get("UNKNOWN"));
			return;
		}
	}
	
	public static void handleUpload(InputStream in, Uploader uploader) {
		FTPConnectionParameter para = FTPConnectionParameter.buildFTPConnectionParameter();
		if(para==null){
			uploader.setState(uploader.getErrorInfo().get("FTP"));
			return;
		}
		FTPClient client = new FTPClient();
		try {
			client.connect(para.getHost(), para.getPort());
			client.login(para.getUsername(), para.getPassword());
			client.changeDirectory(uploader.getSavePath());
			String fileName = buildRandomFileName(uploader.getOriginalName());
			client.upload(fileName, in, 0L, 0L, new MyFTPDataTransferListener());
			uploader.setFileName(fileName);
			String savePath = uploader.getSavePath()+"/"+fileName;
			uploader.setSavePath(savePath);
			uploader.setUrl(para.getHttpRootUrl()+savePath);
			uploader.setState(uploader.getErrorInfo().get("SUCCESS"));
		} catch (Exception e) {
			e.printStackTrace();
			uploader.setState(uploader.getErrorInfo().get("FTP"));
		} finally {
			if (null != client && client.isConnected()) {
				try {
					client.disconnect(true);
				} catch (Exception e) {
				}
			}
		}
	}
	
	private static String buildRandomFileName(String fileName) {
		String extension = FilenameUtils.getExtension(fileName);
		Random r = new Random();
		long tempName = System.nanoTime() + r.nextInt(10000);
		return tempName + "." + extension;
	}
	
	

	public static boolean upload(InputStream in,String destDir,String destFileName){
		if(in==null||StringUtils.isEmpty(destDir)||StringUtils.isEmpty(destFileName)){
			return false;
		}
		boolean result = false;
		FTPConnectionParameter para = FTPConnectionParameter.buildFTPConnectionParameter();
		FTPClient client = new FTPClient();	
		try {
			client.connect(para.getHost(), para.getPort());
			client.login(para.getUsername(), para.getPassword());
			client.changeDirectory(destDir);
			client.upload(destFileName, in, 0L, 0L, new MyFTPDataTransferListener());
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (null != client && client.isConnected()) {
				try {
					client.disconnect(true);
				} catch (Exception e) {
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		File f = new File("E:/store/t.xlsx");
		FTPConnectionParameter para = FTPConnectionParameter.buildFTPConnectionParameter();
		FTPClient client = new FTPClient();
		try {
			client.connect(para.getHost(), para.getPort());
			client.login(para.getUsername(), para.getPassword());
			client.changeDirectory("/excel");
			String fileName = buildRandomFileName(f.getName());
			client.upload(fileName, new FileInputStream(f), 0, 0, new MyFTPDataTransferListener());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != client && client.isConnected()) {
				try {
					client.disconnect(true);
				} catch (Exception e) {
					
				}
			}
		}
	}
}
