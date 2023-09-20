package com.ruoyi.student.mapper;

import com.ruoyi.student.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer teacherId);

    int insert(Teacher row);

    int insertSelective(Teacher row);

    Teacher selectByPrimaryKey(Integer teacherId);

    int updateByPrimaryKeySelective(Teacher row);

    int updateByPrimaryKey(Teacher row);

    /**
     * 查询所有老师
     * @return 老师s
     */
    List<Teacher> selectAllTeacher(@Param("startIndex") int startIndex, @Param("rows") Integer pageSize);

    /**
     * 根据学院id查询本学院下的所有教师
     * @param deptId 学院id
     * @param startIndex
     * @param rows
     * @return
     */
    List<Teacher> selectCollageTeacheres(@Param("deptId") Long deptId, @Param("startIndex") Integer startIndex, @Param("rows") Integer rows);

    /**
     * 获取所有教师
     * @return
     */
    List<Teacher> selectAllTeachers();

    /**
     * 获取学院下所有教师数量
     * @param deptId
     * @return
     */
    List<Teacher> selectCollageTeacheresSize(@Param("deptId") Long deptId);
}
