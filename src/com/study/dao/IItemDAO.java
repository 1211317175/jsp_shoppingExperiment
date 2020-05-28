package com.study.dao;

import com.study.entity.Item;

import java.util.List;

/**
 * @author lxy
 * @date 2020-05-12-16:24
 * @function
 **/
public interface IItemDAO {
    //查询所有商品的信息
    public List<Item> queryAll();

    public Item queryByItemId(Integer ItemId);

    //查询记录总条数
    public int getTotalCount();

    //查询指定页码的商品信息
    // currentPage 当前页码
    // pageSize  页面大小
    public List<Item> queryItemByPage(Integer currentPage,Integer pageSize);
}
