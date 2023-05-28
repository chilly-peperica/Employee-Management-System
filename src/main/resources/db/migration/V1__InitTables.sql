-- STORAGE COMPLEXITY OF THE EMPLOYEES TABLE :
--employee_id: 16 bytes for a UUID data type
--emp_name: 255 bytes for a VARCHAR(255) data type
--country: 50 bytes for a VARCHAR(50) data type
--pin_code: 8 bytes for a REAL data type
--city: 100 bytes for a VARCHAR(100) data type
--national_state: 50 bytes for a VARCHAR(50) data type
--mobile_number: 10 bytes for a VARCHAR(10) data type
--salary: 15 bytes for a DECIMAL(15,2) data type
--joining_date: 4 bytes for a DATE data type
--postition_id: 16 bytes for a UUID data type
--16 + 255 + 50 + 8 + 100 + 50 + 10 + 15 + 4 + 16 = 524 bytes

 CREATE SEQUENCE employee_id_seq START WITH 100000;

--NOTE THAT ORDER WHICH WE SEPCIFY WHILE MENTIONING PROpeRtIES FOR id
--COLUMNS IS IMP WHLIE USING H2 DB : https://groups.google.com/g/h2-database/c/XvVHoFAVKrA
 create TABLE employees(
     id integer  NOT NULL DEFAULT nextval('employee_id_seq') PRIMARY KEY,
 --   Personal Details:
     name varchar(255),
 --      Adresses
         country varchar(50),
         pin_code integer,
         city varchar(100),
         state varchar(50),
 --  --
     mobile_number varchar(10),
 --    CORPORATE DETAILS
     salary DECIMAL(15,2),
     joining_date DATE ,
     position_id integer

 );