package com.study.dao.impl;

import com.study.dao.IOrderFormDAO;
import com.study.entity.OrderForm;
import com.study.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

/**
 * @author lxy
 * @date 2020-05-27-8:45
 * @function
 **/
public class OrderFormDAOImpl implements IOrderFormDAO {

    Connection connection = JDBCUtil.getConn();
    QueryRunner queryRunner = new QueryRunner();

    @Override
    public boolean create(OrderForm orderForm) {

        try {
            String sql = "insert into order_form(serial_number,username,item_name,item_singlePrice,item_num,item_totalPrice,create_time) values(?,?,?,?,?,?,?)";
            queryRunner.update(connection,sql,orderForm.getSerial_number(),orderForm.getUsername(),orderForm.getItem_name(),orderForm.getItem_singlePrice(),orderForm.getItem_num(),orderForm.getItem_totalPrice(),orderForm.getCreate_time());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OrderForm> queryByUsername(String username) {
        try {
            String sql = "select * from order_form where username=?";
            return queryRunner.query(connection,sql,new BeanListHandler<>(OrderForm.class),username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OrderForm> queryByUsernameAndSerialNumber(String username, String serial_number) {
        try {
            String sql = "select * from order_form where username=? and serial_number=?";
            return queryRunner.query(connection,sql,new BeanListHandler<>(OrderForm.class),username,serial_number);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
