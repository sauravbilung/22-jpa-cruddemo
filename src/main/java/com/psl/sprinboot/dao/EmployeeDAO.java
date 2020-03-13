package com.psl.sprinboot.dao;

import java.util.List;

import com.psl.sprinboot.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee employee);
	
	public void deleteById(int theId);
}
