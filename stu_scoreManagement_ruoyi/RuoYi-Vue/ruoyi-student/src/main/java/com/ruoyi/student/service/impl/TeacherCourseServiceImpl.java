package com.ruoyi.student.service.impl;

import com.ruoyi.student.mapper.TeacherCourseMapper;
import com.ruoyi.student.mapper.TeacherMapper;
import com.ruoyi.student.pojo.Teacher;
import com.ruoyi.student.pojo.TeacherCourse;
import com.ruoyi.student.pojo.ThreeSelectByTeacherId;
import com.ruoyi.student.service.TeacherCourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className TeacherCourseServiceImpl
 * @description
 * @since 1.0
 */
@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    @Resource
    private TeacherCourseMapper teacherCourseMapper;

    @Resource
    private TeacherMapper teacherMapper;

    /**
     * 根据teacherId 查询表所有符合的信息
     *
     * @param teacherId  老师id
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<ThreeSelectByTeacherId> queryTeacherCourseByTeacherId(Integer teacherId, int startIndex, Integer pageSize) {
        return teacherCourseMapper.selectAllCourseInfoByTeacherId(teacherId, startIndex, pageSize);
    }

    /**
     * 添加老师教授课程
     *
     * @param teacherCourse@return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addTeacherCourse(TeacherCourse teacherCourse) {
        int rows = 0;
        // 设置 教师姓名。教师工号
        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCourse.getTeacherId());
        teacherCourse.setTeacherNo(teacher.getTeacherNo());
        teacherCourse.setRealName(teacher.getRealName());

        TeacherCourse selectTeacherCourse = teacherCourseMapper.selectTeacherCourse(teacherCourse);
        if (selectTeacherCourse != null) {
            // teacher_course表有此教师的数据
            rows = teacherCourseMapper.deleteTeacherCourse(teacherCourse);
            // 添加
            rows += teacherCourseMapper.insertSelective(teacherCourse);

        } else {
            // 没有此教师的数据
            rows = teacherCourseMapper.insertSelective(teacherCourse);
        }

        return rows;
    }

    /**
     * 修改老师教授课程
     *
     * @param originTeacherId     原老师id
     * @param originCourseId      原课程id
     * @param originCourseclassId 原上课班级id
     * @param destTeacherId       目的老师id
     * @param destCourseId        目的课程id
     * @param destCourseclassId   目的上课班级id
     * @param destTerm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int modifyTeacherCourse(Integer originTeacherId, Integer originCourseId, Integer originCourseclassId,
                                   Integer destTeacherId, Integer destCourseId, Integer destCourseclassId, String destTerm) {
        int rows = 0;
        TeacherCourse originTeacherCourse = new TeacherCourse();
        originTeacherCourse.setTeacherId(originTeacherId);
        originTeacherCourse.setCourseId(originCourseId);
        originTeacherCourse.setCourseclassId(originCourseclassId);
        TeacherCourse sqlTeacherCourse = teacherCourseMapper.selectTeacherCourse(originTeacherCourse);
        if(destTeacherId.compareTo(sqlTeacherCourse.getTeacherId()) == 0){
            // 更新teahcercourse  原teacher和现在teacher是同一个教师
            sqlTeacherCourse.setCourseId(destCourseId);
            sqlTeacherCourse.setCourseclassId(destCourseclassId);
            sqlTeacherCourse.setTerm(destTerm);
            rows = teacherCourseMapper.updateByTeacherCourse(sqlTeacherCourse, originTeacherId, originCourseId, originCourseclassId);
        }else{
            // 更新teahcercourse  原teacher和现在teacher不是同一个教师
            Teacher destTeacher = teacherMapper.selectByPrimaryKey(destTeacherId);
            sqlTeacherCourse.setTeacherNo(destTeacher.getTeacherNo());
            sqlTeacherCourse.setRealName(destTeacher.getRealName());
            // 更改目的数据
            sqlTeacherCourse.setTeacherId(destTeacherId);
            sqlTeacherCourse.setCourseId(destCourseId);
            sqlTeacherCourse.setCourseclassId(destCourseclassId);
            sqlTeacherCourse.setTerm(destTerm);

            rows = teacherCourseMapper.updateByTeacherCourse(sqlTeacherCourse, originTeacherId, originCourseId, originCourseclassId);
        }


        return rows;
    }

    @Override
    public List<ThreeSelectByTeacherId> queryTeacherCourseByTeacherIdSize(Integer teacherId) {
        return teacherCourseMapper.selectAllCourseInfoByTeacherIdSize(teacherId);
    }
}
