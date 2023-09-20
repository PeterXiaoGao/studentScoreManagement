package com.ruoyi.student.service;

import com.ruoyi.student.pojo.Teacher;

import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className TeacherService
 * @description
 * @since 1.0
 */
public interface TeacherService {

    /**
     * 获取所有的老师信息
     * @return 老师信息
     */
    List<Teacher> queryAllTeacher(int startIndex, Integer pageSize);

    /**
     * 删除教师
     * @param teacherId
     * @return
     */
    int deleteTeacherById(Integer teacherId);

    /**
     * 添加教师
     * @param teacher 老师
     * @return
     */
    int addTeacher(Teacher teacher);

    /**
     * 修改教师
     * @return
     */
    int modifyTeacher(Teacher teacher);

    /**
     * 根据老师id查询教师信息
     * @param teacherId
     * @return
     */
    Teacher queryTeacherById(Integer teacherId);

    /**
     * 根据学院id 查询本学院下的所有教师
     * @param deptId 学院id
     * @param startIndex 页码
     * @param pageSize   页数
     * @return
     */
    List<Teacher> queryCollegeTeacheres(Long deptId, Integer startIndex, Integer pageSize);

    /**
     * 获取所有教师
     * @return
     */
    List<Teacher> queryAllTeachers();

    /**
     * 获取学院下所有教师的数量
     * @param deptId
     * @return
     */
    List<Teacher> queryCollegeTeacheresSize(Long deptId);
}
