package com.jainva.api.models;

public class Employee {

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

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

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    int eid;
    String name;
    int salary;
    String address;
    int manager;

    public Employee(){

    }
    public Employee(int id, String name, int sal, String addr, int manager){
        this.eid = id;
        this.name = name;
        this.salary = sal;
        this.address = addr;
        this.manager = manager;
    }


}
