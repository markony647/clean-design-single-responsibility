package com.epam.cleandesign.srp.service;

import com.epam.cleandesign.srp.Employee;
import com.epam.cleandesign.srp.converter.EmployeeConverter;
import com.epam.cleandesign.srp.converter.HtmlEmployeeConverter;
import com.epam.cleandesign.srp.converter.JsonEmployeeConverter;

import java.util.List;

public class EmployeeRepresentationService {
    private EmployeeConverter employeeConverter;

    public synchronized String getAllAsHtml(List<Employee> employees) {
        employeeConverter = new HtmlEmployeeConverter();

        StringBuilder builder = new StringBuilder();
        builder.append("<table>").append("<tr><th>Employee</th><th>Position</th></tr>");

        for (Employee employee : employees) {
            String htmlRow = employeeConverter.covert(employee);
            builder.append(htmlRow);
        }

        builder.append("</table>");
        return builder.toString();
    }

    public synchronized String getAllAsJson(List<Employee> employees) {
        employeeConverter = new JsonEmployeeConverter();
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < employees.size(); i++) {
            result.append(employeeConverter.covert(employees.get(i)));
            if (i < employees.size() - 1) {
                result.append(",");
            }
        }

            result.append("]");
        return result.toString();
    }
}
