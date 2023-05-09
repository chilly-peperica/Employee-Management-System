package com.jainva.api.dao;

import static com.google.common.base.Preconditions.checkNotNull;

import com.jainva.api.mapper.EmployeeMapper;
import com.jainva.api.utils.DateUtils;
import com.openapi.gen.springboot.dto.CorporateDetails;
import com.openapi.gen.springboot.dto.CreateEmployeeRequest;
import com.openapi.gen.springboot.dto.Employee;
import com.openapi.gen.springboot.dto.PersonalDetails;
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
            return jdbcTemplate.query(DataBaseQueries.GET_ALL_EMPLOYEES, new EmployeeMapper());
        } catch (Exception e) {
            log.error("Failed to map data from database with message : {} with trace : {}", e.getMessage(), e.getStackTrace());
        }
        return null;
    }

    @Override
    public Employee getEmployee(Long id) throws EMSException {
        try {
            log.info("Retrieving data from DB for eid: {} ",id);
            Object[] params = new Object[] {id};
            List<Employee> retrievedEmployees = jdbcTemplate.query(DataBaseQueries.RETRIEVE_EMPLOYEE_VIA_ID, params, new EmployeeMapper());
            if (retrievedEmployees != null) {
                if (retrievedEmployees.size() == 1) {
                    return retrievedEmployees.get(0);
                }
                throw new EmployeeNotFoundException(String.format("Found %d entries in DB for eid : %d, hence failing employee retrieval", retrievedEmployees.size(), id));
            }
        } catch (EmployeeNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to map data from database with message : {} with trace : {}", e.getMessage(), e.getStackTrace());
            throw new EMSException("Failed to retrieve user from DB");
        }
        return null;
    }

    @Override
    public int createEmployee(CreateEmployeeRequest body) {
        try {
            nullCheckForMandatoryParams(body);
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


    private void nullCheckForMandatoryParams(CreateEmployeeRequest body) {
        try {
            checkNotNull(body.getCorporateDetails(), "Corporate Details can't be null");
            checkNotNull(body.getPersonalDetails(), "Corporate Details can't be null");
            PersonalDetails pd = body.getPersonalDetails();
            CorporateDetails cd = body.getCorporateDetails();
            checkNotNull(pd.getName(), "Employee name is a mandatory parameter");
            checkNotNull(pd.getMobileNumber(), "Employee contact number is a mandatory parameter");
            checkNotNull(cd.getSalary(), "Employee salary is a mandatory parameter");
        } catch (NullPointerException e) {
            throw e;
        }
    }

}
