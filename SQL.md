
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
Пример через три / третью таблицу:  
select f.Name, f.description, g.name 
from films as f
inner join film_genre as fg //3 таблица
on f.id = fg.id_film
inner join genres as g
on fg.id_genre = g.id;

**Пример через три табл с помощь UNION как FULL JOIN:**
select f.Name, f.description, g.name
from films as f
left join film_genre as fg
on f.id = fg.id_film
left join genres as g
on fg.id_genre = g.id
union
select f.Name, f.description, g.name
from films as f
right join film_genre as fg
on f.id = fg.id_film
right join genres as g
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
* ALTER TABLE - изменение таблицы
  * alter table tablename change oldcolumnname newcolumnname varchar(45); // изменить имя столбца
  * Alter table films add language varchar(50) null; //добавить столбец 
    * ALTER TABLE books ADD PRIMARY KEY (book_id); //Добавить ключ (индекс) после создания таблицы:
  * alter table films DROP COLUMN language; //удалить столбец
  * alter table films MODIFY language int; //изменить тип данных в столбце
  * alter table peoples modify column name varchar(100) not null; //добавить NOT NULL
* ALTER COLUMN
  * alter table pets alter column id_human_friend set default 1; //установить значение по умолчанию уже после создания
* DROP - удаление текущего объекта (базы данных)
  * DROP TABLE student; //удалить таблицу
  * DROP DATABASE db; //удалить базу данных
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
* UPDATE - изменение/изменить уже существующих данных (полей)
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
**Транзакция** - это набор команд или инструкций которые выполняются как единый блок. Если одна из команд блока не выполнится по какой-то причине, то изменения откатятся обратно.
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
  + DECIMAL(N) - число с заданной точностью для сложных вычислений. (8,2) - всего 8 цифр, 2 после запятой. 
  Макс = 999999.99
  + BOOL
* Символьные
  + VARCHAR(N), N - максимальная длина строки
  + TEXT - для больших текстов, типа статьи, до 65КВ
* Дата и время
  + DATE - только дата типа '9999-12-31' // обазятельно кавычки
  + TIME - только время - часы, минуты, секунды "hh:mm:ss". Память хранения - 3 Байта
    * разница между двумя значениями: time(next_st - station_time)
    * разница между двумя значениями в формате времени: timediff (value1, value2)
  + DATETIME - дата и время, 8 Байтов // YYYY-MM-DD hh:mm:ss
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
  WHEN condition THEN expression;
  WHEN ... THEN ...;
  ELSE ...
END case;
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

IF statement then statement;
END IF;

---

`Ошибки/errors`

+ 3730 - нельзя удалить таблицу со связанными вторичными ключами
SET foreign_key_checks = 0; //удалил
SET foreign_key_checks = 1; //сделал как было

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
* COUNT: вычисляет количество строк в запросе (значения NULL не учитываются)
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

---

> `Оконные функции`

**Синтаксис оконной функции**
SELECT
тело функции
OVER
  (PARTITION BY столбец для группировки)
  ORDER BY столбец для сортировки
  ROWS или RANGE выражение для ограничения строк в пределах группы
)

**Оператор OVER** отличается от функции аггрегации Group by тем, что при использовании оконных функций количество строк в запросе не уменьшается по сравнении с исходной таблицей.
  Пример: если мы хотим получить сумму по 1 из столбцов, эта сумма будет отображаться для каждой строки
  * select model, manufacturer, quantity, price, sum(quantity) over (partition by manufacturer order by quantity) totalQuantity from phones; //в каждой строке общая сумма, группируем с помощью `partition by`, сортируем по какому-то критерию (при этом общая сумма будет как бы по нарастанию)

`Партиции` - набор из 1 и более столбцов, по которым производится выборка с помощью partition

ROWS и RANGE //для оператора OVER
UNBOUNDED PRECEDING — указывает, что окно начинается с первой строки группы;
UNBOUNDED FOLLOWING – с помощью данной инструкции можно указать, что окно заканчивается на последней строке группы;
CURRENT ROW – инструкция указывает, что окно начинается или заканчивается на текущей строке;
BETWEEN «граница окна» AND «граница окна» — указывает нижнюю и верхнюю границу окна;
«Значение» PRECEDING – определяет число строк перед текущей строкой (**не допускается в предложении RANGE**).;
«Значение» FOLLOWING — определяет число строк после текущей строки (**не допускается в предложении RANGE**).
Пример
select model, manufacturer, quantity, price, sum(quantity) 
over (partition by manufacturer order by quantity rows between current row and 1 following) totalQuantity 
from phones; //в каждой строке сумма текущего значения и следующего, группируем с помощью `partition by`, сортируем по какому-то критерию (при этом общая сумма будет как бы по нарастанию)

`Оконные функции:` (доступены в MySql с версии 8.0)
1. Аггрегатные функции //уже изучили
  * sum()
  * max()
  * min()
  * avg()
  * count()
2. **Функции ранжирования**
  * ROW_NUMBER – функция возвращает номер строки и используется для нумерации;
```sql
with hd as (select id_pack_animal, name, birth_day from horses UNION ALL select id_pack_animal, name, birth_day from donkeys)
select ROW_NUMBER() OVER (ORDER BY name) AS seq_numb, hd.name as name, hd.birth_day as birth_day, pa.kind as kind
from pack_animals as pa
right join hd
on hd.id_pack_animal = pa.id_pack_animal;
```

  * RANK — функция возвращает ранг каждой строки. Ранг определяется относительно значения строки по определенному столбцу. КРЧ, если, например, значения цены одинаковые - ранг будет одинаковый.  Но! с пропуском следующего значения. Это значит, что если у следующей строки значение будет отличаться, то она будет уже рангом не 1, а 3;
  * DENSE_RANK — функция возвращает ранг каждой строки. Но в отличие от функции RANK, она для одинаковых значений
  возвращает ранг, не пропуская следующий; Это значит, что у первых двух одинаковых строк будет ранг 1, а у третей 2
  * NTILE – это функция, которая позволяет определить к какой группе относится текущая строка. Количество групп задается в
  скобках //В случае, когда число строк не делится нацело на число групп, функция NTILE помещает в последние группы на одну строку меньше, чем в первые.
    * распределение по группам происходит на основании order by. Количество элементов списка делится на кол-во групп. Берем N элементов и помещаем в 1 группу и т.д.
3. **Функции смещения**
  * first_value() //крч первое значение в окне по столбцу
    * select model, manufacturer, quantity, price, 
    first_value(price) over (partition by manufacturer order by manufacturer) as f_v from phones;
  * last_value(столбец) //крч крайнее значение в столбце
  * lag(столбец, 1 - на сколько смещение) //крч предыдущее значение в столбце
  * lead() //крч следующее значение в столбце
  * nth_value(столбец, N строки) //крч вернет конкретную строку по ее номеру в каждом окне (группе)
    * select model, manufacturer, quantity, price, 
    nth_value(model, 2) over (partition by manufacturer order by price RANGE BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) as cheapest
from phones;
Пример использования **агрегатной функции + оконная**
select model, manufacturer, quantity, price, 
sum(quantity) over (partition by manufacturer) as totalQuantity,
count(quantity) over (partition by manufacturer) as count,
max(quantity) over (partition by manufacturer) as max,
min(quantity) over (partition by manufacturer) as min,
avg(quantity) over (partition by manufacturer) as avg
from phones;

Пример, демонстрирующий работу **ранжирующих функций**
select model, manufacturer, quantity, price, 
row_number() over (partition by manufacturer order by quantity) as quan,
rank() over (partition by manufacturer order by quantity) as rnk,
dense_rank() over (partition by manufacturer order by quantity) as d_rank,
ntile(3) over (partition by manufacturer order by quantity) as n_tile
from phones;

`Функции смещения`
**LAG или LEAD** – функция LAG обращается к данным из
предыдущей строки окна, а LEAD к данным из следующей строки.
Функцию можно использовать для того, чтобы сравнивать
текущее значение строки с предыдущим или следующим. Имеет
три параметра: столбец, значение которого необходимо вернуть,
количество строк для смещения (по умолчанию 1), значение,
которое необходимо вернуть если после смещения возвращается
значение NULL;
**FIRST_VALUE или LAST_VALUE** — с помощью функции можно
получить первое и последнее значение в окне. В качестве
параметра принимает столбец, значение которого необходимо
вернуть.

`Представления` - являются виртуальной копией таблицы (результатом запроса)

**Синтаксис представления** 
create or repleace view view_name as alias_name //где "or replace" не обяз, однако если такое представление существует - вернется ошибка
select columns
from tables
where conditions; //where - необязательная часть

**Пример**: 
create view apple_market as
select * from phones 
where manufacturer = 'apple';

**Создание view через join:**
create view view1 as
select f.Name as film_name, f.description, g.name
from films as f
inner join film_genre as fg
on f.id = fg.id_film
inner join genres as g
on fg.id_genre = g.id
and g.name = 'adventure';

**Операции с представлениями** = удаление (через DROP VIEW) и объединение (через Join), изменение (через Alter view)
  * изменение
    * alter view apple_market as select model, manufacturer, price from phones; //просто выбрал меньше столбцов

`DT (derived tables) ` Производные таблицы (вложенный запрос) //крч фишка в том, чтобы джоинить с таблицей, которая создается в процессе запроса (в скобках еще один селект после join)
Пример
select f.name, f.date, f.duration, f.description, ft.name as genre
from films f
left join 
(select fg.id_film, g.name from film_genre as fg inner join genres as g on fg.id_genre = g.id)
as ft
on f.id = ft.id_film

`CTE (common table expressions)` Обобщенные табличные выражения //тоже самое что и вложенные, только мы как бы сразу прописываем вложенную таблицу, с которой будем джоинить) - **проще читаются**
Пример
with ft as (select fg.id_film, g.name from film_genre as fg inner join genres as g on fg.id_genre = g.id)
select f.name, f.date, f.duration, f.description, ft.name as genre
from films f
left join ft
on f.id = ft.id_film; 
**можно и несколько выражений с with в начале**

`Рекурсивная функция (запрос)`
Пример
with recursive sequence (n) as
(
select 0 //начальное значение
union
select n + 1 //прирост на каждом шагу
from sequence 
where n + 1 <= 10
)
select n 
from sequence;

---

`Транзакция` — это набор последовательных операций с базой
данных, соединенных в одну логическую единицу.

**Транзакции делят на два вида:**
**Неявные транзакции,** которые предусмотрены на уровне базы
данных. Например, БД задает отдельную инструкцию INSERT,
UPDATE или DELETE как единицу транзакции.
**Явные транзакции** — их начало и конец явно обозначаются такими
инструкциями, как BEGIN TRANSACTION, COMMIT или ROLLBACK.

ACID - свойства транзакций
A - атомарность. Либо все действия будут выполнены успешно в целом, либо не выполнены вовсе.
С - согласованность. Исходные данные должны остаться неизменными после работы с ними. 
I - изоляция - свойство транзакции, которая позволяет скрывать изменения, которые были внесены одной операцией, а также устранение эффектов потери данных. 
D - устойчивость. Сделанные изменения точно будут применены несмотря ни на что.
  * **эффект потерянного обновления** - случай, когда две транзакции обновляют одни и теже исходные данные. Например, исходные 1000 рублей, две транзакции +200 и +300 рублей. Итоговые 300 обновляют не 1200, а исходные 1000. В итоге получаем неверный результат 1300 вместо 1500.
  * **эффект грязного чтения** - транзакции могут видеть промежуточные результаты других транзакций. 
  * **эффект фантомного чтения** - возникает в случае, если вы выбираете некоторый диапазон строк, затем другая транзакция вставляет новую строку в этот диапазон, после чего вы выбираете тот же диапазон снова. В результате вы увидите новую фантомную строку.
  * **эффект неповторяемое чтение** — это аномалия, которая возникает при повторном чтении ячейки таблицы, в которую были между этими чтениями внесены изменения. Таким образом, при повторном чтении мы можем увидеть вовсе не те данные, что были там раньше.

  Уровни изоляции:
  1. Read Uncommited - транзакции могут видеть результаты не зафиксированных транзакций
    + эффект потерянного обновления
    - остальные эффекты
  2. Read Commited - транзакция увидит только те изменения, которые были уже зафиксированы другими транзакциями к моменту ее начала. 
    + эффект потерянного обновления
    + эффект грязночго чтения
    - эффект фантомного чтения
    - эффект неповторяемого чтения
  3. Repeatable read - считывает изменения данных, позволяет считывать условия поиска
    + все, кроме
    - эффект чтения фантомов
  4. Serializable 
    + решает все эффекты
D - долговечность - если транзакция выполнилась, то результат сохранится даже в случае каких-либо нештатных ситуаций

**Пример транзакции**
start transaction;
select total from accounts where id = 2;
update account set total = total - 3000 where id = 2;
update account set total = total + 3000 where id = 3;
commit;

`Временные таблицы` - таблицы, которые живут только на протяжении сеанса в MySql и доступны только тому пользователю, который их создает. Зачастую во время использования нужно не забывать удалять.
create temporary table table_name (
 fcol,
 scol, 
 thcol
);

`Переменные в MySQL:`
Задание/создание переменной:
  * SET @variable_name := value
  * SELECT @variable_name := value
  * select 
  @most_expensive := max(price) 
  from phones;

`Оператор IF`
IF(выражение, если правда, если ложь)
  * select IF (@mose_expensive > 200, true, false);
  * select 
  if (language is null, 'eng', language)
  from films;
  * **C Агрегатными функциями**:
  Select
    sum(if(status = 'Shipped', 1, 0)) as Shipped,
    sum(if(status = 'Cancelled', 1, 0)) as Cancelled
  from orders;

`Процедуры` - тоже самое, что и функции, только ничего не возвращают. //используются чаще всего для UPDATE, INSERT
Пример
DELIMITER$$ //меняет разделить по умолчанию (;) на // до след. определения
delimiter // - **внимание на пробел**
DROP PROCEDURE IF EXISTS square;
CREATE PROCEDURE square(n INT)
BEGIN
    select n*n;
END;
               //ЗДЕСЬ ПРОБЕЛ, ПО-ДРУГОМУ НЕ РАБОТАЕТ ХЗ
delimiter ;

call square (5) ;
CALL pr_name (); //вызов процедуры

**Пример №2**
delimiter //
DROP PROCEDURE IF EXISTS hello//
create procedure hello()
	begin
			SET @current_time := CURTIME();
		case
			when current_time >= '18:00:00' then select 'Добрый вечер!';
			when current_time >= '12:00:00' then select 'Добрый день!';
			when current_time >= '06:00:00' then select 'Доброе утро!';
			else select 'Доброй ночи!';
		end case;
	end//
delimiter ;
call hello();

`While цикл`
While condition DO
body
END while;

delimiter //
drop procedure if exists numbs;
create procedure numbs (n int)
begin
create temporary table numbers (Numb int);
while (n >=0) do
insert into numbers (Numb) value (n);
set n = n - 1;
end while;
select * from numbers;
drop table numbers;
end//
delimiter ;
call numbs(10);

`Текущее время:`
select CURTIME();


`Фибоначчи` + PROCEDURE + WHILE + IF

DELIMITER //
DROP procedure IF EXISTS fibonacci;
CREATE PROCEDURE fibonacci(input INT)
BEGIN
	Set @n1 := 0, @n2 := 1, @i := 2, @temp := 0;
	CREATE TEMPORARY TABLE Numbers (Num int); 
    if input = 0 then select 'Число не может быть < 1';
    elseif input = 1 then insert into Numbers (Num) value (0); 
    select Num from Numbers;
    drop table Numbers;
    else
    insert into Numbers (Num) values (0), (1); 
	WHILE (@i < input) Do
		Set @temp = @n2;
		Set @n2 = @n2 + @n1;
		Set @n1 = @temp;
		Insert Into Numbers (Num) VALUE(@n2);
		SET @i := @i + 1;
	END WHILE;	
	SELECT Num FROM Numbers;
	DROP TABLE Numbers;
    END IF;
END//
DELIMITER ;

call fibonacci(6);


`Конкантенация/соединение(объединение) строк`
Пример:
set @result := '';
set @result := concat_ws(' ', @result, n); //не работает, по идее работает с сепоратором
set @result := concat(' ', @result, n);

* Разница даты в днях. Чтобы в месяцах: /30
datediff(CURDATE(), birth_day)

* Объединить таблицы с разным количеством столбцов:
Select Col1, Col2, Col3, Col4, Col5 from Table1
Union
Select Col1, Col2, Col3, Null as Col4, Null as Col5 from Table2 //за счет Null значения





