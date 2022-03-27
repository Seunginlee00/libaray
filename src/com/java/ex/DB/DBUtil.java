package com.java.ex.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3307/library"; 
	static String uid = "root";
	static String pwd = "root";
	
	public static Connection getConnection(){
		Connection con = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, pwd);	
		}catch(ClassNotFoundException e1) {
			System.out.println("드라이버가 없음");
		}catch(SQLException e2) {
			System.out.println("사용자 계정 또는 비밀번호가 일치하지 않습니다.");
		}
		return con;
	}
	
	public static void close(Connection con) {
		try {if(con != null) {con.close();}}catch(Exception e) {e.printStackTrace();}
	}
	public static void close(Statement stmt) {
		try {if(stmt != null) {stmt.close();}}catch(Exception e) {e.printStackTrace();}
	}
	public static void close(PreparedStatement pstmt) {
		try {if(pstmt != null) {pstmt.close();}}catch(Exception e) {e.printStackTrace();}
	}
	public static void close(ResultSet rs) {
		try {if(rs != null) {rs.close();}}catch(Exception e) {e.printStackTrace();}
	}
}
