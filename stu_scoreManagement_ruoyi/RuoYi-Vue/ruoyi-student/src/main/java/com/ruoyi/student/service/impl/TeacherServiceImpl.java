package com.ruoyi.student.service.impl;

import com.ruoyi.student.mapper.TeacherMapper;
import com.ruoyi.student.pojo.Teacher;
import com.ruoyi.student.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className TeacherServiceImpl
 * @description
 * @since 1.0
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    /**
     * 查询所有老师
     * @return 所有老师信息
     */
    @Override
    public List<Teacher> queryAllTeacher(int startIndex, Integer pageSize) {

        List<Teacher> teachers = teacherMapper.selectAllTeacher(startIndex, pageSize);

        Iterator<Teacher> iterator = teachers.iterator();
        while (iterator.hasNext()){
            Teacher node = iterator.next();
            if("2".equals(node.getDelFlag())){
                iterator.remove();
            }
        }

        return teachers;
    }

    /**
     * 删除教师
     * @param teacherId 教师id
     * @return >0 成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteTeacherById(Integer teacherId) {
        // 进行逻辑删除
        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);
        teacher.setDelFlag("2");
        return teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    /**
     * 添加教师
     * @param teacher 老师
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addTeacher(Teacher teacher) {

        return teacherMapper.insertSelective(teacher);
    }

    /**
     * 修改教师
     * @param teacher 教师信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int modifyTeacher(Teacher teacher) {
        return teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    /**
     * 根据教师id查询教师
     * @param teacherId 教师id
     * @return
     */
    @Override
    public Teacher queryTeacherById(Integer teacherId) {
        return teacherMapper.selectByPrimaryKey(teacherId);
    }

    /**
     * 根据学院id 查询本学院下的所有教师
     * @param deptId 学院id
     * @param startIndex 页码
     * @param pageSize   页数
     * @return
     */
    @Override
    public List<Teacher> queryCollegeTeacheres(Long deptId, Integer startIndex, Integer pageSize) {
        return teacherMapper.selectCollageTeacheres(deptId, startIndex, pageSize);
    }

    /**
     * 获取所有教师
     * @return
     */
    @Override
    public List<Teacher> queryAllTeachers() {

        return teacherMapper.selectAllTeachers();

    }

    /**
     * 获取学院下所有教师
     * @param deptId
     * @return
     */
    @Override
    public List<Teacher> queryCollegeTeacheresSize(Long deptId) {
        return teacherMapper.selectCollageTeacheresSize(deptId);
    }
}
