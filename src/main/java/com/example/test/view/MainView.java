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

    public MainView() {
        // Создаем изображение
        String imageUrl = "https://raw.githubusercontent.com/WMHillock/MyWayButtonTestTask/master/src/main/resources/images/jokeava.jpg";
        Image image = new Image(imageUrl, "Фото отличного Java разработчика");
        image.setWidth("auto"); // Установите ширину изображения
        image.setHeight("55vh"); // Автоматический расчет высоты
        image.getStyle().set("margin-right", "1em"); // Отступ справа для изображения

        Div text = new Div();
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
        text.getElement().setProperty("innerHTML", formattedText);

        HorizontalLayout contentLayout = new HorizontalLayout(image, text);
        contentLayout.setAlignItems(FlexComponent.Alignment.CENTER); // Выравнивание по вертикали

        add(contentLayout);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        add(buttonLayout);

        Button testTaskButton = new Button("Тестовое задание");
        testTaskButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.getPage().setLocation("http://localhost:8080/button"));
        });
        buttonLayout.add(testTaskButton);

        Button customButton = new Button("Полное резюме");
        customButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.getPage().executeJs("window.open('https://hh.ru/resume/591dc6aaff0aee5f9d0039ed1f6270384f4861');"));
        });
        buttonLayout.add(customButton);
    }
}

