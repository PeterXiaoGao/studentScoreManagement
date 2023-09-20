package com.ruoyi.student.pojo;

public class TeacherCourse extends TeacherCourseKey {
    private String teacherNo;

    private String term;

    private Integer courseclassId;

    private String realName;

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo == null ? null : teacherNo.trim();
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }

    public Integer getCourseclassId() {
        return courseclassId;
    }

    public void setCourseclassId(Integer courseclassId) {
        this.courseclassId = courseclassId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }
}