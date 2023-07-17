package com.example.test.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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

    public ButtonView() {
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        createIncreaseButton();
        createH2ConsoleButton();
        createBackButton();
        createTextField();

        add(backButton, button, textField, h2ConsoleButton);
    }

    private void createIncreaseButton() {
        button = new Button("Увеличить");
        button.addClassName("custom-button");
        button.addClickListener(e -> {
            value++;
            textField.setValue(String.valueOf(value));
            valueService.setValue(value);
        });
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

    private void createTextField() {
        textField = new TextField("Значение", String.valueOf(value));
        textField.addClassName("custom-input");
        textField.addValueChangeListener(e -> {
            value = Integer.parseInt(e.getValue());
            valueService.setValue(value);
        });
    }
}
