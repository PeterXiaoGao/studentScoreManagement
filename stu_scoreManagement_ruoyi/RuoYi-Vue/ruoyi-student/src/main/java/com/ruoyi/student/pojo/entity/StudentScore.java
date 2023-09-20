package com.ruoyi.student.pojo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaogao
 * @version 1.0
 * @className StudentScore
 * @description
 * @since 1.0
 */
@Data
public class StudentScore {
    private Integer studentId;          // 学生id
    private Integer courseclassId;      // 上课班级id
    private Integer pingshiScore;       // 学生平时成绩
    private Integer qimoScore;          // 学生期末成绩
    private Integer zongpingScore;      // 学生综合成绩
    private String realName;            // 学生姓名
    private String studentNo;           // 学生学号
    private String grade;               // 学生年级
    private String gender;              // 学生性别
    private Long userId;              // 学生对应的管理系统的用户id
    private String courseclassNo;       // 学生上课班级的班号
    private Integer deptId;             // 课程对应的学院  那个学院的课程
    private Integer courseId;           // 课程id
    private String courseNo;            // 课程号
    private String courseName;          // 课程名字
    private Double pingshiPercent;      // 课程平时分的占比
    private Double qimoPercent;         // 课程期末考试占比
    private String deptName;            // 学院名字

}
