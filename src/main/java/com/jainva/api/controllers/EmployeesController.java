package com.jainva.api.controllers;

import com.jainva.api.exceptions.EMSException;
import com.jainva.api.services.EmployeServices;
import com.openapi.gen.springboot.api.EmployeesDataApi;
import com.openapi.gen.springboot.dto.CreateEmployeeRequest;
import com.openapi.gen.springboot.dto.Employee;
import com.openapi.gen.springboot.dto.EmployeesData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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
    public ResponseEntity<Void> createEmployee(CreateEmployeeRequest request) {
        if (request.getCorporateDetails().getJoiningDate() == null) {
            request.getCorporateDetails().setJoiningDate(LocalDate.now());
        }
        int responseCode = empServices.createEmployee(request);
        if (responseCode >= 1) {
            log.info("Data inserted in DB successfully");
            return ResponseEntity.ok().build();
        } else if (responseCode == 0) {
            log.info("Data is insert into DB but it was an update call as 0 rows were affected");
            return ResponseEntity.ok().build();
        } else {
            throw new EMSException("Problem Detected in inserting employee");
        }
    }

    @Override
    public ResponseEntity<EmployeesData> getAllEmployeesData() {
        System.out.println("Retrieving all employees from the DB");
        EmployeesData ed = new EmployeesData();
        ed.setEmployees(empServices.getAllEmployees());
        return ResponseEntity.ok().body(ed);
    }

    @Override
    public ResponseEntity<Employee> getEmployee(String employeeId) {
        Long l = Long.parseLong(employeeId);
        Employee e = empServices.getEmployee(l);
        return ResponseEntity.ok().body(e);

    }

    @GetMapping("api/v1/response")
    public String response() {
        return "Successful working server";
    }


}
