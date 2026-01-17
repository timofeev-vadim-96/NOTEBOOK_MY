

Настройка логирования через XML:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <!-- Определение уровня логирования для корневого логгера -->
    <root level="info">
        <!-- Консольный аппендер для вывода логов в консоль -->
        <appender-ref ref="consoleAppender"/>
    </root>

    <!-- Консольный аппендер -->
    <appender name="consoleAppender" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <!-- Шаблон для вывода сообщений -->
            <pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- Настройка уровня логирования для пакетов приложения -->
    <logger name="com.example.myapp" level="debug"/>

</configuration>
```