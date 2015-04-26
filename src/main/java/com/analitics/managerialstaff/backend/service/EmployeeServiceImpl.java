package com.analitics.managerialstaff.backend.service;

import com.analitics.managerialstaff.backend.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Iterable<Employee> findEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void remove(Employee employee) {
        employeeRepository.delete(employee);
    }
}
