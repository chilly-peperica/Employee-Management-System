package com.jainva.api.dao;

//import com.jainva.api.configs.TestConfig;

import com.jainva.api.utils.DateUtils;
import com.jainva.api.utils.TestUtils;
import com.openapi.gen.springboot.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@ExtendWith({SpringExtension.class})
@SpringBootTest
// -> Optionally, you can use @DataJpaTest or @SpringBootTest annotations to configure a test database and load the application context for your tests12.
//@ContextConfiguration(classes = {SpringJUnit4ClassRunner.class})
class EmployeeDaoImplTest {

    @Autowired
    private EmployeeDaoImpl daoImpl;

    @BeforeEach
    void setUp() {
        log.info("Starting the DAO Impl Testcases");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Get all employees API is working fine")
    void getAllEmployees() {
        List<Employee> employees = daoImpl.getAllEmployees();
        assertThatList(employees).hasSizeGreaterThan(0);
    }

    @Test
    @DisplayName("Create Employee API is working fine")
    void createEmployeeWorkingFine() {
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

        int res = daoImpl.createEmployee(body);
        assertThat(res).isEqualTo(1);

        List<Employee> employees = daoImpl.getAllEmployees();
        employees.stream()
                .filter(employee -> employee.getPersonalDetails().getName().equals(name))
                .map(employee -> {
                    assertThat(employee.getEmployeeId()).isNotNull();
                    PersonalDetails per = employee.getPersonalDetails();
                    assertThat(p.getAddress()).isNotNull();
                    assertThat(p.getMobileNumber()).isNotNull();
                    assertThat(p.getAddress().getCountry()).isEqualTo(country);
                    CorporateDetails cd = employee.getCorporateDetails();
                    assertThat(cd.getSalary()).isEqualTo(salary);
                    return employee;
                });

    }

    @Test
    @DisplayName("Create employee failed to create employee, due to invalid input params")
    void createEmployeeFailed() {
        CreateEmployeeRequest body = TestUtils.mockCreateEmployeeRequest();
        body.getPersonalDetails().setName(null);
        log.info("Test case is being started here:");
        assertThatThrownBy(() -> {
            daoImpl.createEmployee(body);
        }).isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Employee name is a mandatory parameter");


    }

}