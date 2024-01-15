### Hibernate - enterprise-фреймворк для Java - решает задачу связи классов Java с таблицами базы данных (и типов данных Java с типами данных SQL)

> учу здесь: https://javarush.com/groups/posts/hibernate-java

> ссылка на PostgreSQL: https://www.postgresql.org/download/

Пользователь по умолчанию в PostgreSQL:  'postgres', заданный мной пароль: мой дефолтный, порт: 8083

Jakarta Persistence API (`JPA`; ранее Java Persistence API) — спецификация API Jakarta EE, предоставляет возможность сохранять в удобном виде Java-объекты в базе данных[1].

`Аннотации`:
@Entity (сущность)

`Entity` (Сущность) — POJO-класс(англ. Plain Old Java Object) — «старый добрый Java-объект», связанный с БД с помощью аннотации ( 
```java 
@Entity 
```
) или через XML. К такому классу предъявляются следующие **требования**:

* Должен иметь пустой конструктор (public или protected)
* Не может быть вложенным, интерфейсом или enum
* Не может быть final и не может содержать final-полей/свойств
* Должен содержать хотя бы одно @Id-поле

При этом entity **может**:
* Содержать непустые конструкторы
* Наследоваться и быть наследованным
* Содержать другие методы и реализовывать интерфейсы
* Entities могут быть связаны друг с другом (один-к-одному, один-ко-многим, многие-к-одному и многие-ко-многим)

> Проверьте ваши entity-классы, эти пункты — очень популярное место для "выстрела себе в ногу". Очень легко что-нибудь забыть. 

```java

@Id // это указание, что поле является идентификатором объектов этого класса. 

@Table //над классом указывает как называется таблица, в которую записываются объекты.
  * @Table (name = "users")

@Transient //помечать поля, которые не нужно серриализовывать

@Column(name = "name") // над полем или сеттером. Можно не размещать над полем, если имя поля класса и имя колонки совпадает.

@GeneratedValue(strategy = GenerationType.IDENTITY) //генерация id. 
/*
Всего 4 стратегии:
AUTO - по умолчанию
IDENTITY //БД сама будет устанавливать id. Обычно, у этой колонки стоит PRIMARY KEY, AUTOINCREMENT
SEQUENCE - генерация в определенном порядке. Ниже, начиная с 4 будет применен инкремент
	@GeneratedValue(generator = "sequence-generator")
	@GenericGenerator(
  	name = "sequence-generator",
  	strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
  	parameters = {
    	@Parameter(name = "sequence_name", value = "user_sequence"),
    	@Parameter(name = "initial_value", value = "4"),
    	@Parameter(name = "increment_size", value = "1")
    	}
	)
TABLE
*/

@OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL, orphanRemoval = true) //связь поля с нескольким полями в др. табл. user_id - поле в другой таблице. orphanRemoval = true - удалять все связные объекты при удалении этого.
private List<Auto> autos;

```

