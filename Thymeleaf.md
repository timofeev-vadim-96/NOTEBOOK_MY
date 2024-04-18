> [НАЗАД к СОДЕРЖАНИЮ](README.md)

---

## Thymeleaf - шаблонизатор представлений

`Шаблонизатор` - художник веб-страниц. Это инструмент, который помогает нам создавать веб-страницы более эффективно. Он позволяет нам разделить структуру страницы (HTML) и данные, которые мы хотим отобразить на этой странице.  

**Представления** шаблонизатора должны располагаться в директории:  
src/main/resources/templates  

**Статичные страницы**, такие как index.html (первая страница сайта) и таблицы стилей CSS, должны располагаться в директории:  
src/main/resources/static

Шаблонизатор `Thymeleaf`  

**Thymeleaf в цикле:**
```xml
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
        <a th:href="@{/people/{id}(id=${person.getId()})}" th:text="${person.getName()}">user</a> <!--создаст ссылку по айди из геттера с именем пользователя, тоже из геттера-->
    </th:block>
</div>
</body>
</html>
```

`Thymeleaf-форма для ввода данных` и отправки POST-запроса и других, за исключением GET. Get запрос отпр-ся с помощью тега \<a>

```xml
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
```

`Thymeleaf работа с текстом` - подставить в определенное место текст, соответствующий, в данном случае, сообщению
```xml
<p th:text="${message}"></p>
```

Чтобы подключить таблицу стилей в представление:  
```html
<head>
<title>Main page<title>
<link href="styles.css" rel="stylesheet" type="styles/css">
</head>
```

Пример "ручки" при работе с **отображениями с помощью Thymeleaf**:  
```java
    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAll());
        return "users";
    }
```
Model model - ровно тоже самое, что HashMap. В модель внедрятся те значения, которые следует передать в представление
.addAttrubete - добавляем ключ, значение  