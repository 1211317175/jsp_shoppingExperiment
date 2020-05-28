package com.study.dao;

import com.study.entity.OrderForm;

import java.util.List;

/**
 * @author lxy
 * @date 2020-05-27-8:45
 * @function
 **/
public interface IOrderFormDAO {
    public boolean create(OrderForm orderForm);

    public List<OrderForm> queryByUsername(String username);

    public List<OrderForm> queryByUsernameAndSerialNumber(String username,String serial_number);
}
