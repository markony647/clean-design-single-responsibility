package com.epam.cleandesign.srp.repository;

import com.epam.cleandesign.srp.Employee;
import com.epam.cleandesign.srp.EmployeeRole;
import com.epam.cleandesign.srp.EmployeeSeniority;
import com.epam.cleandesign.srp.service.EmployeeCachingService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EmployeeRepository {
    private final EmployeeCachingService employeeCachingService;
    private final Connection connection;

    public EmployeeRepository(Connection connection) {
        employeeCachingService = new EmployeeCachingService();
        this.connection = connection;
    }

    public List<Employee> findAll() {
        if (employeeCachingService.isCacheEmpty()) {
            return retrieveFromStorage();
        }
        return employeeCachingService.getCache();
    }

    private List<Employee> retrieveFromStorage() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Employees")) {

            while (resultSet.next()) {

                Employee employee = new Employee();

                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                String role = resultSet.getString("ROLE");
                String seniority = resultSet.getString("SENIORITY");

                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setRole(EmployeeRole.valueOf(role));
                employee.setSeniority(EmployeeSeniority.valueOf(seniority));
                employeeCachingService.add(employee);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return employeeCachingService.getCache();
    }
}
