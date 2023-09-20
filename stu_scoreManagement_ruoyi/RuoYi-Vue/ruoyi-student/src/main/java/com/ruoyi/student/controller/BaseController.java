package com.ruoyi.student.controller;

import com.ruoyi.student.service.*;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;

import javax.annotation.Resource;

/**
 * @author xiaogao
 * @version 1.0
 * @className BaseController
 * @description
 * @since 1.0
 */
public class BaseController {
    /**
     * 课程服务对象
     */
    @Resource
    protected CourseService courseService;

    /**
     * 班级服务对象
     */
    @Resource
    protected MetaClassService metaClassService;

    /**
     * 管理老师服务对象
     */
    @Resource
    protected TeacherService teacherService;

    /**
     * 学院 dept表的  服务对象
     */
    @Resource
    protected ISysDeptService sysDeptService;

    /**
     * user表的  服务对象
     */
    @Resource
    protected ISysUserService userService;

    /**
     * teacher_course表的服务对象
     */
    @Resource
    protected TeacherCourseService teacherCourseService;

    /**
     * student表的服务对象
     */
    @Resource
    protected StudentService studentService;

    /**
     * courseclass表的服务对象
     */
    @Resource
    protected CourseclassService courseclassService;

    /**
     * courseclass_student表的服务对象
     */
    @Resource
    protected CourseclassStudentService courseclassStudentService;
}
