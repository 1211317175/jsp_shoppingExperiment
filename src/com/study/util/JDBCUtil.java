package com.study.util;

import javax.sql.DataSource;
import java.sql.*;


public class JDBCUtil {

	public static final String driver = "com.mysql.jdbc.Driver";
	//由于mysql默认情况下不支持批处理。若想支持,则需要在工具包的url地址后添加上  ?rewriteBatchedStatements=true
	public static final String url = "jdbc:mysql://localhost:3307/jsp?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String user="root";
	public static final String password ="123";

	static Connection connection = null;
	static Statement st = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs=null;
	public static DataSource ds = null;


	/*static里的语句只执行一次,而加载驱动执行一次即可,没必要每次获取connection对象都加载一次*/
	static { 
		try {
			Class.forName(driver);//加载驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//获取connection对象
	public static Connection getConn() {
		try {
			return DriverManager.getConnection(url, user, password);//通过drivermanager获取连接对象
//			return ds.getConnection();//通过DBCP连接池获取连接对象
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//创建statement类对象，用来执行SQL语句
	public static PreparedStatement createPreparedStatement(String sql,Object[] params) throws ClassNotFoundException, SQLException {
		pstmt = getConn().prepareStatement(sql);
		if(params!=null) {
			for(int i=0;i<params.length;i++) {
				pstmt.setObject(i+1, params[i]);
			}
		}
		return pstmt;
	}

	//释放资源
	public static void CloseAll(Connection connection,Statement st,ResultSet rs) {
		try {
			if(rs!=null)rs.close();
			if(st!=null)st.close();
			if(connection!=null)connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//通用的增删改
//	public static boolean commonCUD(String sql, Object[] params){
//		try {
//			/*
//			//Object[] objs = {id,name,age,address}
//			pstmt = getConnection().prepareStatement(sql);
//
//			//setXxx()方法个数依赖于?的个数，而?的个数又和数字params的个数一致
//			//setXxx()方法的个数与数组params的个数一致
//			for(int i=0;i<params.length;i++) {
//				pstmt.setObject(i+1, params[i]);
//			}
//			等价于*/pstmt = createPreparedStatement(sql,params);
//
//			int count = 0;
//			count = pstmt.executeUpdate();
//
//			if(count>0) {
//				return true;
//			}
//
//		}catch(ClassNotFoundException e) {
//			e.printStackTrace();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			CloseAll(connection,pstmt,null);
//		}
//		return false;
//	}


}
