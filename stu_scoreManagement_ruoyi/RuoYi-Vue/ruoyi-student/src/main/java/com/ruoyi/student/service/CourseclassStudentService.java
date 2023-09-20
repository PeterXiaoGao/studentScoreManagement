package com.ruoyi.student.service;

import com.ruoyi.student.pojo.CourseclassStudent;
import com.ruoyi.student.pojo.entity.StudentScore;

/**
 * @author xiaogao
 * @version 1.0
 * @className CourseclassStudentService
 * @description
 * @since 1.0
 */
public interface CourseclassStudentService {

    /**
     * 录入学生成绩
     * @param courseclassStudent
     * @return >0 成功
     */
    int addCourseclassStudentByStuIdAndCouclassId(CourseclassStudent courseclassStudent);

    /**
     * 修改学生成绩
     * @param courseclassStudent 参数对象
     * @return >0 成功
     */
    int modifyStudentScore(CourseclassStudent courseclassStudent);

    /**
     * 老师查询学生成绩
     * @param studentId 学生唯一标识  id
     * @param courseclassId 上课班级唯一标识 id
     * @return
     */
    StudentScore queryStudentScore(Integer studentId, Integer courseclassId);
}
