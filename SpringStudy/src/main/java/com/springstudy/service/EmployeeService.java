package com.springstudy.service;

import java.util.List;

import com.springstudy.entity.Employee;
import com.springstudy.util.AuthInfo;
import com.springstudy.util.LoginCommand;

public interface EmployeeService {
	public void addEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public void deleteEmployee(Long id);

	public Employee getEmployee(Long id);

	public Employee updateEmployee(Employee employee);

	AuthInfo loginAuth(LoginCommand loginCommand) throws Exception;
}
