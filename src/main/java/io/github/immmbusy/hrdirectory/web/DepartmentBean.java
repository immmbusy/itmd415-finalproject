package io.github.immmbusy.hrdirectory.web;

import io.github.immmbusy.hrdirectory.entity.Department;
import io.github.immmbusy.hrdirectory.service.DepartmentService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class DepartmentBean {

    @Inject
    private DepartmentService departmentService;

    public List<Department> getDepartments() {
        return departmentService.findAll();
    }
}