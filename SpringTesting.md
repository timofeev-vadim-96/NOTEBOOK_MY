> [НАЗАД к СОДЕРЖАНИЮ](README.md)

---

### Spring Testing

Spring TestContext Framework - основная фича Spring Testing  

`@SpringBootTest` - для загрузки контекста приложения. Начинает работу с поиска класса с аннотацией @SpringBootApplication 
`@MockBean` - мок-версия бина на базе контекста
`@WebMvcTest` - для тестирования контроллеров (веб-часть - api/контроллеры)  
`@DataJpaTest` - для тестирования jpa dao-слоя. Поднимет также все @Repository и EntityManager  
`@JdbcTest` - для тестирования кастомных dao с JdbcTemplate  
  * также выполнит schema.sql и data.sql
  * по умолчанию в начале КАЖДОГО теста создаст транзакцию, и откатит ее в конце теста. Чтобы отключить: @Transational(propagation = Propagation.NOT_SUPPORTED/NEVER) - над классом, или @Commit - над методом, где нужно сохранить изменения в БД
  * нужен @ExtendWith(SpringExtension.class), если старые версии Spring
  * репозитории в контекст не поднимуются, нужно добавить через `@Import`
`@TestConfiguration` - для определения ДОПОЛНИТЕЛЬНЫХ бинов для теста, поверх бинов в основном приложении или их ПОДМЕНЫ
  * для ПОДМЕНЫ добавить: @TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true"), либо прописать тоже самое в application.yml  
`@AutoConfigureTestDatabase`(replace = AutoConfigureTestDatabase.Replace.NONE) - отключить замену Postgres на H2 в тестах! (если в конфиге postgre)


Можно еще:  
```
//создаются только те бины, которые указаны (чтобы не поднимать весь контекст)
@SpringBootTest(classes = {
        AutoService.class,
        Auto.class,
        TestConfig.class // обычно используется именно класс конфига, где создаются необходимые бины
})
```

`Нагрузочное тестирование`  
Как провести?  
1. Определите ожидаемую нагрузку - например, для банка это зарплатные дни  
2. Создайте тестовые сценарии - какие операции будут использовать пользователи чаще всего
3. Подготовьте тестовое окружение - поднять тестовые бд и сервера
4. Выполните тесты. 
5. Анализируйте результаты. Анализ с помощью `Locust`/`JMetr`- приоритет

Нагрузочное тестирование с помощью `JMetr`  
1. Установка JMeter
2. Создание тестового плана
3. Настройка запросов
4. Добавление слушателей = агрегатчики результатов
5. Запуск теста

Аннотация `@Lob` - для пометки теоретически больших объектов, тяжелых строк и двоичных данных. После пометки объект может быть сохранен в форме CLOB (для текстовых данных) или BLOB (для двоичных данных) в базе данных.  

`Нагрузочное тестирование` нашего Spring-проекта  
1. Установка JMeter.
2. Создание тестового плана.
3. Добавление и конфигурации HTTP Request.
4. Добавление Listener.
5. Запуск теста.
6. Анализ результатов.

`Метрики нагрузочного тестирования`
![](images/JMeter_metrics.png)
1. Throughput (Пропускная способность)
2. Response Time (Время ответа)
3. Error Rate (Процент ошибок)
4. Concurrent Users
(Конкурентные пользователи)
5. CPU/Memory Utilization
(Использование ЦПУ/Памяти)

`Зависимости` для тестирования:
1. spring-boot-starter-test
2. mockito-core

`@TestConfiguration`  - создаст доп. бины  
![](images/spring_test_configuration.png)

`Unit-Тест на Spring:`  - вместо @InjectMocks здесь @Autowired
![](images/spring-test-example.png)
* @RunWith (Depricated) - расширение для запуска тестов - `@ExtendWith` в JUnit5. НЕ НУЖНО ПИСАТЬ, ЕСТЬ ВНУТРИ @SpingBootTest
* @InjectMocks - внедряет мок-объект
* @Mock - создает мок-объект, не связано с контекстом СПРИНГА!
* MockitoAnnotations.initMocks - инициализация мок-объектов

`Интеграционнный тест на Sping:`  
![](images/spring_integration_test.png)
* @SpringBootTest - для загрузки контекста приложения (для внедрения реальных бинов)

Пример работающего теста с помощью Spring-test:  
```java
@SpringBootTest
@ExtendWith(MockitoExtension.class) //есть внутри @SpringBootTest
class ReaderControllerTest {
    @Autowired
    private ReaderController readerController;
    @MockBean
    private ReaderService readerServiceMock;
    @MockBean
    private IssueService issueServiceMock;

    @BeforeEach
    void resetMock() {
        reset(readerServiceMock, issueServiceMock);
    }

    @Test
    void getByIdWhenExists() {
        ReaderEntity readerEntity = new ReaderEntity();
        readerEntity.setId(1);
        when(readerServiceMock.findById(1)).thenReturn(readerEntity);

        ResponseEntity<ReaderEntity> response = readerController.getById(1);

        Assertions.assertEquals(1, response.getBody().getId());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getByIdWhenNotExists() {
        when(readerServiceMock.findById(1)).thenReturn(null);

        ResponseEntity<ReaderEntity> response = readerController.getById(1);

        Assertions.assertNull(response.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
```

`@SpyBean` - оригинальный бин. Но можно заменить реализацию методов и следить за вызовами


Чтобы протестировать приложение с Security:
```java
@TestConfiguration
public class WebSecurityTestConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("Создался тестовый бин WebSecurityConfig!!!");

        return httpSecurity
                .authorizeHttpRequests(registry-> registry
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }
}
```

> Чтобы не поднимать контекст приложения с помощью @SpringBootTest в каждом новом классе, можно создать единственный класс с этой аннотацией, и наследовать все тестовые классы от него

`WebTestClient` - подключить webflux - для тестирования API/Контроллеров

`Зависимость` 
```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
```

```java
//mono
        ReaderEntity response = webTestClient.get()
                .uri("reader/" + readerEntity.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ReaderEntity.class)
                .returnResult()
                .getResponseBody();

//collection
        List<ReaderEntity> readers = webTestClient.get()
                .uri("reader")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<ReaderEntity>>() {
                })
                .returnResult()
                .getResponseBody();
```

`@TestInstance` - создает общий экземпляр класса для всех тестов
```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
```

Как поднять не весь контекст приложения для тестирования:  
- Разместить класс, помеченный @SpringBootApplication/@SpringBootConfiguration (Евгений Борисов 2017г.) в корне тестового проекта (шляпа)
- Разместить внутри тестового класса статический внутренний класс, помеченный @Configuration + @ComponentScan("путь туда, где лежат нужные бины в соурсе")
- @Import(Someclazz.class) - но у бина должно быть задано имя вручную, типа @Component("someName")
- @SpringBootTest(classes = {SomeClazz.class})
- @SpringBootTest/@DataJpaTest/@WebMvcTest... + @ContextConfiguration(classes = {SomeTestConfiguration.class}) - сработает в чистом Спринге. В контекст конфиг передавать тестовый конфиг, где создаем тестовые бины

> если тестовый application.yml называется кастомно, например - application_test_profile.yml, то использовать @ActiveProfiles("test_profiles")

Для использования кастомного property:  
```java
@TestPropertySource("classpath:test.properties")
@SpringBootTest
```

> SpringBootTest может кэшировать похожие бины, поэтому их состояние может передаваться между тестовыми классами. Для избежания, указывать явно необходимые бины: @SpringBootTest(classes = {.....})

`ЯВНО ПЕРЕСОЗДАТЬ КОНТЕКСТ`:  
```java
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest
```

Также можно обновить контекст на уровне метода: 
```java
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
@Test
```

`Кэширование контекста`, поднимаемого для тестирования:  
1. @SpringBootTest над каждым тестовым классом
2. @ContextHierarchy(
    {
        @ContextConfiguration(classes = {перечислять классы в нужно порядке! для инъекции})
    }
)
3. logging.level.org.springframework.test.context.cache=debug - больше инфы про кэшируемые бины
4. сделай общий абстрактный класс со всеми аннотациями


`@DataJpaTest` - сканирует вверх в поиске SpringBootConfiguration, а позже вниз ищет все бины @Reposiroty

`@WebMvcTest` - сканирует вверх в поиске SpringBootConfiguration, а позже вниз ищет все бины 
Пример тестов:
```java
@WebMvcTest(PersonController.class)
class PersonControllerTest {

    public static final String ERROR_STRING = "Таких тут нет!";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    //...

    @Test
    void shouldReturnCorrectPersonByNameInRequest() throws Exception {
        Person person = new Person(1, "Person1");
        given(repository.findByName(person.getName())).willReturn(List.of(person));
        PersonDto expectedResult = PersonDto.toDto(person);

        mvc.perform(get("/persons").param("name", person.getName()))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }
```

Еще один примет тестирования классических контроллеров @Controller с @WebMvcTest
```java
    @Test
    void createCommentInvalid() throws Exception {
        long bookId = 1L;
        BookDto book = new BookDto(bookId, "Book Title", new AuthorDto(), List.of(new GenreDto(1L, "Genre")));
        List<CommentDto> comments = List.of(new CommentDto(1L, "Comment 1", book));

        Mockito.when(bookService.findById(bookId)).thenReturn(book);
        Mockito.when(commentService.findAllByBookId(bookId)).thenReturn(comments);

        mvc.perform(MockMvcRequestBuilders.post("/{bookId}", bookId)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("text", "") // Invalid input for 'text' field
                        .param("bookId", String.valueOf(bookId)))
                .andExpect(status().isOk())
                .andExpect(view().name("book"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("comments"))
                .andExpect(model().attributeExists("genres"));
    }
```

@Controller
  * добавить необходимые бины: @ContextConfiguration(classes = {SomeTestConfiguration.class})
  * @Autowired MockMvc MockMvc - прям поле, чтобы не поднимать Tomcat
  * чтобы замокать сервисы контроллеров - можно поставить @MockBean(class...) - на каждый сервис прямо над тестовым классом (не поле)


`@EnableConfigurationProperties`(MinioProperties.class) - Для тестирования проперти-класса:  
```java
@Configuration //Включит enableConfigurationProperties
@EnableConfigurationProperties(MinioProperties.class)
public class MinioTestConfig {

//property-класс
    @Bean
    @Primary
    MinioProperties minioProperties() {
        return new MinioProperties();
    }
}
```

`перед транзакцией` - для каких-то действий
```java
@BeforeTransactional 
@Test
//здесь доп. метод для действий, которые будут выполняться перед каждой транзакцией
```

`после транзакции` - для каких-то действий
```java
@AfterTransactional
@Test
//здесь доп. метод для действий, которые будут выполняться после каждой транзакции
```

принудительно `завиксировать транзакцию в тестах с @JdbcTest`
```java
@Commit
//или
@Rollback(value = false)
```

Для тестирования JPA:  `TestEntityManager` - работает только в рамках @Transactional (по умолчанию есть) 
```java
@Autowired
private TestEntityManager em;
```

Пример теста с `TestEntityManager`:  
```java
@DisplayName("Репозиторий на основе Jpa для работы со студентами ")
@DataJpaTest
@Import(OtusStudentRepositoryJpaImpl.class)
class OtusStudentRepositoryJpaImplTest {
private static final long FIRST_STUDENT_ID = 1L;
@Autowired
private OtusStudentRepositoryJpaImpl repositoryJpa;
@Autowired
private TestEntityManager em;
@DisplayName(" должен загружать информацию о нужном студенте по его id")
@Test
void shouldFindExpectedStudentById() {
val optionalActualStudent = repositoryJpa.findById(FIRST_STUDENT_ID);
val expectedStudent = em.find(OtusStudent.class, FIRST_STUDENT_ID);
assertThat(optionalActualStudent).isPresent().get()
.isEqualToComparingFieldByField(expectedStudent);
}
}
```