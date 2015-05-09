package com.analitics.managerialstaff.backend.service;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.backend.model.enums.Grade;

/**
 * @author by nikolai.pashkevich
 */
public interface EmployeeService {
    void saveOrUpdate(Employee employee);
    void remove(Employee employee);
    Iterable<Employee> findEmployees();
    Iterable<Employee> findByGrade(Grade grade);
    Iterable<Employee> findSpecialists();
    Iterable<Employee> findManagers();
    boolean isSurnameUnique(String surname);
    boolean isForenameUnique(String forename);
}
