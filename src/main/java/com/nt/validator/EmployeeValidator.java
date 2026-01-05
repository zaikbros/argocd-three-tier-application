package com.nt.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nt.entity.Employee;

@Component
public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {// to checkwhether the current modal class taken or not
		return clazz.isAssignableFrom(Employee.class);// if true the next validate(-) method executes
		// otherwise validate(-) method does not execute
	}

	@Override
	public void validate(Object target, Errors errors) {
		// Type casting with Model class
		Employee emp = (Employee) target;

		// Form validation logic(server side) and place the error in Errors object
		if (emp.getEmpname() == null || emp.getEmpname().isBlank())// required rule
			errors.rejectValue("empname", "emp.empname.required");
		else if (emp.getEmpname().length() < 5 || emp.getEmpname().length() >= 15)// length rule
			errors.rejectValue("empname", "emp.empname.length");

		if (emp.getJob() == null || emp.getJob().isBlank())// required rule
			errors.rejectValue("job", "emp.job.required");
		else if (emp.getJob().length() < 5 || emp.getJob().length() >= 10)// length rule
			errors.rejectValue("job", "emp.job.length");

		if (emp.getSal() == null)// required rule
			errors.rejectValue("sal", "emp.sal.required");
		else if (emp.getSal() < 10000 || emp.getSal() >= 200000.0)// range rule
			errors.rejectValue("sal", "emp.sal.range");

		if (emp.getDeptno() == null)
			errors.rejectValue("deptno", "emp.deptno.required");
	}

}
