package com.springstudy.util;

/**
 * 로그인 정보를 담는 클래스
 */
public class AuthInfo {

	private String email;
	private String name;
//	private int grade;

	public AuthInfo(String email, String name) {
		this.email = email;
		this.name = name;
	}

//	public AuthInfo(String email, String name, int grade) {
//		this.email = email;
//		this.name = name;
//		this.grade = grade;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public int getGrade() {
//		return grade;
//	}
//
//	public void setGrade(int grade) {
//		this.grade = grade;
//	}

}
