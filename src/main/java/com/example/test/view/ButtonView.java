package com.example.test.view;

import com.example.test.entity.ValueEntity;
import com.example.test.listeners.BackButtonClickListener;
import com.example.test.listeners.H2ConsoleButtonClickListener;
import com.example.test.listeners.IncreaseButtonClickListener;
import com.example.test.listeners.TextFieldValueChangeListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.Route;
import com.example.test.service.ValueService;

@Route("/button")
@CssImport("./styles/custom-styles.css")
public class ButtonView extends VerticalLayout {

    private final String H2_TOOLTIP = "Путь к базе: jdbc:h2:mem:testdb";

    private Button button;
    private Button h2ConsoleButton;
    private Button backButton;
    private TextField textField;
    private int value = 0;

    private final ValueService valueService;
    private final Binder<Integer> binder = new Binder<>();

    public ButtonView(ValueService valueService) {
        this.valueService = valueService;
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        createTextField();
        createIncreaseButton();
        createH2ConsoleButton();
        createBackButton();

        add(backButton, button, textField, h2ConsoleButton);

        binder.setBean(value);
    }

    private void createTextField() {
        textField = new TextField("Значение");
        textField.addClassName("custom-input");

        binder.forField(textField)
                .withConverter(new StringToIntegerConverter("Неверное значение"))
                .bind(bean -> valueService.getValue(), (bean, fieldValue) -> {
                    valueService.setValue(fieldValue);
                });

        textField.addValueChangeListener(new TextFieldValueChangeListener(binder, valueService));

        // Загрузка значения из базы данных и установка в TextField
        ValueEntity valueEntity = valueService.getValueEntity();
        if (valueEntity != null) {
            textField.setValue(String.valueOf(valueEntity.getCountedValue()));
        }
    }

    private void createH2ConsoleButton() {
        h2ConsoleButton = new Button("H2 Console");
        h2ConsoleButton.setTooltipText(H2_TOOLTIP);
        h2ConsoleButton.addClickListener(new H2ConsoleButtonClickListener());
    }

    private void createBackButton() {
        backButton = new Button("Назад");
        backButton.getStyle().set("position", "absolute");
        backButton.getStyle().set("top", "0");
        backButton.getStyle().set("left", "0");
        backButton.addClickListener(new BackButtonClickListener());
    }

    private void createIncreaseButton() {
        button = new Button("Увеличить");
        button.addClassName("custom-button");
        button.addClickListener(new IncreaseButtonClickListener(valueService, textField));
    }
}
