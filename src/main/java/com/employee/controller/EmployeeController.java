package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeDto;
import com.employee.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}




	@PostMapping
	public ResponseEntity<EmployeeDto> createEmp(@RequestBody EmployeeDto employeeDto){
		
		EmployeeDto savedemp = employeeService.createEmp(employeeDto);
		
		return new ResponseEntity<>(savedemp , HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> findEmpById(@PathVariable Long id){
		
		EmployeeDto emp = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(emp);
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmp(){
		
		List<EmployeeDto> emp = employeeService.getAllEmp();
		return ResponseEntity.ok(emp);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmp(@PathVariable Long id ,@RequestBody EmployeeDto emp){
		
		EmployeeDto employeedto = employeeService.updateEmp(id, emp);
		return ResponseEntity.ok(employeedto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmp(@PathVariable Long id){
		
		employeeService.deleteEmp(id);
		return ResponseEntity.ok("emp successfull deleted");
	}
}
