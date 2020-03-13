package com.psl.sprinboot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.psl.sprinboot.entity.Employee;
import com.psl.sprinboot.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeRestController {
     private EmployeeService employeeService;

     @Autowired
     public EmployeeRestController(EmployeeService employeeService) {
    	 //Remember spring boot automatically generates the beans
         //so here we are implementing constructor injection to get the bean from spring boot
         //we can use any injection methods as per our needs 
		this.employeeService = employeeService;
	 }
     
	 @GetMapping("/employees")
	 public List<Employee> findAll(){
		 return employeeService.findAll();
	 }
	 
	 @GetMapping("/employees/{employeeId}")
     public Employee getEmployee(@PathVariable int employeeId) {
		 Employee employee=employeeService.findById(employeeId);
		 if(employee == null) {
			 throw new RuntimeException("Employee id not found - "+employeeId);
		 }
		 return employee;		 
	 }
	 
	 @PostMapping("/employees")
	 public Employee addEmployee(@RequestBody Employee employee) {
		//we are setting the employee id to 0 
		//coz in our case if user enters an id we want to force a save operation and not the update operation 
		employee.setId(0);
		employeeService.save(employee);
		return employee;
		 
	 }
	 
	 @PutMapping("/employees")
	 public Employee updateEmployee(@RequestBody Employee theEmployee) {
		 employeeService.save(theEmployee);
		 return theEmployee;
	 }	 	 
	 
	 @DeleteMapping("/employees/{employeeId}")
	 public String deleteEmployee(@PathVariable int employeeId) {
		 Employee tempEmployee=employeeService.findById(employeeId);
		 
		 if(tempEmployee == null) {
			 throw new RuntimeException("Employee id not found - "+ employeeId);
		 }
		 
		 employeeService.deleteById(employeeId);
		 return "Deleted employee with id : "+ employeeId;
	 }
}
