package com.amdocs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private final static String DRIVERCLASS = "";
	private final static String URL="jdbc:mysql://localhost:3306/amdocs";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Connection Success....");
		} catch(SQLException e) {
			System.err.println("Connection Not Established...");
			System.err.println(e);
		}
		return connection;
	}
}
