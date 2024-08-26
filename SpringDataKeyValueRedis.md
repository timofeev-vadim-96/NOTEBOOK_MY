
## Spring Data Key-Value  

Это подпроект Spring Data, абстракция для работ с Key-Value БД
* содержит дефолтную реализацию на базе ConcurrentHashMap
* можно использовать и др. реализации, например WeakMap

Есть более специфичные модули типа `spring-data-redis`, которые предоставляют методы Templat-a (RedisTemplate)  

`Зависимость для обобщенной key-value`
```xml
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-keyvalue</artifactId>
    <version>3.3.2</version>
    <scope>test</scope>
</dependency>
```

`Зависимость для Redis`  
```xml

```

`@KeySpace` - это collection в MongoDb/ElasticSearch, таблицы в JPA, cores в Solr
```java
@KeySpace("employees") //
public class Employee {
    @Id
    private Integer id;
    private String name;
    private String department;
    private String salary;
}

```

`@EnableMapRepositories` - чтобы сказать спрингу о работе с Key-Value репозиториями. Автоматом создаст бины Templeat-а
```java
@EnableMapRepositories(mapType = WeakHashMap.class) //вешать над главным классом. По умолчанию - ConcurrentHashMap
public class SpringApplication {}
```

Template
```java
private final KeyValueOperations keyValueTemplate; //аналог EntityManager-a, только для key-value
```

Репозиторий
```java
public interface PersonRepository extends KeyValueRepository<Person, Integer>
```

Query  
```java
KeyValueQuery<String> query = new KeyValueQuery<>();
query.setSort(new Sort(Sort.Direction.DESC, "salary"));
Iterable<Employee> employees = keyValueTemplate.find(query, Employee.class);
```