Alishev - youtube

`Framework` - каркас/платформа, которая определяет структуру системы (приложения), и облегчает разработку компонентов
системы и их интеграцию //предоставляет определенные паттерны разработки

## SPRING FRAMEWORK

от 2003г. 

**Компоненты:**
* Контекст приложения (Application Context) 
  * все объекты описываются в специальном кофигурационном файле для автомат. составления зависимостей между ними внитри структуры приложения
* Внедрение зависимостей (Dependency Injection)
* Удобный доступ к БД (замена JDBC)
* Компонент для разработки Web-приложений на Java (Spring MVC) (замена Java EE Servlets)
  * исп. в качестве backend-API для мобильных приложений
  * исп. для создания web-приложений

**Др. компоненты:**
* Spring Security - для безопасности и настройки Авторизации и аутентификации
* Spring Boot - помогает избавляться от лишней конфигурации приложения
* Spring Webflow
* др. spring.io/projects


При взаимодействии нескольких классов с одним (например DB):
* делает так, чтобы DB создавался лишь один раз
* внедряет ссылку на DB все остальным классам, которым необходимо с ней работать

Стандартная/дефолтная `структура` проекта Spring:
Используем  `архитип maven-webapp`: //хз, насоздавал целый ворох папок для sonarlint
1. файл web.xml - для обращения к приложению в дальнейшем на сервере /src/main/webapp/WEB-INF/web.xml
2. в resources - applicationContext.xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans  xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <bean id="testBean"
          class="org.example.TestBean"> <!--НЕЛЬЗЯ прописывать абстрактные вещи. Только класс конкретной реализации-->
        <constructor-arg value="Neil"/> <!--Аргумент, передаваемый в конструктор. Если нет конструктора - УДАЛИТЬ эту строку-->
    </bean>

</beans>
```

---

`ЗАВИСИМОСТИ`:
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId> <!--Основная зависимость-->
            <version>6.1.2</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId> <!--Доверить спрингу создание новых объектов для дальнейшего извлечения из Spring Application context-->
            <version>6.1.2</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>6.1.2</version>
        </dependency>

---

> В Spring - объекты, созданные фреймворком называются "Bean"

Элементарная работа с бинами через `КОНТЕКСТ`
```java
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); //файл лежит в resources
        TestBean test = context.getBean("TestBean", TestBean.class); //bean id, интерфейс или класс, указанный слева
        System.out.println(test.getName());
        context.close();
```

### Тема: Инверсия управления /IoC - Inversion of Control

`Инверсия управления` - такой архитектурный подход, когда мы передаем зависимости в конструктор, а не создаем их внутри класса. Создавать зависимости внутри классов - bad practice
Типа:
public class MusicPlayer {
  private Music music;
  public MusicPlayter(Music music){ //вот что такое инверсия управления. Внедряем зависимость ИЗВНЕ
    this.music = music;
  }
}

`Способы конфигурации Spring:`
1. XML-файл конфигурации - старый способ, но бывает встречается
2. Java-аннтотацией + XML - современный способ
3. Вся конфигурации на Java - тоже современный способ

### ЗАВИСИМОСТИ

### Тема: Внедрение зависимостей / Dependency Injection

Типичные шаги в работе с Spring:
1. Создаем java-классы (для будующих бинов)
2. Создаем и связываем бины с помощь Spring (аннтотации, XML или Java код)
3. При использовании, все объекты (бины) берутся из контейнера Spring

> Внедрение зависимостей в Spring возможно через `autowiring` //todo

Создать класс с внедрением зависимости через `КОНСТРУКТОР`: //далее создания этого класса через конекст
    <bean id="musicBean"
          class="org.example.RockMusic">
    </bean>
    <bean id="musicPlayer"
    class="org.example.MusicPlayer">
        <constructor-arg ref="musicBean"/> <!--Внедряем предыдущий бин в качестве аргумента в конструктор-->
    </bean>

Создать класс с внедрением зависимости через `СЕТТЕР`:
    <bean id="musicBean"
          class="org.example.RockMusic">
    </bean>
    <bean id="musicPlayer"
          class="org.example.MusicPlayer">
    <property name="music" ref="musicBean"/> <!--Задание зависимости через сеттер. Условие: должен быть 
    ПУСТОЙ конструктор--> //name - имя сеттера без слова "set", ref - id бина 
    Внедрение других зависимостей через сеттер:
    <property name="volume" value="10"/>

Чтобы `задать значения полей ЧЕРЕЗ ФАЙЛ`: создать className.properties в ресурсах, далее:
musicPlayer.name=Some name, в формате className.поле=значение //формат ключ-значение
musicPlayer.volume=50
```xml
    <context:property-placeholder location="classpath:musicPlayer.properties"/> <!--подключение файла со свойствами бина-->

    <bean id="musicBean"
          class="org.example.RockMusic">
    </bean>
    <bean id="musicPlayer"
          class="org.example.MusicPlayer">
    <property name="music" ref="musicBean"/> <!--Задание зависимости через сеттер. Условие: должен быть
    ПУСТОЙ конструктор-->
        <property name="name" value="${musicPlayer.name}"/> <!--Задание значения через файл-->
        <property name="volume" value="${musicPlayer.volume}"/>
</bean>
```

Создать бин-список:
    <bean id="myList" class="java.util.ArrayList">
        <constructor-arg>
            <list>
                <ref bean="classicBean" />
                <ref bean="popBean" />
                <ref bean="rockBean"/>
            </list>
        </constructor-arg>
    </bean>

### Тема: Область видимости бинов (Bean scope)
Scope - задает то, КАК Spring будет создавать бины
Виды scope:
* singleton - по умолчанию. При всех вызовах getBean() - возвращается один и тот же объект //используетяс тогда, когда у нашего бина нет изменяемых состояний (свойств)
Пример:  
```xml
   <bean id="classicBean" class="org.example.ClassicMusic" scope="singleton">
    </bean>
```
* prototype - создает новый объект каждый раз при вызове getBean() //используется тогда, когда у бина есть изменяемые состояния (stateful)
* request
* session
* global-session

### Тема: специальные методы бинов

* init-method - метод, вызываемый при создании бина. Обычно это: инициализация ресурсов, обращение к внешним файлам и запуск БД
* destroy-method - метод, вызываемый при уничтожении бина (завершении работы приложения). Обычно это: очищение ресурсов, закрытие потоков ввода-вывода, закрытие доступа к БД
  * для бинов с scope=prototype - метод уничтожения вызван не будет
* factory method - фабричный метод для создания объектов класса //см. NOTEBOOK
  * для бинов с scope=singletone - вызовется лишь единожды

Особенности init и destroy:
* любой модификатор доступа
* тип возвращаемого значения - любой. Но чаще всего void
* название методов - произвольное
* не может принимать аргументы на вход

Пример задания специальных методов: //разумеется, в классе должны быть реализованы эти методы (названия произвольные)
    <bean id="rockBean"
          class="org.example.RockMusic"
          scope="prototype"
          init-method="doMyInit"
          destroy-method="doMyDestroy"
          factory-method="fabric"> //если singleton - создастся лишь один объект, т.к. вызовется единожды
    </bean>

Жизненный цикл бина:
1. Запуск приложения
2. запускается Spring-контейнер
3. создается объект бина
4. в бин внедряются зависимости
5. вызывается указанный init-method
6. бин готок к использованию
7. вызывается указанный destroy-method
8. остановка Spring-приложения



### Тема: Spring `Аннотации`

`Java Аннотации` - специальный вид комментариев для передачи инструкций компллятору, либо анализаторам кода, а также метаданные, исполуемые другими приложениями или фреймворками.

+ @Component("musicBean") - для создания бина из класса. Можно указать id. по дефолту - id=название класса с маленькой буквы

`"Включить" аннотации`: <context:component-scan base-package="org.example"/>

+ @Autowired - для автоматического внедрения зависимостей. Добавлять над зависимостью в классе. Ищет подходящий тип по интерфейсу или классу
  * если спринг не найдет зависимость -> UnsatisfiedDependencyException
  * если спринг находит несколько подходящих бинов -> UnsatisfiedDependencyException
  * можно использовать как на конструкторе, так на поля и сеттерах
    * насчет ПОЛЯ - внедрит даже в приватное, даже без сеттера или конструктора (с помощью Reflection API)

> Какой способ выбрать (через конструктор, сеттер или поле)? - ЛЮБОЙ. но придерживаться выбранного во всем проекте.

+ @Qualifier("rockBean") - для уточнения бина, используемого при @Autowired. //rockBean - id бина //можно использовать на конструктарах, сеттерах и полях
  * НО! когда используем в конструкторе, указывать рядом с входящим аргументом вот так:
  ```java
    @Autowired
    public MusicPlayer(@Qualifier("rockBean") Music music) {
        this.music = music;
    }
    ```

+ @Value(${musicPlayer.volume}) - для задания значения ПОЛЮ бина через файл.
  * если через файл: <context:property-placeholder location="classpath:musicPlayer.properties"/>

+ @Scope("singleton") / @Scope("prototype") - задать scope - способ создания бинов (см. выше)

+ @PostConstruct - init-method (особенности выше) главное - не должны принимать методы и чаще всего это void

+ @PostDestroy - destroy-method (особенности выше) главное - не должны принимать методы и чаще всего это void

### Тема: Конфигурация Spring-приложения через Java (вместо XML)

+ @Configuration - для пометки класса-конфигурации для Spring
```java
@Configuration
@ComponentScan("org.example") //рекурсивно сканирует все бины в указанном пакете. @Value работает без него
public class SpringConfig {
}
```

> пустой такой класс = всему начальному конфигу в XML (до бинов)

+ @CompenentScan("org.example") - для поиска тегов бинов в пакете

+ @Bean - помечается метод внутри конфиг. класса для создания бина //по умолчанию - scope "singleton"
```java //пример создания через Бины
    @Bean
    public RockMusic rockMusic(){
        return new RockMusic();
    }
    @Bean
    public ClassicMusic classicMusic(){
        return new ClassicMusic();
    }
    @Bean
    public MusicPlayer musicPlayer(){
        return new MusicPlayer(rockMusic(), classicMusic());
    }
```

Базовая настройка в Main для работы с конфигом на Java:
```java 
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class); //SpringConfig - класс, созданный ручками. Мин. набор выше.
```

+ @PropertySource("classpath:musicPlayer.properties") - подключить конфигурационный файл к Spring


### Тема: Spring MVC

Предполагает архитектуру: Model - View - Contoller

`Spring MVC` - один из компонентов фреймворка, который позволяет разрабатывать Web-приложения на 

`Состав` Spring MVC - приложения:
1. Java POJO (контроллеры, модели и прочее) + аннотации
2. Набор HTML-страниц(представления). JS+HTML+CSS
3. Spring-конфигурация (XML/аннотации или Java)

Особенный компонент: DispatcherServlet
DispatcherServlet:
* является входной точкой приложения
* реализован за нас командой Spring

HTTP-запрос от пользователя:  
1. Приходит на сервер. Сервер его обрабатывает и передает запрос в приложение.
2. Запрос попадает в DispatcherServlet
3. DispatcherServlet отправляет запрос на правильный контроллер

MVC модель по-подробнее:
`Контроллер` - помечается аннотацией @Controller
1. Обрабатывает запросы от пользователя
2. Обменивается данными с моделью
3. Показывает пользователю правильное представление
4. Переадресовывает пользователя на другие страницы

`Модель`
1. Хранит в себе данные
2. Взаимодействует с БД (имеется ввиду через Hibernate)
3. Отдает данные контроллеры

`Представление`
1. Получает данные от контроллера и отображает их в браузере
2. Для динамического отображения данных используются шаблонизаторы (Thymeleaf, Freemarker, Velocity) //под каждого отдельного юзера страница генерируется с учетом его персональных данных аккаунта


Тема: MVC приложение с подключением Tomcat + конфигурация через .xml (устарело)

Связать приложение с сервером (Tomcat):

1. скачиваем Tomcat в ZIP
2. Добавить путь - Run - Edit Configuration - "+" - Tomcat SERVER!! (не TomcatEE!!!) - local - Configure -> указать путь -> кнопка FIX (war exploded) - внизу где application context (пустое значение - для того, чтобы не отображалось в URL позже)
  * после связи с Idea могут возникать баги -> лучше перезапустить среду разработки

> может потребоваться дать права на исполнение файла catalina.sh (на Unix-системах)

3. добавить доп. зависимостью для Spring MVC - Spring Web
3.1** добавить зависимость на шаблонизатор Thymeleaf Spring5
4. настроить `web.xml` (его будет читать сервер Tomcat) //старый способ
Пример:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         id="WebApp_ID" version="3.1">

    <display-name>spring-mvc-app1</display-name> <!--аналог @EnableWebMvc-->

    <absolute-ordering/>

    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/applicationContextMVC.xml</param-value> <!--обращение к XML файлу с конфигом-->
        </init-param>
        <load-on-startup>1</load-on-startup> <!--Запуск сервлета в первую очередь при запуске приложения-->
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern> <!--для перенаправления любого URL на dispetcher servlet-->
    </servlet-mapping>

</web-app>
```
5. создать и настроить для работы с `Tymeleaf` applicationContext.xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
		http://www.springframework.org/schema/beans
    	http://www.springframework.org/schema/beans/spring-beans.xsd
    	http://www.springframework.org/schema/context
    	http://www.springframework.org/schema/context/spring-context.xsd
    	http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="org.example"/>

    <mvc:annotation-driven/>

    <bean id="templateResolver" class="org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver">
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".html"/>
    </bean>

    <bean id="templateEngine" class="org.thymeleaf.spring6.SpringTemplateEngine">
        <property name="templateResolver" ref="templateResolver"/>
        <property name="enableSpringELCompiler" value="true"/>
    </bean>

    <bean class="org.thymeleaf.spring6.view.ThymeleafViewResolver">
        <property name="templateEngine" ref="templateEngine"/>
        <property name="order" value="1"/>
        <property name="viewNames" value="*"/>
    </bean>
</beans>
```
6. Создать контроллер с методом, в который будет приходить URL, и будет возвращать View
7. Создать представление в указанном applicationContext.xml месте
8. перезапустить сервак

`Аннотации MVC-приложения:`
@Controller - неявно наследуется от аннотации @Component
@GetMapping("some-url") //помечаются методы, которые будут вызываться при вызове определенной URL
@EnableWebMvc //поддержка работы с Web
@PathVariable("id") - помечается параметр в запросе, принимаемый на вход в качестве аргумента
  *     @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") int id){

    }
@ModelAttribute - 2 поведения:
  * когда аннотируем метод - для добавления во Все модали всех методов контроллера определенной пары ключ-значение.
  @ModelAttribute("someHeader")
  public String headerValue(){
    return "some value"; //можно возвращать что угодно. Значением может быть любой объект
  }
  * когда аннотируем аргумент метода - создает объект и добавляет параметры в качестве полей этого объекта + добавляет в модель пару ключ-значение
    * если параметры от пользователя передены не будут, то объект создастся с полями null. Для примитивов - дефолтные.
    @PostMapping()
    public String createNewPerson(@ModelAttribute("person") Person person){
        dao.save(person);
        return String.format("redirect:/people/%d", person.getId()); //redirect - перенаправление по ссылке
    }
### Тема: Конфигурация MVC-приложения с помощью Java (актуально со Spring 3.0)

* `Замена web.xml`:  
1. свой класс impl.. WebApplicationInitializer
2. можно наследоваться от абстрактного класса AbstractAnnotationConfigDispatcherServletInitializer //TOP
3. реализовать необходимые методы (минимум - getRootConfigClasses):
```java
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }
}
```

* `Замена applicationContextMVC.xml`:  
> Для того, чтобы настроить Spring MVC под себя: имплементировать WebMvcConfigurer и реализовать configureViewResolvers()

Пример `реализации КОНФИГА (applicationContext) MVC-приложения` с наследованием от AbstractAnnotationConfigDispatcherServletInitializer:
```java
@Configuration
@ComponentScan("org.example")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/"); //где будут лежать представления в формате .html
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    /**
     * Переопределение для использования шаблонизатора Thymeleaf
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}
```

### Тема: Контроллеры

* Обычно (но не всегда), каждый метод в контроллере соответствует одному URL'у
* Обычно, этим методы возвращают String - имя вьюшки, которую увидит пользователь. НО могут возвращать и JSON и XML (пример: телеграмм)

**Маппинги** - связывают метод контроллера с запросом, по которому можно к нему обратиться

Виды маппинга на методах (5шт.) - зависит от ожидаемого http-запроса
* @GetMapping
* @PostMapping
* @PutMapping
* @DeleteMapping
* PatchMapping
  * Устаревший вариант > 5 лет - @RequestMapping(value = "/new", method = RequestMethod.GET) //после точки - вид запроса

> НА КЛАССЕ используется: @RequestMapping("/new") - все URL-запросы должны иметь в теле "/new/"

> Все представления, относящиеся к конкретному контроллеру следует класть в пакет с его именем

---

### Сервер

Веб-приложение:   
+ Frontend  (Веб-браузер, клиентский код)
+ Backend  (Веб-сервер, серверный код)
+ База данных
+ HTTP(S) - для взаимодейсвтия фронтенда с бэкэндом, S - расширение (SSL/TLS - защищенное соединение)

### Запрос / Request

HTTP - запрос (request) состоит:  
1. Request Line - запрос (метод запроса (GET/POST/...) + Адрес + Используемый протокол(версия))
  * GET /wiki/java HTTP/1.1 //GET - метод запроса, /wiki/java HTTP, 1.1 - протокол
2. Заголовки запросов - содержит кроме всего - Content Type - JSON/XML...
3. Разделитель (пустая строка)
4. Тело запроса (может быть пустым) - информация, передаваемая от клиента серверу (фото, видео и т.д.)

Методы HTTP-запросов:
* Get - переход по ссылке, поисковой запрос и т.д. Он ничего не меняет на сервере. 
  * Тело запроса пустое. Но некоторые **параметры** поиска могут передаваться в самом URL после знака "?" в формате ключ=значение, такие параметры разделяются амперсантом И "&"
* Post - изменение чего-либо на сервере (добавление) - отправка данных из форм, создание учетных данных, постов, загрузка фото и видео. (передаются в теле запроса)
* Put
* Patch - обновление данных
* Delete - удалить данные

Доп.виды HTTP-запросов:  
* HEAD
* CONNECT
* OPTIONS

> HTML5 поддерживает только методы GET и POST

Поэтому, PATCH, DELETE, PUT - запросы передаются с помощью POST запроса, но в скрытом поле _method указывается желаемый HTTP метод. Thymeleaf берет это на себя.   

Эта проблема, в свою очередь, решается с помощью Spring - с помощью фильтра. 

`Фильтр` - объект, который перехватывает все входящие HTTP-запросы. В данном случае фильтр используется для того, чтобы смотреть на значение поля _method в поступающих HTTP-запросах (если это поле есть). 
  * в Spring Boot подключается с помощью одной строки в конфигурационном файле

> Код для `фильтра` вставлять в аналог web.xml - AppInitializer

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }

### Ответ / Response

Структура HTTP-ответа:  
1.  Стартовая строка - Используемый протокол + код ответа (HTTP/1.1 200 OK)
2. Заголовки - дата, сервер и т.д., а также тип контента в ответе: text/html or JSON...
3. Разделитель (пустая строка)
4. Тело ответа

HTTP - ответ состоит:
1. Версия протокола, код ответа (код ошибки), статус сообщения
  * 200-тые - все ок
  * 3хх - редирект (перенаправление запросов)
  * 4хх - ошибки со стороны клиента. Например, неправильный запрос http(адрес)
  * 5хх - серверные ошибки
2. Заголовки ответов

Типы содержимого (content type) в теле ответа:  
* text/html
* text/css
* text/xml
* application/json

--- 

Для `работы с параметрами Get-запроса` (это кторые после ? и объеденены амперсантом): 
**1 способ:** - будет содержать все сведения о Http-запросе. Если нет параметров = null, откроется страница голого запроса
    @GetMapping("/start")
    public String startPage(HttpServletRequest request){ //здесь произойдет автовнедрение зависимости без аннотации
        String name = request.getParameter("name");
        //логика работы с параметром
        return "first/start";
    }
**2 способ:** - будет содержать только указанный параметр. 
    @GetMapping("/close")
    public String goodByePage(@RequestParam(value = "name", required = false) String name){ //здесь required = false это необязательность параметра. При его отсутствии, отрокется гололый запрос. Без required = false при отсутствии параметров будет ошибка сервера
        //логика работы с параметром
        return "first/close";
    }


### Модель

> модель учавствует во всех взаимодействиях между составляющими MVC-приложения. в качестве JSON/XML объектов  

![model_MVC](/images/modelMVC.png)

* Чтобы добавить модель и положить в нее пару ключ-значение 
  * метод addAttribute()
    @GetMapping("/close")
    public String goodByePage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            Model model){
        model.addAttribute("message", "Hello, " + name + " " + surname);
        //логика работы с параметром
        return "first/close";
    }



### View / представление

---

> html-файл

<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"> <!--чтобы шаблонизатор Thymeleaf находил аттрибуты моделей в контроллере-->
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Close page</title>
</head>
<body>
<p>Goodbye!</p>
<p><a href="/start">Come back...</a> <!--ссылка с вызовом контроллера по запросу-->
    or <a href="/exit">exit</a></p>
<p th:text="${message}"></p> <!--вызов модели с параметром message - с помощью шаблонизатора thymeleaf. Текст абзаца будет сгенерирован из модели-->
</body>
</html>

---


CRUD-APP стандарт адресов (из стандарта REST):  

![CRUD_app_standart](images/crud_http_app.png)

Особенности:  

- 3 запрос создания записи вызывает второй запрос - публикации
- 4 запрос по редактированию вызовет 6 запрос (PATCH)

### REST

+ REST - паттерн проектирования web-приложений
+ он описывает то, как посредством протокола HTTP должен взаимодействовать клиент с сервером
+ все взаимодействия с сервером сводятся к 4 операциям: получение данных, добавление новых данных, изменение существующих данных, удаление данных (CRUD)
+ для каждой из 4 операций используется свой HTTP-метод: GET (READ), POST (CREATE), PATCH (UPDATE), DELETE (DELETE)


### Взаимодействие с БД 

* Должен быть класс модели, типа Person/User ...
* Должен быть класс взаимодействия с БД
  * DAO-класс (data access object) - обычно с SQL
  * REPOSITORY-класс - через Hibernate

Работа с шаблонизатором Thymeleaf в цикле:

<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>People</title>
</head>
<body>
<div th:each="person : ${people}"> <!--цикл-->
    <th:block>
        <a th:href="@{/people/{id}(id=${person.getId()})}" th:text="${person.getName()}">user</a> <!--создаст ссылку по айди из геттера с именем пользователя, тоже из геттера>
    </th:block>
</div>
</body>
</html>


### HTML-формы  

Форма для ввода данных и отправки POST-запроса и других, за исключением GET. Get запрос отпр-ся с помощью тега <a>

<body>
<!--1 - тип запроса, 2 - адрес, по которому будет пост-запрос, 3 - пустой объект, который передается для
его инициализации пользователем-->
<form th:method="POST" th:action="@{/people}" th:object="${newPerson}">
    <!--здесь поле для ввода значения для свойства name объекта класса Person-->
    <label for="name">Enter name: </label>
    <input type="text" th:field="*{name}" id="name"/>
    <br>
    <!--Кнопка типа отправить-->
    <input type="submit" value="Create">
</form>
</body>


### Валидация форм

> Статья с аннотациями: https://alexkosarev.name/2023/07/20/objects-validation-in-jakarta-bean-validation/

`Hibernate Validator`  - для валидации моделей в спринг-приложении

Аннотации валидации:
@NotEmpty //NotNull или пустое значение типа ""
  * @NotEmpty(message = "Name should not be empty!")
@Size // для размера строки
  * @Size(min = 2, max = 50, message = "Name should be between 2 and 30 characters")
@Min(value = -, message = "some message")
@Email - для валидации email
  * @Email(message = "Email should be valid!")

`Валидация Date даты:`
добавить в SpringConfig:
```java
    /**
     * Бин, участвующий в валидации поля типа Date
     */
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
```
0. @DateTimeFormat(pattern = "yyyy-MM-dd") - Позволяет настроить формат даты и времени для поля.

1. @Future - Проверяет, что дата в поле должна быть в будущем.

2. @Past - Проверяет, что дата в поле должна быть в прошлом.

3. @FutureOrPresent - Проверяет, что дата в поле должна быть в настоящем времени или в будущем.

4. @PastOrPresent - Проверяет, что дата в поле должна быть в настоящем времени или в прошлом.

После того, как аннотации в Entity расставлены:
@Valid в контроллере + объект типа BindingResult, куда положится ошибка валидации в случае неуспеха. (всегда должен идти после модели)
```java
    @PostMapping()
    public String createNewPerson(@ModelAttribute("person") @Valid Person person,
                                  BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "people/newPersonForm";

        dao.save(person);
        return String.format("redirect:/people/%d", person.getId());
    }
```

В представлении такое:  
<div style="color:red" th:if="${#fields.hasErrors('email')}" th:errors="*{email}">Email validate error</div>