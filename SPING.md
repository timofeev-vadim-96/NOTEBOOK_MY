Alishev - youtube

`Framework` - каркас/платформа, которая определяет структуру системы (приложения), и облегчает разработку компонентов
системы и их интеграцию //предоставляет определенные паттерны разработки

## SPRING FRAMEWORK

от 2003г. 

**Компоненты:**
* Контекст приложения (Application Context) 
  * все объекты описываются в специальном кофигурационном файле для автомат. составления зависимостей между ними внитри структуры приложения
* Внедрение зависимостей (Dependency Injection)
* Удобный доступ к БД (замена JDBC)
* Компонент для разработки Web-приложений на Java (Spring MVC) (замена Java EE Servlets)
  * исп. в качестве backend-API для мобильных приложений
  * исп. для создания web-приложений

**Др. компоненты:**
* Spring Security - для безопасности и настройки Авторизации и аутентификации
* Spring Boot - помогает избавляться от лишней конфигурации приложения
* Spring Webflow
* др. spring.io/projects

Типичное приложение на Java - набор Java-объектов, взаимодействующих и ссылающихся друг на друга

При взаимодействии нескольких классов с одним (например DB):
* делает так, чтобы DB создавался лишь один раз
* внедряет ссылку на DB все остальным классам, которым необходимо с ней работать
