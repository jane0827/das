package com.jane.das.commons.model;

import lombok.Data;

/**
 * **********************************************************
 * <br/>分页对象.
 * <br/>用于前端分页查询的时候使用.
 * <br/>默认从第一页开始，每页显示10条数据
 *
 * @version simo Service Platform, 2016年3月11日
 ***********************************************************
 */
@Data
public class PageModel {

    /** 第几页 */
    private int currentNo = 1;

    /** 总共多少页 */
    @SuppressWarnings("unused")
    private int totalPages = 1;

    /** 每页大小：每页多少条数据 */
    private int pageSize = 10;

    /** 共计多少条数据 */
    private long count = 0;

    /** 查询结果 */
    private Object object;

    /** 获取总页数 */
    public int getTotalPages() {
        return (int) (count % pageSize > 0 ? (count / pageSize) + 1 : count / pageSize);
    }
}