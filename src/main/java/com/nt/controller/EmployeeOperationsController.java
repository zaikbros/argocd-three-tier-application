package com.nt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.entity.Employee;
import com.nt.service.EmployeeMgmtServiceImpl;
import com.nt.validator.EmployeeValidator;

@Controller
public class EmployeeOperationsController {
	@Autowired
	private EmployeeValidator employeeValidator;
	@Autowired
	private EmployeeMgmtServiceImpl serviceImpl;

	@GetMapping("/")
	public String showHomePage() {
		// return LVN page
		return "welcome";
	}

	@GetMapping("/page_report")
	public String showEmployeeByPagination(Map<String, Object> map,
			@PageableDefault(page = 0, size = 8, sort = "job", direction = Sort.Direction.ASC) Pageable pageable) {

		Page<Employee> page = serviceImpl.getEmployeesReportPageDataByPage(pageable);
		map.put("pageData", page);
		return "show_report_pagination";
	}

	@GetMapping("/report")
	public String showEmployeeReportData(Map<String, Object> map) {
		// user service
		System.out.println("EmployeeOperationsController.showEmployeeReportData()");
		List<Employee> showAllEmployee = serviceImpl.showAllEmployee();
		// keep the results in the shared memory
		map.put("empsData", showAllEmployee);

		// return LVN
		return "show_report";
	}

	@GetMapping("/register") // to launch add employee form page
	public String showAddEmployeeFormPage(@ModelAttribute("emp") Employee emp) {
		// return LVN
		System.out.println("EmployeeOperationsController.showAddEmployeeFormPage()");
		return "employee_register_form";
	}

	@PostMapping("/register")
	public String registerEmployee(RedirectAttributes attrs, @ModelAttribute("emp") Employee emp,
			BindingResult errors) {

		if (emp.getVflag().equalsIgnoreCase("no")) {
			// use validator
			if (employeeValidator.supports(Employee.class)) {
				employeeValidator.validate(emp, errors);

				// Application logic errors
				if (serviceImpl.isDesgInRejectedList(emp.getJob()))
					errors.rejectValue("job", "emp.job.reject");

				if (errors.hasErrors())// if form alidations error msgs are found
					return "employee_register_form";
			}

		}
		String msg = serviceImpl.registerEmployee(emp);
		// User shared memeory
		attrs.addFlashAttribute("resultMsg", msg);
		// return LVN
		System.out.println("EmployeeOperationsController.registerEmployee()");
		return "redirect:report";
	}

	@GetMapping("/delete")
	public String deleteById(RedirectAttributes attrs, @RequestParam("no") Integer id) {
		String deleteEmployeeById = serviceImpl.deleteEmployeeById(id);
		attrs.addFlashAttribute("resultMsg", deleteEmployeeById);
		return "redirect:report";
	}

	@GetMapping("/edit")
	public String showEditEmployeeFormPage(@RequestParam("no") int no, @ModelAttribute("emp") Employee emp) {
		Employee employeeByNo = serviceImpl.getEmployeeByNo(no);
		BeanUtils.copyProperties(employeeByNo, emp);
		return "update_employee";
	}

	@PostMapping("/edit")
	public String editEmployee(RedirectAttributes attrs, @ModelAttribute("emp") Employee emp, BindingResult errors) {

		if (emp.getVflag().equalsIgnoreCase("no")) {
			// use Validator
			if (employeeValidator.supports(Employee.class)) {
				employeeValidator.validate(emp, errors);
				if (serviceImpl.isDesgInRejectedList(emp.getJob()))
					errors.rejectValue("job", "emp.job.reject");

				if (errors.hasErrors())
					return "update_employee";
			}

		}
		String msg = serviceImpl.updateEmployee(emp);

		// add the msg to flashAttribute
		attrs.addFlashAttribute("resultMsg", msg);

		return "redirect:report";
	}
}
