package com.analitics.managerialstaff.backend.service;

import com.analitics.managerialstaff.backend.model.Employee;

import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
public interface EmployeeService {
    List<Employee> findEmployees();
}
