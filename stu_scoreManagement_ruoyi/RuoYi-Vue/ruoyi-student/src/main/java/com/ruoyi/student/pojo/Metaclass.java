package com.ruoyi.student.pojo;

import java.io.Serializable;
import java.util.Date;

public class Metaclass implements Serializable {
    private static final long serialVersionUID = 2634326471391451113L;
    private Integer meataclassId;

    private String metaclassNo;

    private String metaclassName;

    private String grade;

    private Long deptId;

    private String status;

    private String delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    public Integer getMeataclassId() {
        return meataclassId;
    }

    public void setMeataclassId(Integer meataclassId) {
        this.meataclassId = meataclassId;
    }

    public String getMetaclassNo() {
        return metaclassNo;
    }

    public void setMetaclassNo(String metaclassNo) {
        this.metaclassNo = metaclassNo == null ? null : metaclassNo.trim();
    }

    public String getMetaclassName() {
        return metaclassName;
    }

    public void setMetaclassName(String metaclassName) {
        this.metaclassName = metaclassName == null ? null : metaclassName.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
