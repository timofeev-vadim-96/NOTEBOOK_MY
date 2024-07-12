## Spring Shell

Speing Shell - для создания консольных приложений

Аннотации (legacy):

`@ShellComponent` - набор методов-команд, вешать над классом  
`@ShellMethod("CommandName")` - метод-команда, вешать над методом  
`@ShellOption` - аргумент (передается как --name, обязательно по умолчанию в 2.0.0), вешать рядом с принимаемым аргументом  
`@ShellMethodAvailability` - позволяет управлять доступностью команд (это метод)

Аннотации (modern):  
`Command(group = "commands group")` - набор методов-команд, вешать над классом  
`@Command(description = "command description, command = "commandName, alias = {"com", "cmd"})` - метод-команда, вешать над методом  
`@Option(defaultValue = "defValue")` - аргумент (передается как --name, обязательно по умолчанию в 2.0.0), вешать рядом с принимаемым аргументом
`@CommandAvailability(provider = "someBean")` - позволяет управлять доступностью команд. Provider - это БИН!!! ->  
`@CommandScan` - над Main классом - ищет команды, делает из них бины и складывает в контекст  

Пример бина для @CommandAvailability
```java
@Configuration
public class ShellBeanConfig {

    @Bean
    public AvailabilityProvider publishEventCommandAvailabilityProvider(SomeBean someBean) {
        return () -> someBean.someMethod()
                ? Availability.available()
                : Availability.unavailable("some reason");
    }
}
```

2 варианта использования:  
* legacy (примеры по нему будут)
* modern - современный

`зависимость:`  
```xml
<dependency>
    <groupId>org.springframework.shell</groupId>
    <artifactId>spring-shell-starter</artifactId>
</dependency>
```

Пример класса команд:  
```java
@ShellComponent
@RequiredArgsConstructor
public class ShellCommandExample {
    private final HelloService helloService;

    /**
     * команда в консоли: Hello Vadim
     * value - то, что будет показано в help
     * key - алиасы команды
     * availability - название метода с условием
     */
    @ShellMethod(value = "Hello", key = {"l", "login"})
    @ShellMethodAvailability("isHelloCommandAvailable")
    public String sayHello(@ShellOption(defaultValue = "AnyUserName") String name) {
        return helloService.hello(name);
    }

    private Availability isHelloCommandAvailable() {
        return Availability.available();
//        return Availability.unavailable("some reason");
    }
}
```

Пример метода команды modern-стиль:  
```java
    @Command(description = "Find all books", command = "books", alias = "ab")
    public String findAllBooks() {
        return bookService.findAll().stream()
                .map(Book::toString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }
```

`Тестирование SpringShell` - чтобы он не заблокировал приложение потоком ввода - выключение интерактивного режима      

```yml
spring:
  shell:
    interactive: 
        enabled: false #true, в обычном состоянии
```