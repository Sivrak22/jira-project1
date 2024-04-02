package com.example.demo.dao.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.entity.Employee;
import com.example.demo.dao.repository.EmployeeRepo;

@Service
public class EmployeeDao{
	@Autowired
	private EmployeeRepo empRepo;

	public Employee saveEmployee(Employee emp) {
		return this.empRepo.save(emp);
	}
	
	public List<Employee> getAllEmp(){
		return this.empRepo.findAll();
	}
	
	public Employee getEmpById(Long id) {
		return this.empRepo.findById(id).orElseThrow(()-> new RuntimeException("Employee Not Found"));
	}
	
	public Employee updateEmp(Long eid, Employee newEmp) {
		Employee existing = this.empRepo.findById(eid).orElseThrow(()-> new RuntimeException("Employee Not Found!"));
		existing.setName(newEmp.getName());
		existing.setEmail(newEmp.getEmail());
		existing.setDepartment(newEmp.getDepartment());
		existing.setSalary(newEmp.getSalary());
		return this.empRepo.save(existing);
	}
	
	public void deleteEmp(Long id) {
		Employee e = this.empRepo.findById(id).orElseThrow(()-> new RuntimeException("Employee Not Found"));
		this.empRepo.delete(e);
	}
}
