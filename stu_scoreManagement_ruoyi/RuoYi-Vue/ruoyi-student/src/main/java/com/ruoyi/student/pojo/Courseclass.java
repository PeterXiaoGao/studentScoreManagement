package com.ruoyi.student.pojo;

import java.io.Serializable;
import java.util.Date;

public class Courseclass implements Serializable {
    private static final long serialVersionUID = -1132591385643198273L;
    private Integer courseclassId;

    private Integer courseId;

    private String courseclassNo;

    private String status;

    private String delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String term;

    private String courseclassScore;

    private String courseclassState;

    public Integer getCourseclassId() {
        return courseclassId;
    }

    public void setCourseclassId(Integer courseclassId) {
        this.courseclassId = courseclassId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseclassNo() {
        return courseclassNo;
    }

    public void setCourseclassNo(String courseclassNo) {
        this.courseclassNo = courseclassNo == null ? null : courseclassNo.trim();
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

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }

    public String getCourseclassScore() {
        return courseclassScore;
    }

    public void setCourseclassScore(String courseclassScore) {
        this.courseclassScore = courseclassScore == null ? null : courseclassScore.trim();
    }

    public String getCourseclassState() {
        return courseclassState;
    }

    public void setCourseclassState(String courseclassState) {
        this.courseclassState = courseclassState == null ? null : courseclassState.trim();
    }
}
