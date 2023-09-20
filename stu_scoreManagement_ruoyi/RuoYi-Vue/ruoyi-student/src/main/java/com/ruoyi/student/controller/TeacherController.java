package com.ruoyi.student.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.student.enums.Rcode;
import com.ruoyi.student.pojo.CourseclassStudent;
import com.ruoyi.student.pojo.Teacher;
import com.ruoyi.student.pojo.TeacherCourse;
import com.ruoyi.student.pojo.ThreeSelectByTeacherId;
import com.ruoyi.student.pojo.entity.StudentScore;
import com.ruoyi.student.utils.KeyUtils;
import com.ruoyi.student.view.PageInfo;
import com.ruoyi.student.view.RespResult;
import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sun.security.util.KeyUtil;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className TeacherController
 * @description
 * @since 1.0
 */
@Api(tags = "老师服务管理")
@RestController
@RequestMapping("/student/teacher")
public class TeacherController extends BaseController {


    /**
     * 获取所有老师
     */
    @ApiOperation("获取所有老师信息")
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin,teacher')")
    @GetMapping("/list")
    public RespResult getAllTeacher(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        RespResult result = RespResult.fail();
        if(pageNum != null && pageSize != null){
            int startIndex = (pageNum - 1) * pageSize;
            List<Teacher> list = teacherService.queryAllTeachers();
            // 查询所有老师信息
            List<Teacher> teachers =  teacherService.queryAllTeacher(startIndex, pageSize);
            if(teachers != null){
                // 获取所有老师信息成功
                result = RespResult.ok();
                result.setPageInfo(new PageInfo(pageNum, pageSize, list.size()));
                result.setList(teachers);

            }else {
                result.setRcode(Rcode.QUERY_ERR);
            }

        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }


    /**
     * 删除老师
     */
    @ApiOperation(value = "根据teacherId删除老师", notes = "teacherId是主键")
    @ApiImplicitParam(name = "teacherId", value = "教师主键，老师的唯一标识", required = true)
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @DeleteMapping("/deleteTeacher")
    public RespResult deleteTeacher(Integer teacherId){
        RespResult result = RespResult.fail();
        if(teacherId != null && teacherId > 0){
            // 开始删除教师
            int rows = teacherService.deleteTeacherById(teacherId);
            if(rows > 0){
                // 删除成功
                result = RespResult.ok();
                result.setRcode(Rcode.DELETE_SUCC);

            }else {
                result.setRcode(Rcode.DELETE_ERR);
            }

        }else {
            //参数有误
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }

    /**
     * 增加教师
     */
    @ApiOperation("添加教师")
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @PostMapping("/addTeacher")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "部门(学院)id", required = true),
            @ApiImplicitParam(name = "createBy", value = "创建人", required = true),
            @ApiImplicitParam(name = "teacherNo", value = "教师工号", required = true),
            @ApiImplicitParam(name = "realName", value = "教师姓名", required = true),
            @ApiImplicitParam(name = "gender", value = "性别", required = true),
            @ApiImplicitParam(name = "academicTitle", value = "职位(高等教师 普通教师等)", required = true),
            @ApiImplicitParam(name = "internal", value = "是否外聘；1表示内部，0表示外聘教师", required = true),
            @ApiImplicitParam(name = "userId", value = "用户表的id， 一个老师对应一个系统的用户", required = true)
    })
    public RespResult addTeacher(Long deptId, String createBy,
                                 String teacherNo, String realName,
                                 String gender, String academicTitle,
                                 String internal, Long userId){
        RespResult result = RespResult.fail();
        if(deptId != null && createBy != null && teacherNo != null
                && realName != null && gender != null
                && academicTitle != null && internal != null && userId != null){
            if(KeyUtils.isValidCollegeDeptId(deptId,sysDeptService)){
                if(KeyUtils.isValidUserId(userId,userService)){
                    Teacher teacher = Teacher.builder().deptId(deptId).createBy(createBy)
                            .teacherNo(teacherNo).realName(realName).gender(gender)
                            .academicTitle(academicTitle).internal(internal).userId(userId)
                            .delFlag("0").createTime(new Date()).status("0").build();
                    // 新增老师
                    int rows = teacherService.addTeacher(teacher);
                    if(rows > 0){
                        // 增加成功
                        result = RespResult.ok();
                        result.setRcode(Rcode.ADD_SUCC);

                    }else {
                        result.setRcode(Rcode.ADD_ERR);
                    }
                }else {
                    result.setRcode(Rcode.USER_ID_ERR);
                }

            }else {
                result.setRcode(Rcode.DEPTID_PARAM_ERR);
            }


        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }


        return result;
    }

    /**
     * 修改教师
     */
    @ApiOperation("修改教师信息")
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @PutMapping("/modifyTeacher")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherId", value = "老师id", required = true),
            @ApiImplicitParam(name = "deptId", value = "部门(学院)id", required = true),
            @ApiImplicitParam(name = "updateBy", value = "修改人", required = true),
            @ApiImplicitParam(name = "teacherNo", value = "教师工号", required = true),
            @ApiImplicitParam(name = "realName", value = "教师姓名", required = true),
            @ApiImplicitParam(name = "gender", value = "性别", required = true),
            @ApiImplicitParam(name = "academicTitle", value = "职位(高等教师 普通教师等)", required = true),
            @ApiImplicitParam(name = "internal", value = "是否外聘；1表示内部，0表示外聘教师", required = true),
            @ApiImplicitParam(name = "userId", value = "用户表的id， 一个老师对应一个系统的用户", required = true)
    })
    public RespResult modifyTeacher(Integer teacherId, Long deptId, String updateBy,
                                    String teacherNo, String realName,
                                    String gender, String academicTitle,
                                    String internal, Long userId){
        RespResult result = RespResult.fail();
        if(deptId != null && updateBy != null && teacherNo != null
                && realName != null && gender != null
                && academicTitle != null && internal != null && userId != null && teacherId != null) {
            if(KeyUtils.isValidCollegeDeptId(deptId,sysDeptService)){
                Teacher teacher = teacherService.queryTeacherById(teacherId);
                if(teacher != null){
                    // 修改学生
                    teacher.setDeptId(deptId);
                    teacher.setUpdateBy(updateBy);
                    teacher.setTeacherNo(teacherNo);
                    teacher.setRealName(realName);
                    teacher.setGender(gender);
                    teacher.setAcademicTitle(academicTitle);
                    teacher.setInternal(internal);
                    teacher.setUserId(userId);
                    teacher.setUpdateTime(new Date());
                    int rows = teacherService.modifyTeacher(teacher);
                    if(rows > 0){
                        // 修改成功
                        result = RespResult.ok();
                        result.setRcode(Rcode.MODIFY_SUCC);
                    }else {
                        // 修改失败
                        result.setRcode(Rcode.MODIFY_ERR);
                    }

                }else {
                    // 提供老师id不正确
                    result.setRcode(Rcode.REQUEST_PARAM_ERR);
                }

            }else {
                result.setRcode(Rcode.DEPTID_PARAM_ERR);
            }

        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }

    /**
     * 根据教师id查询教师
     */
    @ApiOperation("根据老师id获取教师信息")
    @ApiImplicitParam(name = "teacherId", value = "教师的唯一id 主键", required = true)
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin,teacher')")
    @GetMapping("/oneTeacher")
    public RespResult getTeacher(Integer teacherId){
        RespResult result = RespResult.fail();
        if(teacherId != null && teacherId > 0){
            Teacher teacher = teacherService.queryTeacherById(teacherId);
            if(teacher != null){
                // 查询成功
                result = RespResult.ok();
                result.setData(teacher);
            }else {
                // 查询失败
                result.setRcode(Rcode.QUERY_ERR);
            }
        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }
        return result;
    }

    /**
     * 根据学院id查询学院的教师
     */
    @ApiOperation("根据学院id获取学院下的所有教师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", required = true, value = "dept标的主键，学院的唯一标识"),
            @ApiImplicitParam(name = "pageNum", required = false, value = "页码"),
            @ApiImplicitParam(name = "pageSize", required = false, value = "页数")
    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin,teacher')")
    @GetMapping("/collegeTeachers")
    public RespResult getCollegeTeacheres(@RequestParam(value = "deptId") Long deptId,
                                          @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        RespResult result = RespResult.fail();
        if(deptId != null && pageNum != null && pageSize != null){
            if (KeyUtils.isValidCollegeDeptId(deptId, sysDeptService)) {
                // 进行查询
                Integer startIndex = (pageNum - 1) * pageSize;
                List<Teacher> list = teacherService.queryCollegeTeacheresSize(deptId);
                List<Teacher> teachers = teacherService.queryCollegeTeacheres(deptId, startIndex, pageSize);
                if(teachers != null){
                    // 查询成功
                    result = RespResult.ok();
                    result.setList(teachers);
                    result.setPageInfo(new PageInfo(pageNum, pageSize, list.size()));

                }else {
                    result.setRcode(Rcode.QUERY_ERR);
                }

            }else {
                //失败
                result.setRcode(Rcode.COLLEGE_ID_ERR);
            }

        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }
        return result;
    }

    /**
     * 查看教师所讲的课程
     */
    @ApiOperation("获取教师所教授的所有课程")
    @ApiImplicitParam(name = "teacherId", value = "老师Id 根据老师Id去查询所教的所有课程", required = true)
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin,teacher')")
    @GetMapping("/getTeachCourses")
    public RespResult getTeachCourses(@RequestParam("teacherId") Integer teacherId,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        RespResult result = RespResult.fail();
        if(teacherId != null && KeyUtils.isValidTeacherId(teacherId, teacherService)){
            int startIndex = (pageNum - 1) * pageSize;
            // 开始查询
            // 1.查询teacher_course表所有的 teacherId对应的所有的TeacherCourse表的记录
            // 根据获得 TeacherCourse 中的 course_Id  来查询Course表 获得课程信息集合
            List<ThreeSelectByTeacherId> list = teacherCourseService.queryTeacherCourseByTeacherIdSize(teacherId);
            List<ThreeSelectByTeacherId> threeSelectByTeacherIdList = teacherCourseService.queryTeacherCourseByTeacherId(teacherId, startIndex, pageSize);
            if(threeSelectByTeacherIdList != null){
                result = RespResult.ok();
                result.setList(threeSelectByTeacherIdList);
                result.setPageInfo(new PageInfo(pageNum, pageSize, list.size()));

            }else {
                result.setRcode(Rcode.QUERY_ERR);
            }
        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }

    /**
     * 教师录入成绩接口
     */
    @ApiOperation("老师录入成绩，给学生录入成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentId", value = "学生唯一标识，学生id 外键", required = true),
            @ApiImplicitParam(name = "courseclassId", value = "上课班级唯一标识，上课班级id 外键", required = true),
            @ApiImplicitParam(name = "pingshiScore", value = "平时成绩 必须大于等于0", required = true),
            @ApiImplicitParam(name = "qimoScore", value = "期末成绩 必须大于等于0", required = true),
            @ApiImplicitParam(name = "zongpingScore", value = "两者综合成绩，必须大于等于0", required = true)
    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin,teacher')")
    @PostMapping("/writeScore")
    public RespResult writeStudentScore(CourseclassStudent courseclassStudent){
        RespResult result = RespResult.fail();
        if(courseclassStudent != null && KeyUtils.isValidStudentId(courseclassStudent.getStudentId(), studentService)
                && KeyUtils.isValidCourseclassId(courseclassStudent.getCourseclassId(), courseclassService)
                && courseclassStudent.getPingshiScore() != null && courseclassStudent.getPingshiScore() >= 0
                && courseclassStudent.getQimoScore() != null && courseclassStudent.getQimoScore() >= 0
                && courseclassStudent.getZongpingScore() != null &&  courseclassStudent.getZongpingScore() >= 0){
            //录入成绩
            int rows = courseclassStudentService.addCourseclassStudentByStuIdAndCouclassId(courseclassStudent);
            if(rows > 0){
                // 录入成绩成功
                result = RespResult.ok();
                result.setRcode(Rcode.ADD_SUCC);
            }else {
                // 录入成绩失败
                result.setRcode(Rcode.ADD_ERR);
            }

        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }

    /**
     * 教师修改成绩接口
     */
    @ApiOperation("老师修改录入的学生成绩，给学生修改成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentId", value = "学生唯一标识，学生id 外键", required = true),
            @ApiImplicitParam(name = "courseclassId", value = "上课班级唯一标识，上课班级id 外键", required = true),
            @ApiImplicitParam(name = "pingshiScore", value = "修改的平时成绩 必须大于等于0", required = true),
            @ApiImplicitParam(name = "qimoScore", value = "修改的期末成绩 必须大于等于0", required = true),
            @ApiImplicitParam(name = "zongpingScore", value = "修改的两者综合成绩，必须大于等于0", required = true)
    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin,teacher')")
    @PostMapping("/modifyScore")
    public RespResult modifyStudentScore(CourseclassStudent courseclassStudent){
        RespResult result = RespResult.fail();
        if(courseclassStudent != null && KeyUtils.isValidStudentId(courseclassStudent.getStudentId(), studentService)
                && KeyUtils.isValidCourseclassId(courseclassStudent.getCourseclassId(), courseclassService)
                && courseclassStudent.getPingshiScore() != null && courseclassStudent.getPingshiScore() >= 0
                && courseclassStudent.getQimoScore() != null && courseclassStudent.getQimoScore() >= 0
                && courseclassStudent.getZongpingScore() != null &&  courseclassStudent.getZongpingScore() >= 0){
            // 进行修改成绩
            int rows = courseclassStudentService.modifyStudentScore(courseclassStudent);
            if(rows > 0){
                // 修改成功
                result = RespResult.ok();
                result.setRcode(Rcode.MODIFY_SUCC);

            }else {
                result.setRcode(Rcode.MODIFY_ERR);
            }

        }else {
            // 参数有问题
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }


        return result;
    }


    /**
     * 教师查询学生成绩接口
     */
    @ApiOperation("教师查询学生成绩接口,对应学生在哪个班级什么课程的评分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentId", value = "学生唯一标识，学生id 外键", required = true),
            @ApiImplicitParam(name = "courseclassId", value = "上课班级唯一标识，上课班级id 外键", required = true)
    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin,teacher')")
    @GetMapping("/queryStudentScore")
    public RespResult queryStudentScore(Integer studentId, Integer courseclassId){
        RespResult result = RespResult.fail();
        if(KeyUtils.isValidStudentId(studentId, studentService)
                && KeyUtils.isValidCourseclassId(courseclassId, courseclassService)
                && courseclassId != null && courseclassId >= 0
                && studentId != null && studentId >= 0){
            // 开始查询学生 成绩
            StudentScore studentScore = courseclassStudentService.queryStudentScore(studentId, courseclassId);
            if(studentScore != null){
                //查询成功
                result = RespResult.ok();
                result.setRcode(Rcode.QUERY_SUCC);
                result.setData(studentScore);

            }else {
                result.setRcode(Rcode.QUERY_ERR);
            }

        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }


    /**
     * 添加老师授课接口
     */
    @ApiOperation("增加老师授课，管理老师的授课那个班级 哪门课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherId",value = "老师id 老师身份的标识信息", required = true),
            @ApiImplicitParam(name = "courseId",value = "课程id， 表示老师教授哪一门课", required = true),
            @ApiImplicitParam(name = "courseclassId",value = "上课班级id， 表示老师在那个班级上课", required = true),
            @ApiImplicitParam(name = "term",value = "老师教学时间", required = true),
            @ApiImplicitParam(name = "teacherNo",value = "教师工号", required = false),
            @ApiImplicitParam(name = "realName",value = "教师名字", required = false),
    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @PostMapping("/addTeacherCourse")
    public RespResult addTeacherCourse(TeacherCourse teacherCourse){
        RespResult result = RespResult.fail();
        if(teacherCourse.getTeacherId() != null && teacherCourse.getTeacherId() >= 0 && teacherCourse.getCourseId() != null
                && teacherCourse.getCourseId() >= 0 && teacherCourse.getCourseclassId() != null
                && teacherCourse.getCourseclassId() >= 0 && KeyUtils.isValidTeacherId(teacherCourse.getTeacherId(), teacherService)
                && KeyUtils.isValidCourseclassId(teacherCourse.getCourseclassId(), courseclassService)
                && KeyUtils.isValidCourseId(teacherCourse.getCourseId(), courseService)){

            int rows = teacherCourseService.addTeacherCourse(teacherCourse);
            if(rows > 0){
                // 添加成功
                result = RespResult.ok();
                result.setRcode(Rcode.ADD_SUCC);

            }else{
                result.setRcode(Rcode.ADD_ERR);
            }

        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }

    /**
     * 修改教师讲授课程 的信息
     */
    @ApiOperation(value = "修改教师讲授课程的信息", notes = "更改教师教授哪门课 去那个班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "originTeacherId",value = "要修改的原始老师id 老师身份的标识信息， 也就是未修改前的 准备修改的，不是你想要修改的数据", required = true),
            @ApiImplicitParam(name = "originCourseId",value = "要修改的原始课程id， 表示修改老师教授哪一门课，也就是未修改前的 准备修改的，不是你想要修改的数据", required = true),
            @ApiImplicitParam(name = "originCourseclassId",value = "要修改的原始上课班级id， 表示修改老师在那个班级上课，也就是未修改前的 准备修改的，不是你想要修改的数据", required = true),
            @ApiImplicitParam(name = "destTeacherId",value = "要修改的老师id 老师身份的标识信息， 想要修改的数据", required = true),
            @ApiImplicitParam(name = "destCourseId",value = "要修改的课程id， 表示修改老师教授哪一门课，想要修改的数据", required = true),
            @ApiImplicitParam(name = "destCourseclassId",value = "要修改的上课班级id， 表示修改老师在那个班级上课，想要修改的数据", required = true),
            @ApiImplicitParam(name = "destTerm",value = "要修改的老师教学时间", required = true)

    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @PutMapping("/modifyTeacherCourse")
    public RespResult modifyTeacherCourse(Integer originTeacherId, Integer originCourseId, Integer originCourseclassId,
                                          Integer destTeacherId, Integer destCourseId, Integer destCourseclassId, String destTerm){
        RespResult result = RespResult.fail();
        if(originTeacherId != null && originTeacherId >= 0 && originCourseId != null && originCourseId >= 0
                && originCourseclassId != null && originCourseclassId >= 0
                && destTeacherId != null && destTeacherId >= 0
                && destCourseId != null && destCourseId >= 0
                && destCourseclassId != null && destCourseclassId >= 0
                && StringUtils.isNotEmpty(destTerm) && StringUtils.isNotNull(destTerm)
                && KeyUtils.isValidTeacherId(destTeacherId, teacherService)
                && KeyUtils.isValidTeacherId(originTeacherId, teacherService)
                && KeyUtils.isValidCourseId(originCourseId, courseService)
                && KeyUtils.isValidCourseId(destCourseId, courseService)
                && KeyUtils.isValidCourseclassId(originCourseclassId, courseclassService)
                && KeyUtils.isValidCourseclassId(destCourseclassId, courseclassService)){
            // 参数没问题 开始修改
            int rows = teacherCourseService.modifyTeacherCourse(originTeacherId,originCourseId,originCourseclassId
                                                                ,destTeacherId,destCourseId,destCourseclassId, destTerm);
            if(rows > 0){
                //修改成功
                result = RespResult.ok();
                result.setRcode(Rcode.MODIFY_SUCC);
            }else {
                // 修改失败
                result.setRcode(Rcode.MODIFY_ERR);
            }
        }else{
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }
        return result;
    }
}
