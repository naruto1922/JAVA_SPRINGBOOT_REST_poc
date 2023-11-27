package com.accenture.lkm.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeEntity, Integer> {

		EmployeeEntity findByEmployeeId(Integer empId);
}



//at runtime, a class which implements EmployeeDAO is created
//proxy object is also created
