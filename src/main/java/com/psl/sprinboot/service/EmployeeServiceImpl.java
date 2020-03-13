package com.psl.sprinboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psl.sprinboot.dao.EmployeeDAO;
import com.psl.sprinboot.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	//@Service annotation is used with classes that provide some business functionalities
	//Spring context will autodetect these classes when annotation-based configuration and classpath scanning is used.
	//This is used so that we can get its instance from the context.(Refer google)
	

	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl") EmployeeDAO employeeDAO) {
		//Here we are using qualifier because spring doesn't know which DAOImpl to use since we have two DAOImpls in our case
		//so to resolve this we have used @Qualifier("bean ID") annotation
		//Easy solution is to keep just one DAOImpl
		//bean ID is generally class name with first letter as small
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		return employeeDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		employeeDAO.save(theEmployee);

	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeDAO.deleteById(theId);
	}

}
