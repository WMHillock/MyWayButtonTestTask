# MyWayTask

### Реализация тестового задания для MyWay
### Оглавление:
1) [Условия задачи](#условия-задачи)
2) [Условия задачи](#реализация)
3) [Функционал](#функционал)
4) [Дополнительная информация](#дополнительно)
5) [Запуск через Docker](#docker-команды)

#### Условия задачи:
* Добавить кнопку и текстовое поле на страницу
* По нажатию на кнопку - значение в поле увеличивается на 1
* Значение поля можно изменить руками, вписав нужное значение
* Изменения должны сохранятся в БД автоматически при каждом изменении
* Необходимо использовать binder и Spring Data

> В качестве БД подойдет H2.
> Остальное: Vaadin 24, Java 17, Maven, Spring Boot 3

#### Реализация:
> Использован стек: Java 17 (corretto), Spring 3.1.1, Vaadin 24.1.2, Maven
* Создана сущность Value в которой описана ее структура и аннотации для JPA
* Создан Repository для сущности Value
* Создан Service для работы с Repository
* Созданы Listeners для фиксации использования кнопок и полей
* Созданы два View класса для отображения наших данных:

1) MainView в нем отображены - мое краткое резюме, ссылка на полное резюме на HH и кнопка перехода к тестовому заданию
> http://localhost:8090*

2) ButtonView - реализация тестового задания, кнопка перехода в консоль H2, текстовое поле с текущим значением и кнопка возврат на MainView
> http://localhost:8090/button*

3) Так как мы используем H2 (mem) есть еще отдельная страница с его консолью для проверки результатов,
данные для подключения к БД из консоли: 
* jdbc:h2:mem:testdb 
* user: sa
* password:
> http://localhost:8090/h2-console*
*(ссылки относительные) 

#### Функционал:
* При сборке программы в H2 автоматически создается таблица - value_table с полями id, counted_value
* В Buttonview есть кнопка для увеличения значения и поле для введения нужного знчения в которое можно внести свое число, так же поле отображает текущее значение, это значение сразу пишется в БД
* Дополнительно в Buttonview есть кнопка ведущая на консоль H2 с всплывающей подсказкой для подключения к БД
* Настроен Binder между кнопкой увеличения значения и поля в котором оно отображается

#### Дополнительно:
* К кнопке "Увеличить" роута ButtonView подключены CSS стили
* Добавлен ToolTip для кнопки перехода в консоль H2
* Включена Lumo.DARK тема для страниц
* В настройках подключены логи Hibernate
* Для Vaadin подключен hotdeploy для быстрой пересборки
* Добавлена фича с сохранением в поле значения при обновлении страницы
* Настроена контейнеризация
* Ссылки переработаны на относительные

#### Docker команды:
* Скачать контейнер: 
> $ docker pull wmhillock/mywaytask:latest

* Для быстрой проверки задачи: 
> $ docker run --rm --name my-container -p 8090:8080 wmhillock/mywaytask:latest

* Обратите внимание, что запуск по умолчанию на 8090 порту, ссылки относительные
* Для остановки удаления контейнера в консоли нажмите ctrl + c

[Наверх](#mywaytask)

