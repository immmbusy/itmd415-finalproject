package io.github.immmbusy.hrdirectory.service;


import io.github.immmbusy.hrdirectory.entity.Employee;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Stateless
public class EmployeeService {

    @PersistenceContext(unitName = "hrPU")
    private EntityManager em;

    // Requirement #2 + #3 (filter + search using JPQL parameters)
    public List<Employee> findEmployees(Long deptId, String q) {
        String term = (q == null) ? "" : q.trim().toLowerCase();

        return em.createQuery("""
            select e
            from Employee e
            join fetch e.department d
            where (:deptId is null or d.deptId = :deptId)
              and (:term = '' or lower(e.fullName) like :likeTerm or lower(e.title) like :likeTerm)
            order by e.fullName
        """, Employee.class)
                .setParameter("deptId", deptId)
                .setParameter("term", term)
                .setParameter("likeTerm", "%" + term + "%")
                .getResultList();
    }

    public Employee findById(Long empId) {
        try {
            return em.createQuery("""
                select e from Employee e
                join fetch e.department
                where e.empId = :id
            """, Employee.class)
                    .setParameter("id", empId)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    // Requirement #4 (ADMIN-only write)
    @RolesAllowed("ADMIN")
    public void updateTitleAndSalary(Long empId, String title, BigDecimal salary) {
        Employee e = em.find(Employee.class, empId);
        if (e == null) throw new IllegalArgumentException("Employee not found: " + empId);

        e.setTitle(title);
        e.setSalary(salary);
    }
}