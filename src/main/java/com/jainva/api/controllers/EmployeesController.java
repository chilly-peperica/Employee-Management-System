package com.jainva.api.controllers;

import com.jainva.api.services.EmployeServices;
import com.openapi.gen.springboot.api.EmployeesDataApi;
import com.openapi.gen.springboot.dto.CreateEmployeeRequest;
import com.openapi.gen.springboot.dto.Employee;
import com.openapi.gen.springboot.dto.EmployeesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

//****************** SOURCE OF SWAGGER AUTOCODEGEN ******************
//https://github.com/DevProblems/openapigen-swaggerui-springboot/blob/master/pom.xml


@RestController
public class EmployeesController implements EmployeesDataApi {

    private final EmployeServices empServices;

    @Autowired
    public EmployeesController(EmployeServices empServices) {
        this.empServices = empServices;
    }


    @Override
    public ResponseEntity<Employee> createEmployee(CreateEmployeeRequest request) {
        try {
            if (request.getCorporateDetails().getJoiningDate() == null) {
                request.getCorporateDetails().setJoiningDate(LocalDate.now());
            }
            return ResponseEntity.ok().body(empServices.createEmployee(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }
    }

    @Override
    public ResponseEntity<EmployeesData> getAllEmployeesData() {
        System.out.println("Controller layer for get all employees");
        try {
            EmployeesData ed = new EmployeesData();
            ed.setEmployees(empServices.getAllEmployees());
            return ResponseEntity.ok().body(ed);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }
    }

    @GetMapping("api/v1/response")
    public String response() {
        return "Successful working server";

    }


}
