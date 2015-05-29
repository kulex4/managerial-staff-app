package com.analitics.managerialstaff.ui.view.navigations.reporting.analyzing;

import com.analitics.managerialstaff.ui.view.navigations.reporting.analyzing.dto.TrainingUniformityCoefRow;
import com.vaadin.navigator.View;

import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
public interface TrainingUniformityCoefView extends View {
    String NAME = "trainingUniformity";
    void insertData(List<TrainingUniformityCoefRow> rows);
}
