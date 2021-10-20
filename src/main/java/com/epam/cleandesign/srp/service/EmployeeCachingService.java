package com.epam.cleandesign.srp.service;

import com.epam.cleandesign.srp.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeCachingService {
    private final List<Employee> cache;

    public EmployeeCachingService() {
        this.cache = new ArrayList<>();
    }

    public boolean isCacheEmpty() {
        return cache.isEmpty();
    }

    public void add(Employee employee) {
        cache.add(employee);
    }

    public List<Employee> getCache() {
        return this.cache;
    }
}
