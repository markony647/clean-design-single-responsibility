package com.epam.cleandesign.srp.service;

import com.epam.cleandesign.srp.Employee;
import com.epam.cleandesign.srp.converter.GenericEmployeeConverter;
import com.epam.cleandesign.srp.converter.HtmlEmployeeConverter;
import com.epam.cleandesign.srp.converter.JsonEmployeeConverter;

import java.util.List;

public class EmployeeRepresentationService {

    private GenericEmployeeConverter converter;

    public synchronized String getAllAsHtmlTable(List<Employee> employees) {
        converter = new HtmlEmployeeConverter();
        return converter.convert(employees);
    }

    public synchronized String getAllAsJson(List<Employee> employees) {
        converter = new JsonEmployeeConverter();
        return converter.convert(employees);
    }
}
