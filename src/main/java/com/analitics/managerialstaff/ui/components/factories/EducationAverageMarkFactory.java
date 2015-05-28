package com.analitics.managerialstaff.ui.components.factories;

import com.analitics.managerialstaff.backend.model.enums.EducationType;
import com.analitics.managerialstaff.ui.view.navigations.reporting.analyzing.dto.EducationAverageMarkRow;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class EducationAverageMarkFactory implements Serializable {

    public List<EducationAverageMarkRow> generateTableData() {
        List<EducationAverageMarkRow> result = new ArrayList<>(2);

        EducationAverageMarkRow firstItem = new EducationAverageMarkRow();
        firstItem.setName(EducationType.ECONOMIC.toString());
        firstItem.setNumberOfEmployeesCurrent(272);
        firstItem.setNumberOfEmployeesPrevious(270);
        result.add(firstItem);

        EducationAverageMarkRow secondItem = new EducationAverageMarkRow();
        secondItem.setName(EducationType.TECHNICAL.toString());
        secondItem.setNumberOfEmployeesCurrent(70);
        secondItem.setNumberOfEmployeesPrevious(70);
        result.add(secondItem);

        EducationAverageMarkRow thirdItem = new EducationAverageMarkRow();
        thirdItem.setName(EducationType.HUMANITARIAN.toString());
        thirdItem.setNumberOfEmployeesCurrent(19);
        thirdItem.setNumberOfEmployeesPrevious(19);
        result.add(thirdItem);

        return result;
    }
}
