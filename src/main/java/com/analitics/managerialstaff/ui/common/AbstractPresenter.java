package com.analitics.managerialstaff.ui.common;

import org.springframework.util.Assert;
import org.vaadin.spring.events.EventBus;

/**
 * Abstract Presenter
 * http://blog.frankel.ch/improving-the-vaadin-4-spring-project-with-a-simpler-mvp
 *
 * @param <T> view
 */
public abstract class AbstractPresenter<T> {
    private final T view;
    private final EventBus.UIEventBus eventBus;

    public AbstractPresenter(T view, EventBus.UIEventBus eventBus) {
        Assert.notNull(view);
        Assert.notNull(eventBus);
        this.view = view;
        this.eventBus = eventBus;
        eventBus.subscribe(this);
    }

    public T getView() {
        return view;
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}