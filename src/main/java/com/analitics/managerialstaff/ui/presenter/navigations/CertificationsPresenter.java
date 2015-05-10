package com.analitics.managerialstaff.ui.presenter.navigations;

import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.view.navigations.certification.CertificationsView;
import com.analitics.managerialstaff.ui.view.navigations.commands.CertificationsMenuCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import java.io.Serializable;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class CertificationsPresenter extends AbstractPresenter<CertificationsView> implements Serializable {

    @Autowired
    public CertificationsPresenter(CertificationsView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod
    private void onCertificationsMenuItemSelected(CertificationsMenuCommand certificationsMenuCommand) {

    }
}
