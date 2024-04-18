> [НАЗАД к СОДЕРЖАНИЮ](README.md)

---

` Библиотека Lombok` - для генерации геттеров и сеттеров во время компилляции, не оставляя следов в исходном коде

Мин. версия для **JDK21** = `1.18.30`

`Аннотации`:
* @Data - генерирует пустой конструктор, геттеры, сеттеры, методы equals, hashCode, toString.
* @Value - генерирует конструктор, только геттеры, методы equals, hashCode, toString. А также делает все поля private и final.
  * С неизменяемыми классами хорошо сочетаются аннотации @With и @Builder.
* @With - добавляет методы для каждого поля, которые делают клон объекта с одним измененным полем.
  * Cat anotherCat = cat.withName("Вася");
* @Builder - генерирует методы, которыми мы инициализируем объект по цепочке. Это удобно когда мы не хотим использовать конструктор со всеми параметрами (Если у нас класс неизменяемый, то в нем единственный конструктор со всеми параметрами).
* @Slf4j - добавляет в класс логгер log  
**Пример логирования:**  
```java
log.info("Входящий запрос: {}", uri);
```
* @SneakyThrows - делает проверяемые исключения непроверяемыми
* @NoArgsConstructor //добавляет конструктор без аргументов
* @AllArgsConstructor //добавляет конструктор со всеми параметрами
* @RequiredArgsConstructor //добавляет конструктор для всех final полей
* @Getter //добавляет геттеры для всех параметров класса
* @Setter //добавляет сеттеры для всех параметров класса
* @EqualsAndHashCode //добавляет реализации методов equals и hashCode
* @ToString //добавляет реализацию метода toString
* @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) //делает все поля private и final

**Пример работы с билдером:**  
```java
Cat cat = Cat.builder()
                .name("Мурка")
                .age(3)
                .person(person)
                .build();
```

**Чтобы исключить поля:**     
```java
@ToString.Exclude
@EqualsAndHashCode.Exclude
```

**Пример с final полями:**  
```java
@Value
@With
@Builder
public class Cat {
    String name;
    int age;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Person person;
}
```

---