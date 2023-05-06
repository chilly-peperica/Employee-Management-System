package com.jainva.api.dao;

//import com.jainva.api.configs.TestConfig;

import com.openapi.gen.springboot.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatList;

@Slf4j
@ExtendWith({SpringExtension.class})
@SpringBootTest
// -> Optionally, you can use @DataJpaTest or @SpringBootTest annotations to configure a test database and load the application context for your tests12.
@ContextConfiguration(classes = {SpringJUnit4ClassRunner.class})
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
    void getAllEmployees() {
        List<Employee> employees = daoImpl.getAllEmployees();
        assertThatList(employees).hasSizeGreaterThan(0);
    }

}