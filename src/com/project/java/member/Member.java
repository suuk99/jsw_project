package com.project.java.member;

public class Member {
	private String member_id;
	private String pw;
	private String name;
	
	public String getMember_id() {
		return member_id;
	}
	
	public String get_pw() {
		return pw;
	}
	
	
	public String get_name() {
		return name;
	}
	
	public Member(String member_id, String pw, String name) {
		this.member_id = member_id;
		this.pw = pw;
		this.name = name;
	}

}
