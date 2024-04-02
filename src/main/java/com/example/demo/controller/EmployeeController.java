package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.entity.Employee;
import com.example.demo.dao.repository.EmployeeRepo;
import com.example.demo.dao.services.EmployeeDao;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.SimpleReponseDTO;

@RestController
@RequestMapping
public class EmployeeController {
	
	private EmployeeDao empDao;
	
	@Autowired
	public EmployeeController(EmployeeDao empDao) {
		this.empDao = empDao;
	}
	
	@GetMapping("/emp/{id}")
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("id") Long id){
		Employee emp = this.empDao.getEmpById(id);
		return new ResponseEntity<EmployeeDTO>(new ModelMapper().map(emp, EmployeeDTO.class), HttpStatus.OK);
	}
	
	@GetMapping("/emp/all")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
		List<EmployeeDTO> allEmp = this.empDao.getAllEmp().stream().map(emp->{return new ModelMapper().map(emp, EmployeeDTO.class);}).collect(Collectors.toList());
		return new ResponseEntity<List<EmployeeDTO>>(allEmp,HttpStatus.OK);
	}
	
	@PostMapping("/empnew")
	public ResponseEntity<SimpleReponseDTO> createNewEmployee(@RequestBody EmployeeDTO empdto){
		Employee emp = new ModelMapper().map(empdto,Employee.class);
		this.empDao.saveEmployee(emp);
		return new ResponseEntity<SimpleReponseDTO>(new SimpleReponseDTO("Creation completed", "Success"),HttpStatus.OK);
	}
	
	@PutMapping("/empup/{id}")
	public ResponseEntity<SimpleReponseDTO> updateEmployee(@PathVariable long id, @RequestParam Map<String,String> empDto){
		System.out.println(empDto.entrySet());
		Employee emp = this.empDao.getEmpById(id);
		if(empDto.get("name")!=null) emp.setName(empDto.get("name"));
		if(empDto.get("salary")!=null) emp.setSalary(Double.parseDouble(empDto.get("salary")));
		if(empDto.get("department")!=null) emp.setDepartment(empDto.get("department"));
		if(empDto.get("email")!=null) emp.setEmail(empDto.get("email"));
		this.empDao.saveEmployee(emp);
		return new ResponseEntity<SimpleReponseDTO>(new SimpleReponseDTO("Update Succesfully done", "Success"),HttpStatus.OK);
	}
	
	@DeleteMapping("/empdel/{id}")
	public ResponseEntity<SimpleReponseDTO> deleteEmp(@PathVariable Long id){
		this.empDao.deleteEmp(id);
		return new ResponseEntity<SimpleReponseDTO>(new SimpleReponseDTO("Deleted Employee", "Success"),HttpStatus.OK);
	}
	
	@GetMapping("/empby/{val}")
	public ResponseEntity<List<EmployeeDTO>> findByNameOrEmail( @PathVariable String val){
		List<EmployeeDTO> allEmp = this.empDao.findByNameOrEmail(val).stream().map(emp->{return new ModelMapper().map(emp, EmployeeDTO.class);}).collect(Collectors.toList());
		return new ResponseEntity<List<EmployeeDTO>>(allEmp,HttpStatus.OK);
	}

	@GetMapping("/emp/operate")
	public ResponseEntity<Map<String,List<EmployeeDTO>>> getAllEmployeeOperate(){
		Map<String,List<EmployeeDTO>> allEmp = this.empDao.getAllEmp().stream()
				.map(emp->{return new ModelMapper().map(emp, EmployeeDTO.class);})
				.filter(val->val.getSalary()>10)
				.sorted((a,b)-> Double.compare(b.getSalary(), a.getSalary()))
				.collect(Collectors.groupingBy(EmployeeDTO::getDepartment));
		return new ResponseEntity<Map<String,List<EmployeeDTO>>>(allEmp,HttpStatus.OK);
	}
}
