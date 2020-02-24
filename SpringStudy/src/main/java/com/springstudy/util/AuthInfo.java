package com.springstudy.util;

/**
 * 로그인 정보를 담는 클래스
 */
public class AuthInfo {

	private String id;
	private String name;
//	private int grade;

	public AuthInfo(String id, String name) {
		this.id = id;
		this.name = name;
	}

//	public AuthInfo(String id, String name, int grade) {
//		this.id = id;
//		this.name = name;
//		this.grade = grade;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
