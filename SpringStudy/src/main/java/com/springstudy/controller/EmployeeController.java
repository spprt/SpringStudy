package com.springstudy.controller;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springstudy.entity.Employee;
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

	@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Employee employee = new Employee();
		model.addObject("employee", employee);
		model.setViewName("EmployeeForm");
		return model;
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
 		if (employee.getIdx() == null) { // if employee id is 0 then creating the
			// employee other updating the employee
			employeeService.addEmployee(employee);
		} else {
			employeeService.updateEmployee(employee);
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		Long idx = Long.parseLong(request.getParameter("id"));
		employeeService.deleteEmployee(idx);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		Long idx = Long.parseLong(request.getParameter("id"));
		Employee employee = employeeService.getEmployee(idx);
		ModelAndView model = new ModelAndView("EmployeeForm");
		model.addObject("employee", employee);

		return model;
	}
}
