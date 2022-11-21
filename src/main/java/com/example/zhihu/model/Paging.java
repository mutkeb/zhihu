package com.example.zhihu.model;

import java.io.Serializable;
import java.util.List;

//  分页模型
public class Paging<R> implements Serializable {

    private static final long serialVersionID = 522660448543880825L;

    /**
     *页数
     */
    private int pageNum;

    /**
     *每页数量
     */
    private int pageSize;

    /**
     *总页数
     */
    private int totalPage;

    /**
     *总记录数
     */
    private long totalCount;

    /**
     *集合数据
     */
    private List<R>  data;

    public Paging(){

    }

    public Paging(int pageNum, int pageSize, int totalPage, long totalCount, List<R> data){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.data = data;
    }

    public static long getSerialVersionID() {
        return serialVersionID;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<R> getData() {
        return data;
    }

    public void setData(List<R> data) {
        this.data = data;
    }
}
