package com.accenture.lkm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.businessbean.EmployeeBean;
import com.accenture.lkm.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value="/getDetails", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeBean>> retrieveAllEmployees(){
			List<EmployeeBean> empList = employeeService.getAllEmployees(); 
			if(empList.size() > 0)
				return new ResponseEntity<List<EmployeeBean>>(empList, HttpStatus.OK);
			else
				return new ResponseEntity<List<EmployeeBean>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value="/addEmployee", method=RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createEmployee(@RequestBody EmployeeBean employeeBean){
		int empId = employeeService.createEmployee(employeeBean);
		return new ResponseEntity<String>("Employee Created successfully with id "+empId, HttpStatus.CREATED);
	}

	@RequestMapping(value="/getDetailsById/{empId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeBean> retrieveEmployeeById(@PathVariable("empId") int empId){
		EmployeeBean bean = employeeService.findByEmployeeId(empId);
		if(bean != null)
			return new ResponseEntity<EmployeeBean>(bean, HttpStatus.FOUND);
		else
			return new ResponseEntity<EmployeeBean>(HttpStatus.NOT_FOUND);
	}

	
	@RequestMapping(value="/updateEmployee", method=RequestMethod.PUT, 
				consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeBean> updateEmployee(@RequestBody EmployeeBean employeeBean){
		EmployeeBean bean = employeeService.updateEmployeeById(employeeBean);
		return new ResponseEntity<EmployeeBean>(bean, HttpStatus.OK);
	}

	@RequestMapping(value="/deleteEmployee/{empId}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeBean> deleteEmployee(@PathVariable("empId") int empId){
		EmployeeBean bean = employeeService.deleteEmployeeById(empId);
		if(bean != null)
			return new ResponseEntity<EmployeeBean>(bean, HttpStatus.OK);
		else
			return new ResponseEntity<EmployeeBean>(HttpStatus.NOT_FOUND);
	}
	
}
