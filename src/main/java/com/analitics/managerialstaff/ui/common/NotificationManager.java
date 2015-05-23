package com.analitics.managerialstaff.ui.common;

import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;

import java.io.Serializable;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinComponent
public class NotificationManager implements Serializable {

    public static final int NOTIFICATION_DELAY = 2000;

    public void showNotification(String message, String style) {
        Notification notification = new Notification(message, Notification.Type.HUMANIZED_MESSAGE);
        notification.setDelayMsec(NOTIFICATION_DELAY);
        notification.setStyleName(MyTheme.NOTIFICATION_BAR + " " + style);
        notification.setPosition(Position.TOP_CENTER);
        notification.show(Page.getCurrent());
    }
}
