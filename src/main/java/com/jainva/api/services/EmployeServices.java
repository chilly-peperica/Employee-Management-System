package com.jainva.api.services;

import com.openapi.gen.springboot.dto.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EmployeServices {


    public List<Employee> db = new ArrayList<>();

    EmployeServices() {
        try {
            init();
        }catch (Exception e){
            System.out.println(e.getMessage()+ " with stack trace "+ e.getStackTrace());
        }

    }

    public void init() {
        Employee e1 = new Employee();
        PersonalDetails p1 = new PersonalDetails();
        CorporateDetails c1 = new CorporateDetails();
        p1.setName("Raghu Ram Rajan");
        p1.setMobileNumber("9238181930");
        c1.setSalary(1000000);
        Address a1 = new Address();
        a1.city("Palo Alto");
        a1.country("USA");
        a1.setState("California");
        a1.setPinCode(94020L);
        p1.setAddress(a1);
        e1.setPersonalDetails(p1);
        e1.setCorporateDetails(c1);

        Employee e2 = new Employee();
        PersonalDetails p2 = new PersonalDetails();
        CorporateDetails c2 = new CorporateDetails();
        p2.setName("Mark Lohemyer");
        c2.setSalary(7000000);
        Address a2 = new Address();
        a2.city("Palo Alto");
        a2.country("USA");
        a2.setState("California");
        a2.setPinCode(94303L);
        p2.setAddress(a1);
        e2.setPersonalDetails(p2);
        e2.setCorporateDetails(c2);

        db.add( e1);
        db.add(e2);


    }

    public Employee createEmployee(CreateEmployeeRequest body) {
        Employee emp = new Employee();

//        UUID eid = UUID.randomUUID();
//        // Protects our DB for employee ID collission
//        while (db.containsKey(eid)) {
//            eid = UUID.randomUUID();
//        }
//        emp.setEmployeeId(eid);
        emp.setPersonalDetails(body.getPersonalDetails());
        emp.setCorporateDetails(body.getCorporateDetails());
        CorporateDetails cd = new CorporateDetails();
        System.out.println("Pushing the user into db : " + emp + " before is : " + db.size());
        db.add(emp);
        return emp;
    }

    public List<Employee> getAllEmployees() {
        System.out.println("Get employees api call to server");
        List<Employee> employees = new ArrayList<>();

        for (Employee e : db) {
            System.out.println("Getting employee : " + e);;
        }
        return db;
    }
}
