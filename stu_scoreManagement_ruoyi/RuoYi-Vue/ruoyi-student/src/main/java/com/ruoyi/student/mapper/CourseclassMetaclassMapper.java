package com.ruoyi.student.mapper;

import com.ruoyi.student.pojo.CourseclassMetaclassKey;

public interface CourseclassMetaclassMapper {
    int deleteByPrimaryKey(CourseclassMetaclassKey key);

    int insert(CourseclassMetaclassKey row);

    int insertSelective(CourseclassMetaclassKey row);
}