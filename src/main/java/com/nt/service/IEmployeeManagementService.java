package com.nt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nt.entity.Employee;

public interface IEmployeeManagementService {

	public List<Employee> showAllEmployee();

	public String registerEmployee(Employee emp);

	public String deleteEmployeeById(Integer id);

	public String updateEmployee(Employee emp);

	public Employee getEmployeeByNo(int no);

	public Boolean isDesgInRejectedList(String list);
	
	public Page<Employee> getEmployeesReportPageDataByPage(Pageable page);
}
