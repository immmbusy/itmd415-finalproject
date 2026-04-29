package io.github.immmbusy.hrdirectory.web;

import io.github.immmbusy.hrdirectory.entity.Department;
import io.github.immmbusy.hrdirectory.entity.Employee;
import io.github.immmbusy.hrdirectory.service.DepartmentService;
import io.github.immmbusy.hrdirectory.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EmployeeListBean implements Serializable {

    @Inject
    private EmployeeService employeeService;

    @Inject
    private DepartmentService departmentService;

    private Long deptId;
    private String q = "";

    @PostConstruct
    public void init() {
        // Optional: you can read URL parameters here too if needed
    }

    public List<Department> getDepartments() {
        return departmentService.findAll();
    }

    public List<Employee> getEmployees() {
        return employeeService.findEmployees(deptId, q);
    }

    public Long getDeptId() { return deptId; }
    public void setDeptId(Long deptId) { this.deptId = deptId; }

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }
}