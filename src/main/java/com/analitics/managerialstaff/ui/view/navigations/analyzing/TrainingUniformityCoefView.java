package com.analitics.managerialstaff.ui.view.navigations.analyzing;

import com.analitics.managerialstaff.ui.view.navigations.analyzing.dto.TrainingUniformityCoefRow;
import com.vaadin.navigator.View;

import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
public interface TrainingUniformityCoefView extends View {
    String NAME = "trainingUniformity";
    void insertData(List<TrainingUniformityCoefRow> rows);
}
