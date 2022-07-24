package com.employee;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class EmployeeApplicationTests {

	@Autowired
	EmployeeRepository empRepo;
	
	@Test
	@Order(1)
	public void testCreate() {
		Employee emp=new Employee();
		emp.setId(1L);
		emp.setFirstName("Adil");
		emp.setLastName("jain");
		emp.setEmailId("adil@gmail.com");
		empRepo.save(emp);
		assertNotNull(empRepo.findById(1L).get());
	}

	@Test
	@Order(2)
	public void testReadAll () {
		List<Employee> list = empRepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void testRead () {
		Employee employee = empRepo.findById(1L).get();
		assertEquals("Adil", employee.getFirstName());
	}
	
	@Test
	@Order(4)
	public void testUpdate () {
		Employee p = empRepo.findById(1L).get();
		p.setLastName("Arun");
		empRepo.save(p);
		assertNotEquals("Adil", empRepo.findById(1L).get().getLastName());
	}
	
	@Test
	@Order(5)
	public void testDelete () {
		empRepo.deleteById(1L);
		assertThat(empRepo.existsById(1L)).isFalse();
	}
}
