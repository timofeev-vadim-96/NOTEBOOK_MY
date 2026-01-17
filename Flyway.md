`Зависимость`
```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
    <version>7.11.0</version>
</dependency>
```

Особенности Flyway:  
* Поддерживает ChangeLog-и в форматах sql /java
* По умолчанию ChangeLog-и ищутся в
“resources/db.migration”. И ChangeLog-и принято
называть миграциями
* Очень хитрое именование ChangeLog-ов. Часть
имени это версия БД, к которой приведут
изменения (только для версионных миграций)
* Накатываются изменения в порядке сортировки
файлов миграций
* В SpringBoot срабатывает на старте приложения
если spring.flyway.enabled=true

Виды миграций:  
* Versioned Migrations (версионные миграции)
– каждая такая миграция приводит БД к
версии указанной в начале имени скрипта
* Repeatable Migrations (повторяемые
миграции) – миграции данного вида
накатываются каждый раз, после
выполнения всех версионных
* Undo Migrations – содержит операции,
которые призваны отменить изменения
вызванные заданной (в имени скрипта)
миграцией. Доступно только в Enterprise
версии

Нейминг миграций:  
* Префикс: V для верисионных миграций, U для undo-миграций и R для
повторяемых
* Версия: Разделяется точками или единичными подчеркиваниями.
Может быть достаточно длинной. На столько, на сколько это нужно
* Разделитель: “__” два подчеркивания
* Описание: Слова разделяются единичными подчеркиваниями или
пробелами
* Суффикс : .sql
* Пример: V4.0.1_add_new_table.sql

Конфиг
```yml
spring.flyway.enabled=true                         # Включает Flyway для выполнения миграций
spring.flyway.locations=classpath:db/migration     # Указывает папку с миграционными SQL-скриптами
spring.flyway.schemas=my_schema                    # Устанавливает схему для миграций
spring.flyway.baseline-on-migrate=true             # Устанавливает начальную точку миграций
spring.flyway.out-of-order=true                    # Позволяет выполнять миграции вне порядка
spring.flyway.validate-on-migrate=true             # Проверяет корректность всех миграций
spring.flyway.clean-disabled=false                 # Устанавливает возможность очистки базы данных (осторожно!)

```

Команды
* migrate: запускает все миграции, которые еще не были выполнены, начиная с самой старой версии. Это команда, которая чаще всего используется в работе.
* clean: удаляет все объекты (таблицы, схемы) из базы данных, что делает ее пустой. Команда особенно полезна в процессе разработки и тестирования, но крайне не рекомендуется для использования в продакшене.
* info: выводит информацию о состоянии базы данных, включая выполненные миграции, ожидающие миграции, и их текущие версии. Это полезно для контроля состояния миграций.
* repair: исправляет таблицу учета версий Flyway (обычно flyway_schema_history) после неудачных миграций, позволяя устранить неконсистентность.

Пример скрипта миграции db/migration/1.0/V1.0_create_schema.sql:
```sql
--date: 2026-01-13 -- эти метаданные ДЛЯ СЕБЯ - BEST PRACTICE
--author: Timofeev Vadim

CREATE SCHEMA IF NOT EXISTS s_pg_srv_pl_okk;
```

ОБЯЗАТЕЛЬНЫЙ ДЛЯ JPA конфиг:  
```java
// Ставим фабрику менеджеров сущностей в зависимость от Flyway, 
// чтобы гарантировать ее создание после внесения изменений в базу
@Bean
@DependsOn("flyway") //речь об этом
public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
    bean.setDataSource(dataSource);
    return bean.getObject();
}
```