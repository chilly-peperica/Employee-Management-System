package com.jainva.api.controllers;

import com.jainva.api.http.models.CreateEmployeeRequestBody;
import com.jainva.api.services.EmployeServices;
import com.openapi.gen.springboot.api.EmployeesDataApiDelegate;
import com.openapi.gen.springboot.dto.EmployeesData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

//****************** SOURCE OF SWAGGER AUTOCODEGEN ******************
//https://github.com/DevProblems/openapigen-swaggerui-springboot/blob/master/pom.xml


//@RestController
@Service
public class EmployeesController implements EmployeesDataApiDelegate {

    private final EmployeServices empServices;

    public EmployeesController(EmployeServices empServices) {
        this.empServices = empServices;
    }

    @PostMapping("/api/v1/employee")
    public void createEmployee(@RequestBody CreateEmployeeRequestBody body) {
        try {
            System.out.println("Post request to the server from controller layer");
            empServices.createEmployee(body);
        } catch (Exception e) {
            ResponseEntity.internalServerError()
                    .body(e.getMessage());
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
