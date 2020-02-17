package com.springstudy.service;

import com.springstudy.util.RegisterRequest;

public interface UserService {

	void register(RegisterRequest regReq) throws Exception;

}
