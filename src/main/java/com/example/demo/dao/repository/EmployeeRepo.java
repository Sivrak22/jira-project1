package com.example.demo.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	public List<Employee> findByNameOrEmailContaining(String name, String email);
	public List<Employee> findByNameContainingOrEmailContaining(String name, String email);	
}
