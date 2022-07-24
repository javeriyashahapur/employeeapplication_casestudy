package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepo;
	
	//create
	public Employee createEmployee(Employee emp) {
		return empRepo.save(emp);
	}
	
	//Read
	public List<Employee> getAllEmployees(){
		return empRepo.findAll();
	}
	
	//ReadSpecificEmployee
	public Employee retrieveEmployee(Long empId) {
		for (Employee emp :empRepo.findAll()) {
			if (emp.getId().equals(empId)) {
				return emp;
			}
		}
		return null;
	}
	//Delete
	public void deleteEmployee(Long empId) {
		empRepo.deleteById(empId);
	}
	
	//Update
	public void updateEmployee(Long empId,Employee employee) {
		empRepo.findById(empId).ifPresent(emp->
		{
		   emp.setFirstName(employee.getFirstName());
		   emp.setLastName(employee.getLastName());
		   emp.setEmailId(employee.getEmailId());
		   empRepo.save(emp);
		});
	}
}
