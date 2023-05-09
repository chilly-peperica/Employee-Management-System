package com.jainva.api.dao;

public final class  DataBaseQueries {

    public static String INSERT_EMPLOYEE =
            """
                    INSERT INTO employees(name, 
                    country, 
                    pin_code, 
                    city,
                    state, 
                    mobile_number, 
                    salary, 
                    joining_date, 
                    position_id)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
            
            """;
    public static String GET_ALL_EMPLOYEES =
            """
                    SELECT * FROM employees;
            """;

    public static String RETRIEVE_EMPLOYEE_VIA_ID =
            """
                    SELECT * FROM employees WHERE id = ?;
            """;
}
