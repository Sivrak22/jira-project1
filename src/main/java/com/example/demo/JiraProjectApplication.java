package com.example.demo;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.dao.entity.Employee;
import com.example.demo.dao.services.EmployeeDao;

@SpringBootApplication
public class JiraProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiraProjectApplication.class, args);
	}
	
	public EmployeeDao empSer;
	
	public JiraProjectApplication() {}
	
	@Autowired
	public JiraProjectApplication(EmployeeDao empDao) {
		this.empSer = empDao;
	}
	
	@Bean
	public CommandLineRunner cliRunner() {
		return args -> {
			System.out.println("From Bean CLI");
			Employee e = new Employee("Siva","supermans@gmial.com","CSE",200000);
			this.empSer.saveEmployee(e);
			Employee e1 = new Employee("Siva2","superman@gmial.com","CSE",200000);
			this.empSer.saveEmployee(e1);
			Employee e2 = new Employee("Siva2","no2superman@gmial.com","CSE",2000011);
			this.empSer.updateEmp(2l, e2);
			System.out.println(this.empSer.getAllEmp().stream().map((Employee v)->{System.out.println(v.getName()+","+v.getEmail());return v;}).collect(Collectors.toList()));
			this.empSer.deleteEmp(1L);
			};
	 }
}
