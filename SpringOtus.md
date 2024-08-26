Main OTUS repo: https://github.com/OtusTeam/Spring/tree/master

XML-конфигурация Spring-приложения (/resources/spring-context.xml):  

зависимость:
```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>6.1.8</version>
        </dependency>
    </dependencies>
```

`Main`  поднятие контекста:  
```java
ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        var testRunnerService = context.getBean(TestRunnerService.class);
```

1. внедрение через конструктор
ref/val
* val - строковое значение
* ref - ссылка на бин
```xml
<bean id="personSerivce" class = "ru.otus.spring.service.PersonServiceImpl">
    <constructor-arg index="0" ref="someBean"> //здесь ref - ссылка на имя бина
</bean>
```

2. внедрение через сеттер
```xml
<bean id="personSerivce" class = "ru.otus.spring.service.PersonServiceImpl">
    <property name="dao" ref="someBean"/>
</bean>
```

3. внедрение через val - константное значение (для полей)
```xml
<bean id="personSerivce" class = "ru.otus.spring.service.PersonServiceImpl">
    <property name="age" val="18"/>
</bean>
```

> @Lazy - для внедрения ЦИКЛИЧЕСКИХ зависимостей

**Что не нужно класть в контекст**  
* бизнес-объекты (бины, пользователи)
* поштучные настройки, кроме пачек/файлов настроек
* стандартные классы (String, InputStream, Scanner, Locale)

`Чекстайл (checkstyle):`  
```xml
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-checkstyle-plugin</artifactId>
                <version>${checkstyle-plugin.version}</version>
                <dependencies>
                    <dependency>
                        <groupId>com.puppycrawl.tools</groupId>
                        <artifactId>checkstyle</artifactId>
                        <version>${checkstyle.version}</version>
                    </dependency>
                </dependencies>
                <configuration>
                    <configLocation>${checkstyle.config.url}</configLocation>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>check</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```

---

`Создание прокси-объектов в spring:`   
1. JDK dynamic proxy - Spring AOP по умолчанию использует JDK dynamic proxy, которые позволяют проксировать любой интерфейс (или набор интерфейсов). Если целевой объект реализует хотя бы один интерфейс, то будет использоваться динамический прокси JDK.
2. CGLIB-прокси - используется по умолчанию, если бизнес-объект не реализует ни одного интерфейса.


@PropertySource("classpath:application.properties") - над конфигом (@Configuration), для того, чтобы проперти тянулись из application.properties

`Переопределить конфиг файла application.properties/yml`:  
1. Environment var (run/debug config приложения): some.property=не видишь суслика, а он есть #1
2. VM Option (run/debug config приложения): -Dsome.property=some.property="не видишь суслика, а он есть #2"
3. Системные свойства (в коде): System.setProperty("some.property", "не видишь суслика, а он есть #3")

`Локализация приложения` (язык):
/resources/Resource Bundle 'anyname' - папка
в ней:  
1. somename_en.properties /somename_ru_RU.properties - примеры (имя_локаль.properties)
2. somename.properties - для спрингбута ОБЯЗАТЕЛЬНО нужен файл ПУСТОЙ без лопкализации вназвании

`Класс для локализации сообщения`:
```java
@RequiredArgsConstructor
@Service
public class LocalizedMessagesServiceImpl implements LocalizedMessagesService {

    private final LocaleConfig localeConfig;

    private final MessageSource messageSource;

    @Override
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, localeConfig.getLocale());
    }
}

@ConfigurationProperties(prefix = "test")
public class AppProperties implements LocaleConfig {

    @Getter
    private final Locale locale;

    @ConstructorBinding
    public AppProperties(String locale) { 
        this.locale = Locale.forLanguageTag(locale);
    }
}
```

`конфиг для локализации:`
```yml
spring:
  messages:
    baseName: messages
    encoding: UTF-8
    # Не дает свалиться в системную локаль если не найден файл для переданной в MessageSource
    # Если false то будут браться сообщения из дефолтного файла, если true, то из файла соответствующего системной локали
    fallbackToSystemLocale: false
test:
  rightAnswersCountToPass: 4
  locale: ru-RU
  fileNameByLocaleTag:
    ru-RU: questions_ru.csv
    en-US: questions.csv
```

`Структура файлов для локализации`:  
![](images/localization_files_structure.png)
  * hw03 в ДЗ к OTUS

Чтобы добавить **баннер вместо дефолтного Спринговского**:  
1. в resource/ добавить файл banner.txt с нужным текстом
  * аски графика - рисунки с помощью текста


Варианты запуска приложения: [тык](https://habr.com/ru/articles/572828/)  
  * CommandLineRunner(Интерфейс) - реализовать метод run - запустит свое тело для запуска функционала при подъеме приложения