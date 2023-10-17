package net.crud_be.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.crud_be.springboot.exception.ResourceNotFoundException;
import net.crud_be.springboot.model.Employee;
import net.crud_be.springboot.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get all employee
	@GetMapping("/employee")
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	
	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable Long id){
		
		return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not found"));
		
	}
	
	//create employee
	@PostMapping("/employee")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return employeeRepository.findById(id)
				.map(employee ->{
					employee.setFirstName(newEmployee.getFirstName());
					employee.setLastName(newEmployee.getLastName());
					employee.setEmailId(newEmployee.getEmailId());
					return employeeRepository.save(employee);
				}).orElseThrow(()-> new ResourceNotFoundException("Id not found"));
	}
	
	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		if(!employeeRepository.existsById(id)) {
			throw new ResourceNotFoundException("Id not found");
		}		
		employeeRepository.deleteById(id);
		return "Employee with id "+id+" has been removed.";		
	}	
	
}
