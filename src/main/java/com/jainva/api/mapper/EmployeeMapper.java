package com.jainva.api.mapper;

import com.jainva.api.utils.DateUtils;
import com.openapi.gen.springboot.dto.Address;
import com.openapi.gen.springboot.dto.CorporateDetails;
import com.openapi.gen.springboot.dto.Employee;
import com.openapi.gen.springboot.dto.PersonalDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee e = new Employee();
        e.setEmployeeId(Long.valueOf(resultSet.getInt("id")));
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

    }
}
