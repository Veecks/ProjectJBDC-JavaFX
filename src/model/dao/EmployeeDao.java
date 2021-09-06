package model.dao;

import java.util.List;

import model.entities.Employee;

public interface EmployeeDao {
	void insert(Employee emp);
	void update(Employee emp);
	void delete(Employee emp);
	Employee findByid(int id);
	List<Employee> findAll();
	List<String> getFields();
}
