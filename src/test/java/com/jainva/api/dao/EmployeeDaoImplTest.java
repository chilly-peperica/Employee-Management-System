package com.jainva.api.dao;

//import com.jainva.api.configs.TestConfig;

import com.jainva.api.exceptions.EMSException;
import com.jainva.api.exceptions.EmployeeNotFoundException;
import com.jainva.api.mocker.EmployeeMocker;
import com.openapi.gen.springboot.dto.CorporateDetails;
import com.openapi.gen.springboot.dto.CreateEmployeeRequest;
import com.openapi.gen.springboot.dto.Employee;
import com.openapi.gen.springboot.dto.PersonalDetails;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        CreateEmployeeRequest body = EmployeeMocker.createEmployeeMockPayload();
        int res = EmployeeMocker.createMockEmployee(daoImpl, body);
        assertThat(res).isEqualTo(1);

        List<Employee> employees = daoImpl.getAllEmployees();
        employees.stream()
                .filter(employee -> employee.getPersonalDetails().getName().equals(body.getPersonalDetails().getName()))
                .map(employee -> {
                    assertThat(employee.getEmployeeId()).isNotNull();
                    PersonalDetails per = employee.getPersonalDetails();
                    assertThat(per.getAddress()).isNotNull();
                    assertThat(per.getMobileNumber()).isNotNull();
                    assertThat(per.getAddress().getCountry()).isEqualTo(body.getPersonalDetails().getAddress().getCountry());
                    CorporateDetails cd = employee.getCorporateDetails();
                    assertThat(cd.getSalary()).isEqualTo(body.getCorporateDetails().getSalary());
                    return employee;
                });

    }

    @Test
    @DisplayName("Create employee failed to create employee, due to invalid input params")
    void createEmployeeFailed() {
        CreateEmployeeRequest body = EmployeeMocker.createEmployeeMockPayload();
        body.getPersonalDetails().setName(null);
        log.info("Test case is being started here:");
        assertThatThrownBy(() -> {
            daoImpl.createEmployee(body);
        }).isInstanceOf(EMSException.class)
                .hasMessageContaining("Employee name is a mandatory parameter");

    }


    @Test
    @DisplayName("Get Employee from employee-id API is working fine")
    void getEmployeeFromEidWorkingCorrectly() {
        CreateEmployeeRequest body = EmployeeMocker.createEmployeeMockPayload();
        int res = EmployeeMocker.createMockEmployee(daoImpl, body);
        assertThat(res).isEqualTo(1);

        List<Employee> employees = daoImpl.getAllEmployees();
        Employee someEmployee = employees.get(0);
        assertThat(daoImpl.getEmployee(someEmployee.getEmployeeId())).isNotNull();

    }

    @Test
    @DisplayName("Get Employee from employee-id API should fail with employee not found")
    void getEmployeeFromEidShouldFailWithNotFoundException() {
        CreateEmployeeRequest body = EmployeeMocker.createEmployeeMockPayload();
        int res = EmployeeMocker.createMockEmployee(daoImpl, body);
        assertThat(res).isEqualTo(1);

        assertThatThrownBy(()->{
            daoImpl.getEmployee(28910029L);
        }).isInstanceOfAny(EmployeeNotFoundException.class);

    }
}