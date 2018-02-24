package com.urbanfit.apiserver.entity;

import com.urbanfit.apiserver.common.base.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class UserDO extends BaseModel implements Serializable {

	/**冻结状态*/
	public static final String STATUS_FREEZE = "status_freeze" ;
	/**正常状态*/
	public static final String STATUS_NORMAL = "status_normal" ;

	private int id;		// ID
	private String username;	// 用户名
	private String password;	// 密码
	private String name;		// 姓名
	private String status ; // 用户状态
	private Date createTime ; //创建时间
	
	public UserDO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
