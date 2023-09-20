package com.ruoyi.student.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.student.enums.Rcode;
import com.ruoyi.student.pojo.Metaclass;
import com.ruoyi.student.utils.KeyUtils;
import com.ruoyi.student.view.PageInfo;
import com.ruoyi.student.view.RespResult;
import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className MetaClassController
 * @description
 * @since 1.0
 */
@Api(tags = "班级管理服务")
@RestController
@RequestMapping("student/metaclass")
public class MetaClassController extends BaseController {


    /**
     * 获取所有班级
     * @param pageNum  页码
     * @param pageSize 页数
     * @return 响应结果
     */
    @ApiOperation(value = "获取所有班级同时支持分页", notes = "pageNum和pageSize可以没有 没有默认 pageNum是1， pageSize是8")
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @GetMapping("/list")
    public RespResult queryAllMetaclass( @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(required = false, defaultValue = "8") Integer pageSize){
        RespResult result = RespResult.fail();

        if(StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)){
            int startIndex = (pageNum - 1) * pageSize;
            List<Metaclass> list = metaClassService.queryAllMetaclass();
            List<Metaclass> metaclassList = metaClassService.queryAllMetaclass(startIndex, pageSize);
            if(metaclassList != null){
                // 查询成功
                result.setPageInfo(new PageInfo(pageNum, pageSize, list.size()));
                result.setList(metaclassList);
                result.setRcode(Rcode.SUCC);
            }else {
                // 获取失败
                result.setRcode(Rcode.QUERY_METACLASS_ERR);
            }
        }else {
            // 参数错误
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }


    /**
     * 删除班级
     * @param meataclassId 班级id
     * @return
     */
    @ApiOperation(value = "根据meataclassId删除班级", notes = "meataclassId必须有且正确")
    @ApiImplicitParam(value = "班级id,主键必须有 保证正确", required = true,name = "meataclassId")
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @DeleteMapping("/deleteMetaclass")
    public RespResult deleteMetaclass(Integer meataclassId){
        RespResult result = RespResult.fail();
        if(meataclassId != null && meataclassId > 0){
            // 开始删除
            int rows = metaClassService.deleteMetaclassByMeataclassId(meataclassId);
            if(rows > 0){
                // 删除成功
                result = RespResult.ok();
                result.setRcode(Rcode.DELETE_SUCC);
            }else {
                // 删除失败
                result.setRcode(Rcode.DELETE_ERR);
            }

        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }

    /**
     * 修改班级信息
     */
    @ApiOperation("修改班级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "meataclassId", value = "班级主键，必须要有保证正确", required = true),
            @ApiImplicitParam(name = "metaclassNo", value = "班级号，不改写原来的", required = true),
            @ApiImplicitParam(name = "metaclassName", value = "班级名字，不改写原来的", required = true),
            @ApiImplicitParam(name = "deptId", value = "学院id，必须要有保证正确，不改写原来的", required = true),
            @ApiImplicitParam(name = "grade", value = "年级，必须要有保证正确，不改写原来的", required = true)
    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @PutMapping("/modifyMetaclass")
    public RespResult modifyMetaclass(Integer meataclassId, String metaclassNo, String metaclassName, String grade, Long deptId){
        RespResult result = RespResult.fail();
        if(metaclassNo != null && metaclassName != null && grade != null && deptId != null){

            if(KeyUtils.isValidCollegeDeptId(deptId, sysDeptService)){
                // 进行修改
                int rows = metaClassService.modifyMetaclass(meataclassId, metaclassNo, metaclassName, grade, deptId);
                if(rows > 0){
                    // 修改成功
                    result = RespResult.ok();
                    result.setRcode(Rcode.MODIFY_SUCC);

                }else {
                    result.setRcode(Rcode.MODIFY_ERR);
                }
            }else {
                result.setRcode(Rcode.DEPTID_PARAM_ERR);
            }

        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }

    /**
     * 增加班级
     */
    @ApiOperation(value = "增加班级信息",notes = "没有require的可以不用写")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "meataclassId", value = "不要给自动生成 主键", required = false),
            @ApiImplicitParam(name = "metaclassNo", value = "班级编号", required = true),
            @ApiImplicitParam(name = "metaclassName", value = "班级名字", required = true),
            @ApiImplicitParam(name = "grade", value = "年级", required = true),
            @ApiImplicitParam(name = "deptId", value = "学院id 对应那个学院的班级", required = true),
            @ApiImplicitParam(name = "createBy", value = "创建人 的用户名", required = true),
    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @PostMapping("/addMetaclass")
    public RespResult addMetaclass(Metaclass metaclass){
        RespResult result = RespResult.fail();
        if(metaclass != null){

            if(KeyUtils.isValidCollegeDeptId(metaclass.getDeptId(),sysDeptService)){
                // 添加班级
                int rows = metaClassService.addMetaclass(metaclass);
                if(rows > 0){
                    // 添加成功
                    result = RespResult.ok();
                    result.setRcode(Rcode.ADD_SUCC);
                }else {
                    // 添加失败
                    result.setRcode(Rcode.ADD_ERR);
                }
            }else {
                result.setRcode(Rcode.COLLEGE_ID_ERR);
            }



        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }

    /**
     * 根据班级id查询班级信息
     */
    @ApiOperation("根据id获取班级信息")
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin,teacher')")
    @GetMapping("/oneMetaclass")
    @ApiImplicitParam(name = "meataclassId", value = "班级id 主键", required = true)
    public RespResult getCourse(Integer meataclassId){
        RespResult result = RespResult.fail();
        if(meataclassId != null && meataclassId > 0){
            // 开始查询
            Metaclass metaclass = metaClassService.queryMetaclass(meataclassId);
            if(metaclass != null){
                result = RespResult.ok();
                result.setData(metaclass);
            }else {
                // 查询失败
                result.setRcode(Rcode.QUERY_ERR);
            }

        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }


    /**
     * 根据学院id 获取本学院的班级
     */
    @ApiOperation("根据学院id获取学院下的班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "学院id 外键，dept表的id 主键", required = true),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false),
            @ApiImplicitParam(name = "pageSize", value = "页数一页多少行数据", required = false)

    })
    @PreAuthorize("@ss.hasAnyRoles('admin,schoolAdmin,collegeAdmin')")
    @GetMapping("/collegeMetaclass")
    public RespResult getCollegeMetaclass(@RequestParam Long deptId, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        RespResult result = RespResult.fail();
        if(deptId != null && pageNum != null && pageSize != null){
            if (KeyUtils.isValidCollegeDeptId(deptId, sysDeptService)) {
                // 有效的deptId
                int startIndex = (pageNum - 1) * pageSize;
                List<Metaclass> list = metaClassService.queryCollageMetaclassByDeptIdSize(deptId);
                List<Metaclass> metaclassList = metaClassService.queryCollegeMetaclassByDeptId(deptId, startIndex, pageSize);
                if(metaclassList != null){
                    // 查询成功 封装返回的数据
                    result = RespResult.ok();
                    result.setList(metaclassList);
                    result.setPageInfo(new PageInfo(pageNum,pageSize,list.size()));
                }else {
                    // 获取失败
                    result.setRcode(Rcode.QUERY_ERR);
                }
            }else{
                result.setRcode(Rcode.COLLEGE_ID_ERR);
            }

        }else {
            result.setRcode(Rcode.REQUEST_PARAM_ERR);
        }

        return result;
    }

}
