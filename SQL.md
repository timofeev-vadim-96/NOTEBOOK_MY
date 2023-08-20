
# SQL  

* Извлечение данных из таблицы:  
Примеры:
  * **из одной таблицы:**  
    Select * From - Общий список, где:  
    * Общий список - название таблицы  
    * Select - указываем набор столбцов  
    * "*" - означает, что мы хотим видеть все столбцы  
    * From - откуда необходимо выбрать информацию    

  * *Если не все столбцы:  *
    * Select Фамилия, Тел., Комментарий  
    * From Общий список  
    * Where статус = "холост" // в SQL знак " = " - это сравнение  

  *  **из нескольких таблиц:**  
    Select * From peoples;  
    Select * From addresses;  
    Select * From peoples LeftJoin addresses ON peoples.identifier = addresses.Whose_addresses;  

  *  *Если необходимо выбрать таблицы по столбикам:*  
    Select  
      peoples.full_name,  
      peoples.status,  
      telephones.phone_number  
    From peoples LeftJoin telephones ON peoples.identifier = telephones.whose_phone  

> Команды SQL  

like - содержит в себе  
Пример: select * from ученики where характеристика like *'ленив'*;  

Чтобы **обратиться к полю с пробелом**, необх. взять его в квадратные скобки []  
Пример:  Классы.[Номер класса]

>Присоединение таблиц  
* Вместо ON можно использовать USING, но столбцы в USING должны быть одинаковые в обеих таблицах. USING (name)

* Inner Join - покажет все элементы, где есть соответствие между таблицами
Пример через третью таблицу:  
select f.Name, f.description, g.name 
from films as f
inner join film_genre as fg //3 таблица
on f.id = fg.id_film
inner join genres as g
on fg.id_genre = g.id;

* Left OUTER Join - покажет все элементы левой таблицы + элементы правой таблицы, которые соответствуют элементам левой таблицы
* Right OUTER Join - покажет все элементы правой таблицы + элементы левой таблицы, которые соответствуют элементам левой таблицы  
* Full OUTER Join - покажет все элементы обеих таблиц  //невозможно напрямую реализовать в MySQL, но можно реализовать через `UNION`
  * select f.Name, f.description, g.name //`первая честь через left outer join`
from films as f
left outer join film_genre as fg
on f.id = fg.id_film
left outer join genres as g
on g.id = fg.id_genre
* Cross Join - декартово произведение (все строчки слева на все справа)

**Пример**: Left Join Люди, адреса on id = чей адрес

**База данных** - некоторый набор постоянно хранимых данных
Решаемые задачи:
База данных VS СУБД
●Хранение данных
●Получение данных
●Обработка
СУБД (DBMS) — система управления базами данных

`ТОП СУБД:`
1. Oracle (Сбербанк)
2. MySQL
3. Microsoft SQL Server
4. PostgreSQL
5. MongoDB (не реляционная СУБД)

`Реляционные базы данных` (от англ. Relation – связь) — базы данных, в которых данные
распределены по отдельным, но связанным между собой таблицам.
`Кортеж (tuple)` — это множество пар {имя атрибута, значение}.
(например {Фамилия, Петров}, {Телефон, +7921-123-56-69}) — фактически это строка таблицы, где имена
атрибутов — это столбцы таблицы.
`Отношение (relation)` - это множество кортежей, соответствующих одной схеме. (На прикладном
уровне это соотносится с таблицей.)

`Первичный ключ (Primary key)` – поле(или набор полей) позволяющее однозначно
идентифицировать запись в БД. Если ключ состоит из нескольких полей его называют
составным. // следит за тем, чтобы поле более не повторялось.
  * primary key (column1, column2) //составной ключ
`Суррогатный ключ` - автоматически сгенерированное поле, никак не связанное с
информационным содержанием записи.
`Естественный ключ` — ключ состоящий из информационных полей таблицы.
Пример:  
Естественный ключ (Паспорт серия + номер), недостатки:
●Может изменяться
●Может отсутствовать
●Широкий — два текстовых поля

`Потенциальный ключ` – подмножество атрибутов таблицы, удовлетворяющее требованиям
уникальности и минимальности.

`Язык SQL`
1970-е – компанией IBM был разработан прототип СУБД System R, язык запросов (Structured Query
Language) SQL
...
2016 — стандарт SQL 2016
`Декларати́вное программи́рование` - описывается ожидаемый результат, а не способ его получения
SQL — декларативный язык

`Диалекты SQL`
●PL/SQL (Procedural Language SQL)
Oracle
●T/SQL (Transact SQL)
MSSQL
●PL/pgSQL ( Procedural Language PostGres SQL)
Postgresql
●Множество других
●в каждой СУБД, свой диалект

### ОПЕРАТОРЫ/КОМАНДЫ SQL:
> Ctrl + Enter - для ввода запроса  

`Data Definition Language (DDL) – это группа операторов определения данных`
* USE - подключение к БД
  * use sem_1;
* CREATE - создание новой таблицы
  * CREATE DATABASE newdatabase;
* ALTER - заполение таблицы
  * alter table tablename change oldcolumnname newcolumnname varchar(45); // изменить имя столбца
  * Alter table films add language varchar(50) null; //добавить столбец
  * alter table films DROP COLUMN language; //удалить столбец
  * alter table films MODIFY language int; //изменить тип данных в столбце
* DROP - удаление текущего объекта (базы данных)
  * DROP TABLE student; //удалить таблицу
* AS - позволяет определить название выходному столбцу
* RENAME
  * rename table tablename to newtablename; // переименовать таблицу
`Data Manipulation Language (DML) – это группа операторов для манипуляции с данными`
* INSERT - добавление новых данных. VALUE - для одной записи, VALUES - для нескольких
  * INSERT INTO `newdatabase`.`customers` (`Age`, `FirstName`, `SecondName`) VALUES ('27', 'Анастасия', 'Лисничая');
  * INSERT INTO customers (age, firstname, secondname) VALUES ('20', 'Снежана','Субботина');
  * 2. INSERT INTO таблица(перечень_полей) SELECT //ДЛЯ ВСТАВКИ ИЗ ДРУГОЙ ТАБЛИЦЫ
перечень_значений FROM … – вставка в таблицу новых
строк, значения которых формируются из значений строк
возвращенных запросом.
* UPDATE - изменение уже существующих данных (полей)
  * UPDATE customers set secondname = 'Леницкая' where id = 2;
  * UPDATE customers set age = age - 5; //изменяем сразу все строки в табличке
  * UPDATE table set column = case ... //заполнение через кейс
* DELETE - полное удаление
  * delete from customers where firstname = 'Снежана';
* SELECT - выборка данных
  * SELECT * FROM tablename
    * SELECT tablename.* FROM tablename; // тоже выведет все
  * SELECT * FROM girls WHERE name IN ('Анастасия', 'Валентина'); // оператор IN
  * SELECT name, email FROM tablename WHERE password = "pass1"
  * SELECT * FROM table WHERE date BETWEEN '2017-01-01' AND '2017-12-31'; //покажет все строки с датой за весь 2017 год (крайние значения включаются в выборку)
* Оператор LIKE
  * Select * from student where name like 'A%'; - а в начале
  * ... like '%A%' A - в любом месте
  * ... like 'A_ _' - всего 3 символа, начинается с А
  
* SHOW DATABASES; //посмотреть список всех баз данных
* SHOW TABLES; //посмотреть все таблицы в текущей базе данных
* TRUNCATE TABLE tablename; //очищает(удалить) всю таблицу от данных, при этом авто-инкремент дропается до дефолта (1)
`Data Control Language (DCL) – группа операторов определения доступа к данным`
* GRANT - предоставить права доступа к базе данных
* REVOKE - отозвать права доступа к базе данных
* DENY - запрет на объект, имеет приоритет выше, чем у GRANT
`Transaction Control Language (TCL) – группа операторов для управления транзакциями`
**Транзакция** - это набор команд или инструкций которые выполняются как единый блок
* BEGIN TRANSACTION - определяет место, в котором начинается транзакция
* COMMIT TRANSACTION - конечная точка (от отправителя получателю)
* ROLLBACK TRANSACTION - откат всех изменений (в случае возникновения ошибки)
* SAVE TRANSACTION - промежуточное сохранение транзакции

`Основные компоненты СУБД`
●Storage engine п- одсистема постоянного хранения данных
●Query parser - парсер и транслятор запросов //запрос проверятеся на синтаксис, и в случае успеха транслируется
●Query optimizer - оптимизатор запросов //каким-то образом прокладывает оптимальный путь выполнения запроса
●Query executor - подсистема выполнения //выполняет инструкцию
●Buffer cache - подсистемы кэширования данных //обращется к месту хранения данных (жесткий диск)

??Подключение к СУБД, компоненты доступа??
●ODBC (Open Database Connectivity)
программный интерфейс (API) доступа к базам данных
●JDBC (Java DataBase Connectivity)
программный интерфейс (API) доступа к базам данных в среде Java

`Клиентские приложения для работы с СУБД:  `
1. MySQL
...
●Dbeaver - open-source, поддержка более 20ти различных СУБД
●Azure Data Studio - Microsoft, поддержка различных СУБД за счет плагинов
●dbForge - компания Devart, требуется покупка лицензии
●DataGrip - компания JetBrain, требуется покупка лицензии
●MSMS - компания Microsoft, только для MSSQL
●PGAdmin - open-source, только для Postgres

Онлайн-приложения для работы с SQL: 
* sqlfiddle.com
* db-fiddle.com

MySQL
Рабочая история - Workbench

Поля таблиц:
Datatype - VARCHAR(String) в скобках - длина строчки (максимальная)
PK - PRIMARY KEY
NN - NOT NULL
UQ - UNIQUE
AI - AUTO_INCEMENT (++)
FK - FOREIGN KEY (внешний ключ) // чтобы добавить, внешний ключ должен быть уникальным (primary or unique)
  * FOREIGN KEY (CustomerId) REFERENCES Customers (id) - чтобы связать наше поле CustomerId в одной таблице с полем id в другой  таблице с кастомерами.
DEFAULT
  * DEFAULT 0 - значение по дефолту = 0

**Вручную запустить сервер MySQL 80:**  
Диспетчер задач -> Службы -> MySQL80 -> Запустить  

**Найти таблицу или базу данных** -> MySQL -> Schemas

**Создать новую базу данных** -> левой кнопкой мыши в навигаторе схем -> create Schemas, кодировка utf-8, utf-8-general-s -> apply -> finish

SQL Data Tipes/Типы данных SQL:  
* Числовые
  + INT
  + DECIMAL(N) - число с заданной точностью для сложных вычислений
  + BOOL
* Символьные
  + VARCHAR(N), N - максимальная длина строки
  + TEXT - для больших текстов, типа статьи, до 65КВ
* Дата и время
  + DATE - только дата типа '9999-12-31' // обазятельно кавычки
  + TIME - только время - часы, минуты, секунды "hh:mm:ss". Память хранения - 3 Байта
  + DATETIME - дата и время, 8 Байтов
  + TIMESTAMP - хранит дату и время начиная с 1970
* Бинарные
  + BLOB - до 65 КБ бинарных данных
  + LARGEBLOB - до 4 ГБ

**Внешний ключ/связывание таблиц/FOREIGN KEY**  
https://metanit.com/sql/mysql/2.5.php  
* ON DELETE CASCADE - при удалении в основной тиблице все связанные строки во вторичной удаляются


Пример: создание таблицы:  
CREATE TABLE orders
(
	Id INT PRIMARY KEY AUTO_INCREMENT,
  CustomerId INT, //суррогатный ключ для связи с другой таблицей
  CreatedAt DateTime,
  FOREIGN KEY (CustomerId) REFERENCES Customers (id)
);

---

`Комментарии:`

-- Комментарий

# Комментарий

/*
Многострочный комментарий
*/

---

**Запустить скрипт с клавиатуры (с начала):**
Ctrl + Shift + Enter  
  **Запустить скрипт с места расположения курсора**  
  Ctrl + Enter

**Арифметические операции в MySQL:**  
SELECT 3+5 - выведет столбик сназванием "3+5" и значение в этом столбике "8"  
  * Деление на 0 дает NULL

**Логические операторы:**  
> Приоритет логических операторов по убыванию: NOT, AND, OR  
Переопределение приоритета возможно произвести с помощью скобок - то, что в скобках выполняется первое  
* AND 
  * where age > 25 and SecondName = 'Лисничая';
* OR
* NOT, тоже самое что !=/!
  * where NOT SecondName = 'Лисничая';

**Конструкция с кейсами:**  //если не задать ELSE - значение будет NULL для тех, которые не попали под условия кейсов
//приоритет от первого к последнему по условиям. Если в первое условие входит - то дальше не пойдет. Например, если первое условие > 450, а второе between 400 and 450, то до второго условия не дойдет и ошибок не будет
SELECT column1, column2,
CASE
  WHEN condition THEN expression
  WHEN ... THEN ...
  ELSE ...
END AS columnname
FROM tablename;

select *, case //перед запиливанием в БД проверяем как будет работать через селект
when grade = 'A++' then 'DISTINCTION'
when grade ='A+' then 'FIRST CLASS'
when grade ='A' then 'SECOND CLASS'
when grade ='B' then 'SECOND CLASS'
when grade ='C' then 'THIRD CLASS'
else 'FAIL'
END as class_grade
from student_grades;

**Конструкция с условием IF**  //работает только с одним условием
Select age, firstname, secondname, 
  IF (age >= 25, 'девушка', 'девочка')
  AS difinition
from customers;

---

`Ошибки/errors`

+ 1175 - нельзя использовать UPDATE где WHERE не использует ключ PRIMARY KEY в безопасном режиме. Решение:  
SET SQL_SAFE_UPDATES = 0;  

+ 1055 - не получается сгруппировать какой-то отдельный столбец с другими. Решение: 

https://stackoverflow.com/questions/36207042/error-code-1055-incompatible-with-sql-mode-only-full-group-by 

SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));  

+ 1452 - крч есть одно из полей Foreign, которые привязаны к основной тиблице. Так вот, есть ограничение по заполнению таких значений в соответствии со значениями в основной таблице. Если привязка по id, значит такой id должен существовать в основной таблице!  

+ 1833 - нельзя изменить колонку, к которой привязан вторичный ключ. Решение: 
  * SET FOREIGN_KEY_CHECKS = 0; //делаем что надо
  * SET FOREIGN_KEY_CHECKS = 1; //возращаем проверку !!! Не делать в продашине, убедиться, что есть backup of the database
---

**Для работы в командной строке с MySQL - Command Line Client**  

`Сортировка`  
с помощью оператора **ORDER BY**  
* select * from sales order by bucket DESC; //таблица sales, отсортированная в обратном порядке по столбцу bucket     
ORDER BY [ ASC | DESC ], где ASC (по умолчанию) - ascending (по возрастанию), DESC - descending (по убыванию)
* select name, surname, age from staff order by name desc, age desc;  //сортировка по двум столбцам

* Псевдоним в запросе сортировки
SELECT column1, column2 * column3 AS totalcolumn
FROM tablename
ORDER BY totalcolumn;

---

**Ограничение выборки в различных СУБД**  
Ключевое слово    Система баз данных  
TOP               MS SQL Server, MS Access  
LIMIT             MySQL, PostgreSQL, SQLite  
FETCH FIRST       Oracle  

* LIMIT - позволяет извлечь определенное количество строк
Имеет 2 параметра. 1 - первые N строчек пропустить, 2 - вывести определенное количество строк  
  * select * from sales limit 3, 2; // выведет все строки, начия с 4
  * select * from sales limit 3; //выведет первые 3 строки

* TOP - тоже самое, что LIMIT, используется в MS SQL Server
  * SELECT TOP 2 * FROM tablename;  

* FETCH - тоже самое, что LIMIT. 
  * select * from sales (сюда сортировку, если надо) OFFSET m ROWS FETCH NEXT p ROWS ONLY; //будут извлекаться строки только от (m+1) до (m+1+p).

---

* **DISTINCT - ператор уникальный значений**
  * select distinct manufacturer from phones;
  * select distinct manufacturer, model from phones; //можно и несколько столбцов, но может потеряться уникальность для одного из них
  * select count(distinct name) as count_unique from emp; //посчитать количество уникальных значений столбца

* **GROUP BY - группировка**  
SELECT ... FROM table WHERE ... GROUP BY... HAVING ... ORDER BY ...
  * select manufacturer, count(*) as modelsCount from phones group by manufacturer;
  

`Агрегатные функции`  //если совмещаются с обычными колонками - добавлять Group by колонка
В MySQL есть следующие агрегатные функции:  
* AVG: вычисляет среднее значение  
  * select AVG(price) as average_price from phones;
  * select AVG(price) as average_price from phones where manufacturer = 'apple'; //с фильтрацией
* SUM: вычисляет сумму значений  
  * select name, sum(daily_typing_pages) as sum_pages from emp group by name;
* MIN: вычисляет наименьшее значение  
* MAX: вычисляет наибольшее значение  
  * select min(price) as min, max(price) as max from phones;
* COUNT: вычисляет количество строк в запросе 
  * select count(*) from phones; //вывести общее кол-во строк в таблице 
  * select manufacturer, count(*) as modelsCount from phones group by manufacturer; //выведет производителей с количеством строк относительно каждого
  * select count(distinct name) as count_unique from emp; //количество уникальных значений в столбце

* HAVING - для фильтрации групп //похож на where (но where - для фильтрации строк), используется для агрегированных функций  
  * select manufacturer, count(*) from phones group by manufacturer having count(*) > 1; //здесь мы выводим количество моделей у производителей и затем прогоняем через еще один фильтр HAVING, где отсеиваем фирмы с кол-вом менее 1
  * select manufacturer, count(*) as models, sum(quantity) as Units from phones group by manufacturer having Units > 5 order by Units DESC; //sum(quantity) - суммарное количество единиц телефонов данного производителя
 
* `Приоритет операций в SQL:`
1. FROM, включая JOINs
2. WHERE
3. GROUP BY
4. HAVING
5. Функции WINDOW
6. SELECT
7. DISTINCT
8. UNION
9. ORDER BY
10. LIMIT и OFFSET

**Посмотреть версию MySQL:**  
select version ();  

**Оператор UNION** - для объединение результатов двух запросов (`повторяющиеся значения удаляются`)  
  * select name, second_name from girls UNION select name, surname from staff; // название столбцов будут как в первой выборке  

**Оператор UNION ALL** - тоже самое, что UNION, только не удаляет повторяющиеся значения  
  * select name, second_name from girls UNION ALL select name, surname from staff order by second_name desc;
  * select name, surname, salary,salary + salary*0.1 as total_sum
  from staff where salary < 35000
  union all
  select name, surname, salary,salary + salary*0.3 as total_sum
  from staff where salary >= 35000; // запрос из одной таблицы

**Показать все ключи таблицы и всю инфу о ее создании**  
  * show create table tablename;  
  
**Посмотреть тип колонки**  
  * SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS
  WHERE table_name = 'films' AND COLUMN_NAME = 'Date';

**Оператор IN**
  * select * from films where id in (select id_film from film_genre where id_genre in (select id from genres));
  //выведет все строки в фильмах id которых присутствует в таблице film_genre, id_genre которых присутствует в genres

**Оператор EXISTS**
  * select * from films where exists (select id_film from film_genre where id_film = films.id);
  //выведет все фильмы из левой таблицы у которой есть соответствия по id в правой таблице. 
  * select exists(выражение) 

**Чтобы скопировать таблицу в новую**  
  * create table testTable select * from phones;  

**Выделить уникальные данные из таблицы при соединении двух**
  * select * from teacher where id not in (select teacher.id from teacher inner join lesson on teacher.id = lesson.teacher_id); //выделяем данные, которых нет в пересечении
  * select * from teacher right join lesson on teacher.id = lesson.teacher_id where teacher.id is null;
  //выделяем данные со значением левой таблицы налл

* Посчитать количество записей в возвращенном запросе  
  * select count(*) from (select * from users union select * from clients order by id) as x;