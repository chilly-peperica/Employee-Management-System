package com.jainva.api.dao;

import com.jainva.api.exceptions.EMSException;
import com.openapi.gen.springboot.dto.CreateEmployeeRequest;
import com.openapi.gen.springboot.dto.Employee;

import java.util.List;

public interface EmployeeDao {

    public List<Employee> getAllEmployees();

    public int createEmployee(CreateEmployeeRequest e);

    public Employee getEmployee(Long id) throws EMSException;
}
