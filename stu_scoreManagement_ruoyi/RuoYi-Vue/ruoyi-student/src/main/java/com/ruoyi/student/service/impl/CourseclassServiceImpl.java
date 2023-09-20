package com.ruoyi.student.service.impl;

import com.ruoyi.student.mapper.CourseclassMapper;
import com.ruoyi.student.pojo.Courseclass;
import com.ruoyi.student.service.CourseclassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiaogao
 * @version 1.0
 * @className CourseclassServiceImpl
 * @description
 * @since 1.0
 */
@Service
public class CourseclassServiceImpl implements CourseclassService {

    @Resource
    private CourseclassMapper courseclassMapper;

    @Override
    public Courseclass queryCourseclassByKey(Integer courseclassId) {
        return courseclassMapper.selectByPrimaryKey(courseclassId);
    }
}
