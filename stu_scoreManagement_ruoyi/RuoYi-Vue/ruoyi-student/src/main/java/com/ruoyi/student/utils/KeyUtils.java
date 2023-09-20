package com.ruoyi.student.utils;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.student.pojo.Course;
import com.ruoyi.student.pojo.Courseclass;
import com.ruoyi.student.pojo.Student;
import com.ruoyi.student.pojo.Teacher;
import com.ruoyi.student.service.CourseService;
import com.ruoyi.student.service.CourseclassService;
import com.ruoyi.student.service.StudentService;
import com.ruoyi.student.service.TeacherService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import lombok.Data;

/**
 * @author xiaogao
 * @version 1.0
 * @className KeyUtils
 * @description
 * @since 1.0
 */
@Data
public class KeyUtils {

    /**
     * 判断学院 deptId 是否有效
     * @param deptId 学院id
     * @param sysDeptService dept表的业务对象
     * @return
     */
    public static boolean isValidCollegeDeptId(Long deptId, ISysDeptService sysDeptService) {
        boolean valid = false;
        if (deptId != null && deptId > 0) {
            // 判断deptId
            SysDept sysDept = sysDeptService.selectDeptById(deptId);
            if (sysDept != null) {

                SysDept parentDept = sysDeptService.selectDeptById(sysDept.getParentId());
                if (0 == parentDept.getParentId()) {
                    valid = true;
                }
            }
        }
        return valid;
    }

    /**
     * 判断userId 是否有效
     * @param userId 用户id
     * @param userService  用户业务服务对象
     * @return
     */
    public static boolean isValidUserId(Long userId, ISysUserService userService) {
        boolean valid = false;
        if (userId != null && userId > 0) {
            // 判断deptId
            SysUser sysUser = userService.selectUserById(userId);
            if (sysUser != null) {
                valid = true;
            }
        }
        return valid;
    }

    /**
     * 判断teacherId是否有效
     */
    public static boolean isValidTeacherId(Integer teacherId, TeacherService teacherService) {
        boolean valid = false;
        if (teacherId != null && teacherId > 0) {
            // 判断teacherId
            Teacher teacher = teacherService.queryTeacherById(teacherId);
            if (teacher != null) {
                valid = true;
            }
        }
        return valid;

    }

    /**
     * 判断studnetId是否有效
     */
    public static boolean isValidStudentId(Integer studentId, StudentService studentService) {
        boolean valid = false;
        if(studentId != null && studentId > 0){
            Student student = studentService.queryStudentByKey(studentId);
            if(student != null){
                // studentId有效
                valid = true;
            }
        }

        return valid;
    }

    /**
     * 判断courseclassId是否有效
     * @param courseclassId courseclassId courseclass表主键id
     * @param courseclassService courseclass服务表对象
     * @return
     */
    public static boolean isValidCourseclassId(Integer courseclassId, CourseclassService courseclassService) {
        boolean valid = false;
        if(courseclassId != null && courseclassId > 0){
            Courseclass courseclass = courseclassService.queryCourseclassByKey(courseclassId);
            if(courseclass != null){
                valid = true;
            }
        }

        return valid;
    }

    /**
     * 判断courseId是否有效
     * @param courseId 课程id
     * @param courseService 课程服务对象
     * @return
     */
    public static boolean isValidCourseId(Integer courseId, CourseService courseService) {
        boolean valid = false;
        if(courseId != null && courseId > 0){
            Course course = courseService.queryCourse(courseId);
            if(course != null){
                valid = true;
            }
        }

        return valid;
    }
}
