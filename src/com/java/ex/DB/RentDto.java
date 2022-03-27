package com.java.ex.DB;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class RentDto {
	private String rent_date;
	private String return_date;
	private String rent_return_date;
	private int book_number;
	private String book_name;
	private String id;
	private String rent_return_yn;
	public RentDto(String rent_date, String return_date, String rent_return_date, int book_number, String book_name,
			String id, String rent_return_yn) {
		super();
		this.rent_date = rent_date;
		this.return_date = return_date;
		this.rent_return_date = rent_return_date;
		this.book_number = book_number;
		this.id = id;
		this.rent_return_yn = rent_return_yn;
		this.book_name = book_name;
	}
	
	public String getRent_date() {
		return rent_date;
	}

	public void setRent_date(String rent_date) {
		this.rent_date = rent_date;
	}

	
	
	
	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public String getRent_return_date() {
		return rent_return_date;
	}
	public void setRent_return_date(String rent_return_date) {
		this.rent_return_date = rent_return_date;
	}
	public int getBook_number() {
		return book_number;
	}
	public void setBook_number(int book_number) {
		this.book_number = book_number;
	}
	
	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRent_return_yn() {
		return rent_return_yn;
	}
	public void setRent_return_yn(String rent_return_yn) {
		this.rent_return_yn = rent_return_yn;
	}
	
	

}
