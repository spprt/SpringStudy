package com.springstudy.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springstudy.domain.UserVO;
import com.springstudy.exception.AlreadyExistingEmailException;
import com.springstudy.exception.AlreadyExistingIdException;
import com.springstudy.persistence.UserDAO;
import com.springstudy.util.RegisterRequest;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDAO")
	private UserDAO userDAO;

	@Override
	public void register(RegisterRequest regReq) throws Exception {

		UserVO email = userDAO.selectByEmail(regReq.getEmail());
		if (email != null) {
			throw new AlreadyExistingEmailException(regReq.getEmail() + " is duplicate email.");
		}

		UserVO id = userDAO.selectById(regReq.getId());
		if (id != null) {
			throw new AlreadyExistingIdException(regReq.getId() + " is duplicate id.");
		}

		userDAO.insertUser(regReq);
	}

}
