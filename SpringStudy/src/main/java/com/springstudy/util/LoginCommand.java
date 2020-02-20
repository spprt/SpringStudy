package com.springstudy.util;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class LoginCommand {

	@NotEmpty(message = "���̵� �Է����ּ���.")
	private String id;

	@NotEmpty(message = "��й�ȣ�� �Է����ּ���.")
	private String password;
	private boolean rememberId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberId() {
		return rememberId;
	}

	public void setRememberId(boolean rememberId) {
		this.rememberId = rememberId;
	}

}
