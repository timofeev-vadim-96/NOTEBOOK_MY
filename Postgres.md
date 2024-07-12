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