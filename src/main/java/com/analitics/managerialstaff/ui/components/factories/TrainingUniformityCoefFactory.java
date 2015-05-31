package com.analitics.managerialstaff.ui.components.factories;

import com.analitics.managerialstaff.ui.view.navigations.analyzing.dto.TrainingUniformityCoefRow;
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
public class TrainingUniformityCoefFactory implements Serializable {

    public List<TrainingUniformityCoefRow> generateTableData() {
        List<TrainingUniformityCoefRow> result = new ArrayList<>(3);

        TrainingUniformityCoefRow firstItem = new TrainingUniformityCoefRow();
        firstItem.setName("1");
        firstItem.setNumberOfEmployees(54);
        result.add(firstItem);

        TrainingUniformityCoefRow secondItem = new TrainingUniformityCoefRow();
        secondItem.setName("2");
        secondItem.setNumberOfEmployees(113);
        result.add(secondItem);

        TrainingUniformityCoefRow thirdItem = new TrainingUniformityCoefRow();
        thirdItem.setName("3");
        thirdItem.setNumberOfEmployees(65);
        result.add(thirdItem);

        TrainingUniformityCoefRow fourthItem = new TrainingUniformityCoefRow();
        fourthItem.setName("4");
        fourthItem.setNumberOfEmployees(80);
        result.add(fourthItem);

        return result;
    }
}
