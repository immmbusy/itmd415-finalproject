package io.github.immmbusy.hrdirectory.web;

import io.github.immmbusy.hrdirectory.entity.Employee;
import io.github.immmbusy.hrdirectory.service.EmployeeService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.math.BigDecimal;

@Named
@ViewScoped
public class EmployeeEditBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmployeeService employeeService;

    private Long empId;

    private String title;
    private BigDecimal salary;

    private Employee employee;

    public void load() {
        if (empId == null) {
            employee = null;
            return;
        }

        employee = employeeService.findById(empId);

        // Pre-fill form fields from DB
        if (employee != null) {
            if (title == null) {
                title = employee.getTitle();
            }
            if (salary == null) {
                salary = employee.getSalary();
            }
        }
    }

    public String save() {
        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            if (empId == null) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Missing empId."));
                return null;
            }

            employeeService.updateTitleAndSalary(empId, title, salary);

            // Reload so the view reflects updated DB state
            employee = employeeService.findById(empId);
            if (employee != null) {
                title = employee.getTitle();
                salary = employee.getSalary();
            }

            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Saved", "Employee updated successfully."));
            return null;

        } catch (Exception ex) {
            // Includes: security errors when USER tries to save, validation/conversion issues, etc.
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Save failed", ex.getClass().getSimpleName() + ": " + ex.getMessage()));
            return null;
        }
    }

    public Long getEmpId() { return empId; }
    public void setEmpId(Long empId) { this.empId = empId; }

    public Employee getEmployee() { return employee; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
}