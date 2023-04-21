package com.jainva.api.services;

import com.openapi.gen.springboot.dto.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EmployeServices {


    public Map<UUID, Employee> db;

    EmployeServices() {
        init();
    }

    public void init() {
        Employee e1 = new Employee();
        e1.setEmployeeId(UUID.randomUUID());
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
        e2.setEmployeeId(UUID.randomUUID());
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

        db = new HashMap<>();
        db.put(e1.getEmployeeId(), e1);
        db.put(e2.getEmployeeId(), e2);


    }

    public Employee createEmployee(CreateEmployeeRequest body) {
        Employee emp = new Employee();

        UUID eid = UUID.randomUUID();
        // Protects our DB for employee ID collission
        while (db.containsKey(eid)) {
            eid = UUID.randomUUID();
        }
        emp.setEmployeeId(eid);
        emp.setPersonalDetails(body.getPersonalDetails());
        emp.setCorporateDetails(body.getCorporateDetails());
        CorporateDetails cd = new CorporateDetails();
        System.out.println("Pushing the user into db : " + emp.getEmployeeId() + " before is : " + db.size());
        db.put(eid, emp);
        return emp;
    }

    public List<Employee> getAllEmployees() {
        System.out.println("Get employees api call to server");
        List<Employee> employees = new ArrayList<>();

        for (UUID key : db.keySet()) {
            System.out.println("Getting data for key :" + key + " with value : " + db.get(key));
            employees.add(db.get(key));
        }
        return employees;
    }
}
