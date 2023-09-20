package com.ruoyi.student.mapper;

import com.ruoyi.student.pojo.Metaclass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetaclassMapper {
    int deleteByPrimaryKey(Integer meataclassId);

    int insert(Metaclass row);

    int insertSelective(Metaclass row);

    Metaclass selectByPrimaryKey(Integer meataclassId);

    int updateByPrimaryKeySelective(Metaclass row);

    int updateByPrimaryKey(Metaclass row);

    /**
     * 获取所有班级
     * @param startIndex 起始下标
     * @param pageSize 页数
     * @return 封装MetaClass的集合
     */
    List<Metaclass> selectAllMetaclass(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * 获取学院下所有的班级
     * @param deptId 学院Id
     * @param startIndex 起始下标
     * @param rows 多少行数据
     * @return
     */
    List<Metaclass> selectCollegeMetaclasses(@Param("deptId") Long deptId, @Param("startIndex") int startIndex, @Param("rows") Integer rows);

    /**
     * 获取所有班级信息数量
     * @return
     */
    List<Metaclass> selectAllMetaclasses();

    /**
     * 获取学院所有班级信息数量
     * @param deptId
     * @return
     */
    List<Metaclass> selectCollegeMetaclassesSize(@Param("deptId") Long deptId);
}
