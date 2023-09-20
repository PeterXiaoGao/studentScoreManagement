package com.ruoyi.student.mapper;

import com.ruoyi.student.pojo.Course;
import com.ruoyi.student.pojo.Student;
import com.ruoyi.student.pojo.entity.StudentScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    /**
     * 通过学生id查询学生
     * @param studentId
     * @return
     */
    Student selectByStudentId(@Param("studentId") Integer studentId);

    /**
     * 查询学生所有的课程 和课程对应的学院
     *
     * @param studentId
     * @param startIndex
     * @param rows
     * @return
     */
    List<StudentScore> selectStudentAllCourseScore(@Param("studentId") Integer studentId, @Param("startIndex") Integer startIndex, @Param("rows") Integer rows);

    /**
     * 查询所有的学生
     * @return
     */
    List<Student> selectAllStudent(@Param("startIndex") Integer startIndex, @Param("rows") Integer rows);

    /**
     * 查询学生所有的课程
     * @param studentId
     * @param startIndex
     * @param rows
     * @return
     */
    List<Course> selectAllStudentCourse(@Param("studentId") Integer studentId, @Param("startIndex") Integer startIndex, @Param("rows") Integer rows);


    List<StudentScore> selectStudentAllCourseScores(@Param("studentId") Integer studentId);


    List<Student> selectAll();

    List<Course> selectAllStudentCourses(@Param("studentId") Integer studentId);
}
