package com.ruoyi.student.mapper;

import com.ruoyi.student.pojo.Courseclass;

public interface CourseclassMapper {
    int deleteByPrimaryKey(Integer courseclassId);

    int insert(Courseclass row);

    int insertSelective(Courseclass row);

    Courseclass selectByPrimaryKey(Integer courseclassId);

    int updateByPrimaryKeySelective(Courseclass row);

    int updateByPrimaryKey(Courseclass row);
}