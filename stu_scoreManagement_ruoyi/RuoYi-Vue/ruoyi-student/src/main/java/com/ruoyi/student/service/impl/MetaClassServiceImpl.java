package com.ruoyi.student.service.impl;

import com.ruoyi.student.mapper.MetaclassMapper;
import com.ruoyi.student.pojo.Metaclass;
import com.ruoyi.student.service.MetaClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className MetaClassServiceImpl
 * @description
 * @since 1.0
 */
@Service
public class MetaClassServiceImpl implements MetaClassService {

    @Resource
    private MetaclassMapper metaclassMapper;

    /**
     * 查询所有的班级
     * @return
     */
    @Override
    public List<Metaclass> queryAllMetaclass(int startIndex, int pageSize) {

        // 查询所有的班级并且分页
        List<Metaclass> metaclassList = metaclassMapper.selectAllMetaclass(startIndex, pageSize);

        Iterator<Metaclass> iterator = metaclassList.iterator();
        while(iterator.hasNext()){
            Metaclass next = iterator.next();
            if("2".equals(next.getDelFlag())){
                iterator.remove();
            }
        }

        return metaclassList;
    }

    /**
     * 删除班级
     * @param meataclassId 班级id
     * @return >0代表成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteMetaclassByMeataclassId(Integer meataclassId) {
        Metaclass metaclass = metaclassMapper.selectByPrimaryKey(meataclassId);
        metaclass.setDelFlag("2");
        int rows = metaclassMapper.updateByPrimaryKeySelective(metaclass);

        return rows;
    }

    /**
     * 修改班级
     *
     * @param meataclassId  班级id主键
     * @param metaclassNo   班级编号
     * @param metaclassName 班级名称
     * @param grade         年级
     * @param deptId        部门id
     * @return >0 成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int modifyMetaclass(Integer meataclassId, String metaclassNo, String metaclassName, String grade, Long deptId) {
        // 先查询数据库 根据班级id
        Metaclass metaclass = metaclassMapper.selectByPrimaryKey(meataclassId);
        metaclass.setMetaclassNo(metaclassNo);
        metaclass.setMetaclassName(metaclassName);
        metaclass.setGrade(grade);
        metaclass.setDeptId(deptId);
        int rows = metaclassMapper.updateByPrimaryKeySelective(metaclass);

        return rows;
    }

    /**
     * 添加班级
     * @param metaclass 班级信息
     * @return >0 成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addMetaclass(Metaclass metaclass) {
        metaclass.setMeataclassId(null);
        metaclass.setDelFlag("0");
        metaclass.setCreateTime(new Date());
        metaclass.setUpdateBy("");
        metaclass.setStatus("0");

        int rows = metaclassMapper.insertSelective(metaclass);

        return rows;
    }

    @Override
    public Metaclass queryMetaclass(Integer meataclassId) {
        return  metaclassMapper.selectByPrimaryKey(meataclassId);
    }

    @Override
    public List<Metaclass> queryCollegeMetaclassByDeptId(Long deptId, int startIndex, Integer pageSize) {

        return metaclassMapper.selectCollegeMetaclasses(deptId, startIndex, pageSize);

    }

    @Override
    public List<Metaclass> queryAllMetaclass() {

        return metaclassMapper.selectAllMetaclasses();
    }

    /**
     * 获取学院下所有班级信息数量
     * @param deptId
     * @return
     */
    @Override
    public List<Metaclass> queryCollageMetaclassByDeptIdSize(Long deptId) {
        return metaclassMapper.selectCollegeMetaclassesSize(deptId);
    }
}
