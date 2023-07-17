package com.example.test.listeners;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.UI;

public class BackButtonClickListener implements ComponentEventListener<ClickEvent<Button>> {
    private final String BACK_BUTTON_URL = "/";

    @Override
    public void onComponentEvent(ClickEvent<Button> event) {
        UI.getCurrent().getPage().setLocation(BACK_BUTTON_URL);
    }
}
