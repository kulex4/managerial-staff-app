package com.analitics.managerialstaff.ui.view.navigations.employees.validators;

import com.analitics.managerialstaff.backend.service.EmployeeService;
import com.vaadin.data.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
public class EmployeeForenameValidator implements Validator {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void validate(Object value) throws Validator.InvalidValueException {
        if (value == null) {
            return;
        }

        if (!employeeService.isForenameUnique((String) value)) {
            throw new Validator.InvalidValueException("Фамилия должна быть уникальной");
        }
    }
}
