package com.epam.cleandesign.srp;

import com.epam.cleandesign.srp.converter.GenericEmployeeConverter;
import com.epam.cleandesign.srp.converter.HtmlEmployeeConverter;

import java.util.List;

public class HtmlEmployeeTableBuilder {

    private final StringBuilder table;
    private final GenericEmployeeConverter converter;

    public HtmlEmployeeTableBuilder() {
        table = new StringBuilder();
        converter = new HtmlEmployeeConverter();
    }

    public void addHeader() {
        table.append("<table>").append("<tr><th>Employee</th><th>Position</th></tr>");
    }

    public void addFooter() {
        table.append("</table>");
    }

    public void addRows(List<Employee> employees) {
        employees.forEach(this::addRow);
    }

    public String getTable() {
        return table.toString();
    }

    private void addRow(Employee employee) {
        String htmlRow = converter.covert(employee);
        table.append(htmlRow);
    }
}
