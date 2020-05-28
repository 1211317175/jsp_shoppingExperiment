package com.study.dao.impl;

import com.study.dao.IUserDAO;
import com.study.entity.User;
import com.study.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.util.List;

/**
 * @author lxy
 * @date 2020-05-10-11:45
 * @function
 **/
public class UserDAOImpl implements IUserDAO {

    Connection connection = JDBCUtil.getConn();//直接从Util工具包里获取连接对象
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs=null;
    QueryRunner queryRunner = new QueryRunner(); //使用dbUtils提供的查询方法

    @Override
    public boolean add(User user) {
//        String sql ="insert into user(username,password) values(?,?)";
//        Object[] params = {user.getUsername(),user.getPassword()};
//        return JDBCUtil.commonCUD(sql,params);
        try{
            String sql ="insert into user(username,password) values(?,?)";
            if(queryRunner.update(connection,sql,user.getUsername(),user.getPassword())>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean login(User user){
//        try {
//            //1.加载驱动
//            //2.连接数据库
//            connection = JDBCUtil.getConn();//直接从Util工具包里获取连接对象
//
//            //3.创建sql语句
//            //预编译sql语句
//            String sql = "select * from user where username =? and password=?";
//            //3.1传入sql,获得预编译语句
//            pst = connection.prepareStatement(sql);
//            //3.2设置参数
//            pst.setString(1, user.getUsername());
//            pst.setString(2,user.getPassword());
//
//            //4.执行sql语句
////			rs = st.executeQuery(sql); //静态sql语句执行方式
//            rs = pst.executeQuery();//预编译sql语句执行方式
//
//            //查看执行的预编译sql语句
////			System.out.println(((JDBC4PreparedStatement)pst).asSql());
//
//            if (rs.next()){
//                return 1;
//            }
//
//            return 0;
//        }catch(SQLException e){
//            e.printStackTrace();
//            return 0;
//        }finally {
//            //5.释放资源
//            JDBCUtil.CloseAll(connection, pst, rs); //直接从Util包里调用释放资源方法
//        }

        try {
            String sql = "select * from user where username=? and password=?";
            if(queryRunner.query(connection, sql, new BeanHandler<>(User.class), user.getUsername(), user.getPassword())!=null) {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User queryByUsername(String username){
        try {
            String sql = "select * from user where username=?";
            return queryRunner.query(connection,sql,new BeanHandler<>(User.class),username);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<User> queryAll(){
        try{
            String sql ="select * from user";
            return queryRunner.query(connection,sql, new BeanListHandler<>(User.class));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
