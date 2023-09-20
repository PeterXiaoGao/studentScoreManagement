package com.ruoyi.student.mapper;

import com.ruoyi.student.pojo.TeacherCourse;
import com.ruoyi.student.pojo.TeacherCourseKey;
import com.ruoyi.student.pojo.ThreeSelectByTeacherId;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherCourseMapper {
    int deleteByPrimaryKey(TeacherCourseKey key);

    int insert(TeacherCourse row);

    int insertSelective(TeacherCourse row);

    TeacherCourse selectByPrimaryKey(TeacherCourseKey key);

    int updateByPrimaryKeySelective(TeacherCourse row);

    int updateByPrimaryKey(TeacherCourse row);

    /**
     * 根据teacherId 查询表所有符合的信息
     * @param teacherId 老师id
     * @return TeacherCourse
     */
    List<TeacherCourse> selectAllByTeacherId(@Param("teacherId") Integer teacherId);

    /**
     * 根据teacherId 查询表所有符合的信息
     *
     * @param teacherId  老师id
     * @param startIndex 起始下标
     * @param rows 页数
     * @return ThreeSelectByTeacherId
     */
    List<ThreeSelectByTeacherId> selectAllCourseInfoByTeacherId(@Param("teacherId") Integer teacherId, @Param("startIndex") int startIndex, @Param("rows") Integer rows);

    /**
     * 根据teacher_id,course_id,courseclass_id查询teacher_course 的对象
     * @param teacherCourse 参数
     * @return
     */
    TeacherCourse selectTeacherCourse(TeacherCourse teacherCourse);

    /**
     * 删除teacher_course表的 数据
     * @param teacherCourse
     */
    int  deleteTeacherCourse(TeacherCourse teacherCourse);

    /**
     * 修改teacher_course表的数据
     *
     * @param sqlTeacherCourse
     * @param originTeacherId
     * @param originCourseId
     * @param originCourseclassId
     * @return
     */
    int updateByTeacherCourse(@Param("sqlTeacherCourse") TeacherCourse sqlTeacherCourse,
                              @Param("originTeacherId") Integer originTeacherId,
                              @Param("originCourseId") Integer originCourseId,
                              @Param("originCourseclassId") Integer originCourseclassId);

    /**
     * 根据老师查询所有教授课程信息
     * @param teacherId
     * @return
     */
    List<ThreeSelectByTeacherId> selectAllCourseInfoByTeacherIdSize(@Param("teacherId") Integer teacherId);
}
