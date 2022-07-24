package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
    @RequestMapping(value="/employees", method=RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee emp) {
	    return empService.createEmployee(emp);
	}
    
    @RequestMapping(value="/employees", method=RequestMethod.GET)
    @Cacheable(value = "cacheEmployees")
    public List<Employee> readEmployees() {
    	System.out.println("inside getAllEmployees() method....");
    	List<Employee> empList= empService.getAllEmployees();
    	System.out.println("employeeList="+empList);
        return empService.getAllEmployees();
    }
    
    /**
     * @CacheEvict will clear the cache when delete any employee info from the database.
     */
    @RequestMapping(value="/clearCache", method=RequestMethod.POST)
    @CacheEvict(value="cacheEmployees", allEntries = true)
    public void clearCacheEmployees() {
    	
    }
    
    @RequestMapping(value="/employees/{empId}", method=RequestMethod.GET)
    @Cacheable(value = "cacheEmployees")
    public Employee readSpecificEmployee(@PathVariable(value = "empId") Long id) {
    	System.out.println("Employee found...");
    	return empService.retrieveEmployee(id);
    }
    
    @RequestMapping(value="/employees/{empId}", method=RequestMethod.DELETE)
    public void deleteEmployees(@PathVariable(value = "empId") Long id) {
        empService.deleteEmployee(id);
    }
    
    @RequestMapping(value="/employees/{empId}", method=RequestMethod.PUT)
    public void updateEmployees(@PathVariable(value = "empId") Long id,@RequestBody Employee emp) {
        empService.updateEmployee(id,emp);
    }
}
