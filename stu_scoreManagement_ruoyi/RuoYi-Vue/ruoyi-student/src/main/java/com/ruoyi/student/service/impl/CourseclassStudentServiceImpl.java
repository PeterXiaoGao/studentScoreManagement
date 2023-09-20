package com.ruoyi.student.service.impl;

import com.ruoyi.student.mapper.CourseclassStudentMapper;
import com.ruoyi.student.pojo.CourseclassStudent;
import com.ruoyi.student.pojo.CourseclassStudentKey;
import com.ruoyi.student.pojo.entity.StudentScore;
import com.ruoyi.student.service.CourseclassStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author xiaogao
 * @version 1.0
 * @className CourseclassStudentServiceImpl
 * @description
 * @since 1.0
 */
@Service
public class CourseclassStudentServiceImpl implements CourseclassStudentService {
    @Resource
    private CourseclassStudentMapper courseclassStudentMapper;

    /**
     * 添加学生成绩
     * @param courseclassStudent 封装学生信息
     * @return >0 成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addCourseclassStudentByStuIdAndCouclassId(CourseclassStudent courseclassStudent) {
        int rows = 0;
        // 异常  metaclassId是主键 根据他查数据库 看是否存在这条记录
        CourseclassStudentKey courseclassStudentKey = new CourseclassStudentKey();
        courseclassStudentKey.setCourseclassId(courseclassStudent.getCourseclassId());
        courseclassStudentKey.setStudentId(courseclassStudent.getStudentId());

        CourseclassStudent selectCourseclassStudent = courseclassStudentMapper.selectByPrimaryKey(courseclassStudentKey);
        if(selectCourseclassStudent != null){
            // 数据库已经存在
            // 只能修改
            rows = courseclassStudentMapper.updateByPrimaryKeySelective(courseclassStudent);
        }else{
            // 数据库不存在可以插入
            rows = courseclassStudentMapper.insertSelective(courseclassStudent);
        }

        return rows;
    }

    /**
     *
     * @param courseclassStudent 参数对象
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int modifyStudentScore(CourseclassStudent courseclassStudent) {
        return courseclassStudentMapper.updateByPrimaryKeySelective(courseclassStudent);
    }

    /**
     * 老师查询学生成绩
     * @param studentId 学生唯一标识  id
     * @param courseclassId 上课班级唯一标识 id
     * @return
     */
    @Override
    public StudentScore queryStudentScore(Integer studentId, Integer courseclassId) {

        return courseclassStudentMapper.queryStudentScore(studentId, courseclassId);
    }

}
