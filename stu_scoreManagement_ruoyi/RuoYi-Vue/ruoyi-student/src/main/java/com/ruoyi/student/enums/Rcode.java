package com.ruoyi.student.enums;

/**
 * @author xiaogao
 * @version 1.0
 * @className Rcode
 * @description
 * @since 1.0
 */
public enum Rcode {

    UNKOWN(0, "请稍后重试"),
    SUCC(1000, "请求成功"),
    QUERY_SUCC(1001, "获取成功"),
    DELETE_SUCC(1002, "删除成功"),
    MODIFY_SUCC(1003, "修改成功"),
    ADD_SUCC(1004, "增加成功"),


    /*****************************ERROR********************************/
    REQUEST_PARAM_ERR(2000, "参数有误"),
    DEPTID_PARAM_ERR(2001, "课程所属学院deptId错误"),
    COURSEID_PARAM_ERR(2002, "课程courseId错误"),
    QUERY_COURSE_ERR(2003, "查询课程失败 请联系管理员"),
    ADD_COURSE_ERR(2004, "课程添加失败 请联系管理员"),
    DELETE_COURSE_ERR(2005, "课程删除失败 请联系管理员"),
    QUERY_METACLASS_ERR(2006, "获取所有班级失败 请联系管理员"),
    QUERY_ERR(2007, "获取失败 请联系管理员"),
    DELETE_ERR(2008, "删除失败 请联系管理员"),
    MODIFY_ERR(2009, "修改失败 请联系管理员"),
    ADD_ERR(2010, "增加失败 请联系管理员"),
    COLLEGE_ID_ERR(2011, "请传入正确的学院id"),
    USER_ID_ERR(2012, "请传入正确的用户id"),



    ;

    Rcode(int code, String text) {
        this.code = code;
        this.text = text;
    }

    private int code;
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
