package com.bgnt.vo;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class PageInfo<T> implements Serializable {

    private static final long serialVersionUID = -631980233399559374L;
    /**
     * 请求查询的页码
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数
     */
    private Integer pageSize = 10;

    /**
     * 总条数
     */
    private long count;

    /**
     * 总页数
     */
    private int pageCount;

    /**
     * 结果集
     */
    private List<T> result;

    public PageInfo() {
    }


    public PageInfo(com.github.pagehelper.PageInfo<T> pageInfo) {
        this.pageNo = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.pageCount = pageInfo.getPages();
        this.result = pageInfo.getList();
        this.count = pageInfo.getTotal();
    }

    public static <S> PageInfo<S> emptyPageInfo() {
        return emptyPageInfo(1, 10);
    }

    public static <S> PageInfo<S> emptyPageInfo(Integer pageNo, Integer pageSize) {
        PageInfo<S> pageInfo = new PageInfo<>();
        pageInfo.setResult(Collections.emptyList());
        pageInfo.setPageNo(pageNo == null ? 1 : pageNo);
        pageInfo.setPageSize(pageSize == null ? 10 : pageSize);
        return pageInfo;
    }

    public static <S> PageInfo<S> emptyPageInfo(PageInfo pageInfo) {
        PageInfo<S> resultPage;
        if (pageInfo == null) {
            resultPage = emptyPageInfo();
        } else {
            resultPage = emptyPageInfo(pageInfo.getPageNo(), pageInfo.getPageSize());
        }

        return resultPage;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
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

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     * 获取最大页数
     *
     * @return
     * @author dujh
     */
    public int getPageCount() {
        Long totalCount = this.count;
        Double pages = Math.ceil(totalCount.doubleValue() / this.pageSize);
        this.pageCount = pages.intValue();
        return this.pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
