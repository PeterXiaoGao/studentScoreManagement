package com.ruoyi.student.pojo;

import lombok.*;

/**
 * @author xiaogao
 * @version 1.0
 * @className ThreeSelectByTeacherId
 * @description
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThreeSelectByTeacherId {
    private Integer teacherId;
    private String term;
    private String realName;
    private String teacherNo;
    private Integer courseclassId;
    private String deptName;
    private Integer courseId;
    private String courseName;
    private Integer deptId;
    private String courseNo;
    private Double pingshiPercent;
    private Double qimoPercent;
    private String courseclassNo;
}
