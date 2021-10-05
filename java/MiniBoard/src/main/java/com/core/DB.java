package com.core;

import java.sql.*;
import javax.servlet.FilterConfig;

public class DB {
	private static Connection conn = null;
	private static String DBDriver;
	private static String DBurl;
	private static String DBuser;
	private static String DBpass;
	
	public static void init(FilterConfig config) {
		init(config.getInitParameter("DBDriver"),config.getInitParameter("DBurl"),
			 config.getInitParameter("DBuser"),config.getInitParameter("DBpass"));
	}
	
	public static void init(String DBDriver, String DBurl, String DBuser, String DBpass) {
		DB.DBDriver = DBDriver;
		DB.DBurl = DBurl;
		DB.DBuser = DBuser;
		DB.DBpass = DBpass;
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DBDriver);
		
		conn = DriverManager.getConnection(DBurl,DBuser,DBpass);
		
		return conn;
	}
}
