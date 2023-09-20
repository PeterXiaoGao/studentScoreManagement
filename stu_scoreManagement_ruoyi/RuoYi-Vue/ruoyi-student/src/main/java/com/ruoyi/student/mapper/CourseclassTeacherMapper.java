package com.ruoyi.student.mapper;

import com.ruoyi.student.pojo.CourseclassTeacher;
import com.ruoyi.student.pojo.CourseclassTeacherKey;

public interface CourseclassTeacherMapper {
    int deleteByPrimaryKey(CourseclassTeacherKey key);

    int insert(CourseclassTeacher row);

    int insertSelective(CourseclassTeacher row);

    CourseclassTeacher selectByPrimaryKey(CourseclassTeacherKey key);

    int updateByPrimaryKeySelective(CourseclassTeacher row);

    int updateByPrimaryKey(CourseclassTeacher row);
}