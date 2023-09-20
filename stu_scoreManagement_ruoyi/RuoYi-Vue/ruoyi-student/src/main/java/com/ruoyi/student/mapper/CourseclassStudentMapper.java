package com.ruoyi.student.mapper;

import com.ruoyi.student.pojo.CourseclassStudent;
import com.ruoyi.student.pojo.CourseclassStudentKey;
import com.ruoyi.student.pojo.entity.StudentScore;
import org.apache.ibatis.annotations.Param;

public interface CourseclassStudentMapper {
    int deleteByPrimaryKey(CourseclassStudentKey key);

    int insert(CourseclassStudent row);

    int insertSelective(CourseclassStudent row);

    CourseclassStudent selectByPrimaryKey(CourseclassStudentKey key);

    int updateByPrimaryKeySelective(CourseclassStudent row);

    int updateByPrimaryKey(CourseclassStudent row);

    /**
     * 教师查询学生成绩
     * @param studentId 学生id
     * @param courseclassId 学生课程
     * @return
     */
    StudentScore queryStudentScore(@Param("studentId") Integer studentId, @Param("courseclassId") Integer courseclassId);
}
