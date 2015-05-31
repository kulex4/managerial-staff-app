package com.analitics.managerialstaff.backend.service.employee;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.backend.model.enums.Department;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    @Query("FROM Employee WHERE surname = ?1 ORDER BY surname ASC")
    Employee findBySurname(String surname);

    @Query("FROM Employee WHERE forename = ?1 ORDER BY forename ASC")
    Employee findByForename(String forename);

    @Query("FROM Employee WHERE grade = ?1 ORDER BY forename ASC")
    Iterable<Employee> findByGrade(Grade grade);

    @Query("FROM Employee WHERE department = ?1 ORDER BY forename ASC")
    Iterable<Employee> findByDepartment(Department department);

    @Query("FROM Employee WHERE department = ?1 AND grade = ?2 ORDER BY forename ASC")
    Iterable<Employee> findByDepartmentAndGrade(Department department, Grade grade);
}
