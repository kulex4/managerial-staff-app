package com.analitics.managerialstaff.ui.view.navigations.reporting;

import com.vaadin.navigator.View;

/**
 * @author by nikolai.pashkevich
 */
public interface AverageAgeView extends View {
    String NAME = "averageAge";
    void generateChart();
}
