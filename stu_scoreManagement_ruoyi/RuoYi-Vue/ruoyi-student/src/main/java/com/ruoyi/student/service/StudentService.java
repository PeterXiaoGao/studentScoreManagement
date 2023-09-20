package com.ruoyi.student.service;

import com.ruoyi.student.pojo.Course;
import com.ruoyi.student.pojo.Student;
import com.ruoyi.student.pojo.entity.StudentScore;

import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className StudentService
 * @description
 * @since 1.0
 */
public interface StudentService {

    /**
     * 根据studentId查询学生
     */
    Student queryStudentByKey(Integer studentId);

    /**
     * 查询学生自己的成绩
     *
     * @param studentId  学生id
     * @param startIndex 起始下标
     * @param pageSize 行数 记录
     * @return 学生成绩
     */
    List<StudentScore> queryStudentScore(Integer studentId, Integer startIndex, Integer pageSize);

    /**
     * 查询所有的学生
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Student> queryAllByStudentId(Integer startIndex, Integer pageSize);

    /**
     * 查询学生所有的课程
     * @param studentId
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Course> queryAllCourseByStudentId(Integer studentId, Integer startIndex, Integer pageSize);

    List<StudentScore> queryStudentScore(Integer studentId);

    /**
     * 查询所有学生
     * @return
     */
    List<Student> queryAll();

    List<Course> queryAllCourseByStudentIdSize(Integer studentId);
}
