package com.cg.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cg.dto.Employee;
@Repository("employeedao")
public class EmployeeDaoImpl implements IEmployeeDao {
	
	@PersistenceContext
	EntityManager entitymanager;
	
	
	
	@Override
	public int addEmployeeData(Employee emp) {
		entitymanager.persist(emp);
		entitymanager.flush();
		return emp.getEmpId();
	}

	@Override
	public List<Employee> showAllEmployee() {
		Query query1 = entitymanager.createQuery("From Employee");
		List<Employee> myList= query1.getResultList();
		return myList;
	}

	@Override
	public void deleteEmployee(int empId) {
		Query query2 = entitymanager.createQuery("delete from Employee where empId=:eid");
		query2.setParameter("eid", empId);
		query2.executeUpdate();

	}

	@Override
	public void updateEmployee(Employee emp) {
	
	   entitymanager.merge(emp);
		
	

	}

	@Override
	public Employee searchEmployee(int id) {
		Query query3 = entitymanager.createQuery("select e from Employee e where empId=:eid");
		query3.setParameter("eid", id);
		
		return searchEmployee(id);
	}

}
