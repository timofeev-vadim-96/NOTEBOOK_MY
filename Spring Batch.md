
# Spring Batch 

Предназначен для миграции данных из различных форматов в базу данных

[Пример Otus](https://github.com/OtusTeam/Spring/tree/master/2024-05/spring-25-spring-batch/src/main/java/ru/otus/example/springbatch)

`Зависимость`
```xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-batch</artifactId>
</dependency>

```

`ETL` (Extract, Transform, Load) - извлечение, преобразование и загрузка. Процесс, с помощью которого данные
загружаются из одного или нескольких источников,
приводятся к общему виду и сохраняются в одно (или
более) хранилище данных

* В процессе трансформации данные часто проходят
процедуру очистки, при которой откидываются не
валидные записи. Так же данные могут быть
дополнены/обогащены

* Источники данных могут быть любые
  * файлы разных типов (CSV, XLS и т.д.);
  * базы данных;
  * очереди;

`ETL-процессы` помогают в решении следующих задач:
* миграция между базами данных разных типов
* облачная миграция;
* наполнение хранилищ данных;
* подготовка данных для машинного обучения;
* репликация базы данных 

`Терминология`  
* **Job** – собственно, абстракция «работы» - содержит
описание шагов, из которых состоит обработка
данной «работы».
* **Step** – шаг работы. Может быть стандартным
(чтение из CSV, например), а может быть кастомным
– преобразование, или отправка в какой-нибудь
сервис
* **Item** – элемент обработки (строчка в БД, строчка в
CSV файле, Ваш доменный объект)
* **Chunk** – «чанк», пачка – набор item-ов,
которые будут обрабатываться за «раз»

**Step** обычно состоит из стандартного набора
«обработчиков»

* **ItemReader** (прочитать) – специальный интерфейс для чтения
какого-то Item
* **ItemProcessor** (преобразовать) – принимает один Item, но возвращает
в общем виде уже другой (а может и этот) – этот шаг,
нам нужно будет задавать самостоятельно
* **ItemWriter** (записать) – записывает полученный Item куда-то
* JobInstance – создается перед
выполнением каждой Job-ы. Похоже на
отношение класс-экземпляр. Есть свой ID
* JobExecution – создается для каждого
выполнения конкретного JobInstance.
Привязано по ID. У одного JobInstance
может быть несколько JobExecution
* StepExecution – тоже самое, но для
шага
* JobRepository – репозиторий для
взаимодействия с хранилищем всех Jobов, execution-ов и всех остальных
сущностей
* SimpleJobRepository – его простая
реализация
* Но может использовать непростые DAO
для хранения Job-ов

`JobRegistry`
Работа с Job-ами  (после 2023 года - автоматически находится в авто конфиге (это `JobRegistry`))
* JobRegistry – позволяет динамически
работать с Job-ами. Регистрировать (и
наоборот), получать список имен Job-ов,
получать Job-ы по имени
* MapJobRegistry – его реализация
* Job-ы, которые мы сделали бинами может
автоматически зарегистрировать
JobRegistryBeanPostProcessor
```java
@SuppressWarnings("unused")
//@Configuration
public class BatchConfig {
    @Bean
    public JobRegistryBeanPostProcessor postProcessor(JobRegistry jobRegistry) {
        var processor = new JobRegistryBeanPostProcessor();
        processor.setJobRegistry(jobRegistry);
        return processor;
    }
}
```

`Ридеры`:  
• AbstractCursorItemReader
• AbstractItemCountingItemStreamIt
emReader
• AbstractItemStreamItemReader
• AbstractPaginatedDataItemReader
• AbstractPagingItemReader
• AmqpItemReader
• AvroItemReader
• FlatFileItemReader
• HibernateCursorItemReader
• HibernatePagingItemReader
• ItemReaderAdapter
• ItemStreamReader
• IteratorItemReader
• JdbcCursorItemReader
• JdbcPagingItemReader
• JmsItemReader
• JpaCursorItemReader
• JpaPagingItemReader
• JsonItemReader
• KafkaItemReader
• LdifReader
• ListItemReader
• MappingLdifReader
• MongoItemReader
• MultiResourceItemReader
• Neo4jItemReader
• PeekableItemReader
• RepositoryItemReader
• ResourceAwareItemReaderItemStr
eam
• ResourcesItemReader
• SingleItemPeekableItemReader
• StaxEventItemReader
• StoredProcedureItemReader
• SynchronizedItemStreamReader

`Врайтеры: ` 
• AbstractFileItemWriter
• AbstractItemStreamItemWriter
• AmqpItemWriter
• AvroItemWriter
• ClassifierCompositeItemWriter
• CompositeItemWriter
• FlatFileItemWriter
• ItemWriterAdapter
• JdbcBatchItemWriter
• JmsItemWriter
• JpaItemWriter
• JsonFileItemWriter
• KafkaItemWriter
• KeyValueItemWriter
• ListItemWriter
• MimeMessageItemWriter
• MongoItemWriter
• MultiResourceItemWriter
• PropertyExtractingDelegatingItem
Writer
• RepositoryItemWriter
• SimpleMailMessageItemWriter
• StaxEventItemWriter
• SynchronizedItemStreamWriter

`Примеры Reader-ов`:  
```java
//JPA
    @Bean
    public JpaPagingItemReader<Book> itemReader(EntityManagerFactory emf) {
        return new JpaPagingItemReaderBuilder<Book>()
                .name("bookReader")
                .entityManagerFactory(emf)
                .queryString("select b from Book b")
                .pageSize(1000)
                .build();
    }

//Mongo
@StepScope
@Bean
public MongoPagingItemReader<Book> itemReader(MongoTemplate template) {
        return new MongoPagingItemReader<Book>()
                .name("mongoItemReader")
                .template(template)
                .jsonQuery("{}")
                .targetType(Book.class)
                .sorts(new HashMap<>())
                .build();
    }

//Из файла
    @StepScope
    @Bean
    public FlatFileItemReader<Person> reader(@Value("#{jobParameters['" + INPUT_FILE_NAME + "']}") String inputFileName) {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new FileSystemResource(inputFileName))

                .delimited()
                .names("name", "age")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Person.class);
                }}).build();
    }
```

Пример `Job`
```java
@Bean
public Job footballJob() {
return new JobBuilder("footballJob“
, jobRepository)
.start(playerLoadStep())
.next(gameLoad())
.next(playerSummarization())
.end()
.build();
}
```

Пример `Step`  
```java
@Bean
public Step playerLoadStep() {
return new StepBuilder("playerLoad", jobRepository)
.<Person, Person>chunk(5, platformTransactionManager)
.reader(reader())
.processor(itemProcessor())
.writer(writer())
.build();
}
```

`Tasklet` - для разовых действий, таких как подготовка структуры БД, создание временных таблиц, папок, а также их последующее удаление. [Подробнее](https://docs.spring.io/spring-batch/reference/step/tasklet.html)

`Как запустить Job`:  
* spring.batch.job.enabled – при true (значение
по умолчанию) все Job-ы запустятся на
старте приложения
* С помощью бина `JobLauncher` – принимает на
вход Job-у. ДЛЯ ОБЫЧНЫХ КОНСОЛЬНЫХ УТИЛИТ
* С помощью бина `JobOperator` – принимает
на вход имя Job-ы. Так что запускаются
только зарегистрированные в
JobRegistry  
* Кроме запуска у JobOperator есть еще
много функций для мониторинга и
управления Job-ами
* Еще есть `JobExplorer`. Это JobOperator с
урезанным функционалом. Работает
только на ЧТЕНИЕ

`Параметры`
* Параметры передаются при запуске Job-ы
* В JobOperator-е передаются через строку
формата name=value или объекта Properties
* В JobLauncher-е передаются с помощью
объекта JobParameters (созадется через
JobParametersBuilder)
* Могут быть получены через @Value у
компонентов степа, определенных в
сокоупе @StepScope

Пример `задания параметра работе` (через @Value):  
```java
    @StepScope //обязательно
    @Bean
    public FlatFileItemReader<Person> reader(@Value("#{jobParameters['" + INPUT_FILE_NAME + "']}") String inputFileName) {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new FileSystemResource(inputFileName))

                .delimited()
                .names("name", "age")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Person.class);
                }}).build();
    }
```

`@StepScope` - означает, что бин будет создан тогда, когда наступит шаг - уже с введенными параметрами. ЕСЛИ В ШАГЕ НЕТ ПАРАМЕТРОВ, то АННОТАЦИЯ НЕ НУЖНА

## Алгоритм использования:
1. Подключить стартер
2. **НЕ НУЖНО** Выставить над конфигом @EnableBatchProcessing
3. Сконфигурировать Reader-ы, Writer-ы и Processor-ы в
виде бинов
4. Собрать из них Step-ы
5. А из Step-ов Job-у
6. Если планируем работать с JobOperator не забыть
зарегистрировать Job-у
7. Запустить Job-у

## Тестирование

spring-batch-test

Зависимость
```xml
        <dependency>
            <groupId>org.springframework.batch</groupId>
            <artifactId>spring-batch-test</artifactId>
            <scope>test</scope>
        </dependency>
```

[Пример OTUS](https://github.com/OtusTeam/Spring/blob/master/2024-05/spring-25-spring-batch/src/test/java/ru/otus/example/springbatch/config/ImportUserJobTest.java)

Spring-batch-test
* Содержит класс-чекер `AssertFile` - позволяет сравнить два файла по содержимому DEPRECATED! сравнить два файла по содержимому можно:  
```java
        assertThat(new File(TEST_OUTPUT_FILE_NAME))
                .hasSameTextualContentAs(new File(expectedResultFileName), StandardCharsets.UTF_8);
    }
```
* Содержит `@SpringBatchTest`
* Который позволяет внедрять в тесты такие штуки
как:
* JobRepositoryTestUtils
* JobLauncherTestUtils - работает только для одной работы
* StepScopeTestUtils
* НЕ АНАЛОГ @SpringBootTest. Его нужно указать
отдельно. Иначе не поднимется контекст

> Как запараллелить процессы в работе (job) [ссылка](https://docs.spring.io/spring-batch/reference/scalability.html)