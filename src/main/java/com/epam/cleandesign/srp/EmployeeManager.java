package com.epam.cleandesign.srp;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class EmployeeManager {

    private List<Employee> cache = null;

    public void sendEmployeesReport(Connection connection) {
        String to = "abcd@gmail.com";
        String from = "web@gmail.com";
        String host = "localhost";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Employees report");

            String employeesHtml = getAllEmployeesAsHtml(connection);

            message.setContent(employeesHtml, "text/html; charset=utf-8");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException(e);
        }
    }

    private synchronized String getAllEmployeesAsHtml(Connection connection) {
        List<Employee> employees = readEmployees(connection);

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

    public synchronized String employeesAsJson(Connection connection) {
        List<Employee> employees = readEmployees(connection);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(employees);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Employee> readEmployees(Connection connection) {
        if (cache == null) {
            cache = new ArrayList<>();
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
                    cache.add(employee);
                }
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }

        return cache;
    }
}
