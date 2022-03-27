package com.java.ex.DB;


public class MemberDto {
	private String id;
	private String pw;
	private String name;
	private String email;
	private String checks;
	private int count;
	private int rent_count;
	
	public MemberDto(String id, String pw, String name, String email, String checks, int count, int rent_count) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.checks = checks;
		this.count = count;
		this.rent_count = rent_count;
	}
	


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getChecks() {
		return checks;
	}
	public void setChecks(String checks) {
		this.checks = checks;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRent_count() {
		return rent_count;
	}

	public void setRent_count(int rent_count) {
		this.rent_count = rent_count;
	}

	
}
