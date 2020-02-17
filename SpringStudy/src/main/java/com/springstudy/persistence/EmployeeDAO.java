package com.springstudy.persistence;

import java.util.List;

import com.springstudy.domain.Employee;

public interface EmployeeDAO {

	public void addEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public void deleteEmployee(Integer employeeId);

	public Employee updateEmployee(Employee employee);

	public Employee getEmployee(int employeeid);
}
