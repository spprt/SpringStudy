package com.springstudy.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.entity.Employee;

@Repository("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addEmployee(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {
		return sessionFactory.getCurrentSession().createQuery("from user").list();
	}

	@Override
	public void deleteEmployee(Long idx) {
		Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, idx);
		if (null != employee) {
			this.sessionFactory.getCurrentSession().delete(employee);
		}
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		sessionFactory.getCurrentSession().update(employee);
		return employee;
	}

	@Override
	public Employee getEmployee(Long idx) {
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, idx);
	}

	@Override
	public Employee getEmployee(String id) {
//		Query query = sessionFactory.getCurrentSession().getNamedQuery("User_findById");
		Query query = sessionFactory.getCurrentSession().createQuery("from user u where u.id =:id");
		query.setParameter("id", id);

		Employee emp = (Employee) query.uniqueResult();
		return emp;
	}
}
