package com.java.ex.DB;

public class BookDto {
	private String general;
	private int book_number;
	private String book_name;
	private String book_publisher;
	private String book_author_name;
	private int book_count;
	private String book_rent_yn ;
	private String book_delete_yn ;
	
	public BookDto(String general, int book_number, String book_name, String book_publisher, String book_author_name,
			int book_count, String book_rent_yn, String book_delete_yn) {
		this.general = general;
		this.book_number = book_number;
		this.book_name = book_name;
		this.book_publisher = book_publisher;
		this.book_author_name = book_author_name;
		this.book_count = book_count;
		this.book_rent_yn = book_rent_yn;
		this.book_delete_yn = book_delete_yn;
	}

	public String getGeneral() {
		return general;
	}

	public void setGeneral(String general) {
		this.general = general;
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

	public String getBook_publisher() {
		return book_publisher;
	}

	public void setBook_publisher(String book_publisher) {
		this.book_publisher = book_publisher;
	}

	public String getBook_author_name() {
		return book_author_name;
	}

	public void setBook_author_name(String book_author_name) {
		this.book_author_name = book_author_name;
	}

	public int getBook_count() {
		return book_count;
	}

	public void setBook_count(int book_count) {
		this.book_count = book_count;
	}

	public String getBook_rent_yn() {
		return book_rent_yn;
	}

	public void setBook_rent_yn(String book_rent_yn) {
		this.book_rent_yn = book_rent_yn;
	}

	public String getBook_delete_yn() {
		return book_delete_yn;
	}

	public void setBook_delete_yn(String book_delete_yn) {
		this.book_delete_yn = book_delete_yn;
	}

	
	
}
