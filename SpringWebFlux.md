
# Spring Web Flux

> в спринг буте в продакшне - конфигурировать вручную бины, чтобы было больше управления. пример от [OTUS](https://github.com/OtusTeam/Spring/blob/master/2024-05/spring-41-kafka-webflux/client/src/main/java/com/datasrc/config/ApplConfig.java)
  * можно задать кол-во потоков, обрабатывающих инфу на вход
  * и на выход

- инструмент для использования "Event Loop" - когда есть 1 поток. Он сам не работает, но лишь коммутирует работу (этот единственный поток строго нельзя блокировать)

`Когда использовать?`  
  * если нагрузка CPU-bound, то есть присуствуют какие-либо расчеты, бизнес-логика или работы с БД, то лучше MVC
  * если нагрузка IO-bound (по сети), принять запрос и отдать другому приложению (пример: api-gateway), то идеально подходит реактивный подход

(Реактивное программирование)

- основано на потоках данных
- автоматическое распределение изменений между потоками
- реактивный - значит реакция на события (как ячейки в Excel. При обновлении значения ячейки, учавствующей в определении суммы, сумма изменится)

[Манфиест реактивщины](https://www.reactivemanifesto.org/):
- приложение должно быть:
  - Responsive (отзывчивым) - важно, чтобы в момент долгих процессов пользователь видел, что на приложение отзывается, что-то происходит и т.д.. Как пример установка виндовс, когда есть ползунок загрузки и меняющиеся картинки
  - Resilent - устойчивым
  - Elastic - эластичным (время ответа от приложения будет зависеть от количества запросов/сек и не падать в часы пик)
  - Message Driven - ориентированным на события

`Причины применения`:
* Эффективное использование ресурсов - позволяет обрабатывать бОльшее количество запросов в секунду, просто медленее для каждого отдельного запроса
* Использование реактивного API

## Web Flux

[Пример OTUS](https://github.com/OtusTeam/Spring/tree/master/2024-05/spring-38-reactor)

`Web flux` основан на Project Reactor

> [Визуальное отображение того, как рабатают некоторые реактивные операторы](https://rxmarbles.com/)

### Flux и Mono

`Flux` (по сути бесконечная Collection) - Publisher (источник данных) от 0 до N элементов  
`Mono` (по сути Optional) - Publisher (источник данных) от 0 до 1 элементов  

`Виды паблишеров`:
* холодный - когда мы можем обратиться к нему через какое-то время и получить те же данные (предзаписанное видео)
* горячий - когда данные при обращении к паблишеру всегда будут только свежие (передача по радио)

`Flux методы`
* doOnNext - аналог peek. взять данные. item.get делать не придется. данные уже в потоке
* doFirst - действие перед работой с данными
* doOnComplete - действие после окончания работы с данными
* doOnEach - сделать действие с каждым элементом потока данных. При этом, последнее будет null по окончании потока данных
* map - преобразование данных
* subscribe - подписчик на событие появления данных. В данном случае обхявленный Flux - продюсер данных
* Flux.defer - создать ленивого продюсера, который сходит за данными только при обращении к нему от сабскрайбера
* Flux.just - создать флакс из колеекции данных
* Flux.create - создать флакс, определяю данные лямбду
* Flux.fromIterabl - создать флакс из коллекции
* Flux.merge - объединить несколько потоков данных
* Flux.generate - сгенерировать значения для коллекции данных Flux 
* Flux.range(1, 10) - вернуть значения от 1 до 10 (или 9?)
* .delayEmelents(Duration.ofSeconds(3), timer) - задержка между выдачей элементов timer - Scheduler 
* complete - закрыть поток данных коллекции Flux
* onErrorResume - обработка ошибок

> Bad practice: из блокирующего вызова делать Mono.just()... тогда приложение будет вывозить количество ядер = количество потоков = количество запросов, которое будет вывозить. Так что еще хуже, чем было в MVC

> с блокирующим API внешнего сервиса делать так: 
```java
//config
    @Bean(name= "blockingExecutor", destroyMethod = "close")
    public Executor blockingExecutor() {
        var id = new AtomicLong(0);
        return Executors.newFixedThreadPool(BLOCKING_THREAD_POOL_SIZE,
                task -> new Thread(task, String.format("blocking-thread-%d", id.incrementAndGet())));
    }

//в методе вызова блокирующего источника данных

        var future = CompletableFuture
                .supplyAsync(() -> dataProducerStringBlocked.produce(seed), //dataProducerStringBlocked - тупо генерит строки
                blockingExecutor); //выполнять эти запросы в отдельных потоках экзекьютера
        var mono  = Mono.fromFuture(future);
        return mono;
```

> считается, что если внутри реактивных операторов находится if или циклы, то вы что-то делаете не так

`Flux примеры`
```java
    private static void onEachNext() {
        Flux<String> obs = Flux.just("one", "two", "three"); //producer данных
        obs.doFirst(() -> logger.info("Starting:")) //действие на старте
                .doOnComplete(() -> logger.info("The end!")) //действие по завршении данных
                .doOnEach(item -> logger.info("item_1:{}", item.get())) //действие для каждого элемента
                .subscribe(); //подписчик

        logger.info("-----");

//здесь фишка в том, что не надо делать item.get(). мы сразу работаем с данными. 
        obs.doOnNext(item -> logger.info("item_2:{}", item)) //действие для первого элемента
                .map(String::length)
                .doOnNext(item -> logger.info("length_2:{}", item)) //действие для всех последующих элементов
                .subscribe();
    }
```

`Пример Flux-ленивого продюсера`. Он сходит за данными, если подписчик обрабится за данными
```java
    private static void lazyObservable() {
        Flux<String> obs = Flux.defer(() -> {
            logger.info("creating new Observable");
            return Flux.just("one", "two", "three");
        });

        obs.doOnNext(item -> logger.info("item_1:{}", item))
                .subscribe();

        logger.info("---------------");

        obs.doOnNext(item -> logger.info("item_2:{}", item))
                .subscribe();
    }
```

`Пример Flux через create с обработчиком ошибок`
```java
    private static void creatorExample() {
        Flux<String> obs = Flux.create(emitter -> {
            emitter.next("one");
            emitter.next("two");

            emitter.error(new RuntimeException("Error!"));

            emitter.next("three");
            emitter.complete();
        });

//обработка ошибок
        obs.onErrorResume(e -> {
                    logger.error("error:{}", e.getMessage(), e);
                    //здесь пример с переключением на другой источник данных
                    return Flux.just("r1", "r2", "r3");
                })
                .doOnNext(item -> logger.info("item__1:{}", item))
                .subscribe();

        logger.info("---------------");

//Disposable - класс для того, чтобы проверять закрыт ресурс или нет. Если паблишер закрыто, то disposable.isDisposed = false
        Disposable disposable = obs.doOnNext(item -> logger.info("item__2:{}", item))
                .subscribe(next -> logger.info("next:{}", next),
                        error -> logger.info("error:{}", error.getMessage()),
                        () -> logger.info("onComplete"));

        logger.info("isDisposed:{}", disposable.isDisposed()); //показывает отработал ли продюсер данных
    }
```

`Flux в Mono пример`
```java
    private static void fromList() {
        var persons = List.of(
                new Person("John", "Dow", "male", LocalDate.of(1992, 3, 12)),
                new Person("Jane", "Dow", "female", LocalDate.of(2001, 6, 23)),
                new Person("Howard", "Lovecraft", "male", LocalDate.of(1890, 8, 20)),
                new Person("Joanne", "Rowling", "female", LocalDate.of(1965, 6, 30)));

        var disposable = Flux.fromIterable(persons)
                .filter(person -> person.birth().isAfter(LocalDate.of(1990, 1, 1)))
                .map(p -> p.firstName() + " " + p.lastName())
                .collectList() //здесь мы ждем, пока данных в Flux закончатся и собираем их в Mono<List>. Например, для сохранения в БД одним запросом, пачкой
                .subscribe(item -> logger.info("item: {}", item));

        logger.info("disposable.isDisposed:{}", disposable.isDisposed()); //показывает отработал ли продюсер данных
    }
```

`Горячий продюсер, пример`
```java
    public static Flux<String> magicPublisher() {
        var schedulerGenerator = Schedulers.newParallel("generator", 1); //шедулер
        var generator = Flux.generate(
                        () -> 0L, //prev ниже. начальное значение
                        (BiFunction<Long, SynchronousSink<Long>, Long>)
                                (prev, sink) -> {
                                    var newValue = prev + 1;
                                    sink.next(newValue); //это значение отправится на выход
                                    logger.info("newValue:{}", newValue);
                                    return newValue; //это станет следующим предыдующим значением
                                })
                .delayElements(Duration.ofSeconds(5), schedulerGenerator) //задержка в выдаче данных без блокировки потока. На основе шедулера
                .map(id -> "new id:" + id) //можно в поток данных: val-> new StreamData(...). Тогда метод возвращает Flux<StreamData>
                .doOnNext(val -> logger.info("val:{}", val));

        ConnectableFlux<String> generatorConnectable = generator.publish(); //подключаемый продюсер

        return generatorConnectable.autoConnect(0); //активировать Flux даже без подписчиков
    }
```

## Spring WebFlux

[Пример OTUS](https://github.com/OtusTeam/Spring/tree/master/2024-05/spring-40-webflux)

* в основе Reactor Netty (Внутри NIO - низкоуровневый API)

> в spring boot современных версий использовать `@EnableWebFlux` не нужно

`Пример контроллера на WebFLux`
```java
@RestController
public class AnnotatedController {
 @GetMapping ("/flux/one")
 public Mono<String> one() { //возвращаем ничего или 1 значение
 return Mono.just("one");
 }
 @GetMapping ("/flux/ten")
 public Flux<Integer> list() { //возвращаем ничего или множество значений
 return Flux.range( start: 1, count: 10);
 }
```

В случае, когда нужно работать с БД в реактивном стиле - подключать `реактивный драйвер`
```yml
spring:
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/demo
    username: postgres
    password: admin
```

Пример `доменной сущности/модели` для реактивного стека:
  * принято использовать final поля
```java
@Table("person")
public class Person {

    @Id
    private final Long id;

    @NotNull
    private final String lastName;

    private final int age;


    @PersistenceCreator //обозначает, что этот конструктор будет использован при создании экземпляра класса с помощью Spring Data, игнорируя наличие других конструкторов, за ислючением ПУСТОГО
    private Person(Long id, @NotNull String lastName, int age) {
        this.id = id;
        this.lastName = lastName;
        this.age = age;
    }
```

`@PersistenceCreator.` Есть нюансы использования  
Spring Data автоматически пытается определить конструктор сохраняемого объекта, который будет использоваться для материализации объектов этого типа. Алгоритм разрешения работает следующим образом:
* Если есть один статический фабричный метод, аннотированный @PersistenceCreator, то он используется.
* Если есть один конструктор, он используется.
* Если существует несколько конструкторов и только один из них помечен @PersistenceCreator, то используется именно он.
* Если тип является записью Java, используется канонический конструктор.
* Если есть конструктор без аргументов, он используется. Другие конструкторы игнорируются.

Пример `реактивного JPA-репозитория`:
```java
//наследоваться от другого интерфейса. Возвращать если 1 объект - Mono, если много - Flux
public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {

    @NotNull Mono<Person> findById(@NotNull Long id);

    Mono<Person> save(Mono<Person> person);

    Flux<Person> findAllByLastName(String lastName);

    Flux<Person> findAllByAge(int age);
}
```

Пример `метода, работающего с БД на реактивном драйвере`:
```java
    private final Scheduler workerPool; //можно без него
    /*
    @Bean
    public Scheduler workerPool() {
        return Schedulers.newParallel("worker-thread", THREAD_POOL_SIZE);
    }
    */

    //...some code

    @Override
    public void run(ApplicationArguments args) {
        personRepository.saveAll(Arrays.asList(
                new Person("Pushkin", 22),
                new Person("Lermontov", 22),
                new Person("Tolstoy", 60)
        )).publishOn(workerPool) //можно не указывать, тогда будет использован дефолтный. Но так у нас больше управления настройками
                .subscribe(savedPerson -> {
                    logger.info("saved person:{}", savedPerson);
                    notesRepository.saveAll(Arrays.asList(
                                    new Notes(null, "txt_1_" + savedPerson.getId(), savedPerson.getId()),
                                    new Notes(null, "txt_2_" + savedPerson.getId(), savedPerson.getId())))
                            .publishOn(workerPool)
                            .subscribe(savedNotes -> logger.info("saved notes:{}", savedNotes));
                });

//эта штука выполнится параллельно коду выше. И, скорее всего, не выдаст ни одного результата, т.к. выполнится быстрее, чем записи сохранятся в БД. Чтобы гарантированно получить результат, следует выполнять с подпиской на результаты прямо в ОДНОМ ПАЙПЛАЙНЕ
        personRepositoryCustom.findAll()
                .publishOn(workerPool)
                .subscribe(personDto -> logger.info("personDto:{}", personDto));
    }

```

> Поскольку в реактивных приложениях не должно быть бизнес-логики, зачастую отсутствует уровень СЕРВИСОВ. Контроллер напрямую обращается к репозиторию.

Пример эндпоинта контроллера WebFlux, напрямую обращающегося к репозиторию
```java
    @GetMapping("/person/{id}")
    public Mono<ResponseEntity<PersonDto>> byId(@PathVariable("id") Long id) {
        return personRepository.findById(id)
                .flatMap(person -> notesRepository.findByPersonId(person.getId()).map(Notes::getNoteText).collectList() //по идее, здесь мы собираем в Mono<List<String>>
                        .map(notes -> toDto(person, notes)))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }
```

> Реактивные приложения обычно не предназначенны для работы с аналитическими БД. запросы не должны сталкиваться с проблемами n+1 в обычном случае.

Пример `метода репозитория с SQL запросом, решающим проблему n+1` для реактивного стека. Спойлер: все вручную. Зависимую сущности конвертятся в json
```java
    private final R2dbcEntityTemplate template;
    private final ObjectMapper objectMapper;

//база будет конвертить зависимые сущности в json, и группировать по основным сущностям
    private static final String SQL_ALL = """
            select json_agg(n.note_text) as notes, n.person_id, 
                   p.last_name, p.age
                 from notes n
              inner join person p
                on n.person_id = p.id
                group by n.person_id, p.last_name, p.age
            """;

    public Flux<PersonDto> findAll() {
        return template.getDatabaseClient().inConnectionMany(connection ->
        //выполнить JDBC запрос
                Flux.from(connection.createStatement(SQL_ALL)
                                .execute())
                        .flatMap(result -> result.map(this::mapper)));
    }

    private PersonDto mapper(Readable selectedRecord) {
        var notesAsText = selectedRecord.get("notes", String.class);
        try {
            List<String> notes = objectMapper.readValue(notesAsText, new TypeReference<>() {
            });
            return new PersonDto(selectedRecord.get("person_id", String.class),
                    selectedRecord.get("last_name", String.class),
                    selectedRecord.get("age", Integer.class),
                    notes);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("notes:" + notesAsText + " parsing error:" + e);
        }
    }
```


### Функциональные эндпоинты Web Flux / Function endpoints

  * альтернатива аннотаций над методами контроллеров - ХЗ ЗАЧЕМ

`Роутер` - связующее звено между URL запроса и методом, который должен по нему ответить

Пример роутера (эндпоинта)
```java
@Bean
public RouterFunction<ServerResponse> monoRouterFunction() {
 HandlerFunction<ServerResponse> hello = request ->
 ServerResponse.ok().body(fromObject(body: "Hello"));
 return route(GET( pattern: "/hello"), hello);
}
```

> очевидно, что методы, которые нужно вызвать по определенному URL, можно выносить в другие классы

Пример этого: 
```java
@Bean
public RouterFunction<ServerResponse> composedRoutes(PersonRepository repository) {
 PersonHandler handler = new PersonHandler(repository);
 RouterFunction<ServerResponse> route = route()
 .GET ( s: "/func/person", accept(APPLICATION_JSON), handler::list);
}
static class PersonHandler {
 private PersonRepository repository;
 PersonHandler(PersonRepository repository) {
 this.repository = repository;
 }
 Mono<ServerResponse> list(ServerRequest request) {
 return ok().contentType(APPLICATION_JSON)
 .body(repository.findAll(), Person.class);
 }
}
```

А можно через `роутер через лямбду`:
```java
@Bean
public RouterFunction<ServerResponse> composedRoutes(PersonRepository repository) {
 return route()
 .GET( s: "/func/person/{id}", accept{APPLICATION JSON),
 request -> repository.findByld(request.pathVariable(name: "id"))
 .flatMap(
 person -> ok().contentType{APPLICATION_JSON)
 .body(fromObject(person))
 )
 ).build();
}
```

А можно через `композитный роутер` (несколько эндпоинтов определяем каскадом)
```java
@Configuration
public class FunctionalEndpointsConfig {
    @Bean
    public RouterFunction<ServerResponse> composedRoutes(PersonRepository repository) {
        return route()
                // эта функция должна стоять раньше findAll - порядок следования роутов - важен
                .GET("/func/person",
                        queryParam("name", StringUtils::isNotEmpty),
                        request -> request.queryParam("name")
                                .map(name -> ok().body(repository.findAllByLastName(name), Person.class))
                                .orElse(badRequest().build())
                )
                // пример другой реализации - начиная с запроса репозитория
                .GET("/func/person", queryParam("age", StringUtils::isNotEmpty),
                        request ->
                                ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(repository.findAllByAge(request.queryParam("age")
                                                        .map(Integer::parseInt)
                                                        .orElseThrow(IllegalArgumentException::new)), Person.class)
                )
                // Обратите внимание на использование хэндлера
                .GET("/func/person", accept(APPLICATION_JSON), new PersonHandler(repository)::list)
                // Обратите внимание на использование pathVariable
                .GET("/func/person/{id}", accept(APPLICATION_JSON),
                        request -> repository.findById(Long.parseLong(request.pathVariable("id")))
                                .flatMap(person -> ok().contentType(APPLICATION_JSON).body(fromValue(person)))
                                .switchIfEmpty(notFound().build())
                ).build();
    }

    // Это пример хэндлера, который даже не бин
    static class PersonHandler {

        private final PersonRepository repository;

        PersonHandler(PersonRepository repository) {
            this.repository = repository;
        }

        Mono<ServerResponse> list(ServerRequest request) {
            // Обратите внимание на пример другого порядка создания response от Flux
            return ok().contentType(APPLICATION_JSON).body(repository.findAll(), Person.class);
        }
    }
}
```

`Mono.defer` - пример  
```java
    private Mono<Book> save(String id, String title, String authorId, Set<String> genresIds) {
        if (isEmpty(genresIds)) {
            return Mono.error(new IllegalArgumentException("Genres ids must not be null"));
        }

        return Mono.zip(authorService.findById(authorId), getGenres(genresIds))
                .flatMap(tuple -> Mono.defer(() -> {
                    if (id != null) {
                        return bookRepository.findById(id)
                                .switchIfEmpty(Mono.error(
                                        new EntityNotFoundException("Book with id = %s is not found".formatted(id))))
                                .flatMap(book -> {
                                    book.setTitle(title);
                                    book.setAuthor(AuthorDto.fromDto(tuple.getT1()));
                                    book.setGenres(new HashSet<>(tuple.getT2()));
                                    return bookRepository.save(book);
                                });
                    } else {
                        Book book = new Book(null, title,
                                AuthorDto.fromDto(tuple.getT1()),
                                new HashSet<>(tuple.getT2()));
                        return bookRepository.save(book);
                    }
                }));
    }
```

Для обработки ошибок системного уровня в WebFlux:
```java
        Hooks.onErrorDropped(
                error -> {
                    if (error instanceof CancellationException) {
                        log.info("Cancellation event:", error);
                    } else {
                        log.error("error:", error);
                    }
                });
```