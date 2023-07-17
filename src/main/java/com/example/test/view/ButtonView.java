package com.example.test.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.test.service.ValueService;

@Route("/button")
@CssImport("./styles/custom-styles.css")
public class ButtonView extends VerticalLayout {

    private final String CONSOLE = "http://localhost:8080/h2-console";
    private final String BACK_BUTTON = "http://localhost:8080";
    private final String TOOLTIP_H2 = "Путь к базе: jdbc:h2:mem:testdb";

    private Button button;
    private Button h2ConsoleButton;
    private Button backButton;
    private TextField textField;
    private int value = 0;

    @Autowired
    private ValueService valueService;

    private Binder<Integer> binder = new Binder<>();

    public ButtonView() {
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        createIncreaseButton();
        createH2ConsoleButton();
        createBackButton();
        createTextField();

        add(backButton, button, textField, h2ConsoleButton);

        binder.setBean(value);
    }

    private void createH2ConsoleButton() {
        h2ConsoleButton = new Button("H2 Console");
        h2ConsoleButton.setTooltipText(TOOLTIP_H2);
        h2ConsoleButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.getPage().executeJs("window.open('" + CONSOLE + "');"));
        });
    }

    private void createBackButton() {
        backButton = new Button("Назад");
        backButton.getStyle().set("position", "absolute");
        backButton.getStyle().set("top", "0");
        backButton.getStyle().set("left", "0");
        backButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.getPage().setLocation(BACK_BUTTON));
        });
    }

    private void createIncreaseButton() {
        button = new Button("Увеличить");
        button.addClassName("custom-button");
        button.addClickListener(e -> {
            value++;
            binder.setBean(value);
            valueService.setValue(value);
        });
    }

    private void createTextField() {
        textField = new TextField("Значение", String.valueOf(value));
        textField.addClassName("custom-input");

        binder.forField(textField)
                .withConverter(new StringToIntegerConverter("Неверное значение"))
                .bind(bean -> value, (bean, fieldValue) -> {
                    value = fieldValue;
                    textField.setValue(String.valueOf(value));
                    valueService.setValue(value);
                });

        textField.addValueChangeListener(e -> binder.validate());
    }
}
