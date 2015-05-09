package com.analitics.managerialstaff.backend.service;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return employeeRepository.findAll();
    }

    @Override
    public Iterable<Employee> findByGrade(Grade grade) {
        return employeeRepository.findByGrade(grade);
    }

    @Override
    public Iterable<Employee> findSpecialists() {
        return employeeRepository.findByGrade(Grade.SPECIALIST);
    }

    @Override
    public Iterable<Employee> findManagers() {
        return employeeRepository.findByGrade(Grade.MANAGER);
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
