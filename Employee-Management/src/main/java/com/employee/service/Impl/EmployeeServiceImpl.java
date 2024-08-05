package com.employee.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.exception.ResourceNotFoundException;
import com.employee.mapper.EmployeeMapper;
import com.employee.repo.EmployeeRepo;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;
	
	
	@Override
	public EmployeeDto createEmp(EmployeeDto emp) {
		
		Employee employee = EmployeeMapper.maptoEmployee(emp);
		Employee savedemp = employeeRepo.save(employee);
		return EmployeeMapper.maptoEmployeeDto(savedemp) ;
	}


	@Override
	public EmployeeDto getEmployeeById(Long id) {
		
		Employee emp = employeeRepo.findById(id).orElseThrow(()->
		    new ResourceNotFoundException("Employee does not exist of id : " + id));
		
		return EmployeeMapper.maptoEmployeeDto(emp);
	}


	@Override
	public List<EmployeeDto> getAllEmp() {
		
		List<Employee> emps = employeeRepo.findAll();
		return emps.stream().map((emp) -> EmployeeMapper.maptoEmployeeDto(emp)).collect(Collectors.toList());
	}


	@Override
	public EmployeeDto updateEmp(Long id, EmployeeDto updatedEmp) {
		
		Employee emp = employeeRepo.findById(id).orElseThrow(()->
	    new ResourceNotFoundException("Employee does not exist of id : " + id));
		
		emp.setFirstName(updatedEmp.getFirstName());
		emp.setLastName(updatedEmp.getLastName());
		emp.setEmail(updatedEmp.getEmail());
		
		Employee savedemp = employeeRepo.save(emp);  
		
		return EmployeeMapper.maptoEmployeeDto(savedemp);
	}


	@Override
	public void deleteEmp(Long id) {
		
		Employee emp = employeeRepo.findById(id).orElseThrow(()->
	    new ResourceNotFoundException("Employee does not exist of id : " + id));
		
	    employeeRepo.deleteById(id);
	}

	
	
}
