package com.springstudy.persistence;

import java.util.List;

import com.springstudy.entity.Employee;

public interface EmployeeDAO {

	public void addEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public void deleteEmployee(Long id);

	public Employee updateEmployee(Employee employee);

	public Employee getEmployee(Long id);
	
	public Employee getEmployee(String email);
}
