package com.example.test.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    private static final String IMAGE_URL = "https://raw.githubusercontent.com/WMHillock/MyWayButtonTestTask/master/src/main/resources/images/avaForTest.jpg";
    private static final String RESUME_URL = "https://hh.ru/resume/591dc6aaff0aee5f9d0039ed1f6270384f4861";
    private static final String TEST_BUTTON = "http://localhost:8080/button";

    public MainView() {
        Image image = createImage();
        Div text = createTextDiv();

        HorizontalLayout contentLayout = createContentLayout(image, text);
        add(contentLayout);

        HorizontalLayout buttonLayout = createButtonLayout();
        add(buttonLayout);

        Button testTaskButton = createTestTaskButton();
        Button customButton = createCustomButton();

        buttonLayout.add(testTaskButton, customButton);
    }

    private Image createImage() {
        Image image = new Image(IMAGE_URL, "Фото отличного Java разработчика");
        image.setWidth("auto");
        image.setHeight("55vh");
        image.getStyle().set("margin-right", "1em");
        image.addClassName("fade-in-image");
        return image;
    }

    private Div createTextDiv() {
        Div text = new Div();
        text.addClassName("typing-text");
        String formattedText = "<p>Меня зовут Владимир и я Java Разработчик</p>"
                + "<p>На текущий момент я работал над проектами:</p>"
                + "<ul>"
                + "<li>Разработка CRM системы, Web порта, Мобильный GameDev</li>"
                + "</ul>"
                + "<p>Имею опыт работы с системами:</p>"
                + "<ul>"
                + "<li>Синтаксис - Java 8, 11, 17</li>"
                + "<li>Фреймворки - Spring, Hibernate, Thymeleaf</li>"
                + "<li>Базы данных: PostgreSQL, Cassandra, H2, Redis</li>"
                + "<li>Брокеры сообщений: RabbitMQ, Kafka</li>"
                + "<li>Другие системы: Keycloack, Docker, Hazelcast</li>"
                + "</ul>"
                + "<p>Давно работаю с Java и продолжаю развиваться в данном направлении, уверен,"
                + "что могу быть полезным в любом проекте.</p>"
                + "<p> Прошу отправить обратную связь вне зависимости от вашего решения.</p>"
                + "<p>Спасибо за уделенное время! </p>";
        text.getElement().setProperty("innerHTML", formattedText);
        return text;
    }

    private HorizontalLayout createContentLayout(Image image, Div text) {
        HorizontalLayout contentLayout = new HorizontalLayout(image, text);
        contentLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        return contentLayout;
    }

    private HorizontalLayout createButtonLayout() {
        return new HorizontalLayout();
    }

    private Button createTestTaskButton() {
        Button testTaskButton = new Button("Тестовое задание");
        testTaskButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.getPage().setLocation(TEST_BUTTON));
        });
        return testTaskButton;
    }

    private Button createCustomButton() {
        Button customButton = new Button("Полное резюме");
        customButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.getPage().executeJs("window.open('" + RESUME_URL + "');"));
        });
        return customButton;
    }
}
