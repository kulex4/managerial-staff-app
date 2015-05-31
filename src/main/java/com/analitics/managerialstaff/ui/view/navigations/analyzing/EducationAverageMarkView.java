package com.analitics.managerialstaff.ui.view.navigations.analyzing;

import com.analitics.managerialstaff.ui.view.navigations.analyzing.dto.EducationAverageMarkRow;
import com.vaadin.navigator.View;

import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
public interface EducationAverageMarkView extends View {
    String NAME = "educationAverageMark";
    void insertData(List<EducationAverageMarkRow> averageMarkRows);
}
