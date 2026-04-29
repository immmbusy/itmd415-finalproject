package io.github.immmbusy.hrdirectory.service;

import io.github.immmbusy.hrdirectory.entity.Department;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class DepartmentService {

    @PersistenceContext(unitName = "hrPU")
    private EntityManager em;

    public List<Department> findAll() {
        return em.createQuery(
                "select d from Department d order by d.deptName",
                Department.class
        ).getResultList();
    }
}