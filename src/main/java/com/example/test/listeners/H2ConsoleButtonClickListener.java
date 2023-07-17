package com.example.test.listeners;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.UI;

public class H2ConsoleButtonClickListener implements ComponentEventListener<ClickEvent<Button>> {
    private final String CONSOLE_URL = "http://localhost:8080/h2-console";

    @Override
    public void onComponentEvent(ClickEvent<Button> event) {
        UI.getCurrent().getPage().executeJs("window.open('" + CONSOLE_URL + "');");
    }
}
