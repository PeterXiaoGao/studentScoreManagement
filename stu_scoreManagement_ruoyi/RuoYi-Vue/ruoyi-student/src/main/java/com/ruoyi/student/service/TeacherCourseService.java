package com.ruoyi.student.service;

import com.ruoyi.student.pojo.TeacherCourse;
import com.ruoyi.student.pojo.ThreeSelectByTeacherId;

import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className TeacherCourseService
 * @description
 * @since 1.0
 */
public interface TeacherCourseService {

    /**
     * 查询 teacher_course对应的 所有的 teacherId 信息
     *
     * @param teacherId  老师id
     * @param startIndex 起始下标
     * @param pageSize 行数
     * @return TeacherCourse集合
     */
    List<ThreeSelectByTeacherId> queryTeacherCourseByTeacherId(Integer teacherId, int startIndex, Integer pageSize);

    /**
     * 添加老师教授的课程
     *
     * @param teacherCourse@return
     */
    int addTeacherCourse(TeacherCourse teacherCourse);

    /**
     * 修改教师教授课程
     *
     * @param originTeacherId     原老师id
     * @param originCourseId      原课程id
     * @param originCourseclassId 原上课班级id
     * @param destTeacherId       目的老师id
     * @param destCourseId        目的课程id
     * @param destCourseclassId   目的上课班级id
     * @param destTerm  目的教学时期
     * @return
     */
    int modifyTeacherCourse(Integer originTeacherId, Integer originCourseId, Integer originCourseclassId, Integer destTeacherId, Integer destCourseId, Integer destCourseclassId, String destTerm);

    /**
     * 获取
     * @param teacherId
     * @return
     */
    List<ThreeSelectByTeacherId> queryTeacherCourseByTeacherIdSize(Integer teacherId);
}
