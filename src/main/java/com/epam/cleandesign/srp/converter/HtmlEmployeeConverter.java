package com.epam.cleandesign.srp.converter;

import com.epam.cleandesign.srp.Employee;

public class HtmlEmployeeConverter extends GenericEmployeeConverter {
    @Override
    public String covert(Employee employee) {
        return  "<tr><td>" + employee.getFirstName() + " " + employee.getLastName() +
                "</td><td>" + employee.getSeniority() + " " + employee.getRole() +
                "</td></tr>";
    }
}
