package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nt.entity.Employee;
import com.nt.repository.IEmployeeRepository;

@Service("empService")
public class EmployeeMgmtServiceImpl implements IEmployeeManagementService {

	@Autowired
	private IEmployeeRepository empRepository;

	@Override
	public List<Employee> showAllEmployee() {
		return empRepository.findAll();
	}

	@Override
	public String registerEmployee(Employee emp) {
		// use repo
		Integer id = empRepository.save(emp).getEmpno();
		return "Employee obj is saved with id values : " + id;
	}

	@Override
	public String deleteEmployeeById(Integer id) {
		empRepository.deleteById(id);
		return id + " Employee obj is deleted from database.";
	}

	@Override
	public String updateEmployee(Employee emp) {
		return empRepository.save(emp) + "  Employee details is updated.";

	}

	@Override
	public Employee getEmployeeByNo(int no) {

		return empRepository.findById(no).orElseThrow(() -> new IllegalArgumentException("No id matched"));

	}

	@Override
	public Boolean isDesgInRejectedList(String list) {
		return list.equalsIgnoreCase("TeamLeader");
	}

	@Override
	public Page<Employee> getEmployeesReportPageDataByPage(Pageable page) {
		// TODO Auto-generated method stub
		return empRepository.findAll(page);
	}
}
