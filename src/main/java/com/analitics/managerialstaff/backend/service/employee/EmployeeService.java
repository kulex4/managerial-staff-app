package com.analitics.managerialstaff.backend.service.employee;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.backend.model.enums.Department;
import com.analitics.managerialstaff.backend.model.enums.Grade;

/**
 * @author by nikolai.pashkevich
 */
public interface EmployeeService {
    void saveOrUpdate(Employee employee);
    void remove(Employee employee);
    Iterable<Employee> findEmployees();
    Iterable<Employee> findByDepartmentAndGrade(Department department, Grade grade);
    boolean isSurnameUnique(String surname);
    boolean isForenameUnique(String forename);
}
