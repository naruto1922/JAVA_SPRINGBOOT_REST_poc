package com.accenture.lkm.service;

import java.util.List;

import com.accenture.lkm.businessbean.EmployeeBean;

public interface EmployeeService {
	public Integer createEmployee(EmployeeBean employeeBean);
	public EmployeeBean findByEmployeeId(int empId);
	public EmployeeBean updateEmployeeById(EmployeeBean empBean);
	public EmployeeBean deleteEmployeeById(int empId);
	public List<EmployeeBean> getAllEmployees();
}
