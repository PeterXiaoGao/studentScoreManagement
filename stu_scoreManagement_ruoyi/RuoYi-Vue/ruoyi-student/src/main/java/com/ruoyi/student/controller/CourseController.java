package com.ruoyi.student.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.student.enums.Rcode;
import com.ruoyi.student.pojo.Course;
import com.ruoyi.student.utils.KeyUtils;
import com.ruoyi.student.view.PageInfo;
import com.ruoyi.student.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className CourseController
 * @description
 * @since 1.0
 */
@Api(tags = "课程管理服务")
@RestController
@RequestMapping(value = "/student/course")
@Slf4j
public class CourseController extends BaseController {

    /**
     * 查询所有课程细信息
     *
     * @param pageNum  页码
     * @param pageSize 页数
     * @return
     */
    @ApiOperation("获取所有课程同时支持分页")
    @GetMapping("/list")
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    public RespResult queryAllCourse(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        RespResult result = RespResult.fail();
        List<Course> courses = null;
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            List<Course> courseList = courseService.queryAllCourses(new Course());
            Course course = new Course();
            // courses = courseService.queryAllCourse(course,pageNum, pageSize);
            courses = courseService.queryAllCourse(course);
            if (courses != null) {
                // 数据库查询所有课程成功
                result = RespResult.ok();
                // 1.生成pageInfo对象
                PageInfo pageInfo = new PageInfo(pageNum, pageSize, courseList.size());
                result.setPageInfo(pageInfo);
                result.setList(courses);

            } else {
                // 数据查询所有课程失败
                result.setRcode(Rcode.QUERY_COURSE_ERR);
            }
        } else {

            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }


    /**
     * 添加课程
     *
     * @param course 封装课程信息的对象
     * @return
     */
    @ApiOperation(value = "增加课程", notes = "没有标记必须得（require）就是不要给")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "学院id （必须有，并且保证是正确的）", required = true),
            @ApiImplicitParam(name = "createBy", value = "创建人的用户名", required = true),
            @ApiImplicitParam(name = "courseNo", value = "课程号", required = true),
            @ApiImplicitParam(name = "courseName", value = "课程名字", required = true),
            @ApiImplicitParam(name = "pingshiPercent", value = "平时成绩占比", required = true),
            @ApiImplicitParam(name = "qimoPercent", value = "期末成绩占比", required = true),
            @ApiImplicitParam(name = "courseId", value = "课程主键id，不要给自动生成", required = false),

    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @PostMapping("/addCourse")
    public RespResult addCourse(Course course) {
        RespResult result = RespResult.fail();

        if (course != null && course.getDeptId() > 0) {
            if (KeyUtils.isValidCollegeDeptId(course.getDeptId(), sysDeptService)) {
                // 参数没问题
                // 1.调用接口添加课程
                int rows = courseService.addCourse(course);

                if (rows > 0 && rows != 99) {
                    // 添加课程成功
                    result = RespResult.ok();
                    result.setRcode(Rcode.ADD_SUCC);

                } else if (rows == 99) {
                    result.setRcode(Rcode.DEPTID_PARAM_ERR);
                } else {
                    // 课程添加失败
                    result.setRcode(Rcode.ADD_COURSE_ERR);
                }
            } else {
                result.setRcode(Rcode.COLLEGE_ID_ERR);
            }


        } else {
            // 传入参数有误
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }

    /**
     * 更新课程
     *
     * @param course
     * @return
     */
    @ApiOperation(value = "修改课程", notes = "没有标记必须得（require）可以不给")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "学院id （必须有，并且保证是正确的）", required = true),
            @ApiImplicitParam(name = "updateBy", value = "修改人的用户名", required = true),
            @ApiImplicitParam(name = "courseNo", value = "课程号 不改就传原来的", required = true),
            @ApiImplicitParam(name = "courseName", value = "课程名字 不改就传原来的", required = true),
            @ApiImplicitParam(name = "pingshiPercent", value = "平时成绩占比 不改就传原来的", required = true),
            @ApiImplicitParam(name = "qimoPercent", value = "期末成绩占比 不改就传原来的", required = true),
            @ApiImplicitParam(name = "courseId", value = "课程主键id，必须要有", required = true),

    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @PutMapping("/updateCourse")
    public RespResult updateCourse(Course course) {
        RespResult result = RespResult.fail();
        if (course != null && course.getCourseId() > 0) {
            if (KeyUtils.isValidCollegeDeptId(course.getDeptId(), sysDeptService)) {
                // 修改课程
                int rows = courseService.modifyCourse(course);
                if (rows > 0 && rows < 99) {
                    // 修改课程成功
                    result = RespResult.ok();
                    result.setRcode(Rcode.MODIFY_SUCC);

                } else if (rows == 99) {
                    result.setRcode(Rcode.DEPTID_PARAM_ERR);
                } else {
                    // 更新课程失败
                    throw new RuntimeException("课程添加失败");
                }
            } else {
                result.setRcode(Rcode.COLLEGE_ID_ERR);
            }

        } else {
            // 传入参数有误
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }


    /**
     * 删除课程
     */
    @ApiOperation("根据courseId删除课程")
    @ApiImplicitParam(name = "courseId", value = "课程id （必须有，并且保证是正确的）", required = true)
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @DeleteMapping("/deleteCourse")
    public RespResult deleteCourse(@RequestParam("courseId") Integer courseId) {
        RespResult result = RespResult.fail();

        if (courseId != null && courseId > 0) {

            // 删除课程
            int rows = courseService.deleteCourseByCourseId(courseId);
            if (rows > 0) {
                // 删除成功
                result = RespResult.ok();
                result.setRcode(Rcode.DELETE_SUCC);
            } else {
                // 删除失败
                result.setRcode(Rcode.DELETE_COURSE_ERR);
            }

        } else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }
        return result;
    }

    /**
     * 根据课程id查询课程信息
     */
    @ApiOperation("课程信息")
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin,teacher,student')")
    @GetMapping("/oneCourse")
    @ApiImplicitParam(name = "courseId", value = "课程id 主键", required = true)
    public RespResult getCourse(Integer courseId) {
        RespResult result = RespResult.fail();
        if (courseId != null && courseId > 0) {
            // 开始查询
            Course course = courseService.queryCourse(courseId);
            if (course != null) {
                result = RespResult.ok();
                result.setData(course);
            } else {
                // 查询失败
                result.setRcode(Rcode.QUERY_ERR);
            }

        } else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }

    /**
     * 查询学院的课程
     */
    @ApiOperation("通过学院id获取 本学院的所有课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "学院id 外键，dept表的id 主键", required = true),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false),
            @ApiImplicitParam(name = "pageSize", value = "页数一页多少行数据", required = false)

    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @GetMapping("/collegeCourse")
    public RespResult getCollegeCourse(@RequestParam Long deptId, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        RespResult result = RespResult.fail();
        if (deptId != null) {
            // 1.根据deptId 查询学院表  看看是否正确
            if (KeyUtils.isValidCollegeDeptId(deptId, sysDeptService)) {
                // deptId 正确  可以查询
                // 2.然后查询本学院的课程
                int startIndex = (pageNum - 1) * pageSize;
                // 获取所有部门下的所有课程
                List<Course> courseList = courseService.queryCollegeCourses(deptId);
                List<Course> courses = courseService.queryCollegeCourse(deptId, startIndex, pageSize);
                if (courses != null) {
                    // 查询成功
                    result = RespResult.ok();
                    result.setList(courses);
                    result.setPageInfo(new PageInfo(pageNum, pageSize, courseList.size()));

                } else {
                    result.setRcode(Rcode.QUERY_ERR);
                }
            } else {
                result.setRcode(Rcode.COLLEGE_ID_ERR);
            }


        } else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }

}
