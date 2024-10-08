> [НАЗАД к СОДЕРЖАНИЮ](README.md)

---

### Hibernate - enterprise-фреймворк для Java - решает задачу связи классов Java с таблицами базы данных (и типов данных Java с типами данных SQL)

> Программа для работы с БД - `DataGrip` от JetBrains

> учу здесь: https://javarush.com/groups/posts/hibernate-java
для друзей: https://www.youtube.com/watch?v=emg94BI2Jao

> ссылка на PostgreSQL: https://www.postgresql.org/download/

Пользователь по умолчанию в PostgreSQL:  'postgres', заданный мной пароль: мой дефолтный, порт: 8083

Jakarta Persistence API (`JPA`; ранее Java Persistence Arhitecture) — набор аннотаций и интерфейсов для разметки сущностей, объясняет ORM как с ними работать

`JDBC` (Java Database Connectivity) API — это программный интерфейс, предоставляющий набор классов и
методов для взаимодействия с базами данных из языка программирования Java. JDBC обеспечивает
стандартизированный способ подключения к различным системам управления базами данных (СУБД),
выполнения SQL-запросов, получения и обновления данных в базе. Он предоставляет абстракцию,
позволяющую разработчикам писать приложения, которые могут взаимодействовать с базами данных,
независимо от конкретной используемой СУБД. JDBC используется для создания портативных и
эффективных приложений, работающих с данными в базах данных. //`крч это драйвер для конкретной СУБД (драйвер пишется самими разработчиками СУБД)`

`Аннотации`:
@Entity (сущность)

`Entity` (Сущность) — POJO-класс(англ. Plain Old Java Object) — «старый добрый Java-объект», связанный с БД с помощью аннотации или через XML. 
```java 
@Entity 
```
К такому классу предъявляются следующие **требования**:

* Должен иметь пустой конструктор (public или protected)
* Не может быть вложенным, интерфейсом или enum
* Не может быть final и **не может содержать final-полей/свойств**
* Должен содержать хотя бы одно @Id-поле

При этом entity **может**:
* Содержать непустые конструкторы
* Наследоваться и быть наследованным
* Содержать другие методы и реализовывать интерфейсы
* Entities могут быть связаны друг с другом (один-к-одному, один-ко-многим, многие-к-одному и многие-ко-многим)

> Проверьте ваши entity-классы, эти пункты — очень популярное место для "выстрела себе в ногу". Очень легко что-нибудь забыть. 

```java

@Id // это указание, что поле является идентификатором объектов этого класса. 

@EmbeddedId //для составного ключа
//https://www.baeldung.com/jpa-composite-primary-keys
//https://www.baeldung.com/jpa-embedded-embeddable

@Table //над классом указывает как называется таблица, в которую записываются объекты.
  * @Table (name = "users")

@Transient //помечать поля, которые не нужно серриализовывать

@Column(name = "name") // над полем или сеттером. Можно не размещать над полем, если имя поля класса и имя колонки совпадает.
@Column(nullable = false, length = 2000, unique = true) // nullable - поле не может == null, lenght - макс длина, unique - уникальность

@GeneratedValue(strategy = GenerationType.IDENTITY) //генерация id. 
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "idgen", sequenceName = "table_name_seq") //имя формата таблица_seq, allocationSize - инкремент
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen") //генерация на основе заданной последовательности (по умолчанию в старых версиях Hiber.. версий до 6 - hibernate_sequenct, после - tableName_seq) 
@GeneratedValue(strategy = GenerationType.NONE) //принимает с уже заданным id

//генерация UUID
@Id
@GeneratedValue
@UuidGenerator
private UUID id;
//ИЛИ
@GeneratedValue(strategy = GenerationType.UUID)
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

/*
В основной таблице - поле с "mappedBy", а во второй - JoinColumn
*/

@OnDelete(action = OnDeleteAction.CASCADE) - над классом со вторичным ключом для удаления при удалении основного объекта

ПРО КАСКАДНЫЕ ИЗМЕНЕНИЯ (CascadeType) https://javarush.com/quests/lectures/questhibernate.level13.lecture05

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) //связь поля с нескольким полями в др. табл. user - свойство в классе Auto (соотношение по классам, а не по таблицам). orphanRemoval = true - удалять все связные объекты при удалении этого.

@ManyToOne(fetch = FetchType.LAZY) //отношение свойства класса многие:1 - другому классу. Lazy - чтобы не вытащить вместе с объектом кучу других зависимостей по цепочке до бесконечности. Если обратимся к полю, которое не вытащилось из БД - хибернейт его сам дотянет. (в момент открытой СЕССИИ (Персистентности))

//сюда же, для автоматического джойна при запросах
@JoinColumn(name = "user_id") //свойство, которое в таблице связано с другой таблицей через вторичный ключ. Здесь имя своего столбца! Прописывать в таблице с Foreign key

//ЕСЛИ ТАК: - ЛУЧШЕ ТАК НЕ ДЕЛАТЬ
@ManyToOne(fetch = FetchType.EAGER) //то все зависимости будут вытащены вместе с требуемым объектом. Например: получить университет - получим, вместе со списком студентов. 


//для такой связи создается связующая таблица - id to id (так называемый Join Table)
@ManyToMany //многие ко многим. Пример - читатели и книги. CascadeType.ALL - здесь ппц не круто. При удалении одного объекта вызовет каскад удалений вплоть до всей БД.
  * @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER) //PERSIST - операции сохранения, MERGE - операции обновления

@JoinTable( //во втором классе ссылки наоборот. 
	name = "books_readers",
	joinColumns = @JoinColumn(name = book_id), //в текущем классе Book. в классе Reader здесь reader_id
	inverseJoinColumns = @JoinColumn(name = reader_id) //во втором классе. в классе Reader здесь book_id
)

@OneToOne //отношение один к одному. Если связь двунаправленная - mappedBy.

@JoinColumn(name = "user_id") //свойство, которое в таблице связано с другой таблицей через вторичный ключ. Здесь имя своего столбца! Прописывать в таблице с Foreign key

@Temporal //для указания типа даты/времени для java.util.Date или java.util.Calendar

@Enumerted //указывает, что поле является перечислением

@JoinColumns - для объединения не по id - гугли крч, используется не часто  

```

> Для подключения к БД: две зависимости: hibernate-core и mysql-connector-java

`Фабрика сессия для работы с БД`:
Для этого используются классы библиотеки hibernate:
Класс `Configuration`:
  1. Configuration configuration = new Configuration().configure(); //берет конфиги из файла `hibernate.cfg.xml` - нужно его создать предварительно //пример на ПК в папке Hibernate
  2. configuration.addAnnotatedClass(User.class); //добавляем наши Entity-классы

Класс `StandardServiceRegistryBuilder`:
StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()); //билдер, в который кладутся конфиги из предыдущего класса

Класс `SessionFactory:` - создается 1 раз во всем приложении (очень тяжеловестный)
sessionFactory = configuration.buildSessionFactory(builder.build()); //строим сессию с помощью билдера

Методы:
.getCurrentSession(); - возвращают Session

> для КАЖДОЙ НОВОЙ ТРАНЗАКЦИИ нужна НОВАЯ сессия!

`класс Session`

Методы:
.beginTransaction() - начать транзакцию
-- здесь действия с БД
session.getTransaction().commit() - закончить транзакцию
session.get(User.class, id);
session.save(user)
session.update(user); `НО`! как делаетс update - когда я достану объект из базы с помощью get и изменю его свойства - изменения автоматиески применяются и в базе после transaction.commit! Либо - можно принудительно - `session.flush()`
session.delete(user);

HQL - Не SQL запросы. Разница в том, что обращаемся по имени класса, а не таблицы:
session.createQuery("From User").list(); //походу запрос SELECT * FROM table - получить список
session.createQuery("From User", CLASS).getSingleResult() - получить единственный объект
  * или так: User user = session.createQuery("Select i From User i Where i.id = :id", User.class).setParameter("id", 4).getSingleResult();

* Чтобы получить единственный объект по id на языке HQL = setParameter("id", 4).getSingleResult();

### Моя реализация: https://github.com/timofeev-vadim-96/hibernate-app.git

`Цикл существования Entity`, а также тонкости его нахождения в Persistance (отслеживание состояния после .get() из БД)
![](images/entity_life.jpg)
  * Transient - новый объект, его ни в БД, ни в Кэше первого уровня(контексте)
  * Persistence - объект есть в БД и контексте
  * Detached - объект есть в БД, но отсутствует в контексте
  * Removed - объекта нет ни в БД, ни в контексте

Чтобы достать объект из не отслеживать: после .get():
* evict(Object obj) - не отслеживать конкретный объект  
* close() - закрыть сессию
* clear() - очистить контекст постоянства (Persistance) и все объекты переходят в "Detached"

Чтобы вернуть объект в отслеживание:
* update(Object object)
* saveOrUpdate()
* merge()


> Hibernate в своей работе **кэширует** все объекты в Persistance себе, из-за чего происходит их **дублирование** (ведь они есть и в куче). Если мы вытаскиваем большое кол-во объектов из БД, следует периодически либо производить clear(), либо удалять объекты из Persistance c помощью evict()

Осуществить `запрос на чистом SQL`:   //делать без транзакции! - она не нужна
  * возвращает объект Query
session.createNativeQuery("select count(*) from people;").uniqueResult(); //получить данные
session.createNativeQuery("create database if not exists telegramBotDB;").executeUpdate(); //выполнить запрос

`чистый SQL` - в сущность (Entity):  
NativeQuery nativeQuery = session.createNativeQuery("Select * from telegramBotDB.users where id = 12345").addEntity(User.class);  
queryUser = (User) nativeQuery.getSingleResult(); 

native с помощью `@Query`:
```java
@Query(value = "SELECT * FROM table_name WHERE column_name = :columnName", nativeQuery = true)
List<Entity> findByNameNative(@Param("columnName") String columnName);
```


Связь `ManyToMany` многие ко многим: //при добавлении в список одного объекта, новый объект добавится и во вторую таблицу! (пример: студенту добавляем курс, этот курс добавится в таблицу с курсами и в промежуточную таблицу добавится связь!)
```java
    @ManyToMany(cascade = CascadeType.ALL)
	//третья - объединяющая их таблица (нужно создать заранее)
    @JoinTable(name = "student_course",
	//колонка для айдишников текущего класса
    joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
	//колонка для айдишников связанного класса
    inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private List<Course> courses;
```

> Вывод списка зависимостей во взаимосвязанных многие-ко-многим объектах - это РЕКУРСИЯ. Вместо вывода объекта, выводи его свойства. Например вместо списка объектов можно выводить список их названий

```java
//достаем студента
        Student firstStudent = service.findAll(Student.class).stream().findFirst().get();
		//достаем его зависимости
        Hibernate.initialize(firstStudent); - инициализировать прокси в норм объект
		//конвертим список зависимостей с объектами в список с названиями
        List<String> courses = firstStudent.getCourses().stream().map(Course::getTitle).toList();
        System.out.println(courses);
```

Наследование:  
```java
@DescriminatorColumn(name = "descriminator") //колонка, в которой по ее значению мы будем понимать, какой наследник/родитель лежит в строке
@DescriminatorValue("RootA") //имя типа класса в колонке дескриминатора
@Ingeritance(strategy = IngeritanceType.SINGLE_TABLE) //все хранится в одной таблице. Поля для типов, у которых нет каких-то свойств, будут null
@Ingeritance(strategy = IngeritanceType.JOINED) //все сущности хранятся в разных таблицах, ссылаясь на записи в таблицах верхних типов
@Ingeritance(strategy = IngeritanceType.TABLE_PER_CLASS) //все таблицы хранят все необходимые поля. работает только с SEQUENCE (gen strategy)
```

Демонстрация `@Ingeritance(strategy = IngeritanceType.JOINED)` - все сущности хранятся в разных таблицах, ссылаясь на записи в таблицах верхних типов  
![](images/hibernate_inheritance.png)

`EntityManager` - отвечает за всю работу с сущностями в рамках Session. В Spring внедряется с помощью `@PersistenceContext` над полем менеджера  
**Методы EntityManager-а**:  
* find – поиск и загрузка объекта по его id. Объект сразу получается в
состоянии persistent
* persist (исп. для вставки) – меняет состояние объекта из transient в persistent. Выполняет
insert. Бросает PersistentObjectException если задан id
* merge (исп. для обновления) – меняет состояние объекта из transient или detached в persistent.  
Для transient работает аналогично persist. Для detached выполняет загрузку из БД и обновляет в контексте. По завершении сессии или коммита транзакции выполняет update
* remove – удаление объекта из БД и контекста. Меняет состояние на transient. IllegalArgumentException если объект в состоянии detached
* refresh – обновление объекта из БД если он был изменен в другом месте
уже после загрузки
* detach – отключает объект от контекста. Меняет состояние на detached
* createQuery – создание объекта запроса
* getEntityGraph – получение, ранее определенного графа объектов

```java
//именно так инжектить. Со своим конструктором, а не ломбоковским. Хз почему не работает с ним
    @PersistenceContext //для его загрузки. Без него работать не должно
    private final EntityManager em;

    public JdbcGenreRepository(EntityManager em) {
        this.em = em;
    }
```

`EntityGraph` (перебивает LAZY) - позволяет указать какие поля сущности загружать в Persistance Context с ней в одном запросе. Обычно задается с помощью аннотации `@NamedEntityGraph` над сущностью
  * может быть применен к объекту Query с помощью метода setHint ????

`@NamedEntityGraph` для подрузки LAZY сущностей вместе с основной
```java
@NamedEntityGraph(name = "avatars-entity-graph", attributeNodes = {@NamedAttributeNode("avatar")}) //здесь имя поля
public class Clazz {
	//...
	private Avatar avatar;
}

//DAO пример подругузки с помощью EntityGraph
@Override
public List<OtusStudent> findAll() {
EntityGraph<?> entityGraph = em.getEntityGraph("avatars-entity-graph");
TypedQuery<OtusStudent> query = em.createQuery("select s from OtusStudent s",
OtusStudent.class);
query.setHint("jakarta.persistence.fetchgraph", entityGraph); //подсовываем подруженную сущность
return query.getResultList();
}
```

Для использования вместо "jakarta.persistence.fetchgraph" `FETCH.getKey()`
```java
import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;
```

## JPQL

`JPQL` - язык запросов спецификации JPA. Суть языка в том, что запросы производятся к java-сущностям и ее полям. 

Пример `JPQL-запросов`:  
```sql
select e from Employee e
select e from Employee e where e.id = :id
select e from Employee e where e.salary > 100000
select e.firstName from Employee e
select e.firstName, e.lastName from Employee e --список массивов строк будет
select count(e) from Employee e
---------------------------------------------------------------------------------
select max(e.salary) from Employee e
select avg(e.salary) from Employee e
---
select e
from Employee e join
e.projects p1 join
e.projects p2
where p1.name = :p1 and p2.name = :p2
--с подзапросом
select e
from Employee e
where e.firstName = any (select e2.firstName from Employee e2 where e2.id <> e.id)
--IN
select e
from Employee e
where e.firstName in :names
---
select e
from Employee e
where e.firstName in (select e2.firstName
from Employee e2
where e2.lastName = :lastName and e2.id <> :id
---
select e
from Employee e
where e.firstName in (:name1, :name2)
---
select new r.o.e.j.d.CitySalary(e.address.city, avg(e.salary))
from Employee e
group by e.address.city
order by avg(e.salary)
)
```

Классы для JPQL-запросов: `TypedQuery<T>` и `Query`

`JPQL поиск по имени (список)`:  
```java
@Override
public List<OtusStudent> findByName(String name) {
TypedQuery<OtusStudent> query = em.createQuery(
"select s " +
"from OtusStudent s " +
"where s.name = :name",
OtusStudent.class);
query.setParameter("name", name);
return query.getResultList();
}
```

`JPQL обновление`
```java
@Override
public void updateNameById(long id, String name) {
Query query = em.createQuery("update OtusStudent s " +
"set s.name = :name " +
"where s.id = :id");
query.setParameter("name", name);
query.setParameter("id", id);
query.executeUpdate();
}
```

`JPQL удаление`
```java
@Override
public void deleteById(long id) {
Query query = em.createQuery("delete from OtusStudent s where s.id = :id");
query.setParameter("id", id);
query.executeUpdate();
}
```



`JOIN FETCH` (аналог графа) - позволяет загружать дочерние сущности в одном запросе с родительской. Это часть JPQL-запроса  
**Пример:**  
```sql
select distinct s from OtusStudent s join fetch s.emails
```

`@Fetch` - указывает фреймворку, что нужно отдельно подгрузить список дочерних сущностей
```java
@Fetch(FetchMode.SELECT)
//использовать, когда в запросе есть такое: join fetch ...коллекция
"select b from Book b left join fetch b.genres"
```
  * вешается над полем родительской сущности

---

`Дотянуть Lazy-сущности`. Идея - в одной транзакции получить нужные сущности и конвертировать в Dto, где Lazy-поля понадобятся и дотянутся в рамках транзакции
```java
@Transactional(readOnly = true)
@Override
public List<OtusStudentDto> findAll() {
List<OtusStudent> students = studentRepository.findAll();
return students.stream.map(studentConverter::toDto).toList();
}
```