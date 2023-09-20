package com.ruoyi.student.pojo;

import java.io.Serializable;

public class CourseclassMetaclassKey implements Serializable {
    private static final long serialVersionUID = -8296309874014877324L;
    private Integer courseclassId;

    private Integer metaclassId;

    public Integer getCourseclassId() {
        return courseclassId;
    }

    public void setCourseclassId(Integer courseclassId) {
        this.courseclassId = courseclassId;
    }

    public Integer getMetaclassId() {
        return metaclassId;
    }

    public void setMetaclassId(Integer metaclassId) {
        this.metaclassId = metaclassId;
    }
}
