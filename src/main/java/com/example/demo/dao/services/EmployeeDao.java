package com.example.demo.dao.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.entity.Employee;
import com.example.demo.dao.repository.EmployeeRepo;
import com.example.demo.exceptions.ResourceNotFoundException;

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
	
	public List<Employee> findByNameOrEmail(String val){
//		return this.empRepo.findByNameOrEmailContaining(val,val);
		return this.empRepo.findByNameContainingOrEmailContaining(val,val);
	}
	public Employee getEmpById(Long id) {
		return this.empRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found"));
	}
	
	public Employee updateEmp(Long eid, Employee newEmp) {
		Employee existing = this.empRepo.findById(eid).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found!"));
		existing.setName(newEmp.getName());
		existing.setEmail(newEmp.getEmail());
		existing.setDepartment(newEmp.getDepartment());
		existing.setSalary(newEmp.getSalary());
		return this.empRepo.save(existing);
	}
	
	public void deleteEmp(Long id) {
		Employee e = this.empRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found"));
		this.empRepo.delete(e);
	}
}
