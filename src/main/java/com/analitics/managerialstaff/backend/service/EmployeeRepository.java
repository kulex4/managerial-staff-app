package com.analitics.managerialstaff.backend.service;

import com.analitics.managerialstaff.backend.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();
}
