package com.analitics.managerialstaff.ui.view.navigations.analyzing;

import com.analitics.managerialstaff.backend.model.Employee;
import com.vaadin.navigator.View;

/**
 * @author by nikolai.pashkevich
 */
public interface ProfessionalProspectsView extends View {
    String NAME = "professionalProspects";
    void setEmployees(Iterable<Employee> employees);
}
