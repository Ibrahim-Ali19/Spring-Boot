package com.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto;

@Service
public interface EmployeeService {

	EmployeeDto createEmp(EmployeeDto emp );
	EmployeeDto getEmployeeById(Long id);
	List<EmployeeDto> getAllEmp();
	EmployeeDto updateEmp(Long id , EmployeeDto updatedEmp);
	void deleteEmp(Long id);
}
