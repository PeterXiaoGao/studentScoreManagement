package com.ruoyi.student.mapper;

import com.ruoyi.student.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {


    /**
     * 查询所有的课程
     * @param startIndex  开始下标
     * @param pageSize    一页多少个
     * @return 所有课程
     */
    // List<Course> selectAllCourse(@Param("course") Course course, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);
    List<Course> selectAllCourse(Course course);






    int deleteByPrimaryKey(Integer courseId);

    int insert(Course row);

    int insertSelective(Course row);

    Course selectByPrimaryKey(Integer courseId);

    int updateByPrimaryKeySelective(Course row);

    int updateByPrimaryKey(Course row);

    /**
     * 通过部门id查询课程信息
     *
     * @param deptId 学院id
     * @param startIndex 起始下标
     * @param rows 多少行数据
     * @return
     */
    List<Course> selectCourseByDeptId(@Param("deptId") Long deptId, @Param("startIndex") int startIndex, @Param("rows") Integer rows);

    /**
     * 查询所有课程
     * @return
     */
    List<Course> selectAllCourses(Course course);

    List<Course> selectAllCoursesByDeptId(@Param("deptId") Long deptId);
}
