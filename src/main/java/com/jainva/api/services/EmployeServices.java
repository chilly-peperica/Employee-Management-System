package com.jainva.api.services;

import com.jainva.api.dao.EmployeeDaoImpl;
import com.jainva.api.exceptions.EmployeeNotFoundException;
import com.openapi.gen.springboot.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EmployeServices {

    public final EmployeeDaoImpl dbImpl;

    Logger log = LoggerFactory.getLogger(EmployeServices.class);

    public EmployeServices(EmployeeDaoImpl dbImpl) throws Exception {
        try {
            this.dbImpl = dbImpl;
            init();
        } catch (Exception e) {
            log.info("Error at thus point is : " + e.getMessage() + " with " + e.getStackTrace());
            throw e;
        }
    }


    public void init() throws Exception {
        CreateEmployeeRequest cer1 = new CreateEmployeeRequest();
        Employee e1 = new Employee();
        PersonalDetails p1 = new PersonalDetails();
        CorporateDetails c1 = new CorporateDetails();
        p1.setName("Raghu Ram Rajan");
        p1.setMobileNumber("9238181930");
        c1.setSalary(1000000);
        c1.setJoiningDate(LocalDate.now());
        Address a1 = new Address();
        a1.city("Palo Alto");
        a1.country("USA");
        a1.setState("California");
        a1.setPinCode(94020L);
        p1.setAddress(a1);
        e1.setPersonalDetails(p1);
        e1.setCorporateDetails(c1);
        cer1.setCorporateDetails(c1);
        cer1.setPersonalDetails(p1);
        this.createEmployee(cer1);

        Employee e2 = new Employee();
        CreateEmployeeRequest cer2 = new CreateEmployeeRequest();
        PersonalDetails p2 = new PersonalDetails();
        CorporateDetails c2 = new CorporateDetails();
        p2.setName("Mark Lohemyer");
        p2.setMobileNumber("8243431212");
        c2.setSalary(7000000);
        c2.setJoiningDate(LocalDate.now());
        Address a2 = new Address();
        a2.city("Palo Alto");
        a2.country("USA");
        a2.setState("California");
        a2.setPinCode(94303L);
        p2.setAddress(a1);
        e2.setPersonalDetails(p2);
        e2.setCorporateDetails(c2);
        cer2.setPersonalDetails(p2);
        cer2.setCorporateDetails(c2);

        this.createEmployee(cer2);


    }

    public Employee createEmployee(CreateEmployeeRequest body) throws Exception {
        Employee emp = new Employee();
        try {
            int rowsAffected = dbImpl.createEmployee(body);
            log.info("Transaction completed successfully with rows affected : " + rowsAffected);
        } catch (Exception e) {
            throw new Exception("Failed to push data to db with error " + e.getMessage() + "with stacktrace : " + e.getStackTrace());
        }


        emp.setPersonalDetails(body.getPersonalDetails());
        emp.setCorporateDetails(body.getCorporateDetails());
        CorporateDetails cd = new CorporateDetails();
        log.info("Pushing the user into db : " + emp + " before is : ");
        return emp;
    }

    public List<Employee> getAllEmployees() throws Exception {
        try {
            log.info("Getting all employees into DB");
            return dbImpl.getAllEmployees();
        } catch (Exception e) {
            throw new Exception("Failed to get all employee details due to" + e.getMessage());
        }
    }


    public Employee getEmployee(Long id) throws Exception {
        try {
            log.info("Trying to retrieve the employee with eid: {} from db", id);
            return dbImpl.getEmployee(id);
        } catch (EmployeeNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
}
