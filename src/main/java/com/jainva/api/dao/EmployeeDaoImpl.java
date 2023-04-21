package com.jainva.api.dao;

import com.openapi.gen.springboot.dto.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public List<Employee> getAllEmployees(){
        return new ArrayList<>();
    }

    @Override
    public Employee createEmployee(Employee e){
        return e;
    }



}
