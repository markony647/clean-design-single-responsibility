package com.epam.cleandesign.srp;

import com.epam.cleandesign.srp.converter.GenericEmployeeConverter;
import com.epam.cleandesign.srp.converter.JsonEmployeeConverter;

import java.util.List;

public class JsonEmployeeBuilder {
    private final GenericEmployeeConverter converter;
    private final StringBuilder json;

    public JsonEmployeeBuilder() {
        json = new StringBuilder();
        converter = new JsonEmployeeConverter();
    }

    public String getJson() {
        return json.toString();
    }

    public void addJsonStart() {
        json.append("[");
    }

    public void addBody(List<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            Employee currentEmployee = employees.get(i);
            addEmployee(currentEmployee);
            if (isSeparatorNeeded(employees, i)) {
                addSeparatorIfNeeded();
            }
        }
    }

    private boolean isSeparatorNeeded(List<Employee> employees, int currentIndex) {
        return currentIndex < employees.size() - 1;
    }

    public void addJsonEnd() {
        json.append("]");
    }

    private void addEmployee(Employee employee) {
        String employeeJson = converter.convert(employee);
        json.append(employeeJson);
    }

    private void addSeparatorIfNeeded() {
        json.append(",");
    }
}
