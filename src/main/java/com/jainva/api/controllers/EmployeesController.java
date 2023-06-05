package com.jainva.api.controllers;

import com.jainva.api.exceptions.EmployeeNotFoundException;
import com.jainva.api.exceptions.RestException;
import com.jainva.api.services.EmployeServices;
import com.openapi.gen.springboot.api.EmployeesDataApi;
import com.openapi.gen.springboot.dto.CreateEmployeeRequest;
import com.openapi.gen.springboot.dto.Employee;
import com.openapi.gen.springboot.dto.EmployeesData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;

//****************** SOURCE OF SWAGGER AUTOCODEGEN ******************
//https://github.com/DevProblems/openapigen-swaggerui-springboot/blob/master/pom.xml


@RestController
@Slf4j
public class EmployeesController implements EmployeesDataApi {

    private final EmployeServices empServices;

    @Autowired
    public EmployeesController(EmployeServices empServices) {
        this.empServices = empServices;
    }


    @Override
    public ResponseEntity<Employee> createEmployee(CreateEmployeeRequest request) throws Exception {
        try {
            if (request.getCorporateDetails().getJoiningDate() == null) {
                request.getCorporateDetails().setJoiningDate(LocalDate.now());
            }
            return ResponseEntity.ok().body(empServices.createEmployee(request));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ResponseEntity<EmployeesData> getAllEmployeesData() throws Exception {
        System.out.println("Retrieving all employees from the DB");
        try {
            EmployeesData ed = new EmployeesData();
            ed.setEmployees(empServices.getAllEmployees());
            return ResponseEntity.ok().body(ed);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ResponseEntity<Employee> getEmployee(String employeeId) throws Exception {
        try {
            Long l = Long.parseLong(employeeId);
            Employee e = empServices.getEmployee(l);
            return ResponseEntity.ok().body(e);
        } catch (Exception e) {
            log.error("Failed to retrieve employee due to error msg : {}", e.getMessage());
            throw e;
        }

    }

    @GetMapping("api/v1/response")
    public String response() {
        return "Successful working server";
    }


}
