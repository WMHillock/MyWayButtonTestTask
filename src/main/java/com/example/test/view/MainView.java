package com.example.test.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        // Создаем изображение
        String imageUrl = "C:\\Users\\WMHillock\\IdeaProjects\\LFTestTasks\\MyWayButtonTestTask\\src\\main\\resources\\images\\jokeava.jpg";
        Image image = new Image(imageUrl, "Фото отличного Java разработчика");
        add(image);

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
                + "<p> Прошу отправить обратную свзяь вне зависимости от вашего решения.</p>"
                + "<p>Спасибо за уделенное время! </p>";

        Div text = new Div();
        text.getElement().setProperty("innerHTML", formattedText);
        add(text);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        add(buttonLayout);

        Button h2ConsoleButton = new Button("H2 консоль");
        h2ConsoleButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.getPage().setLocation("http://localhost:8080/h2-console"));
        });
        buttonLayout.add(h2ConsoleButton);

        // Создаем вторую кнопку
        Button customButton = new Button("Тестовое задание");
        customButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.getPage().setLocation("http://localhost:8080/button"));
        });
        buttonLayout.add(customButton);
    }
}
