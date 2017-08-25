package com.amwell.util;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public final class PasswordHelper {
	
	private static final String algorithmName = "md5";
	
	private static final int hashIterations = 2;

	public static String getEncryptPassword(String username,String password){
		return new SimpleHash(algorithmName, password,  ByteSource.Util.bytes(username), hashIterations).toHex();
	}
	
	public static void main(String[] args) {
		System.out.println(getEncryptPassword("liuyp","admin"));
	}
	

}
