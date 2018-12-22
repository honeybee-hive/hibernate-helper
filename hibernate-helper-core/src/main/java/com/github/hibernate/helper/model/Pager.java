package com.github.hibernate.helper.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历： v1.0 2015-1-9 zy 初版
 */
public class Pager<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码 从1开始
     */
    private int page;

    /**
     * 每页条数
     */
    private int size;

    /**
     * 总记录数
     */
    private int totalElements;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 每页记录数
     */
    private List<T> content;

    /**
     * 页面显示列表
     */
    private List<Integer> segment;

    /**
     * 开始条数
     */
    private int begin;

    /**
     * 结束条数
     */
    private int end;

    public Pager() {
        this.page = 1;
        this.size = 10;
    }

    public Pager(int page, int size, int totalElements) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        if (totalElements > 0 && size > 0) {
            this.totalPages = (totalElements - 1) / size + 1;
            if (this.page > totalPages) {
                this.page = totalPages;
            }
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public List<Integer> getSegment() {
        segment = new ArrayList<Integer>();
        int start = 0;
        int end = totalPages;
        if (page == 1) {
            start = 1;
            end = start + 2;
        } else if (page == totalPages) {
            start = totalPages - 2;
            end = totalPages;
        } else if (page > 1) {
            start = page - 1;
            end = page + 1;
        }
        if (end > totalPages) {
            end = totalPages;
        }
        if (start < 1) {
            start = 1;
        }
        for (int i = start; i <= end; i++) {
            segment.add(i);
        }
        return segment;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

}
