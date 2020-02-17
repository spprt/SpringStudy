package com.springstudy.persistence;

import org.springframework.stereotype.Repository;

import com.springstudy.common.dao.AbstractDAO;
import com.springstudy.domain.UserVO;
import com.springstudy.util.RegisterRequest;

@Repository("userDAO")
public class UserDAO extends AbstractDAO {

	public UserVO selectByEmail(String email) throws Exception {
		return (UserVO) selectOne("user.selectByEmail", email);
	}

	public UserVO selectById(String id) throws Exception {
		return (UserVO) selectOne("user.selectById", id);
	}

	public void insertUser(RegisterRequest regReq) throws Exception {
		insert("user.register", regReq);
	}
}
