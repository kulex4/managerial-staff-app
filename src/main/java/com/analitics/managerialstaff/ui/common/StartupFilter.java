package com.analitics.managerialstaff.ui.common;

import org.vaadin.spring.events.EventBusListenerMethodFilter;

/**
 * @author by nikolai.pashkevich
 */
public class StartupFilter implements EventBusListenerMethodFilter {
    @Override
    public boolean filter(Object payload) {
        boolean result = false;
        if (Action.class.isAssignableFrom(payload.getClass())) {
            Action action = (Action) payload;
            if (action.equals(Action.START)) {
                result = true;
            }
        }
        return result;
    }
}
