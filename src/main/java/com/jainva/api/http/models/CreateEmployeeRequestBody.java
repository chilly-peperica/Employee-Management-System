package com.jainva.api.http.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;


public class CreateEmployeeRequestBody {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @JsonProperty("name")
    String name;
    @JsonProperty("salary")
    int salary;
    @JsonProperty("address")
    String address;

}
