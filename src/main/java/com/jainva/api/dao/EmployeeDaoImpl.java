package com.jainva.api.dao;

import com.jainva.api.utils.DateUtils;
import com.openapi.gen.springboot.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class EmployeeDaoImpl implements EmployeeDao {


    public final JdbcTemplate jdbcTemplate;

    EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            log.info("Retrieving data from DB");
            return jdbcTemplate.query(DataBaseQueries.GET_ALL_EMPLOYEES, (resultSet, i) -> {
                Employee e = new Employee();

                PersonalDetails p = new PersonalDetails();
                p.setName(resultSet.getString("name"));
                Address a = new Address();
                a.setCountry(resultSet.getString("country"));
                a.setPinCode(resultSet.getLong("pin_code"));
                a.setCity(resultSet.getString("city"));
                a.setState(resultSet.getString("state"));

                p.setAddress(a);
                p.setMobileNumber(resultSet.getString("mobile_number"));

                e.setPersonalDetails(p);

                CorporateDetails c = new CorporateDetails();
                c.setSalary(resultSet.getInt("salary"));
                Date date = resultSet.getDate("joining_date");
                c.setJoiningDate(
                        date == null ? null : DateUtils.asLocalDate(date));
                c.setPositionId(resultSet.getInt("position_id"));

                e.setCorporateDetails(c);

                return e;
            });
        } catch (Exception e) {
            log.error("Failed to map data from database with message : {} with trace : {}", e.getMessage(), e.getStackTrace());
        }
        return null;
    }

    @Override
    public int createEmployee(CreateEmployeeRequest body) {
        try {
            PersonalDetails pd = body.getPersonalDetails();
            CorporateDetails cd = body.getCorporateDetails();
            int rowsAffected = jdbcTemplate.update(DataBaseQueries.INSERT_EMPLOYEE,
                    pd.getName(), pd.getAddress().getCountry(),
                    pd.getAddress().getPinCode(), pd.getAddress().getCity(),
                    pd.getAddress().getState(), pd.getMobileNumber(),
                    (double) cd.getSalary(), DateUtils.asDate(cd.getJoiningDate()),
                    cd.getPositionId());
            return rowsAffected;
        } catch (Exception e) {
            throw e;
        }
    }

}
