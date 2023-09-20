package com.ruoyi.student.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

public class CourseclassStudentKey implements Serializable {
    private static final long serialVersionUID = 1257122720332986094L;
    private Integer studentId;

    private Integer courseclassId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseclassId() {
        return courseclassId;
    }

    public void setCourseclassId(Integer courseclassId) {
        this.courseclassId = courseclassId;
    }
}
