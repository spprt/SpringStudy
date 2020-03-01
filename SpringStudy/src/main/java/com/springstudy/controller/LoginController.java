package com.springstudy.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springstudy.exception.IdPasswordNotMatchingException;
import com.springstudy.service.EmployeeService;
import com.springstudy.util.AuthInfo;
import com.springstudy.util.LoginCommand;

@Controller
public class LoginController {
	private static final Logger logger = Logger.getLogger(EmployeeController.class);

	@Resource(name = "employeeService")
	private EmployeeService empSer;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm(LoginCommand loginCommand,
			@CookieValue(value = "REMEMBER", required = false) Cookie rememberCookie) throws Exception {
		if (rememberCookie != null) {
			loginCommand.setEmail(rememberCookie.getValue());
			loginCommand.setRememberId(true);
		}

		ModelAndView mv = new ModelAndView("user/loginForm");
		return mv;
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ModelAndView loginSuccess(@Valid LoginCommand loginCommand, BindingResult bindingResult, HttpSession session,
			HttpServletResponse response) throws Exception {
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("user/loginForm");
			return mv;
		}

		try {
			AuthInfo authInfo = empSer.loginAuth(loginCommand);
			session.setAttribute("authInfo", authInfo);
			logger.info("authInfo.getName()" + authInfo.getName());

			Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
			rememberCookie.setPath("/");
			if (loginCommand.isRememberId()) {
				rememberCookie.setMaxAge(60 * 60 * 24 * 7);
			} else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);
		} catch (IdPasswordNotMatchingException e) {
			bindingResult.rejectValue("password", "notMatch", "���̵�� ��й�ȣ�� ���� �ʽ��ϴ�.");
			ModelAndView mv = new ModelAndView("user/loginForm");
			return mv;
		}

		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
}
