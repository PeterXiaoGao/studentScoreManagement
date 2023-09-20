package com.ruoyi.student.service;

import com.ruoyi.student.mapper.CourseclassMapper;
import com.ruoyi.student.pojo.Courseclass;

import javax.annotation.Resource;

/**
 * @author xiaogao
 * @version 1.0
 * @className CourseclassService
 * @description
 * @since 1.0
 */
public interface CourseclassService {


    Courseclass queryCourseclassByKey(Integer courseclassId);
}
