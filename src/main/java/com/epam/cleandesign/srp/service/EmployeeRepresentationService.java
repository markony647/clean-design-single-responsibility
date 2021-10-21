package com.epam.cleandesign.srp.service;

import com.epam.cleandesign.srp.Employee;
import com.epam.cleandesign.srp.HtmlEmployeeTableBuilder;
import com.epam.cleandesign.srp.JsonEmployeeBuilder;

import java.util.List;

public class EmployeeRepresentationService {

    public synchronized String getAllAsHtmlTable(List<Employee> employees) {
        HtmlEmployeeTableBuilder tableBuilder = new HtmlEmployeeTableBuilder();
        tableBuilder.addHeader();
        tableBuilder.addRows(employees);
        tableBuilder.addFooter();
        return tableBuilder.getTable();
    }

    public synchronized String getAllAsJson(List<Employee> employees) {
        JsonEmployeeBuilder jsonBuilder = new JsonEmployeeBuilder();
        jsonBuilder.addJsonStart();
        jsonBuilder.addBody(employees);
        jsonBuilder.addJsonEnd();
        return jsonBuilder.getJson();
    }
}
