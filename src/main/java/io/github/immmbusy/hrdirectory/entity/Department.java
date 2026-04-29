package io.github.immmbusy.hrdirectory.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Department")
public class Department {

    @Id
    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "dept_name", nullable = false, unique = true)
    private String deptName;

    public Long getDeptId() { return deptId; }
    public void setDeptId(Long deptId) { this.deptId = deptId; }

    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
}