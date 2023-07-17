package com.example.test.listeners;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.textfield.TextField;
import com.example.test.service.ValueService;

public class IncreaseButtonClickListener implements ComponentEventListener<ClickEvent<Button>> {

    private final ValueService valueService;
    private final TextField textField;

    public IncreaseButtonClickListener(ValueService valueService, TextField textField) {
        this.valueService = valueService;
        this.textField = textField;
    }

    @Override
    public void onComponentEvent(ClickEvent<Button> event) {
        int value = Integer.parseInt(textField.getValue());
        value++;
        textField.setValue(String.valueOf(value));
        valueService.setValue(value);
    }
}
