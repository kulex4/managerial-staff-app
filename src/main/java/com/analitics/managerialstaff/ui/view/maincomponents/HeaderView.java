package com.analitics.managerialstaff.ui.view.maincomponents;

import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class HeaderView extends MHorizontalLayout {

    @PostConstruct
    private void init() {
        Label systemLabel = new Label("<p align=\"center\">Программная поддержка анализа деятельности управленческого персонала</p>");
        systemLabel.setContentMode(ContentMode.HTML);
        systemLabel.setStyleName(MyTheme.LABEL_NO_MARGIN);
        systemLabel.addStyleName(MyTheme.LABEL_HUGE);

        setWidth(100, Unit.PERCENTAGE);
        setHeight(200, Unit.PIXELS);
        addComponent(systemLabel);
    }
}
