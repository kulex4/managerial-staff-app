package com.analitics.managerialstaff.ui.common.forms;

import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;
import org.vaadin.spring.events.EventBus;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author by nikolai.pashkevich
 */
public abstract class EntityFormImpl<ENTITY> extends AbstractForm<ENTITY> implements EntityForm<ENTITY> {

    protected EventBus.UIEventBus eventBus;

    public EntityFormImpl(EventBus.UIEventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public Window openInModalPopup() {
        Window popup = super.openInModalPopup();
        popup.setClosable(false);
        popup.setResizable(false);
        this.adjustResetButtonState();
        this.adjustSaveButtonState();
        return popup;
    }

    @Override
    public HorizontalLayout getToolbar() {
        MHorizontalLayout result = new MHorizontalLayout().withFullWidth();
        result.addStyleName(MyTheme.WINDOW_BOTTOM_TOOLBAR);
        MHorizontalLayout placeHolder = new MHorizontalLayout();
        result.addComponents(placeHolder, getSaveButton(), getResetButton());
        result.setExpandRatio(placeHolder, 1);
        return result;
    }
}
