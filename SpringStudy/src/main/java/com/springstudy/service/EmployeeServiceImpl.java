package com.springstudy.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.springstudy.entity.Employee;
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
		employeeDAO.addEmployee(employee);
	}

	@Override
	@Transactional
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	@Override
	@Transactional
	public void deleteEmployee(Long idx) {
		employeeDAO.deleteEmployee(idx);
	}

	public Employee getEmployee(Long idx) {
		return employeeDAO.getEmployee(idx);
	}

	public Employee getEmployee(String id) {
		return employeeDAO.getEmployee(id);
	}

	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDAO.updateEmployee(employee);
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	public AuthInfo loginAuth(LoginCommand loginCommand) throws Exception {
		Employee emp = getEmployee(loginCommand.getId());
		System.out.println(loginCommand.getId());
		System.out.println(loginCommand.getPassword());
		if (emp == null) {
			throw new IdPasswordNotMatchingException();
		}
		System.out.println("emp.getPassword() ::: " + emp.getPassword());
		System.out.println("loginCommand.getPassword() ::: " + loginCommand.getPassword());
		if (!emp.matchPassword(loginCommand.getPassword())) {
			throw new IdPasswordNotMatchingException();
		}

		return new AuthInfo(emp.getId(), emp.getName());
	}

}
