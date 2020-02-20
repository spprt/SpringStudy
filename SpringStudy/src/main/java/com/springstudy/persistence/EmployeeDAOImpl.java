package com.springstudy.persistence;

import java.util.Iterator;
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
		return sessionFactory.getCurrentSession().createQuery("from Employee").list();
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
		Query query = sessionFactory.getCurrentSession().getNamedQuery("com.kcube.sys.emp.Employee.selectUserByLoginId");
		sessionFactory.getCurrentSession().getNamedQuery("userFindById");
		query.setString("id", id);
		Iterator<Employee> i = query.iterate();
		if (i.hasNext()) {
			return (Employee) i.next();
		}
		return null;
	}
}
