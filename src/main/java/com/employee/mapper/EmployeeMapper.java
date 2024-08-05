package com.employee.mapper;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;

public class EmployeeMapper {

	public static EmployeeDto maptoEmployeeDto (Employee emp) {
		
		return new EmployeeDto(emp.getId(), emp.getFirstName(),
				               emp.getLastName(), emp.getEmail());
	}
	
	public static Employee maptoEmployee (EmployeeDto emp) {
		
		return new Employee(emp.getId(), emp.getFirstName(),
				            emp.getLastName(), emp.getEmail());
	}
}
