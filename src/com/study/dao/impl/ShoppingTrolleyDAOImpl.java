package com.study.dao.impl;

import com.study.dao.IShoppingTrolleyDAO;
import com.study.entity.ShoppingTrolley;
import com.study.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

/**
 * @author lxy
 * @date 2020-05-26-8:04
 * @function
 **/
public class ShoppingTrolleyDAOImpl implements IShoppingTrolleyDAO {

    Connection connection = JDBCUtil.getConn();//直接从Util工具包里获取连接对象
    QueryRunner queryRunner = new QueryRunner();

    @Override
    public List<ShoppingTrolley> queryAll() {
        try {
            String sql = "select * from shopping_trolley";
            return queryRunner.query(connection,sql,new BeanListHandler<>(ShoppingTrolley.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ShoppingTrolley> queryByUsername(String username) {
        try {
            String sql = "select * from shopping_trolley where username=?";
            return queryRunner.query(connection,sql,new BeanListHandler<>(ShoppingTrolley.class),username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ShoppingTrolley queryByItemId(Integer itemId,String username) {
        try {
            String sql = "select * from shopping_trolley where item_id=? and username=?";
            return queryRunner.query(connection,sql,new BeanHandler<>(ShoppingTrolley.class),itemId,username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(ShoppingTrolley shoppingTrolley) {
        try {
            String sql = "insert into shopping_trolley(item_id,item_name,item_singlePrice,item_num,username) values(?,?,?,?,?)";
            queryRunner.update(connection,sql,shoppingTrolley.getItem_id(),shoppingTrolley.getItem_name(),shoppingTrolley.getItem_singlePrice(),shoppingTrolley.getItem_num(),shoppingTrolley.getUsername());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer itemId,String username) {
        try {
            String sql = "delete from shopping_trolley where item_Id=? and username=?";
            queryRunner.update(connection,sql,itemId,username);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(ShoppingTrolley shoppingTrolley) {
        try {
            String sql = "update shopping_trolley set item_num=? where item_id=? and username=?";
            queryRunner.update(connection,sql,shoppingTrolley.getItem_num(),shoppingTrolley.getItem_id(),shoppingTrolley.getUsername());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
