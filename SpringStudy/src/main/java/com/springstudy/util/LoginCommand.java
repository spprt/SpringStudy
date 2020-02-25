package com.springstudy.util;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class LoginCommand {

	@NotEmpty(message = "�̸����� �Է����ּ���.")
	private String email;

	@NotEmpty(message = "��й�ȣ�� �Է����ּ���.")
	private String password;
	private boolean rememberId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
