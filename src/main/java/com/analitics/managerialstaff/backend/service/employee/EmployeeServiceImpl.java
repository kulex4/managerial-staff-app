package com.analitics.managerialstaff.backend.service.employee;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.backend.model.enums.Department;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * @author by nikolai.pashkevich
 */
@Transactional
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveOrUpdate(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void remove(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public Iterable<Employee> findEmployees() {
        Iterable<Employee> employees = employeeRepository.findAll();
        employees.forEach(employee -> employee.getEducations().size());
        employees.forEach(employee -> employee.getCertifications().size());
        employees.forEach(employee -> employee.getTrainings().size());
        return employees;
    }

    @Override
    public Iterable<Employee> findByDepartmentAndGrade(Department department, Grade grade) {
        if (department != null && grade != null) {
            Iterable<Employee> employees = employeeRepository.findByDepartmentAndGrade(department, grade);
            employees.forEach(employee -> employee.getEducations().size());
            employees.forEach(employee -> employee.getCertifications().size());
            employees.forEach(employee -> employee.getTrainings().size());
            return employees;
        } else  if (department != null) {
            Iterable<Employee> employees = employeeRepository.findByDepartment(department);
            employees.forEach(employee -> employee.getEducations().size());
            employees.forEach(employee -> employee.getCertifications().size());
            employees.forEach(employee -> employee.getTrainings().size());
            return employees;
        } else if (grade != null) {
            Iterable<Employee> employees = employeeRepository.findByGrade(grade);
            employees.forEach(employee -> employee.getEducations().size());
            employees.forEach(employee -> employee.getCertifications().size());
            employees.forEach(employee -> employee.getTrainings().size());
            return employees;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean isSurnameUnique(String surname) {
        Employee employee = employeeRepository.findBySurname(surname);
        return employee == null;
    }

    @Override
    public boolean isForenameUnique(String forename) {
        Employee employee = employeeRepository.findByForename(forename);
        return employee == null;
    }
}
