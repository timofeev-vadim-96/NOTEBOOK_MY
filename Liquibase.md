
## Liquebase

Liquebase/Flyway - решает задачу перехода от одной версии БД к другой

Занятие OTUS (liquibase/flyway): [тык](https://otus.ru/learning/277196/)

Миграции:  
* хранят изменения, которые необходимо применить к БД в специальных файлах
* часто такие файлы называются `changelog`, а само изменение - `changeset`
* каждая система миграции умеет отличать один changeset от другого
* и не будет применять к системе changeset, примененный ранее

в src - schema.sql и data.sql  
в test - только data.sql, чтобы структура соответствовала сорсу

`Особенности Liquebase`:  
● Поддерживает changelog-и в форматах
xml/yaml/json/sql  
● По умолчанию changelog-и ищутся в
“resources/db/changelog”  
● Каждый changeset имеет свой id и контрольную
сумму  
● Файлы changelog-ов можно
объединять/включать в один (include*), то
позволяет создать достаточно ветвистую
структуру каталогов  
● Умеет откатывать changelog-и  
● В SpringBoot срабатывает на старте приложения
если spring.liquibase.enabled=true  

> старые версии java-приложений должны уметь работать с новыми версиями БД

`Именование changelog-ов`  
YYYY-MM-DD--description.yml  
**Пример:**  
20200-06-10--Create-persons_table.yml  

* Дата внесения изменений в формате YYYY-MM-DD
* Разделитель: два минуса
* Описание: слова разделяются '-' минусом
* Суффикс: расширение файла (.xml/.yml/.json/.sql)  

Сами changelog-и лучше складывать в папки с именем-номером версии структуры БД, к которой они относятся

Пример chengelog-ов: [тык](https://github.com/OtusTeam/Spring/tree/master/examples/migrations-demo/liquibase-demo/src/main/resources/db/changelog)  

Команды (консоль)
* liquibase update -  выполняет все еще не примененные миграции в базе данных, синхронизируя текущую схему с актуальной версией.
* liquibase rollbackCount 1 -  отменяет изменения до указанной версии или точки, что полезно для возврата к стабильному состоянию в случае ошибок. Например, чтобы откатить на один changeset
* liquibase diff - сравнивает схемы баз данных или базу данных и набор миграций, позволяя находить различия и автоматизировать создание миграций
* liquibase status - показывает список changeset, которые еще не были применены, что помогает отслеживать выполнение миграций в различных средах

```Rollback```
Пример роллбека
```sql
--liquibase formatted sql

-- changeset developer:1
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255)
);

-- rollback developer:1
DROP TABLE users;
```

роллбек yml
```yml
  - changeSet:
      id: 2
      author: developer
      changes:
        - addColumn:
            tableName: users
            columns:
              - column:
                  name: last_login
                  type: datetime
      rollback:
        - dropColumn:
            tableName: users
            columnName: last_login

```

`Зависимость`  
```xml
<dependency>
    <groupId>org.liquibase</groupId>
    <artifactId>liquibase-core</artifactId>
    <version>${liquibase.version}</version>
</dependency>
<!--иногда может понадобится-->
<dependency>
    <groupId>org.yaml</groupId>
    <artifactId>snakeyaml</artifactId>
    <version>2.2</version>
</dependency>
```

Конфиг
```yml
spring.liquibase.enabled=true  # Включает Liquibase для выполнения миграций
spring.liquibase.change-log=classpath:/db/changelog/db.changelog-master.xml
spring.liquibase.default-schema=my_schema  # Задает схему для миграций (опционально)
spring.liquibase.contexts=production,qa  # Устанавливает контексты выполнения миграций
spring.liquibase.drop-first=true # Удаляет существующие объекты перед миграцией (аккуратно с этим параметром)
spring.liquibase.labels=label1,label2 # Метки для выполнения определенных миграций
spring.liquibase.test-rollback-on-update=true # Проверка отката для проверки миграций
```

Что такое `Changeset` и его основные компоненты?
Changeset — это атомарный набор операций, выполняемый на базе данных в рамках одной транзакции. В Liquibase каждый changeset имеет уникальный идентификатор, имя автора и метку времени, что предотвращает повторное применение одного и того же changeset.

Основные компоненты changeset в Liquibase:

ID: уникальный идентификатор, используемый для отслеживания изменений.
Author: имя автора, создавшего changeset.
Context (опционально): окружение, в котором выполняется changeset, например, production или testing.
Labels (опционально): метки, позволяющие фильтровать изменения.
Rollback (опционально): инструкции для отката изменений.

## YML

Пример yml changeset-a `с созданием таблиц`:  
```yml
databaseChangeLog:
  - changeSet:
      id: create_tables
      author: your_name
      changes:
        - createTable:
            tableName: users
            columns:
              - column:
                  name: id
                  type: INT
                  constraints:
                    primaryKey: true
                    nullable: false
              - column:
                  name: username
                  type: VARCHAR
                  length: 50
              - column:
                  name: email
                  type: VARCHAR
                  length: 100
        - createTable:
            tableName: orders
            columns:
              - column:
                  name: order_id
                  type: INT
                  constraints:
                    primaryKey: true
                    nullable: false
              - column:
                  name: user_id
                  type: INT
                  constraints:
                    foreignKeyName: fk_orders_user_id
                    references: users(id)
              - column:
                  name: order_date
                  type: DATE

```

Пример yml changeset-а с `изменением существующей таблицы`:  
```yml
databaseChangeLog:
- changeSet:
    id: 2019-01-12--0001--user
    author: stvort
    addColumn:
      tableName: users
      columns:
      - column:
          name: full_name
          type: varchar(255)
          constraints:
            nullable: false
```

Пример yml `наполнения ТЕСТОВЫМИ данными` (resources/data):  
```yml

```

## SQL

> changeset считается либо до конца файла, либо до нового --changeset комментария

Пример sql changeset-a с `созданием таблиц` (вставка данных ровно также):  
```sql
--liquibase formatted sql

--changeset timofeev_vadim:2024-07-11-init
create table if not exists authors (
    id bigserial,
    full_name varchar(255),
    primary key (id)
);
```

Пример db/changelog/`db.changelog-master.yaml`:  
```yml
databaseChangeLog:
  - includeAll:
      path: db/changelog/1.0/
  - includeAll:
      path: db/changelog/data/
```

Поднять `тестовый конфиг` для H2:
```yml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
  liquibase:
    change-log: classpath:db/changelog/db.changelog-master-test.yaml
```


## XML

Пример 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
    xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">
    
    <!-- Изменение схемы базы данных с помощью changeset -->
    <changeSet id="1" author="admin">
        <!-- Создание таблицы -->
        <createTable tableName="user">
            <column name="id" type="int">
                <constraints primaryKey="true" nullable="false"/>
            </column>
            <column name="username" type="varchar(255)">
                <constraints nullable="false"/>
            </column>
            <column name="email" type="varchar(255)">
                <constraints nullable="false" unique="true"/>
            </column>
        </createTable>
    </changeSet>

    <!-- Добавление нового столбца -->
    <changeSet id="2" author="admin">
        <addColumn tableName="user">
            <column name="created_at" type="timestamp">
                <constraints nullable="false"/>
            </column>
        </addColumn>
    </changeSet>

</databaseChangeLog>
```
Пояснения:
* Корневой элемент databaseChangeLog: это контейнер для всех изменений схемы базы данных.
* changeSet: описывает единичное изменение в базе данных. Атрибуты:
  * id: уникальный идентификатор изменений, который помогает отслеживать их выполнение.
  * author: имя автора изменений.
* createTable: используется для создания новой таблицы. В данном примере создается таблица user с тремя колонками: id, username, и email.
* addColumn: добавляет новый столбец в существующую таблицу. В примере добавляется столбец created_at в таблицу user.