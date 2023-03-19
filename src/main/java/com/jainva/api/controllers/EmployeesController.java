package com.jainva.api.controllers;

import com.jainva.api.http.models.CreateEmployeeRequestBody;
import com.jainva.api.models.Employee;
import com.openapi.gen.springboot.api.EmployeesDataApiDelegate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
public class EmployeesController implements EmployeesDataApiDelegate {


    public Map<Integer, Employee> db = Map.ofEntries(
            Map.entry ( 0, new Employee(0, "Raghu",10000000, "California", -1)),
            Map.entry( 1 , new Employee(1, "Mark Lohmer",3618211, "Palo Alto", -1))
    );

    @Override
    public ResponseEntity getEmployeesData(){
        return ResponseEntity.ok("");
    }


    @PostMapping("/api/v1/employee")
    public void createEmployee(@RequestBody CreateEmployeeRequestBody body){
        try{
            System.out.println("Post request to the server");
            Employee emp = new Employee();
            int eid = 0;
            // Protects our DB for employee ID collission
            while(db.containsKey(eid)){
                eid =  (int)(Math.random()*10000);
            }
            emp.setEid(eid);
            emp.setName(body.getName());
            emp.setSalary(body.getSalary());
            emp.setAddress(body.getAddress());
            emp.setManager(emp.getManager());
            System.out.println("Pushing the user into db : "+ emp.getEid() + " before is : "+db.size());
            db.put(eid, emp);

        }catch(Exception e){
            ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/api/v1/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
//        try{
            System.out.println("Get employees api call to server");
            List<Employee>employees = new ArrayList<>() ;

            for(int key : db.keySet()){
                employees.add(db.get(key));
            }
            return ResponseEntity.ok().body(employees);

//        }catch(Exception e){
//            return ResponseEntity.internalServerError()
//                    .body(e.getMessage());
//        }
    }

    @GetMapping("api/v1/response")
    public String response(){
        return "Successful working server";
    }


}
