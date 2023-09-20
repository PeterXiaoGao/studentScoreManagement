package com.ruoyi.student.service;

import com.ruoyi.student.pojo.Metaclass;

import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className Meta
 * @description 班级服务
 * @since 1.0
 */
public interface MetaClassService {

    /**
     * 查询所有的班级
     * @return
     */
    List<Metaclass> queryAllMetaclass(int startIndex, int pageSize);

    /**
     * 删除班级
     * @param meataclassId 班级id
     * @return
     */
    int deleteMetaclassByMeataclassId(Integer meataclassId);

    /**
     * 修改班级
     *
     * @param meataclassId  班级id 主键
     * @param metaclassNo   班级编号
     * @param metaclassName 班级名称
     * @param grade         年级
     * @param deptId        部门id
     * @return
     */
    int modifyMetaclass(Integer meataclassId, String metaclassNo, String metaclassName, String grade, Long deptId);

    /**
     * 添加班级信息
     * @param metaclass
     * @return
     */
    int addMetaclass(Metaclass metaclass);

    /**
     * 查询班级 通过班级id
     * @param meataclassId
     * @return
     */
    Metaclass queryMetaclass(Integer meataclassId);

    /**
     * 获得某个学院下所有的班级
     * @param deptId
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Metaclass> queryCollegeMetaclassByDeptId(Long deptId, int startIndex, Integer pageSize);

    /**
     * 获取所有班级信息数量
     * @return
     */
    List<Metaclass> queryAllMetaclass();

    /**
     * 获取学院下所有班级信息数量
     * @param deptId
     * @return
     */
    List<Metaclass> queryCollageMetaclassByDeptIdSize(Long deptId);
}
