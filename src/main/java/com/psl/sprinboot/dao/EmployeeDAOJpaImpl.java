package com.psl.sprinboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.psl.sprinboot.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	public EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		//create the query
		//Here import for query is different than hibernate
		Query query=entityManager.createQuery("from Employee");
		
		//execute the query and get the result list
		List<Employee> employees = query.getResultList();
		
		//return the results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//get the employee
		Employee employee=entityManager.find(Employee.class, theId);
		
		//return the employee
		return employee;
	}

	@Override
	public void save(Employee employee) {
		//save or update the employee
		//if id == 0 then insert/save else update
		Employee dbEmployee = entityManager.merge(employee);
		
		//update with id from db ... so we get generated id for save/insert
		employee.setId(dbEmployee.getId());
	}

	@Override
	public void deleteById(int theId) {
		// Deleting the employee by primary key
        Query query=entityManager.createQuery("delete from Employee where id=:employeeId");        
        query.setParameter("employeeId", theId);
        query.executeUpdate();
	}

}
