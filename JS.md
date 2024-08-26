
### JavaScript - динамическая типизация

`JavaScript` - язык для описания функциональности/поведения веб-страницы
  * скрипты js прописываются внутри HTML в \<script> (вроде как в head)
    ```html
    <script type = "text/javascript">
        ...
    </script>
    ```

**Особенности JavaScript:**
* Встроенная устойчивость к ошибкам
* простота языка
* отсутствие классов
* гибкость и устойчивость

`Пример js в html:`  
```html
<script language="JavaScrypt">alert("Привет, мир");</script> //для добавления кода js. Можно не указывать JS - он по умолчанию.
<script src="/script/script.js"></script> //импортировать скрипт извне - прописывать внутри head
```

`методы JS:`  
```js
alert("text") //вспрывающее окно с текстом  
typeof "foo" //тип данных  
prompt("Some text") //и так ясно  
let name = prompt("Введите ваше имя:", "Name"); //1 - подпись сверху, 2 - значение в текстовом поле по умолчанию
confirm("Вопрос, на который можно ответить да или нет") //получим **булевое** значение  
parseInt(); // распарсить строку в число. 

//+переменная // вот здесь плюс приводит переменную в число. WTF вообще, но да...  
```

**Объявление**: let arg = ...;

**Особенные типы данных:**  
- 5/0 = `Infinity`  
- "some text"/4 = `NaN` (not a number)
- let arg; alert(arg) - тип будет `undefined`, т.к. переменная не объявлена

* Чтобы вставить переменную или любое выражение в тексте - одинарные ковычки: //типа String.format  
```js
alert(`Привет, ${name}`); //именно наклонные кавычки (ё)
alert(`result, ${4+4}`);
```

* Возведение в степень: 4**2

if (){} else{} //такой же

**Тернарный оператор** тот же:
let arg = (age > 18) ? "yeas" : "no";

Логические операторы те же: && || !, но можно !! чтобы получить тоже значения в буле  
  * alert(!"") //true - крч пустая строка всегда изначально false 
  * NaN = false
  * 0 = false
  * null = false
  
Сокращенные операторы математические те же: типа x %= y;

Сравнить значение и тип: ===

Циклы те же  

Switch-case тот же  

`Функции`: //просто использовать зарезервированное слово function в сигнатуре  
```js
function functionName (a,b){
  return a+b; //если не писать, будет void
}
```

## AJAX

`AJAX` (Asynchronous JavaScript and HTML)  - это технология, когда с помощью JavaScript мы можем динамически получать куски html-страниц с сервера
1. Небольшая статическая HTML
2. Данные загружаются с сервера отдельно AJAX (только
теперь уже обычно JSON, а не HTML);
3. HTML шаблоны – статические фрагменты компонентов
HTML, отображающие данные
4. JS компоненты - подключаются в HTML, добавляют
поведение к HTML шаблонам
5. CSS – подключается в главной HTML
6. AJAX пишется на клиенте – это JavaScript
7. Изначально XMLHttpRequest
8. Jquery.ajax, axios – обёртки XMLHttpRequest
9. fetch – новое API - **ПРЕДПОЧТЕНИЕ ЕМУ**
  * 7, 8, 9 пункты - все это JS-кие темы для отправки HTTP-запросов

`Технологии JS для отправки запросов`
```xml
<head>
    <title>Технологии JS для отправки запросов</title>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
    
    <script>
        function outputCharacter(character) {
            const dataContainer = document.getElementById("dataContainer")
            const characterPhoto = document.getElementById("characterPhoto")
            dataContainer.innerHTML = JSON.stringify(character, undefined, 4)
            characterPhoto.src = character.image;
            
        }
    </script>
    
    
    <script>
    //XMLHttpRequest

        function getDataByXmlHttpRequest() {
            
            
            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = () => {
                if(xhr.readyState === 4 && xhr.status === 200) {
                    // Вот здесь придёт ответ
                    const json = JSON.parse(xhr.responseText)
                    outputCharacter(json)
                }
            } 
            // Вот здесь запрос отправляется
            xhr.open('GET', 'https://rickandmortyapi.com/api/character/1')
            xhr.send()
        }
    </script>
        
        
    <script>
    //AJAX (JQuery)

        function getDataByJQuery() {
            $.ajax({
                type: 'GET',
                url: 'https://rickandmortyapi.com/api/character/2',
                success: (json) => {
                  // Вот здесь пришёл ответ
                  outputCharacter(json)
                }
              })
        }
    </script>
        
    <script>
//AXIOS

        function getDataByAxios() {
            axios.get('https://rickandmortyapi.com/api/character/3')
                .then(response => outputCharacter(response.data))
        }
    </script>
        
    <script>
//function api - новая тема с 2017-18гг.

        function getDataByFetch() {
            fetch('https://rickandmortyapi.com/api/character/4')    
                .then(response => response.json())
                .then(json => outputCharacter(json))
        }
    </script>
</head>
```

`JQuery` - старая шляпа  
зависимость:    
```xml
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>jquery</artifactId>
            <version>3.7.1</version>
        </dependency>
```  
А потом в хед 
```html
<head><script src="/webjars/jquery/3.7.1/jquery.min.js"></script></head>
```
Теперь можно юзать скрипты в теле html-страницы 

## SPA

SPA - single page application. JS на первом месте. Генерит весь контент с разметкой и стилем

Особенности:
1. Минимальная HTML, только чтобы подключить JS и CSS
(SPA – Single Page Application) и обычно статическая
2. Данные загружаются с сервера отдельно
3. Ract компоненты – содержат и JS код и подобие HTML
разметки для каждого компонента
4. CSS – подключается в HTML
5. Переход по страницам осуществляется с помощью JS! и называется роутингом
6. • В Angular и Vue код и разметка разделены

`NodeJS` - это как JVM, только для JavaScript-a

`Запрос к API с отрисовкой HTML: ` 
```js
    const api = 'http://localhost:8080/api/v1';
    const bookId = document.getElementById('bookId').value;

    fetch(`${api}/book/${bookId}`)
        .then(response => response.json())
        .then(book => {
            const bookDetailsDiv = document.getElementById('bookDetails');
            bookDetailsDiv.insertAdjacentHTML('beforeend', `
<p>id: ${book.id}</p>
<p>title: ${book.title}</p>
<p>author: ${book.author.fullName}</p>
<p>genres:
    <ul>
        ${book.genres.map(g => `<li>${g.name}</li>`).join('')}
    </ul>
</p>
                `);
        });
```

`Слушатель на кнопку` -> чтобы сделала запрос к API
```js
    const deleteButton = document.getElementById('delete-button');
    deleteButton.addEventListener('click', () => {
        fetch(`${api}/book/${bookId}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    console.log('Book deleted successfully!');
                    window.location.href = '/';
                } else {
                    console.error('Error deleting book:', response.status);
                }
            })
            .catch(error => {
                console.error('Fetch error:', error);
            });
    });
```

`Слушатель на кнопку` -> для простого перехода 
```js
    const editButton = document.getElementById('edit-button');
    editButton.addEventListener('click', () => {
        window.location.href = `/edit/${bookId}`;
    });
```

`Построить таблицу`
```js
    function buildCommentTable() {
        fetch(`${api}/comment/${bookId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => response.json())
            .then(comments => {
                const bookTableBody = document.getElementById('commentTableBody');
                comments.forEach(comment => {
                    const row = bookTableBody.insertRow();

                    row.insertCell().textContent = comment.id;
                    row.insertCell().textContent = comment.text;

                    //Кнопка удаления комментария
                    const deleteCell = row.insertCell();
                    const deleteButton = document.createElement('button');
                    deleteButton.textContent = 'Delete';
                    deleteButton.addEventListener('click', () => {
                        deleteComment(comment.id);
                        row.remove(); // удаление комментария из таблицы
                    });
                    deleteCell.appendChild(deleteButton);
                });
            });
```