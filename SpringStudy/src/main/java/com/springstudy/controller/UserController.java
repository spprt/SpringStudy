package com.springstudy.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springstudy.exception.AlreadyExistingEmailException;
import com.springstudy.exception.AlreadyExistingIdException;
import com.springstudy.service.UserService;
import com.springstudy.util.RegisterRequest;

@Controller
public class UserController {

	@Resource(name = "userService")
	private UserService userSer;

	@RequestMapping("/create")
	public String create() {
		return "index";
	}

	@RequestMapping("/register/step1")
	public String step1() throws Exception {
		return "/user/register/step1";
	}

	@RequestMapping("/register/step2")
	public ModelAndView step2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree) throws Exception {
		if (!agree) {
			ModelAndView mv = new ModelAndView("user/register/step1");
			return mv;
		}
		ModelAndView mv = new ModelAndView("user/register/step2");
		mv.addObject("registerRequest", new RegisterRequest());
		return mv;
	}

	@RequestMapping("/register/step3")
	public ModelAndView step3(@Valid RegisterRequest regReq, BindingResult bindingResult) throws Exception {
		System.out.println("step3");
		System.out.println(bindingResult.hasErrors());
		System.out.println(bindingResult.getFieldErrorCount());
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("user/register/step2");
			return mv;
		}

		// 비밀번호 확인
		boolean check = regReq.isPwEqualToCheckPw();
		if (!check) {
			bindingResult.rejectValue("checkPw", "noMatch", "비밀번호를 확인해주세요.");
			ModelAndView mv = new ModelAndView("user/register/step2");
			return mv;
		}
		try {
			userSer.register(regReq);
		} catch (AlreadyExistingEmailException e) {
			bindingResult.rejectValue("email", "duplicate", "이미 가입된 이메일입니다.");
			ModelAndView mv = new ModelAndView("user/register/step2");
			return mv;
		} catch (AlreadyExistingIdException e) {
			bindingResult.rejectValue("id", "duplicate", "이미 가입된 아이디입니다.");
			ModelAndView mv = new ModelAndView("user/register/step2");
			return mv;
		}

		ModelAndView mv = new ModelAndView("user/register/step3");
		return mv;
	}
//	public ModelAndView step3(RegisterRequest regReq, Errors errors) throws Exception {
//		new RegisterRequestValidator().validate(regReq, errors);
//		if (errors.hasErrors()) {
//			ModelAndView mv = new ModelAndView("user/register/step2");
//			return mv;
//		}
//		try {
//			userSer.register(regReq);
//		} catch (AlreadyExistingEmailException e) {
//			errors.rejectValue("email", "duplicate", "이미 가입된 이메일입니다.");
//			ModelAndView mv = new ModelAndView("user/register/step2");
//			return mv;
//		} catch (AlreadyExistingIdException e) {
//			errors.rejectValue("id", "duplicate", "이미 가입된 아이디입니다.");
//			ModelAndView mv = new ModelAndView("user/register/step2");
//			return mv;
//		}
//		ModelAndView mv = new ModelAndView("user/register/step3");
//		return mv;
//	}
}
