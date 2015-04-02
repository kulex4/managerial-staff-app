package com.analitics.managerialstaff.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalSplitPanel;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = BodyViewDisplay.VIEW_NAME)
public class BodyViewDisplay extends VerticalSplitPanel implements ViewDisplay {

    public static final String VIEW_NAME = "body";

    @Override
    public void showView(View view) {
        setSecondComponent((Component) view);
    }

}
