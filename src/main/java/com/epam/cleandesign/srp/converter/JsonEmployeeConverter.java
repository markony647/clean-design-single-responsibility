package com.epam.cleandesign.srp.converter;

import com.epam.cleandesign.srp.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonEmployeeConverter extends GenericEmployeeConverter {
    @Override
    public String convert(Employee employee) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(employee);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
