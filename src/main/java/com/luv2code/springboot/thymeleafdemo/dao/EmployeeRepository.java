package com.luv2code.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!
	
	//add a custom method to sort employees by last name. this is neccessary since we no longer write lowlevel queries but rely on JpaRepository
	public List<Employee> findAllByOrderByLastNameAsc();//this is part of springDataJpa Magic!
	                                                  //see www.luv2code.com/query-methods
}
