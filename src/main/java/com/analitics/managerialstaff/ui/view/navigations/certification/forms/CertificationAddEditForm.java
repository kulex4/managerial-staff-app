package com.analitics.managerialstaff.ui.view.navigations.certification.forms;

import com.analitics.managerialstaff.backend.model.Certification;
import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.ui.common.forms.EntityForm;

/**
 * @author by nikolai.pashkevich
 */
public interface CertificationAddEditForm extends EntityForm<Certification> {
    void setEmployees(Iterable<Employee> employees);
}
