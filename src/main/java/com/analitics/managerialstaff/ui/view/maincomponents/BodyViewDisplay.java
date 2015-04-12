package com.analitics.managerialstaff.ui.view.maincomponents;

import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalSplitPanel;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinComponent
public class BodyViewDisplay extends VerticalSplitPanel implements ViewDisplay {

    public static final int HEIGHT = 37;

    @PostConstruct
    private void init() {
        setSplitPosition(HEIGHT, Unit.PIXELS);
        setLocked(true);
        setStyleName(MyTheme.PANEL_BORDERLESS);
        setStyleName(MyTheme.HIDE_SPLITTER);
        setSizeFull();
    }

    public void setMainMenu(Component mainMenu) {
        setFirstComponent(mainMenu);
    }

    @Override
    public void showView(View view) {
        setSecondComponent((Component) view);
    }
}
