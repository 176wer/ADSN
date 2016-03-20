/**
 * Project Name:ADSN
 * File Name:Nannao.java
 * Package Name:test
 * Date:2015年12月24日下午8:55:35
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * ClassName:Nannao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年12月24日 下午8:55:35 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Nannao {
public static void main(String[] args){
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/adsn";
		Connection con=DriverManager.getConnection(url,"root","123");
		Statement stm=con.createStatement();
		int i=0;
		while(true){
			String sql="update temp set number="+i;
			i++;
			stm.executeUpdate(sql);
		}
	} catch (ClassNotFoundException e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	} catch (SQLException e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	}
 
}

