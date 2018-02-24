package com.urbanfit.apiserver.entity;

import com.urbanfit.apiserver.common.base.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class DataDO extends BaseModel implements Serializable {

	private int id ;		    // ID
	private String projectId ;  //工程
	private String dataId ;		//数据id
	private Date createTime ;	//创建时间
	private Date modifyTime ;	//修改时间
	private Integer userId ;	//创建者
	private String notes ;		//备注
	private String value ;		//键值
	private String valueType;	//值类型


	public DataDO() {
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

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}


}
