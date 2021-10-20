package com.epam.cleandesign.srp.service;

import com.epam.cleandesign.srp.Employee;
import com.epam.cleandesign.srp.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class EmployeeRepresentationService {
    private final EmployeeRepository employeeRepository;

    public EmployeeRepresentationService(Connection connection) {
        employeeRepository = new EmployeeRepository(connection);
    }

    public synchronized String getAllAsHtml(Connection connection) {
        List<Employee> employees = employeeRepository.findAll(connection);

        StringBuilder builder = new StringBuilder();
        builder.append("<table>").append("<tr><th>Employee</th><th>Position</th></tr>");

        for (Employee employee : employees) {
            builder.append("<tr><td>").append(employee.getFirstName()).append(" ").append(employee.getLastName())
                    .append("</td><td>").append(employee.getSeniority()).append(" ").append(employee.getRole())
                    .append("</td></tr>");
        }
        builder.append("</table>");
        return builder.toString();
    }

    public synchronized String getAllAsJson(Connection connection) {
        List<Employee> employees = employeeRepository.findAll(connection);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(employees);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
