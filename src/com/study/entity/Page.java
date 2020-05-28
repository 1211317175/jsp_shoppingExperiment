package com.study.entity;

import java.util.List;

/**
 * @author lxy
 * @date 2020-05-12-16:17
 * @function
 **/
public class Page {
    //当前页 currentPage
    private int currentPage ;
    //页面大小 pageSize
    private int pageSize;
    //当前页的数据集合 currentStus
    private List<Item> currentItem;
    //总数据数 totalCount
    private int totalCount;
    //总页数 totalPage
    private int totalPage;
    @Override
    public String toString() {
        return "Page [currentPage=" + currentPage + ", pageSize=" + pageSize + ", currentItem=" + currentItem
                + ", totalCount=" + totalCount + ", totalPage=" + totalPage + "]";
    }
    /*
     * 注意：总页数 = 数据总数%页面大小==0 ?  数据总数/页面大小 : 数据总数/页面大小 +1;
     *
     * 当我们调用了数据总数的set和页面大小的set方法后，自动计算出总页数
     * */

    public Page() {
        super();
    }
    public Page(int currentPage, int pageSize, List<Item> currentItem, int totalCount, int totalPage) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.currentItem = currentItem;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        //总页数 = 数据总数%页面大小==0 ?  数据总数/页面大小 : 数据总数/页面大小 +1;
        if(pageSize!=0&&totalCount!=0)
            this.totalPage = this.totalCount%this.pageSize==0 ?this.totalCount/this.pageSize-1:this.totalCount/this.pageSize ;
    }
    public List<Item> getCurrentItem() {
        return currentItem;
    }
    public void setCurrentStus(List<Item> currentItem) {
        this.currentItem = currentItem;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        if(pageSize!=0&&totalCount!=0)
            this.totalPage = this.totalCount%this.pageSize==0 ?this.totalCount/this.pageSize-1:this.totalCount/this.pageSize ;
    }
    public int getTotalPage() {
        return totalPage;
    }
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}
}
