package com.ruoyi.student.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.student.enums.Rcode;
import com.ruoyi.student.pojo.Course;
import com.ruoyi.student.pojo.Student;
import com.ruoyi.student.pojo.entity.StudentScore;
import com.ruoyi.student.utils.KeyUtils;
import com.ruoyi.student.view.PageInfo;
import com.ruoyi.student.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className StudentController
 * @description
 * @since 1.0
 */
@RestController
@Api(tags = "学生查询信息模块")
@RequestMapping("/student/stuInfo")
public class StudentController extends BaseController {


    @ApiOperation("查询学生自己所有课程的成绩，和课程对应的学院 ")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "学生id 学生唯一标识", name = "studentId", required = true),
            @ApiImplicitParam(value = "页码", name = "pageNum", required = false),
            @ApiImplicitParam(value = "一页展示多少条记录， 页数", name = "pageSize", required = false),
    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin,teacher,student')")
    @GetMapping("/score")
    public RespResult queryScore(@RequestParam Integer studentId,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        RespResult result = RespResult.fail();
        if (studentId != null && studentId > 0 && KeyUtils.isValidStudentId(studentId, studentService)
                && pageNum != null && pageNum > 0
                && pageSize != null && pageSize > 0) {
            // 参数成功进行学生查询
            Integer startIndex = (pageNum - 1) * pageSize;
            List<StudentScore> list = studentService.queryStudentScore(studentId);
            List<StudentScore> studentScoreList = studentService.queryStudentScore(studentId, startIndex, pageSize);
            if (studentScoreList != null) {
                // 获取成功
                result = RespResult.ok();
                result.setRcode(Rcode.QUERY_SUCC);
                result.setPageInfo(new PageInfo(pageNum, pageSize, list.size()));
                result.setList(studentScoreList);

            } else {
                // 失败
                result.setRcode(Rcode.QUERY_ERR);
            }

        } else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }

    @ApiOperation("查询所有学生")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "pageNum", required = false),
            @ApiImplicitParam(value = "一页展示多少条记录， 页数", name = "pageSize", required = false),
    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin,teacher,student')")
    @GetMapping("/list")
    public RespResult getStudentAll(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        RespResult result = RespResult.fail();
        if (pageNum != null && pageNum > 0
                && pageSize != null && pageSize > 0) {
            Integer startIndex = (pageNum - 1) * pageSize;
            List<Student> list = studentService.queryAll();
            List<Student> studentList = studentService.queryAllByStudentId(startIndex, pageSize);
            if(studentList != null){
                // 查询成功
                result = RespResult.ok();
                result.setRcode(Rcode.QUERY_SUCC);
                result.setList(studentList);
                result.setPageInfo(new PageInfo(pageNum, pageSize, list.size()));

            }else {
                // 查询失败
                result.setRcode(Rcode.REQUEST_PARAM_ERR);
            }
        } else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }


    @ApiOperation("查询学生自己所有课程 ")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "学生id 学生唯一标识", name = "studentId", required = true),
            @ApiImplicitParam(value = "页码", name = "pageNum", required = false),
            @ApiImplicitParam(value = "一页展示多少条记录， 页数", name = "pageSize", required = false),
    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin,teacher,student')")
    @GetMapping("/allCourse")
    public RespResult getAllCourseInfo(@RequestParam Integer studentId,
                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        RespResult result = RespResult.fail();
        if (studentId != null && studentId > 0 && KeyUtils.isValidStudentId(studentId, studentService)
                && pageNum != null && pageNum > 0
                && pageSize != null && pageSize > 0) {
            Integer startIndex = (pageNum - 1) * pageSize;
            List<Course> list = studentService.queryAllCourseByStudentIdSize(studentId);
            Iterator<Course> iterator = list.iterator();
            while (iterator.hasNext()){
                Course next = iterator.next();
                if(next == null){
                    iterator.remove();
                }
            }
            List<Course> courses = studentService.queryAllCourseByStudentId(studentId, startIndex, pageSize);
            Iterator<Course> it = courses.iterator();
            while (it.hasNext()){
                Course next = it.next();
                if(next == null){
                    it.remove();
                }
            }
            if(courses != null ){
                // 查询成功
                result = RespResult.ok();
                result.setRcode(Rcode.QUERY_SUCC);
                result.setList(courses);
                result.setPageInfo(new PageInfo(pageNum, pageSize, list.size()));

            }else{
                // 查询失败
                result.setRcode(Rcode.QUERY_ERR);
            }

        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }
}
