package com.epam.cleandesign.srp.service;

import com.epam.cleandesign.srp.Employee;
import com.epam.cleandesign.srp.HtmlTableBuilder;
import com.epam.cleandesign.srp.converter.EmployeeConverter;
import com.epam.cleandesign.srp.converter.HtmlEmployeeConverter;
import com.epam.cleandesign.srp.converter.JsonEmployeeConverter;

import java.util.List;

public class EmployeeRepresentationService {
    private EmployeeConverter employeeConverter;

    public synchronized String getAllAsHtmlTable(List<Employee> employees) {
        employeeConverter = new HtmlEmployeeConverter();

        HtmlTableBuilder tableBuilder = new HtmlTableBuilder();
        tableBuilder.addHeader();

        for (Employee employee : employees) {
            String employeeHtml = employeeConverter.covert(employee);
            tableBuilder.addRow(employeeHtml);
        }
        tableBuilder.addFooter();
        return tableBuilder.getTable();
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

    private StringBuilder addHeader() {
        return new StringBuilder()
                .append("<table>")
                .append("<tr><th>Employee</th><th>Position</th></tr>");
    }

    private StringBuilder addFooter() {
        return new StringBuilder()
                .append("</table>");
    }

    private StringBuilder addRow(Employee employee) {
        return new StringBuilder().append(employeeConverter.covert(employee));
    }
}
