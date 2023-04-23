package com.jainva.api.dao;

import com.jainva.api.utils.DateUtils;
import com.openapi.gen.springboot.dto.CorporateDetails;
import com.openapi.gen.springboot.dto.CreateEmployeeRequest;
import com.openapi.gen.springboot.dto.Employee;
import com.openapi.gen.springboot.dto.PersonalDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {


    public final JdbcTemplate jdbcTemplate;

    EmployeeDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate  = jdbcTemplate;
    }
    @Override
    public List<Employee> getAllEmployees(){
        return new ArrayList<>();
    }

    @Override
    public int createEmployee(CreateEmployeeRequest body)
    {
        try {
            PersonalDetails pd = body.getPersonalDetails();
            CorporateDetails cd = body.getCorporateDetails();
            int rowsAffected = jdbcTemplate.update(DataBaseQueries.INSERT_EMPLOYEE,
                    pd.getName(), pd.getAddress().getCountry(),
                    pd.getAddress().getPinCode(), pd.getAddress().getCity(),
                    pd.getAddress().getState(), pd.getMobileNumber(),
                    (double) cd.getSalary(), DateUtils.asDate(cd.getJoining()),
                    cd.getPositionId());
            return rowsAffected;
        } catch (Exception e) {
            throw e;
        }
    }

}
