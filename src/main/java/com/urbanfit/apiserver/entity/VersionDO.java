package com.urbanfit.apiserver.entity;

import com.urbanfit.apiserver.common.base.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class VersionDO extends BaseModel implements Serializable {

	private int id ;		    // ID
	private String projectId ;
	private Long version ;
	private Date createDate ;
	private UserDO userDO ;

	public VersionDO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public UserDO getUserDO() {
		return userDO;
	}

	public void setUserDO(UserDO userDO) {
		this.userDO = userDO;
	}
}
