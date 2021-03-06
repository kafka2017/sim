package com.amwell.model;

import javax.persistence.*;
import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = -8736616045315083846L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;//登录名

    private String password;//登录密码
    
    private String realname;//真实姓名
    
    @Column(name="simcompanyId")
    private Integer simcompanyId;//公司id

    private Integer enable;//是否启用 1 启动 0 不启用
    
    @Transient
	private String fullName;
	
    @Transient
	private String shortName;
    
    @Transient
    private Integer adminType;
    
    @Transient
    private Integer parentId;
    
    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
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

    
    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

	public String getRealname() {
		return realname;
	}


	public void setRealname(String realname) {
		this.realname = realname;
	}


	public Integer getSimcompanyId() {
		return simcompanyId;
	}

	public void setSimcompanyId(Integer simcompanyId) {
		this.simcompanyId = simcompanyId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getAdminType() {
		return adminType;
	}

	public void setAdminType(Integer adminType) {
		this.adminType = adminType;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
    
}