package com.analitics.managerialstaff.backend.service;

import com.analitics.managerialstaff.backend.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
@Transactional
@Component("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findEmployees() {
        return employeeRepository.findAll();
    }
}
