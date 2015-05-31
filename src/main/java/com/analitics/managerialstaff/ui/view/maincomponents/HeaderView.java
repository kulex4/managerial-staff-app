package com.analitics.managerialstaff.ui.view.maincomponents;

import com.vaadin.server.ClassResource;
import com.vaadin.ui.Image;
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
        setWidth(100, Unit.PERCENTAGE);
        setHeight(200, Unit.PIXELS);

        Image image = new Image(null, new ClassResource("/img/banner4.png"));
        addComponent(image);
    }
}
