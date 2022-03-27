package com.java.ex.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDao {
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	Connection con = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	public MemberDto getMemberJoin( MemberDto mDto ) throws Exception{
		try {
		con = DBUtil.getConnection(); // DB 연결
		String query = "insert into member values(?,?,?,?,?,?,?)";
		
		pstmt = con.prepareStatement(query);
				
		pstmt.setString(1, mDto.getId());
		pstmt.setString(2, mDto.getPw());
		pstmt.setString(3, mDto.getName());
		pstmt.setString(4, mDto.getEmail());
		pstmt.setString(5, mDto.getChecks());
		pstmt.setInt(6, mDto.getCount());
		pstmt.setInt(6, mDto.getRent_count());
		
		int i = pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		DBUtil.close(pstmt);
		DBUtil.close(con);
		return mDto;
	}
	
	
	public int setClientCount( String id ){
		int i = 0;
		try {
			con = DBUtil.getConnection(); // DB 연결
			String query = "update member set count=count+1 where id = ? ";
			
			pstmt = con.prepareStatement(query);
					
			pstmt.setString(1, id);
			
			i = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace(); 
			}finally {
				DBUtil.close(pstmt);
				DBUtil.close(con);
				
			}
			return i;
		}
	
	
	public int getClientCount( String id ){
		int i = 0;
		try {
			con = DBUtil.getConnection(); // DB 연결
			String query = "select count from member where id=?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 i = rs.getInt("count"); 
			
			}
			}catch(Exception e) {
				e.printStackTrace(); 
			}finally {
				DBUtil.close(pstmt);
				DBUtil.close(con);
				
			}
			return i;
		}

	
	public int setRentCount( String id ){
		// 랜트 업데이트를 변경
		int i = 0;
		try {
			con = DBUtil.getConnection(); // DB 연결
			String query2 = "update member set rent_count=rent_count+1 where id = ? ";
			
			pstmt = con.prepareStatement(query2);
			pstmt.executeUpdate();
		
				i = rs.getInt("rent_count");
			
			
			}catch(Exception e) {
				e.printStackTrace(); 
			}finally {
				DBUtil.close(pstmt);
				DBUtil.close(con);
				
			}
			return i;
		}
	
	
	public int getRentCount( String id ){
		// 랜트 업데이트가 5권 이상인지 판단 
		int i = -1;
		try {
			con = DBUtil.getConnection(); // DB 연결
			String query1 = "select rent_count from member where id = ? and rent_count <= 5";
			
			pstmt = con.prepareStatement(query1);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				i = rs.getInt("rent_count");
			}else {
				String query2 = "update member set rent_count = 0 where id = ?";
				pstmt2 = con.prepareStatement(query2);
				pstmt2.setString(1, id);
				pstmt2.executeUpdate();
				
				
			}
			
			}catch(Exception e) {
				e.printStackTrace(); 
			}finally {
				DBUtil.close(rs);
				DBUtil.close(pstmt2);
				DBUtil.close(pstmt);
				DBUtil.close(con);
				
			}
			return i;
		}
	
	
	
	public int getMemberLoginCheck(String id, String pw) throws Exception{
		int i,j = 0;
		try {
		con = DBUtil.getConnection(); // DB 연결
		String query1 = "select passward id from member where id=? and checks = '일반'";
		String query2 = "select passward from member where id=? and checks = '관리자'";
		pstmt = con.prepareStatement(query1);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		
		pstmt2 = con.prepareStatement(query2);
		pstmt2.setString(1, id);
		rs2 = pstmt2.executeQuery();
		
		if (rs.next()) {
			if(rs.getString(1).contentEquals(pw)) {
				i = 1;
				return i;
			}
			else
				i = 0;
		}else if (rs2.next()) {
			if(rs2.getString(1).contentEquals(pw)) {
				j = 2;

				
				return j;
			}
			else
				j = 0;
		}
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt2);
			DBUtil.close(pstmt);
			DBUtil.close(con);
			
		}

		
		return 0;
		
	}
	
	
	
	
	
	public String getMemberLoginFind(String name, String id) throws Exception{
		String passward ="";
		try {
		con = DBUtil.getConnection(); // DB 연결
		String query1 = "select passward from member where id=? and name=?";
		pstmt = con.prepareStatement(query1);
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
			passward = rs.getString("passward");
		}
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
			
		}

		
		return passward;
		
	}
	
	public int getIdCheck(String id) throws Exception{
		String id_check = "";
		int i = 0;
		try {
		con = DBUtil.getConnection(); // DB 연결
		String query1 = "select id from member where id=?";
		pstmt = con.prepareStatement(query1);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
			id_check = rs.getString("id");
			if(id_check != "") {
				i = 1;
			}
		}
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
			
		}

		
		return i;
		
	}
	
	

	public ArrayList<MemberDto> getMemberSearch(){
		MemberDto mDto = null;
		ArrayList<MemberDto> mtos = new ArrayList<MemberDto>();
		int i = 0;
		try {
		con = DBUtil.getConnection(); // DB 연결
		String query1 = "select id, passward, name, email from member where checks ='일반'";
		
		pstmt = con.prepareStatement(query1);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			String passward = rs.getString("passward");
			String name = rs.getString("name");
			String email = rs.getString("email");
			
			mDto = new MemberDto(id,passward,name,email,"몰랑",0,0);
			mtos.add(mDto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
			
		}
		
		return mtos;
		
	}
	
	
	public ArrayList<String> getMemberSearchColumn() {
		ArrayList<String> mColumns = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String query1 = "select id, passward, name, email from member where checks ='일반'";
		
		try {
			con = DBUtil.getConnection(); // DB 연결
			
			pstmt = con.prepareStatement(query1);
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
