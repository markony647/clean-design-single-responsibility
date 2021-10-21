package com.epam.cleandesign.srp.converter;

import com.epam.cleandesign.srp.Employee;

import java.util.List;

public class HtmlEmployeeConverter extends GenericEmployeeConverter {
    @Override
    public String convert(List<Employee> employees) {
        return String.valueOf(getHeader()) +
                getRows(employees) +
                getFooter();
    }

    private StringBuilder getRows(List<Employee> employees) {
        StringBuilder rows = new StringBuilder();
        for (Employee employee : employees) {
            rows.append(getRow(employee));
        }
        return rows;
    }

    private StringBuilder getHeader() {
        return new StringBuilder()
                .append("<table>")
                .append("<tr><th>Employee</th><th>Position</th></tr>");
    }

    private StringBuilder getFooter() {
        return new StringBuilder().append("</table>");
    }

    private String getRow(Employee employee) {
        return  "<tr><td>" + employee.getFirstName() + " " + employee.getLastName() +
                "</td><td>" + employee.getSeniority() + " " + employee.getRole() +
                "</td></tr>";
    }
}
