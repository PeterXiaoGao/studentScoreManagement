package com.ruoyi.student.view;

/**
 * @author xiaogao
 * @version 1.0
 * @className pageInfo
 * @description
 * @since 1.0
 */
public class PageInfo {
    // 页号
    private Integer pageNo;
    // 每页大小
    private Integer pageSize;
    // 总页数
    private Integer totalPage;
    // 总记录数
    private Integer totalRecord;

    public PageInfo() {
    }

    public PageInfo(Integer pageNo, Integer pageSize, Integer totalRecord) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;

        // 计算总页数
        if(this.totalRecord % this.pageSize == 0){
            this.totalPage = this.totalRecord / this.pageSize;
        }else {
            this.totalPage = this.totalRecord / this.pageSize + 1;
        }
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }
}
