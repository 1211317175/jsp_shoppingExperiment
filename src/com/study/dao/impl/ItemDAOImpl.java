package com.study.dao.impl;

import com.study.dao.IItemDAO;
import com.study.entity.Item;
import com.study.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * @author lxy
 * @date 2020-05-12-16:26
 * @function
 **/
public class ItemDAOImpl implements IItemDAO {

    Connection connection = JDBCUtil.getConn();//直接从Util工具包里获取连接对象
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs=null;
    QueryRunner queryRunner = new QueryRunner();

    @Override
    public int getTotalCount() {
//        int count = 0;
//        try {
//            //3.创建sql语句
//            //预编译sql语句
//            String sql = "select count(1) from item";
//            //3.1传入sql,获得预编译语句
//            pst = connection.prepareStatement(sql);
//
//            //4.执行sql语句
////			rs = st.executeQuery(sql); //静态sql语句执行方式
//            rs = pst.executeQuery();//预编译sql语句执行方式
//
//            //查看执行的预编译sql语句
////			System.out.println(((JDBC4PreparedStatement)pst).asSql());
//
//            //5.返回查询结果
//            if (rs.next()){
//                count = rs.getInt(1);
//            }
//
//            return count;
//        }catch(SQLException e){
//            e.printStackTrace();
//            return -1;
//        }finally {
//            //5.释放资源
//            JDBCUtil.CloseAll(connection, pst, rs); //直接从Util包里调用释放资源方法
//        }

        try {
            String sql = "select count(1) from item";
            Number num = queryRunner.query(connection, sql, new ScalarHandler<>());
            return num.intValue();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Item> queryAll() {
//        List<Item> items = new ArrayList<>();
//        try {
//            //3.创建sql语句
//            //预编译sql语句
//            String sql = "select * from item";
//            //3.1传入sql,获得预编译语句
//            pst = connection.prepareStatement(sql);
//            //3.2设置参数
////            pst.setString(1, username);
//
//            //4.执行sql语句
////			rs = st.executeQuery(sql); //静态sql语句执行方式
//            rs = pst.executeQuery();//预编译sql语句执行方式
//
//            //查看执行的预编译sql语句
////			System.out.println(((JDBC4PreparedStatement)pst).asSql());
//
//            //5.返回查询结果
//            while (rs.next()) {
//                Item item = new Item();
//                item.setId(rs.getInt("id"));
//                item.setName(rs.getString("name"));
//                item.setPrice(rs.getDouble("price"));
//                item.setPortrait(rs.getString("portrait"));
//                items.add(item);
//            }
//
//            return items;
//        }catch(SQLException e){
//            e.printStackTrace();
//            return null;
//        }finally {
//            //5.释放资源
//            JDBCUtil.CloseAll(connection, pst, rs); //直接从Util包里调用释放资源方法
//        }

        try {
            String sql = "select * from item";
            return queryRunner.query(connection,sql,new BeanListHandler<>(Item.class));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Item queryByItemId(Integer ItemId) {
        try{
            String sql = "select * from item where id=?";
            return queryRunner.query(connection,sql,new BeanHandler<>(Item.class),ItemId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Item> queryItemByPage(Integer currentPage, Integer pageSize) {
//        List<Item> items = new ArrayList<>();
//        try {
//            //3.创建sql语句
//            //预编译sql语句
//            String sql = "select * from item limit ?,?";
//            //3.1传入sql,获得预编译语句
//            pst = connection.prepareStatement(sql);
//            //3.2设置参数
//            pst.setInt(1,currentPage*pageSize);
//            pst.setInt(2,pageSize);
//
//            //4.执行sql语句
////			rs = st.executeQuery(sql); //静态sql语句执行方式
//            rs = pst.executeQuery();//预编译sql语句执行方式
//
//            //查看执行的预编译sql语句
////			System.out.println(((JDBC4PreparedStatement)pst).asSql());
//
//            //5.返回查询结果
//            while (rs.next()) {
//                Item item = new Item();
//                item.setId(rs.getInt("id"));
//                item.setName(rs.getString("name"));
//                item.setPrice(rs.getDouble("price"));
//                item.setPortrait(rs.getString("portrait"));
//                items.add(item);
//            }
//
//            return items;
//        }catch(SQLException e){
//            e.printStackTrace();
//            return null;
//        }finally {
//            //5.释放资源
//            JDBCUtil.CloseAll(connection, pst, rs); //直接从Util包里调用释放资源方法
//        }
        try {
            String sql = "select * from item limit ?,?";
            return queryRunner.query(connection,sql,new BeanListHandler<>(Item.class),currentPage*pageSize,pageSize);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
