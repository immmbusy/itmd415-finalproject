package io.github.immmbusy.hrdirectory.web;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Named
@RequestScoped
public class DbCheckBean {

    @Resource(lookup = "jdbc/hr")
    private DataSource ds;

    public String getDatabaseName() {
        try (Connection c = ds.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("select database()")) {
            rs.next();
            return rs.getString(1);
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public int getDeptCount() {
        try (Connection c = ds.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("select count(*) from Department")) {
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            return -1;
        }
    }

    public int getEmpCount() {
        try (Connection c = ds.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("select count(*) from Employee")) {
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            return -1;
        }
    }
}