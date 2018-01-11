package com.hab.bms.sys.basicdata.tools.model;

import java.util.Date;

public class BaseModel<T> extends BaseEntity<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 预留字段 **/
	private String resField1;
	/** 预留字段 **/
	private String resField2;
	/** 预留字段 **/
	private String resField3;
	/** 创建人ID **/
	private String createOprId;
	/** 创建时间 **/
	private Date createTime;
	/** 修改人ID **/
	private String updOprId;
	/** 修改时间 **/
	private Date updateTime;

	public BaseModel() {
		super();
	}

	public BaseModel(String id) {
		super();
		this.id = id;
	}

	public String getResField1() {
		return resField1;
	}

	public void setResField1(String resField1) {
		this.resField1 = resField1 == null ? null : resField1.trim();
	}

	public String getResField2() {
		return resField2;
	}

	public void setResField2(String resField2) {
		this.resField2 = resField2 == null ? null : resField2.trim();
	}

	public String getResField3() {
		return resField3;
	}

	public void setResField3(String resField3) {
		this.resField3 = resField3 == null ? null : resField3.trim();
	}

	public String getCreateOprId() {
		return createOprId;
	}

	public void setCreateOprId(String createOprId) {
		this.createOprId = createOprId == null ? null : createOprId.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdOprId() {
		return updOprId;
	}

	public void setUpdOprId(String updOprId) {
		this.updOprId = updOprId == null ? null : updOprId.trim();
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
