`Создать БД если не существует`
```java
        try {
            jdbcTemplate.execute("CREATE DATABASE bank_database");
        } catch (DataAccessException e){
            log.warn("Exception while trying to create a database. Perhaps it already exists ");
        } finally {
            jdbcTemplate.setDataSource(new DriverManagerDataSource(
                    "jdbc:postgresql://localhost:5432/bank_database",
                    "postgres",
                    "root"));
        }
```

> `BIGSERIAL` в Postgres == AUTO_INCREMENT в MySQL

> при создании подключения в идее - использовать подключение к конкретной БД

`SHOW TABLES in Postgres:`  
```sql
SELECT * FROM pg_catalog.pg_tables;
```

INSERT с возвращением сгенерированного ID:  
```sql
String sql = "insert into books (title, author_id) values (:title, :authorId) returning id";
```

> войти внутрь контейнера: docker exec -it id_конт bash 

подключиться к Postgres в контейнере:
```bash
psql -U postgres psql -h localhost -U postgres -d postgres -W
#затем ввести пароль. Теперь можно юзать SQL!!!
```

`список баз данных`
```bash
\l
```

`создать базу данных (из среды разработки)`
```bash
create database some_database;
```

`посмотреть таблицы (из консоли)` 
```bash
\c database_name #выбрать бд
\dt 
```

Статья про поиск по `JSONB`:  
https://www.sql-ex.ru/blogs/?/Vvedenie_v_rabotu_s_dannymi_JSON_v_PostgreSQL.html

> Оператор -> извлекает значение как JSON (json, если поле имеет тип json, и jsonb, если поле имеет тип jsonb), а оператор ->> получает его в виде текста