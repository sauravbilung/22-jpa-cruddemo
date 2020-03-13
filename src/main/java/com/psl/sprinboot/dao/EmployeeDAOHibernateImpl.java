package com.psl.sprinboot.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.psl.sprinboot.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	//Repository annotation is used to indicate that the class provides the 
	//mechanism for storage, retrieval, search, update and delete operation on objects.
	

	// define field for entity manager
	// EntityManager is automatically created by SpringBoot
	//and we inject it via constructor injection in our case
	// EntityManager is a part of the Java Persistence API.
	// Chiefly, it implements the programming interfaces and lifecycle rules defined
	// by the JPA 2.0 specification.
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	//@Transactional // this annotation handles transaction management so we do not have to manually start and commit the transaction
	public List<Employee> findAll() {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
        Query<Employee> theQuery=currentSession.createQuery("from Employee",Employee.class);
		
		// execute the query and get the resultset
        List<Employee> employees=theQuery.getResultList();
        
		// return the result
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//get the current hibernate session
		Session currentSession=entityManager.unwrap(Session.class);
		
		//get the employee
		Employee theEmployee=currentSession.get(Employee.class, theId);
		
		//return the employee
		return theEmployee;
	}

	@Override
	public void save(Employee employee) {
		//get current session
		Session currentSession=entityManager.unwrap(Session.class);
		
		//save the employee
		currentSession.saveOrUpdate(employee);
	}

	@Override
	public void deleteById(int theId) {
		//get current session
	    Session currentSession=entityManager.unwrap(Session.class);
	    
	    //delete the employee
		Query theQuery=currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
	}

}
