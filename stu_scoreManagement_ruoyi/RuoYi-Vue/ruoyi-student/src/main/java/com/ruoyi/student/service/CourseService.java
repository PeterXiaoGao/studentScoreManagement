package com.ruoyi.student.service;

import com.ruoyi.student.pojo.Course;

import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className CourseService
 * @description
 * @since 1.0
 */
public interface CourseService {

    /**
     * 查询所有课程
     * @return 所有课程
     */
    // List<Course> queryAllCourse(Course course,Integer pageNum, Integer pageSize);
    List<Course> queryAllCourse(Course course);
    /**
     * 查询所有课程
     * @return 所有课程
     */
    List<Course> queryAllCourses(Course course);

    /**
     * 添加课程
     * @param course 封装课程对象
     * @return > 1 && < 99成功
     */
    int addCourse(Course course);

    /**
     * 更改课程
     * @param course  修改课程对象
     * @return > 1 && < 99成功
     */
    int modifyCourse(Course course);

    /**
     * 删除课程
     * @param courseId 课程id
     * @return 1删除成功
     */
    int deleteCourseByCourseId(Integer courseId);

    /**
     * 根据课程id查询课程
     * @param courseId 课程id
     * @return
     */
    Course queryCourse(Integer courseId);

    /**
     * 查询 学院的所有课程
     *
     * @param deptId
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Course> queryCollegeCourse(Long deptId, int startIndex, Integer pageSize);

    /**
     * 获取学院下所有课程
     * @param deptId
     * @return
     */
    List<Course> queryCollegeCourses(Long deptId);
}
