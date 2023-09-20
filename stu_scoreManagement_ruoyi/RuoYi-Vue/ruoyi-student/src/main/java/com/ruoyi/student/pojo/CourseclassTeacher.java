package com.ruoyi.student.pojo;

import java.io.Serializable;

public class CourseclassTeacher extends CourseclassTeacherKey implements Serializable {
    private static final long serialVersionUID = 3799170237644000257L;
    private Float evaluationScore;

    public Float getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(Float evaluationScore) {
        this.evaluationScore = evaluationScore;
    }
}
