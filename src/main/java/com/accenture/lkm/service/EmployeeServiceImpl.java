package com.accenture.lkm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.businessbean.EmployeeBean;
import com.accenture.lkm.entity.EmployeeEntity;
import com.accenture.lkm.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeDAO;

	public Integer createEmployee(EmployeeBean employeeBean) {
		EmployeeEntity entity = convertBeanToEntity(employeeBean);
		EmployeeEntity entity1 = employeeDAO.save(entity);
		return entity1.getEmployeeId();
	}

	public EmployeeBean findByEmployeeId(int empId) {
		EmployeeBean bean = null;
		EmployeeEntity entity = employeeDAO.findByEmployeeId(empId);
		if (entity != null) {
			bean = convertEntityToBean(entity);
		}
		return bean;
	}

	public EmployeeBean updateEmployeeById(EmployeeBean empBean) {
		EmployeeEntity entity = convertBeanToEntity(empBean);
		EmployeeEntity entity1 = employeeDAO.save(entity);
		EmployeeBean bean = convertEntityToBean(entity1);
		return bean;
	}


	private EmployeeEntity convertBeanToEntity(EmployeeBean empBean) {
		EmployeeEntity entity = new EmployeeEntity();
		BeanUtils.copyProperties(empBean, entity);
		return entity;
	}

	private EmployeeBean convertEntityToBean(EmployeeEntity entity) {
		EmployeeBean bean = new EmployeeBean();
		BeanUtils.copyProperties(entity, bean);
		return bean;
	}

	public EmployeeBean deleteEmployeeById(int empId) {
		EmployeeBean bean = null;
		// employeeDAO.delete(empId); //if record not exist, we get the exception
		EmployeeEntity entity = employeeDAO.findByEmployeeId(empId);
		if (entity != null) {
			employeeDAO.delete(entity);
			//bean = convertEntityToBean(entity);
		}
		return bean;
	}

	public List<EmployeeBean> getAllEmployees() {
		Iterable<EmployeeEntity> entities = employeeDAO.findAll();
		List<EmployeeBean> empList = null;
		if (entities != null) {
			empList = new ArrayList<>();
			for (EmployeeEntity entity : entities) {
				EmployeeBean bean = convertEntityToBean(entity);
				empList.add(bean);
			}
		}
		return empList;
	}


}

//Stereotype annotations
//@Service - service class
//@Repository - DAO class
//extends @Component