# Используйте образ Amazon Corretto 17
FROM amazoncorretto:17-alpine

# Создание директории приложения
RUN mkdir /app

# Копирование JAR-файла
COPY target/MyWayButtonTestTask-0.0.1-SNAPSHOT.jar /app/app.jar

# Установка рабочей директории
WORKDIR /app

# Установка порта
EXPOSE 8090

# Запуск приложения
CMD java -jar -Dspring.profiles.active=prod app.jar
