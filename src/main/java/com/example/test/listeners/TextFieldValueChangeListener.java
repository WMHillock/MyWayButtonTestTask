package com.example.test.listeners;

import com.vaadin.flow.component.HasValue;
import com.example.test.service.ValueService;
import com.vaadin.flow.data.binder.Binder;

public class TextFieldValueChangeListener implements HasValue.ValueChangeListener<HasValue.ValueChangeEvent<String>> {

    private final Binder<Integer> binder;
    private final ValueService valueService;

    public TextFieldValueChangeListener(Binder<Integer> binder, ValueService valueService) {
        this.binder = binder;
        this.valueService = valueService;
    }

    @Override
    public void valueChanged(HasValue.ValueChangeEvent<String> event) {
        String fieldValue = event.getValue();
        int value = Integer.parseInt(fieldValue);
        binder.setBean(value);
        valueService.setValue(value);
    }
}
