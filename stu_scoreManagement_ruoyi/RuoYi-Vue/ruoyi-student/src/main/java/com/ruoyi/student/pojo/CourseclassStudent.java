package com.ruoyi.student.pojo;

import java.io.Serializable;

public class CourseclassStudent extends CourseclassStudentKey implements Serializable {
    private static final long serialVersionUID = -1332418784495660705L;
    private Integer pingshiScore;

    private Integer qimoScore;

    private Integer zongpingScore;

    public Integer getPingshiScore() {
        return pingshiScore;
    }

    public void setPingshiScore(Integer pingshiScore) {
        this.pingshiScore = pingshiScore;
    }

    public Integer getQimoScore() {
        return qimoScore;
    }

    public void setQimoScore(Integer qimoScore) {
        this.qimoScore = qimoScore;
    }

    public Integer getZongpingScore() {
        return zongpingScore;
    }

    public void setZongpingScore(Integer zongpingScore) {
        this.zongpingScore = zongpingScore;
    }
}
