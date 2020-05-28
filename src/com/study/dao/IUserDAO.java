package com.study.dao;

import com.study.entity.User;

import java.util.List;

/**
 * @author lxy
 * @date 2020-05-10-11:44
 * @function
 **/
public interface IUserDAO {
    public boolean add(User user);

    public boolean login(User user);

    public User queryByUsername(String username);

    public List<User> queryAll();
}
