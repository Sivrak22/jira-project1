package com.example.demo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
}
