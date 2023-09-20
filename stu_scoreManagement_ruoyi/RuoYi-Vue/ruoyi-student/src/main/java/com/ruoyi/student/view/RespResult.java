package com.ruoyi.student.view;

import com.ruoyi.student.enums.Rcode;

import java.util.List;

/**
 * @author xiaogao
 * @version 1.0
 * @className RespResult
 * @description
 * @since 1.0
 */
public class RespResult {

    private int code;
    private String mes;
    private Object data;
    private List list;
    private PageInfo pageInfo;

    public static RespResult ok(){
        RespResult result = new RespResult();
        result.setRcode(Rcode.SUCC);

        return result;
    }

    public static RespResult fail(){
        RespResult result = new RespResult();
        result.setRcode(Rcode.UNKOWN);

        return result;
    }

    public void setRcode(Rcode rcode){
        this.code = rcode.getCode();
        this.mes = rcode.getText();
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
