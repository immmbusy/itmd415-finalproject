# Deployment Guide (Payara)

## 1) Database Setup (MySQL)
Create the database and seed data (example schema name used by this project: `itmd415_hr`).

Database Scheme:
```sql 
-- =============================================
-- HR Database Setup for ITMD415 Project
-- Database: itmd415_hr
-- =============================================

CREATE DATABASE IF NOT EXISTS `itmd415-hr`;
USE `itmd415-hr`;
-- create tables + insert data
-- then create tables + insert

-- =============================================
-- 1. Drop existing tables (Employee first because of Foreign Key)
-- =============================================
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Department;

-- =============================================
-- 2. Create Department Table
-- =============================================
CREATE TABLE Department (
                            dept_id    BIGINT PRIMARY KEY,
                            dept_name  VARCHAR(100) NOT NULL UNIQUE
);

-- =============================================
-- 3. Create Employee Table
-- =============================================
CREATE TABLE Employee (
                          emp_id      BIGINT PRIMARY KEY,
                          full_name   VARCHAR(200) NOT NULL,
                          title       VARCHAR(100) NOT NULL,
                          dept_id     BIGINT NOT NULL,
                          salary      DECIMAL(10,2) NOT NULL,
                          hire_date   DATE,
                          CONSTRAINT fk_employee_department
                              FOREIGN KEY (dept_id) REFERENCES Department(dept_id)
);

-- =============================================
-- 4. Insert Sample Departments (6 departments)
-- =============================================
INSERT INTO Department (dept_id, dept_name) VALUES
                                                (1, 'Information Technology'),
                                                (2, 'Human Resources'),
                                                (3, 'Finance & Accounting'),
                                                (4, 'Sales & Marketing'),
                                                (5, 'Operations'),
                                                (6, 'Research & Development');

-- =============================================
-- 5. Insert Sample Employees (20 employees)
-- =============================================
INSERT INTO Employee (emp_id, full_name, title, dept_id, salary, hire_date) VALUES

-- IT Department
(101, 'Alice Smith',        'Senior Software Engineer', 1, 105000.00, '2021-03-15'),
(102, 'Brian Chen',         'Systems Administrator',    1,  82000.00, '2022-06-01'),
(103, 'Carla Rodriguez',    'Database Administrator',   1,  95000.00, '2020-11-10'),
(104, 'David Kim',          'Help Desk Technician',     1,  52000.00, '2023-09-05'),

-- HR Department
(201, 'Emma Wilson',        'HR Director',              2,  98000.00, '2019-04-20'),
(202, 'Frank Thompson',     'Talent Acquisition Manager',2,  76000.00, '2021-08-12'),
(203, 'Grace Martinez',     'HR Coordinator',           2,  48000.00, '2022-02-28'),

-- Finance Department
(301, 'Henry Patel',        'Chief Financial Officer',  3, 135000.00, '2018-01-10'),
(302, 'Isabella Garcia',    'Senior Accountant',        3,  78000.00, '2020-05-15'),
(303, 'James Brown',        'Financial Analyst',        3,  72000.00, '2022-11-01'),

-- Sales & Marketing
(401, 'Liam Anderson',      'Sales Director',           4, 115000.00, '2017-09-22'),
(402, 'Mia Johnson',        'Marketing Manager',        4,  85000.00, '2021-07-10'),
(403, 'Noah Williams',      'Sales Representative',     4,  65000.00, '2023-03-18'),

-- Operations
(501, 'Olivia Taylor',      'Operations Manager',       5,  92000.00, '2019-12-05'),
(502, 'William Clark',      'Logistics Coordinator',    5,  58000.00, '2022-04-12'),

-- Research & Development
(601, 'Sophia Lewis',       'Research Scientist',       6,  98000.00, '2020-10-01'),
(602, 'Lucas Walker',       'Product Development Lead', 6, 112000.00, '2021-05-20'),
(603, 'Ava Hall',           'Junior Developer',         6,  68000.00, '2023-08-15');
```


Verify:
```sql
USE `jdbc/hr`;

SELECT COUNT(*) AS dept_count FROM Department;
SELECT COUNT(*) AS emp_count  FROM Employee;

SELECT * FROM Department ORDER BY dept_id;
SELECT * FROM Employee ORDER BY emp_id;
```

---