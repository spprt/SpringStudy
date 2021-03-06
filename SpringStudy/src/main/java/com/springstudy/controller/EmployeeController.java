package com.springstudy.controller;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springstudy.entity.Employee;
import com.springstudy.exception.AlreadyExistingEmailException;
import com.springstudy.service.EmployeeService;

@Controller
public class EmployeeController {
	private static final Logger logger = Logger.getLogger(EmployeeController.class);

	public EmployeeController() {
		if (logger.isInfoEnabled()) {
			logger.info("EmployeeController()");
		}
	}

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/signup/step1")
	public String step1() throws Exception {
		return "/user/signup/step1";
	}

	@RequestMapping("/signup/step2")
	public ModelAndView step2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree) throws Exception {
		if (!agree) {
			ModelAndView mv = new ModelAndView("user/signup/step1");
			return mv;
		}
		ModelAndView mv = new ModelAndView("user/signup/step2");
		mv.addObject("employee", new Employee());
		return mv;
	}

	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public ModelAndView myPage(ModelAndView model) {
		return new ModelAndView("user/mypage");
	}

	@RequestMapping(value = "/employee/new", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Employee employee = new Employee();
		model.addObject("employee", employee);
		model.setViewName("EmployeeForm");
		return model;
	}

	@RequestMapping(value = "/employee/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute Employee employee, BindingResult bindingResult) {
		try {
			if (employee.getId() == null) { // if employee id is 0 then creating the
				// employee other updating the employee
				employeeService.addEmployee(employee);
			} else {
				employeeService.updateEmployee(employee);
			}
		} catch (AlreadyExistingEmailException e) {
			bindingResult.rejectValue("email", "exist", "이미 존재하는 이메일 주소입니다.");
			ModelAndView mv = new ModelAndView("user/signup/step2");
			return mv;
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/employee/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		employeeService.deleteEmployee(id);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/employee/update", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Employee employee = employeeService.getEmployee(id);
		ModelAndView model = new ModelAndView("EmployeeForm");
		model.addObject("employee", employee);

		return model;
	}
}
