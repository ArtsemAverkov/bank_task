# Infopost Account Service

Этот проект представляет собой RESTful сервис для управления счетами пользователей в рамках приложения компании Infopost. Сервис предоставляет возможность хранения персональных данных пользователей, состояния и операций по счетам.

## Функциональность сервиса

- Хранение персональных данных пользователя: имя и уникальный номер пользователя.
- Хранение состояния счета пользователя с точностью до двух знаков после запятой (например, 2,31).
- Хранение данных по всем операциям по счету, включая списание средств и их зачисление.

## Выполняемые операции

### Пополнение счета пользователя

Позволяет пополнить счет пользователя на указанную сумму. Учитывает возможность выполнения двух операций по одному и тому же счету одновременно.

### Списание со счета пользователя

Позволяет списать средства со счета пользователя на указанную сумму. Учитывает возможность выполнения двух операций по одному и тому же счету одновременно.

## Технологии

- Java 17 и выше
- Spring 6 / SpringBoot 3.x
- Сборщик Gradle 
- БД PostgreSQL

## Инструкция по запуску и использованию

1. Клонировать репозиторий на локальную машину.
2. Установить и настроить PostgreSQL.
3. Изменить параметры подключения к базе данных в файле `application.yml`.
4. Запустить приложение с помощью команды `./gradlew bootRun` (для Gradle) или `mvn spring-boot:run` (для Maven).
5. Обращаться к API по адресу `http://localhost:8080`.

## Тестирование

Для тестирования использованы следующие инструменты:

- Junit Jupiter
- Mockito
- SpringBootTest

Для запуска тестов выполните команду `./gradlew test` (для Gradle) или `mvn test` (для Maven).

## Миграционные скрипты для БД

Для управления миграциями базы данных используется Liquibase. Миграционные скрипты находятся в директории `src/main/resources/db/changelog`.

## Docker

Проект содержит базовый Dockerfile для создания Docker-образа сервиса. Для запуска в контейнере, выполните команду `docker build -t infopost-account-service .` для сборки образа и `docker run -p 8080:8080 infopost-account-service` для запуска контейнера.

## OpenAPI 3.0 документация

Документация OpenAPI создана для описания всех доступных операций сервиса. Для просмотра документации обратитесь по адресу `http://localhost:8080/swagger-ui.html`.

