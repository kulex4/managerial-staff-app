package com.analitics.managerialstaff.backend.service;

import com.analitics.managerialstaff.backend.model.Employee;

/**
 * @author by nikolai.pashkevich
 */
public interface EmployeeService {
    void saveOrUpdate(Employee employee);
    Iterable<Employee> findEmployees();
    void remove(Employee employee);
}
