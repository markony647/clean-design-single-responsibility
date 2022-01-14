package com.epam.cleandesign.srp.converter;

import com.epam.cleandesign.srp.Employee;

import java.util.List;

public abstract class GenericEmployeeConverter {

    public abstract String convert(List<Employee> employees);
}
