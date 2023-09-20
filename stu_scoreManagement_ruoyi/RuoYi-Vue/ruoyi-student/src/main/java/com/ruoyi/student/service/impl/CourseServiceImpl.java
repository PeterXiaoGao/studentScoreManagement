package com.ruoyi.student.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.student.mapper.CourseMapper;
import com.ruoyi.student.pojo.Course;
import com.ruoyi.student.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className CourseServiceImpl
 * @description
 * @since 1.0
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    /**
     * 查询所有的课程
     * @return  null 失败
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    @Override
    // public List<Course> queryAllCourse(Course course, Integer pageNum, Integer pageSize) {
    public List<Course> queryAllCourse(Course course) {
        //计算起始下标
        // int startIndex = (pageNum - 1) * pageSize;

        // List<Course> courses = courseMapper.selectAllCourse(course,startIndex, pageSize);
        List<Course> courses = courseMapper.selectAllCourse(course);

        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course c = iterator.next();
            if ("2".equals(c.getDelFlag())) {
                iterator.remove();
            }
        }


        return courses;
    }

    @DataScope(deptAlias = "d", userAlias = "u")
    @Override
    public List<Course> queryAllCourses(Course course) {
        return courseMapper.selectAllCourses(course);
    }

    /**
     *
     * @param course 封装的课程对象
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addCourse(Course course) {
        Date currentDate = new Date();
        course.setCreateTime(currentDate);
        course.setStatus("0");
        course.setDelFlag("0");
        course.setCourseId(null);

        int rows = 0;
        try {
            rows = courseMapper.insertSelective(course);

        } catch (Exception e) {
            log.error("增加课程，deptId错误");
            rows = 99;

        }

        return rows;
    }

    /**
     *
     * @param course  修改课程对象
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int modifyCourse(Course course) {

        int rows = 0;
        try {

            Date currentDate = new Date();

            // 先查询出course对象
            Course preCourse = courseMapper.selectByPrimaryKey(course.getCourseId());
            preCourse.setUpdateTime(currentDate);
            preCourse.setCourseNo(course.getCourseNo());
            preCourse.setDeptId(course.getDeptId());
            preCourse.setUpdateBy(course.getUpdateBy());
            preCourse.setCourseName(course.getCourseName());
            preCourse.setQimoPercent(course.getQimoPercent());
            preCourse.setPingshiPercent(course.getPingshiPercent());

            //更新
            rows = courseMapper.updateByPrimaryKeySelective(preCourse);

        } catch (Exception e) {
            // 更新失败
            rows = 99;
            log.error("修改课程失败，主键出问题了");
        }

        return rows;
    }

    /**
     *
     * @param courseId 课程id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteCourseByCourseId(Integer courseId) {

        Course course = courseMapper.selectByPrimaryKey(courseId);
        // 修改 delFlag状态 2代表删除
        course.setDelFlag("2");

        int rows = courseMapper.updateByPrimaryKeySelective(course);

        return rows;
    }

    /**
     * 查询课程 通过课程id
     * @param courseId 课程id
     * @return
     */
    @Override
    public Course queryCourse(Integer courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    /**
     * 查询学院的所有课程
     *
     * @param deptId     学院id
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<Course> queryCollegeCourse(Long deptId, int startIndex, Integer pageSize) {

        List<Course> courses = courseMapper.selectCourseByDeptId(deptId, startIndex, pageSize);
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course node = iterator.next();
            if("2".equals(node.getDelFlag())){
                iterator.remove();
            }
        }

        return courses;

    }

    @Override
    public List<Course> queryCollegeCourses(Long deptId) {
        return courseMapper.selectAllCoursesByDeptId(deptId);
    }
}
