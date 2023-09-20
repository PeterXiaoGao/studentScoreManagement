package com.ruoyi.student.service.impl;

import com.ruoyi.student.mapper.StudentMapper;
import com.ruoyi.student.pojo.Course;
import com.ruoyi.student.pojo.Student;
import com.ruoyi.student.pojo.entity.StudentScore;
import com.ruoyi.student.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className StudentServiceImpl
 * @description
 * @since 1.0
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;
    @Override
    public Student queryStudentByKey(Integer studentId) {
        return studentMapper.selectByStudentId(studentId);
    }

    /**
     * 查询学生自己所有的课程成绩
     * @param studentId  学生id
     * @param startIndex 起始下标
     * @param pageSize 行数 记录
     * @return
     */
    @Override
    public List<StudentScore> queryStudentScore(Integer studentId, Integer startIndex, Integer pageSize) {
        List<StudentScore> studentScoreList = studentMapper.selectStudentAllCourseScore(studentId, startIndex, pageSize);
        Student student = studentMapper.selectByStudentId(studentId);
        if(studentScoreList != null){
            // 有结果
            studentScoreList.forEach(studentScore -> {
                studentScore.setRealName(student.getRealName());
                studentScore.setStudentNo(student.getStudentNo());
                studentScore.setGrade(student.getGrade());
                studentScore.setGender(student.getGender());
                studentScore.setUserId(student.getUserId());
            });
        }
        return studentScoreList;
    }

    /**
     * 查询所有的学生
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<Student> queryAllByStudentId(Integer startIndex, Integer pageSize) {
        List<Student> students = studentMapper.selectAllStudent(startIndex, pageSize);
        if(students != null){
            Iterator<Student> iterator = students.iterator();
            while (iterator.hasNext()){
                Student node = iterator.next();
                if("2".equals(node.getDelFlag())){
                    iterator.remove();
                }
            }
        }

        return students;
    }

    /**
     * 查询学生所有的课程
     * @param studentId
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<Course> queryAllCourseByStudentId(Integer studentId, Integer startIndex, Integer pageSize) {

        return studentMapper.selectAllStudentCourse(studentId, startIndex, pageSize);

    }

    @Override
    public List<Course> queryAllCourseByStudentIdSize(Integer studentId) {
        return studentMapper.selectAllStudentCourses(studentId);
    }

    @Override
    public List<StudentScore> queryStudentScore(Integer studentId) {
        List<StudentScore> studentScoreList = studentMapper.selectStudentAllCourseScores(studentId);
        Student student = studentMapper.selectByStudentId(studentId);
        if(studentScoreList != null){
            // 有结果
            studentScoreList.forEach(studentScore -> {
                studentScore.setRealName(student.getRealName());
                studentScore.setStudentNo(student.getStudentNo());
                studentScore.setGrade(student.getGrade());
                studentScore.setGender(student.getGender());
                studentScore.setUserId(student.getUserId());
            });
        }
        return studentScoreList;
    }

    @Override
    public List<Student> queryAll() {
        return  studentMapper.selectAll();
    }


}
