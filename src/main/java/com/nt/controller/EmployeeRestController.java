package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Employee;
import com.nt.service.IEmployeeManagementService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*") // Allow React app to access from any port (e.g., 5173)
public class EmployeeRestController {

    @Autowired
    private IEmployeeManagementService service;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<List<Employee>>(service.showAllEmployee(), HttpStatus.OK);
    }
    
    @GetMapping("/page")
    public ResponseEntity<Page<Employee>> getEmployeesPaged(
    		@PageableDefault(page = 0, size = 8, sort = "job", direction = Sort.Direction.ASC) Pageable pageable) {
    	Page<Employee> page = service.getEmployeesReportPageDataByPage(pageable);
    	return new ResponseEntity<Page<Employee>>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        try {
            Employee emp = service.getEmployeeByNo(id);
            return new ResponseEntity<Employee>(emp, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        String msg = service.registerEmployee(employee);
        return new ResponseEntity<String>(msg, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        employee.setEmpno(id); // Ensure ID matches
        String msg = service.updateEmployee(employee);
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        String msg = service.deleteEmployeeById(id);
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }
}
