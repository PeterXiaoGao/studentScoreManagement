package com.ruoyi.student.pojo;

import java.io.Serializable;

public class CourseclassTeacherKey implements Serializable {
    private static final long serialVersionUID = -3916714435898952479L;
    private Integer teacherId;

    private Integer courseclassId;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCourseclassId() {
        return courseclassId;
    }

    public void setCourseclassId(Integer courseclassId) {
        this.courseclassId = courseclassId;
    }
}
