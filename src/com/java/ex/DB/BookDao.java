package com.java.ex.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class BookDao {
	// admin 기능
	PreparedStatement pstmt = null;
	Connection con = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
		public BookDto getBookInsert( BookDto bDto ){
			
		try {
			con = DBUtil.getConnection(); // DB 연결
			String query = "insert into book values(?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(query);
					
			pstmt.setString(1, bDto.getGeneral());
			pstmt.setInt(2, bDto.getBook_number());
			pstmt.setString(3, bDto.getBook_name());
			pstmt.setString(4, bDto.getBook_publisher());
			pstmt.setString(5, bDto.getBook_author_name());
			pstmt.setInt(6, bDto.getBook_count());
			pstmt.setString(7, bDto.getBook_rent_yn());
			pstmt.setString(8, bDto.getBook_delete_yn());
			
			int i = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace(); 
			}finally {
				DBUtil.close(pstmt);
				DBUtil.close(con);
				
			}
			return bDto;
		}
		
		public BookDto getBookUpdate( BookDto bDto ){
			
			try {
				con = DBUtil.getConnection(); // DB 연결
				String query = "update book set general = ?, book_name = ?, book_publisher = ?, book_author_name = ? WHERE book_number= ?";
				
				pstmt = con.prepareStatement(query);
						
				pstmt.setString(1, bDto.getGeneral());
				pstmt.setString(2, bDto.getBook_name());
				pstmt.setString(3, bDto.getBook_publisher());
				pstmt.setString(4, bDto.getBook_author_name());
				pstmt.setInt(5, bDto.getBook_number());
				
				int i = pstmt.executeUpdate();
				}catch(Exception e) {
					e.printStackTrace(); 
				}finally {
					DBUtil.close(pstmt);
					DBUtil.close(con);
					
				}
				return bDto;
			}
		
		public int getBookDelete(int number){
			int i = 0;
			try {
				con = DBUtil.getConnection(); // DB 연결
				String query = "update book set book_delete_yn = 'Y' WHERE book_number = ?";
				
				pstmt = con.prepareStatement(query);

				pstmt.setInt(1, number);
				
				i = pstmt.executeUpdate();
				}catch(Exception e) {
					e.printStackTrace(); 
				}finally {
					DBUtil.close(pstmt);
					DBUtil.close(con);
					
				}
				return i;
			}
		
		public int getBookReturn( int book_number ){
			int i = 0;
			try {
				con = DBUtil.getConnection(); // DB 연결
				String query = "update book set book_rent_yn = ? WHERE book_number= ?";
				
				pstmt = con.prepareStatement(query);
						
				pstmt.setString(1, "Y");
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
		
		
		
		public int setBookCount( int booknumber ){
			int i = 0;
			try {
				con = DBUtil.getConnection(); // DB 연결
				String query = "update book set book_count=book_count+1 where book_number = ? ";
				
				pstmt = con.prepareStatement(query);
						
				pstmt.setInt(1, booknumber);
				
				i = pstmt.executeUpdate();
				}catch(Exception e) {
					e.printStackTrace(); 
				}finally {
					DBUtil.close(pstmt);
					DBUtil.close(con);
					
				}
				return i;
			}
		
		
		public int getBookCount( int booknumber ){
			int i = 0;
			try {
				con = DBUtil.getConnection(); // DB 연결
				String query = "select book_count from book where book_number=?";
				
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1,booknumber);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					 i = rs.getInt("book_count"); 
				
				}
				}catch(Exception e) {
					e.printStackTrace(); 
				}finally {
					DBUtil.close(pstmt);
					DBUtil.close(con);
					
				}
				return i;
			}
		
		public int getBookRentYN( int booknumber ){
			int i = 0;
			try {
				con = DBUtil.getConnection(); // DB 연결
				String query = "select book_rent_yn from book where book_number=?";
				
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1,booknumber);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					//빌린놈
					if(rs.getString(1).contentEquals("N")) {
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
		
		public ArrayList<BookDto> getBookNameSearch(String num){
			// 검색용 서치 
			BookDto bDto = null;
			ArrayList<BookDto> btos = new ArrayList<BookDto>();
			int i = 0;
			try {
			con = DBUtil.getConnection(); // DB 연결
			String query1 = "select general, book_number, book_name, book_publisher, book_author_name, book_rent_yn from book where book_rent_yn = 'Y' and book_delete_yn = 'N' and book_name= ?";
			pstmt = con.prepareStatement(query1);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String general = rs.getString("general");
				int number = rs.getInt("book_number");
				String names = rs.getString("book_name");
				String publisher = rs.getString("book_publisher");
				String author = rs.getString("book_author_name");
				//int count = rs.getInt("book_count");
				String rent = rs.getString("book_rent_yn");
				 bDto = new BookDto(general, number, names, publisher, author, 0, rent, "N");
				 btos.add(bDto);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(rs);
				DBUtil.close(pstmt);
				DBUtil.close(con);
				
			}
			
			return btos;
			
		}
		
		
		
		
		
		public ArrayList<BookDto> getBookReturnSearch(){
			// 반납 테이블 
			BookDto bDto = null;
			ArrayList<BookDto> btos = new ArrayList<BookDto>();
			int i = 0;
			try {
			con = DBUtil.getConnection(); // DB 연결
			String query1 = "select general, book_number, book_name, book_publisher, book_author_name, book_rent_yn from book where book_rent_yn = 'N' and book_delete_yn = 'N'";
			
			pstmt = con.prepareStatement(query1);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String general = rs.getString("general");
				int number = rs.getInt("book_number");
				String names = rs.getString("book_name");
				String publisher = rs.getString("book_publisher");
				String author = rs.getString("book_author_name");
				//int count = rs.getInt("book_count");
				String rent = rs.getString("book_rent_yn");
				 bDto = new BookDto(general, number, names, publisher, author, 0, rent, "N");
				 btos.add(bDto);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(rs);
				DBUtil.close(pstmt);
				DBUtil.close(con);
				
			}
			
			return btos;
			
		}

		
		public ArrayList<BookDto> getBookSearch(){
			BookDto bDto = null;
			ArrayList<BookDto> btos = new ArrayList<BookDto>();
			int i = 0;
			try {
			con = DBUtil.getConnection(); // DB 연결
			String query1 = "select general, book_number, book_name, book_publisher, book_author_name, book_rent_yn from book where book_delete_yn = 'N'";
			
			pstmt = con.prepareStatement(query1);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String general = rs.getString("general");
				int number = rs.getInt("book_number");
				String names = rs.getString("book_name");
				String publisher = rs.getString("book_publisher");
				String author = rs.getString("book_author_name");
				//int count = rs.getInt("book_count");
				String rent = rs.getString("book_rent_yn");
				 bDto = new BookDto(general, number, names, publisher, author, 0, rent, "N");
				 btos.add(bDto);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(rs);
				DBUtil.close(pstmt);
				DBUtil.close(con);
				
			}
			
			return btos;
			
		}
		
		
		public ArrayList<String> getBookSearchColumn() {
			ArrayList<String> mColumns = new ArrayList<String>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ResultSetMetaData rsmd = null;
			String query1 = "select general, book_number, book_name, book_publisher, book_author_name, book_rent_yn from book where book_rent_yn = 'N' and book_delete_yn = 'N'";
			
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
		
		
		/*
		  	public ArrayList<BookDto> getBookSearch(String search, String name) throws Exception{
			BookDto bDto = null;
			ArrayList<BookDto> btos = new ArrayList<BookDto>();
			int i = 0;
			try {
			con = DBUtil.getConnection(); // DB 연결
			String query1 = "select general, book_number, book_name, book_publisher,book_author_name from book where book_name = ?";
			String query2 = "select general, book_number, book_name, book_publisher,book_author_name from book where book_publisher = ?";
			String query3 = "select general, book_number, book_name, book_publisher,book_author_name from book where book_author_name = ?";
			
			if(search == "도서명") {
			pstmt = con.prepareStatement(query1);
			pstmt.setString(1, name);
			}
			else if(search == "출판사") {
				pstmt = con.prepareStatement(query2);
				pstmt.setString(1, name);
				}
			else if(search == "저자명") {
				pstmt = con.prepareStatement(query3);
				pstmt.setString(1, name);
				}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String general = rs.getString("general");
				int number = rs.getInt("book_number");
				String names = rs.getString("book_name");
				String publisher = rs.getString("book_publisher");
				String author = rs.getString("book_author_name");
				int count = rs.getInt("count");
				
				 bDto = new BookDto(general, number, names, publisher, author, count);
				 btos.add(bDto);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(rs);
				DBUtil.close(pstmt);
				DBUtil.close(con);
				
			}
			
			return btos;
			
		}
		
		
		public ArrayList<String> getBookSearchColumn(String search, String name) throws Exception{
			ArrayList<String> mColumns = new ArrayList<String>();
			try {
				con = DBUtil.getConnection(); // DB 연결
				String query1 = "select general, book_number, book_name, book_publisher,book_author_name from book where book_name = ?";
				String query2 = "select general, book_number, book_name, book_publisher,book_author_name from book where book_publisher = ?";
				String query3 = "select general, book_number, book_name, book_publisher,book_author_name from book where book_author_name = ?";
				
				if(search == "도서명") {
				pstmt = con.prepareStatement(query1);
				pstmt.setString(1, name);
				}
				else if(search == "출판사") {
					pstmt = con.prepareStatement(query2);
					pstmt.setString(1, name);
					}
				else if(search == "저자명") {
					pstmt = con.prepareStatement(query3);
					pstmt.setString(1, name);
					}
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
		
		 * 
		 * */
		
}
