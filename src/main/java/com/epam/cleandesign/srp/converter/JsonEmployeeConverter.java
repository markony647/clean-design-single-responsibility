package com.epam.cleandesign.srp.converter;

import com.epam.cleandesign.srp.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonEmployeeConverter extends GenericEmployeeConverter {
    @Override
    public String convert(List<Employee> employees) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(employees);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
