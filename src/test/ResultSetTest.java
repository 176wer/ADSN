/**
 * Project Name:ADSN
 * File Name:ResultSetTest.java
 * Package Name:test
 * Date:2016年1月2日下午9:25:33
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pool.ConnectionPoolManager;
import pool.IConnectionPool;

/**
 * ClassName:ResultSetTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年1月2日 下午9:25:33 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ResultSetTest {
public static void main(String[] agrs){
	 IConnectionPool pool=ConnectionPoolManager.getInstance().getPool("mysql");
     Connection conn=pool.getConnection();
     String sql="select addr from temp where number=2";
     try {
		Statement stm=conn.createStatement();
		ResultSet rs=stm.executeQuery(sql);
		 
				while(rs.next()){
					System.out.println(rs.getString(1));
				}
				System.out.println("dfdddddd");
	} catch (SQLException e1) {
		
		// TODO Auto-generated catch block
		e1.printStackTrace();
		
	}
}
}

