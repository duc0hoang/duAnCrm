package com.myclass.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	private static final String URL 		= "jdbc:mysql://localhost:3306/crm";
	private static final String USERNAME	= "root";
	private static final String PASSWORD	= "duchoang1993";
	private static final String DRIVER		= "com.mysql.cj.jdbc.Driver";
	
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO: handle driver exception
			System.out.println("===========EXCEPTION==========");
			System.out.println("====CAN NOT FIND THE DRIVER===");
			System.out.println(e.getMessage());
			System.out.println("==============================");
		} catch (SQLException e) {
			// TODO: handle database exception
			System.out.println("===========EXCEPTION==========");
			System.out.println("====CAN NOT FIND DATABASE=====");
			System.out.println(e.getMessage());
			System.out.println("==============================");
		}
		return null;
	}
}
