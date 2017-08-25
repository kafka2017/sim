package com.amwell.util.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FTPConnectionParameter {
	private String host;

	private int port;

	private String username;

	private String password;

	private String httpRootUrl;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHttpRootUrl() {
		return httpRootUrl;
	}

	public void setHttpRootUrl(String httpRootUrl) {
		this.httpRootUrl = httpRootUrl;
	}
	
	public static FTPConnectionParameter buildFTPConnectionParameter() {
		Properties prop = new Properties();
		InputStream in = FTPConnectionParameter.class.getResourceAsStream("/FtpConfig.properties");
		if(null!=in){
			try {
				prop.load(in);
				FTPConnectionParameter p = new FTPConnectionParameter();
				p.setHost(prop.getProperty("host"));
				p.setPort(Integer.parseInt(prop.getProperty("port")));
				p.setUsername(prop.getProperty("username"));
				p.setPassword(prop.getProperty("password"));
				p.setHttpRootUrl(prop.getProperty("httpRootUrl"));
				return p;
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
