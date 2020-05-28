package com.study.dao;

import com.study.entity.ShoppingTrolley;

import java.util.List;

/**
 * @author lxy
 * @date 2020-05-26-8:03
 * @function
 **/
public interface IShoppingTrolleyDAO {
    public List<ShoppingTrolley> queryAll();

    public List<ShoppingTrolley> queryByUsername(String username);

    public ShoppingTrolley queryByItemId(Integer itemId,String username);

    public boolean add(ShoppingTrolley shoppingTrolley);

    public boolean delete(Integer itemId,String username);

    public boolean update(ShoppingTrolley shoppingTrolley);
}
