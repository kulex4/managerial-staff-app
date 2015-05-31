package com.analitics.managerialstaff.ui.view.maincomponents;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class MainView extends VerticalLayout {

    private MVerticalLayout content;

    @PostConstruct
    private void init() {
        setMargin(true);
        setSpacing(true);
        setSizeFull();
        initComponents();
    }

    private void initComponents() {
        content = new MVerticalLayout().withFullHeight().withFullWidth().withMargin(false);

        MVerticalLayout leftSide = new MVerticalLayout().withFullHeight().withFullWidth();
        MVerticalLayout rightSide = new MVerticalLayout().withFullHeight().withFullWidth();

        MHorizontalLayout rootContent = new MHorizontalLayout(
                leftSide,
                content,
                rightSide
        ).withFullWidth().withFullHeight();

        rootContent.setExpandRatio(leftSide, 2);
        rootContent.setExpandRatio(content, 12);
        rootContent.setExpandRatio(rightSide, 2);

        addComponent(rootContent);
    }

    public void setHeader(Component header) {
        content.add(header);
        content.setExpandRatio(header, 1);
    }

    public void setBody(Component body) {
        content.add(body);
        content.setExpandRatio(body, 8);
    }
}
