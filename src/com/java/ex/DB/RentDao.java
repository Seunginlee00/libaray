package com.java.ex.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RentDao {
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	Connection con = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	public RentDto getRent( RentDto rDto ){
		try {
		con = DBUtil.getConnection(); // DB 연결
		String query = "insert into rent(rent_date, rent_return_date, book_number, book_name, id, rent_return_yn) values(?,?,?,?,?,?)";
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, rDto.getRent_date());		
		pstmt.setString(2, rDto.getRent_return_date());
		pstmt.setInt(3, rDto.getBook_number());
		pstmt.setString(4, rDto.getBook_name());
		pstmt.setString(5, rDto.getId());
		pstmt.setString(6, rDto.getRent_return_yn());
		
		int i = pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		DBUtil.close(pstmt);
		DBUtil.close(con);
		return rDto;
	}
	
	
	public int getBookRent( int book_number ){
		int i = 0;
		try {
			con = DBUtil.getConnection(); // DB 연결
			String query = "update book set book_rent_yn = ? WHERE book_number= ?";
			
			pstmt = con.prepareStatement(query);
					
			pstmt.setString(1, "N");
			pstmt.setInt(2, book_number);
			
			i = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace(); 
			}finally {
				DBUtil.close(pstmt);
				DBUtil.close(con);
				
			}
			return i;
		}
	

	
	public int getRentReturn(String return_date, int book_number ){
		int i = 0;
		try {
			con = DBUtil.getConnection(); // DB 연결
			String query = "update rent set rent_return_yn = ? , return_date = ?  WHERE book_number= ?";
			
			pstmt = con.prepareStatement(query);
					
			pstmt.setString(1, "Y");
			pstmt.setString(2, return_date);
			pstmt.setInt(3, book_number);
			
			i = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace(); 
			}finally {
				DBUtil.close(pstmt);
				DBUtil.close(con);
				
			}
			return i;
		}

	

	
	public int getOverdue(String id){
		String overdues = "";
		int i = 0;
		try {
			con = DBUtil.getConnection(); // DB 연결
			String query = "select rent_return_date, DATE_FORMAT(now(), '%Y.%m.%d') from rent where id = ? AND rent_return_date < DATE_FORMAT(now(), '%Y.%m.%d')";
			
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if ( rs.next()) {
				overdues = rs.getString("rent_return_date");
				if(overdues == "") {
					i = 1;
				}
			}
				
			
			}catch(Exception e) {
				e.printStackTrace(); 
			}finally {
				DBUtil.close(pstmt);
				DBUtil.close(con);
				
			}
			return i;
		}
	
	
	public int getOverdueReturn(String id){
		String overdues = "";
		int i = 0;
		try {
			con = DBUtil.getConnection(); // DB 연결
			String query = "select return_date from rent where id = ? and return_date=0 ";
			
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if ( rs.next()) {
				overdues = rs.getString("return_date");
				if(overdues == "") {
					i = 1;
				}
			}
				
			
			}catch(Exception e) {
				e.printStackTrace(); 
			}finally {
				DBUtil.close(pstmt);
				DBUtil.close(con);
				
			}
			return i;
		}
	
	
	public ArrayList<RentDto> getClientSearch(String id){
		RentDto rDto = null;
		ArrayList<RentDto> rtos = new ArrayList<RentDto>();
		try {
		con = DBUtil.getConnection(); // DB 연결
		String query1 = "select rent_date, return_date, rent_return_date, book_number, book_name, rent_return_yn from rent where id = ? ";
		
		pstmt = con.prepareStatement(query1);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		while (rs.next()) {

			String rent_date = rs.getString("rent_date");
			String return_date = rs.getString("return_date");
			String rent_return_date = rs.getString("rent_return_date");
			int book_number = rs.getInt("book_number");
			String book_name = rs.getString("book_name");
			String rent_return_yn = rs.getString("rent_return_yn");
			rDto = new RentDto(rent_date, return_date, rent_return_date, book_number, book_name, "0", rent_return_yn);
			rtos.add(rDto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
			
		}
		
		return rtos;
		
	}
	

	public ArrayList<RentDto> getClientRentSearch(String id){
		RentDto rDto = null;
		ArrayList<RentDto> rtos = new ArrayList<RentDto>();
		try {
		con = DBUtil.getConnection(); // DB 연결
		String query1 = "select rent_date, return_date, rent_return_date, book_number, book_name, rent_return_yn from rent where id = ? and rent_return_yn = 'N'";
		
		pstmt = con.prepareStatement(query1);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String rent_date = rs.getString("rent_date");
			String return_date = rs.getString("return_date");
			String rent_return_date = rs.getString("rent_return_date");
			int book_number = rs.getInt("book_number");
			String book_name = rs.getString("book_name");
			String rent_return_yn = rs.getString("rent_return_yn");
			rDto = new RentDto(rent_date,return_date,rent_return_date,book_number,book_name,"0",rent_return_yn);
			rtos.add(rDto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
			
		}
		
		return rtos;
		
	}
	
	
	
	public ArrayList<String> getClientSearchColumn(String id) {
		ArrayList<String> mColumns = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String query1 = "select rent_date, return_date, rent_return_date, book_number, book_name, rent_return_yn from rent where id = ?";
		
		try {
			con = DBUtil.getConnection(); // DB 연결
			
			pstmt = con.prepareStatement(query1);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData(); 
			
			
			int colCount = rsmd.getColumnCount();
			
			for (int i=1; i<=colCount; i++) mColumns.add(rsmd.getColumnName(i));
			

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
			}
		return mColumns;
	}

}
