
> [НАЗАД к СОДЕРЖАНИЮ](README.md)

---

### Spring AOP

> https://docs.spring.io/spring-framework/reference/core/aop/ataspectj/pointcuts.html  

`Spring AOP` (Aspect-Oriented Programming) — это часть
фреймворка Spring, позволяющая вводить аспекты в
программный код, что упрощает модульность приложения
путем разделения кросс-куттинговой функциональности,
такой как логирование, безопасность и транзакции.

**Преимущества Spring AOP**:  
1. Повторное использование кода и модульность .
2. Улучшение читаемости и поддержки кода
3. Гибкая настройка
4. Обработка исключений
5. Безупречные транзакции
6. Понимание проксирования 

`Зависимость Spring-AOP ` 
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>
```

---

`Аспект` в Spring AOP — это модуль, который определяет
“перекрестные” или “сквозные” задачи, такие
как логирование, безопасность или транзакции.
Аспекты работают таким образом, что они
“внедряются” или “вплетаются” в ваш код в определенные
места, которые вы указываете.

`Логирование методов`  
![логирование методов](images/logging_aspect.png)  
@Before... перед всеми выполнениями (execution)  
  * .* - первая звезда - означает все классы пакета
  * .*(..) - все методы классов  
  * joinPoint.getSignature().getName() - имя метода  

**JoinPoint** - класс, содержащий информацию о точке внедрения аспекта.  Это место в программе, где аспект может
быть применен. Это может быть при вызове метода, при обработке
исключения, при инициализации объекта и так далее.  
Место в программе прописывается с помощью языка `Pointcut`  

`Примеры Pointcut-ов` - мест выполнения в программе  
```java
//1. все методы
@Pointcut("execution(* *.*(..))")
private void selectAllMetchods(){}

//2. все сеттеры
@Pointcut("execution(* *.set*(..))")
private void selectAllSetters(){}

//3. все методы по поиску строк 
@Pointcut("execution(* *.find*(String))")
private void selectAllStringFinders(){}
```

> @Pointcut можно использовать в других аспектах, типа:  
```java
@Around("selectAllSetters()")
```

В поинткатах (Pointcut) можно указывать несколько точек ввода через || / &&  

Другой пример аспекта `логирования конкретного метода`  
![логирование конкретного метода](images/aspect_logging2.png)  

`@Around`  
Здесь - измерение времени  
![аспект измерения времени выполнения](images/spring_apect_measure_execution_time.png)   
* ProceedingJoinPoint - точка для выполненного метода  
* @Arount - навешивание функционала вокруг метода (сам аспект как обертка), а выполнение реального метода здесь в **9 строке**  
* обязательно возвращать Object, чтобы отработавший метод вернул значение дальше  

Другой пример **@Around**, здесь вместо пакета используется аннотация (`вызов по наличию аннотации`)  
```java
//аннотация:  
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timer {
}

//Spring AOP
@Aspect
@Component
public class TimerAspect {
    @Around("@within(Timer) || @annotation(Timer)") //методы, помеченные аннотацией
    private Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long elapsedTime = System.currentTimeMillis() - start;

        System.out.println("Метод " + joinPoint.getSignature().getName() + " класса " +
                joinPoint.getTarget().getClass().getSimpleName() + " выполнился за " + elapsedTime + " миллисекунд");

        return result;
    }
}
```


**Аннотации аспектов**: (Advice - действие, выполняемое аспектом)
* Before Advice - что-то сделать перед выполнением метода.   
Пример - логирование или проверка прав доступа
* After Returning Advice (@AfterReturning) - для изменения возвращаемого значения
* After Throwing Advice - для перехвата исключения (добавить метрики, логирование и т.д.)
* After (or After Finally) Advice - выполняется каждый раз после вызова метода

`Around Advice` (@Around) - объединяет в себе все остальные
типы advices, так как позволяет вам вмешиваться в вызов
метода до его выполнения, после него и даже изменять
возвращаемое значение или кидать исключение
вместо целевого метода.  

`@After` advice конкретного метода - после выполнения метода (независимо от результата выполнения), пример:  
![after аспект](images/after_advice_aspect.png)  

`@AfterReturning`, здесь - логирование возвращаемого значения конкретного метода. Можно и поменять возвращаемое значение    
![after returning aspect](images/afterReturning_advice_aspect.png) 
* message - имя переменной, в которой будет сохранено возвращаемое значение

`@AfterThrowing` - выполнить действие, если целевой метод выбросит исключение. Работает как блок **catch**    
![after throwing advice](images/after_throwing_advice_aspect.png)  
С помощью этого способа можно:  
1. Логирование.
2. Уведомления. (например, сообщение в телеграмм если приложение упало)
3. Трансформация исключений.
4. Откат транзакций.  

Другой пример `@AfterThrowing` с трасформацией исключений:  
![](images/after_throwing_advice2.png)  
* название переменной в декларации аннотации должно соответствовать имени аргумента, принимаемого на вход метода

Задать `порядок выполнения аспектов`:  
![](images/aspect_advice_order.png) 

---

`Introductions`(Mixin) - компоненты аспектов. Позволяют добавлять новые методы или свойства в существующие бины.  

**Introductions могут быть полезны** в ряде сценариев:  
1. Переиспользование кода
2. Постепенное внедрение новых возможностей
3. Работа с сторонними библиотеками  

**Пример introductions.**  
Здесь, существующий класс Car теперь будет имплементировать интерфейс  
![](images/introductions_aspect.png)  

---

`JDK Dynamic Proxy vs. CGLIB proxy`  - 2 основных механизма создания proxy

**JDK Dynamic Proxy** - создает прокси для интерфейсов (через рефлексию)

**CGLIB proxy** - создает прокси для классов

Аспекты по своей сути - это прокси объекты.   
`Proxy-объект` - это посредник между вызывающим и целевым объектом. (обертка над самим классом)

---

### Транзакции

Транзакция - способ объединения нескольких действий в одно  

Обозначить метод, как `Транзакцию`:  
```java
@Transactional(propagation = REQUIRED) //из библиотеки спринга
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT) //дефолт. REQUIRED - если нет транзакции, то она будет создана
```  

`Propagation` (**поведение транзакции**):  
* Propagation.**REQUIRED** - если нет транзакции, то она будет создана  
* Propagation.**SUPPORT** - если есть транзакция внутри метода - то будет исполнена, если нет - то метод исполнится без транзакции (хз зачем такая логика, по сути - бесполезно)  
* Propagation.**MANDATORY** - метод должен являться частью другого метода в рамках внешней транзакции
* Propagation.REQUIRED_NEW - всегда создается новая транзакция
* Propagation.**NOT_SUPPORTED** - в момент выполения метода приостанавливаются любые внешние транзакции, чтобы тело метода было выполнено **нетранзакционно**

`Isolation` (**уровни изоляции транзакции**)  - в соответствии с уровнями изоляции в Википедии и проблемами, которые они решают  

Пример **транзакции**. 
![](images/transactional_aop.png)  
* rollbackFor - откатываться только  при возникновении CustomException.class

---

### Interceptors (перехватчики) 
Обычно используются для перехвата сообщений или HTTP-запросов, а также запросов на выполнение методов  

`Пример перехватчика`  
![](images/interceprot_aop.png)  


`все методы в классах вложенных пакетов конкретного пакета`  
```java
@Around("execution(* com.example.restapi.controllers..*.*(..))")
```










