package com.springstudy.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.springstudy.entity.Employee;
import com.springstudy.exception.AlreadyExistingEmailException;
import com.springstudy.exception.IdPasswordNotMatchingException;
import com.springstudy.persistence.EmployeeDAO;
import com.springstudy.util.AuthInfo;
import com.springstudy.util.LoginCommand;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Resource(name = "employeeDAO")
	private EmployeeDAO employeeDAO;

	@Override
	@Transactional
	public void addEmployee(Employee employee) {
		Employee emp = getEmployee(employee.getEmail());
		if (null != emp) {
			throw new AlreadyExistingEmailException();
		}
		employeeDAO.addEmployee(employee);
	}

	@Override
	@Transactional
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	@Override
	@Transactional
	public void deleteEmployee(Long id) {
		employeeDAO.deleteEmployee(id);
	}

	public Employee getEmployee(Long id) {
		return employeeDAO.getEmployee(id);
	}

	public Employee getEmployee(String email) {
		return employeeDAO.getEmployee(email);
	}

	public Employee updateEmployee(Employee employee) {
		return employeeDAO.updateEmployee(employee);
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	public AuthInfo loginAuth(LoginCommand loginCommand) {
		Employee emp = getEmployee(loginCommand.getEmail());
		if (emp == null) {
			throw new IdPasswordNotMatchingException();
		}
		if (!emp.getPassword().equals(loginCommand.getPassword())) {
			throw new IdPasswordNotMatchingException();
		}

		return new AuthInfo(emp.getId(), emp.getEmail(), emp.getName());
	}

}
