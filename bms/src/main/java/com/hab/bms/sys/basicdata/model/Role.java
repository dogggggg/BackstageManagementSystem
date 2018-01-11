package com.hab.bms.sys.basicdata.model;

import java.util.Date;

public class Role {
    private String id;

    private String name;

    private String description;

    private String state;

    private String code;

    private String pid;

    private String resField1;

    private String resField2;

    private String resField3;

    private String createOprId;

    private Date createTime;

    private String updOprId;

    private Date updateTime;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}