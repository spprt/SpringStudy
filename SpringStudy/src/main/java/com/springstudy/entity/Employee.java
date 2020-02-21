package com.springstudy.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity(name = "user")
@Table(name = "user")
public class Employee implements Serializable {
	private static final long serialVersionUID = 7192831414339863019L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idx;

	@Column(name = "id")
	private String id;

	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "regdate")
	private Date regdate;

	private String checkPassword;

	// 비밀번호 확인
	public boolean isPwEqualToCheckPw() {
		return password.equals(checkPassword);
	}

	public boolean matchPassword(String password) {
		return password.equals(password);
	}

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	@Override
	public String toString() {
		return "UserDomain [idx=" + idx + ", id=" + id + ", email=" + email + ", name=" + name + ", password="
				+ password + ", regdate=" + regdate + "]";
	}

}
