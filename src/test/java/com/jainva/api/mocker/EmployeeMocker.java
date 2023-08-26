package com.jainva.api.mocker;

import com.jainva.api.dao.EmployeeDaoImpl;
import com.jainva.api.utils.DateUtils;
import com.openapi.gen.springboot.dto.Address;
import com.openapi.gen.springboot.dto.CorporateDetails;
import com.openapi.gen.springboot.dto.CreateEmployeeRequest;
import com.openapi.gen.springboot.dto.PersonalDetails;

import java.util.Date;

public class EmployeeMocker {

    public static CreateEmployeeRequest createEmployeeMockPayload(){
        CreateEmployeeRequest body = new CreateEmployeeRequest();
        PersonalDetails p = new PersonalDetails();
        String name = "Vaibhav Jain";
        String mobNo = "8826421272";
        p.setMobileNumber(mobNo);
        p.setName(name);

        Address a = new Address();
        a.setCity("Delhi");
        a.setState("Delhi");
        Long pinCode = 110085L;
        a.setPinCode(pinCode);
        String country = "India";
        a.setCountry(country);

        p.setAddress(a);

        CorporateDetails c = new CorporateDetails();
        c.setJoiningDate(DateUtils.asLocalDate(new Date(2021, 9, 6)));
        int salary = 2000000;
        c.setSalary(salary);

        body.setCorporateDetails(c);
        body.setPersonalDetails(p);
        return body;
    }
    public  static int createMockEmployee(EmployeeDaoImpl daoImpl, CreateEmployeeRequest body){


        return daoImpl.createEmployee(body);
    }

}
