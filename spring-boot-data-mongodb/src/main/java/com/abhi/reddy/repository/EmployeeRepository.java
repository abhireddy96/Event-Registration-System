package com.abhi.reddy.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.abhi.reddy.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
	
	public Employee findByMid(String mid);

	public int deleteByMid(String mid);
	
	@Query(value="{mid: {'$in': ?0}}", fields="{events: 0}")
	public List<Employee> findAllByMid(List<String> employeeIds);

	public boolean existsByMid(String employeeId);
	
	/*public List<Employee> findAllByMid(List<String> employeeIds);
*/


}
