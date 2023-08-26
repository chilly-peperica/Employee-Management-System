package com.jainva.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jainva.api.exceptions.EMSException;
import com.jainva.api.services.EmployeServices;
import com.jainva.api.utils.UrlUtils;
import com.openapi.gen.springboot.dto.CorporateDetails;
import com.openapi.gen.springboot.dto.CreateEmployeeRequest;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import javax.servlet.ServletException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.*;

@EnableConfigurationProperties

@Import({EmployeServices.class})
class EmployeesControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;
    @Mock
    EmployeServices employeServices;


    @InjectMocks
    EmployeesController employeesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeesController).build();
    }

    @AfterEach
    void tearDown() {
    }

    public CreateEmployeeRequest mockCreateEmployeeRequest() {
        CreateEmployeeRequest request = new CreateEmployeeRequest();
        CorporateDetails cd = new CorporateDetails();
        request.setCorporateDetails(cd);

        return request;
    }

    @Test
    @Description("Test 200 response of create employees API")
    void createEmployee() throws Exception {

        CreateEmployeeRequest payload = mockCreateEmployeeRequest();
        String payloadInJson = objectMapper.writeValueAsString(payload);


        when(employeServices.createEmployee(any(CreateEmployeeRequest.class))).thenReturn(1);
        mockMvc.perform(
                        post(UrlUtils.CREATE_EMPLOYEE).contentType(MediaType.APPLICATION_JSON)
                                .content(payloadInJson))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Description("Test 200 response of updated employees API")
    void updateEmployee() throws Exception {

        CreateEmployeeRequest payload = mockCreateEmployeeRequest();
        String payloadInJson = objectMapper.writeValueAsString(payload);


        when(employeServices.createEmployee(any(CreateEmployeeRequest.class))).thenReturn(0);
        mockMvc.perform(
                        post(UrlUtils.CREATE_EMPLOYEE).contentType(MediaType.APPLICATION_JSON)
                                .content(payloadInJson))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    @Description("Test create employee API to be failing if DB insertion returns invalid responses silently")
    void createEmployeeFailingDueToSomeErrors() throws Exception {

        CreateEmployeeRequest payload = mockCreateEmployeeRequest();
        String payloadInJson = objectMapper.writeValueAsString(payload);


        when(employeServices.createEmployee(any(CreateEmployeeRequest.class))).thenReturn(-1);

        assertThatThrownBy(() -> {

            mockMvc.perform(
                    post(UrlUtils.CREATE_EMPLOYEE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(payloadInJson)

            ).andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }).hasRootCauseInstanceOf(EMSException.class);


    }


    @Test
    void getAllEmployeesData() {
    }

    @Test
    void response() {
    }


}