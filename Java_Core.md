Преподаватель лекций - Иван Овчинников (Российские космические системы)
Преподаватель семинаров - Байраковский Станислав. Программист в компании ООО «ЕВРОП АССИСТАНС СНГ», занимаюсь разработкой сложных, профильных, программных комплексов компании (Телемедицина, интеграции с платежными шлюзами, провайдерами).

Основные среды разработки:
1. IntelliaIdea - без комментариев
2. Eclipse - среда разработки под разные языки за счет расширяемости с помощью плагинов //медленная и тяжеловесная
3. NetBeans - самая первая, разработанная чисто под Java, продолжительное время не поддерживалась и не обновлялась
4. AndroidStudio - для мобильных устройств

JDK (java development kit) = JRE + инструменты разработчика;
JRE (java runtime environment) = JVM + библиотеки классов;
  * JSE (java standed edition)
  * JEE (java enterprice edition)
JVM (java virtual machine) = Native API + механизм исполнения + управление памятью.

JDK - инструментарий разработчика на языке Java
  * IDE - `обязательное условие`
  * JDK Java Development Kit - инструментарий разработчика - `обязательное условие` (20 версия от Oracle, например)
    * самая распространенная на данный момент - 8я версия
    * тренд на 11 и 13
  * средство для работы с БД
  * средство моделирования
  * средство документирования
  * средство общения
  * средство синхронизации с командой

Вендоры, распространяющие JDK:
  * OracleJDK
  * OpenJDK by Oracle
  * Libercia JDK
  * GOST JDK - имеет лиценизию в гос. корпорациях РФ

JVM - джава-подобные языки:
Groove, Scala, Kotlin ...

Категорирование проектов по сложности:
  * Простейшие (один файл)
  * обычные (несколько пакетов)
  * шаблонные (формируются сборщиками)
  * скриптовые (jupyter notebook)

Проект на Java состоит из:
  * пакет - пространство имен языка (определяет модификаторы доступа к внетренним классам - без импорта эти классы не видны другим классам)
  * классы //могут находиться вне пакетов для простейших проектов в один файл
  * Метод main
  * комментарии
  * ресурсы

Именование пакетов: обратное доменное имя. Если фирма gb.ru -> ru.gb //для пакета //snake_case
  * ВСЕГДА маленькие буквы, можно разделять нижним подчеркиванием _

Именование классов:
  * существительное в именительном падаже
  * с Большой буквы
  * Upper Camel case

* Чтобы определить в Ubuntu mainclass для компилляции: (Если из папки проекта)
javac -sourcepath ./src -d out src/ru/gb/.../Main.java
  * ./src директория с исходным кодом
  * out - куда откомпилированы будут файлы
  * src/ru/gb/.../Main.java - точка входа (главный класс)
  !!! * фактически скомпиллировалось из папки src/main:
    * javac -sourcepath ./java -d out .\java\org\example\Main.java

* Запустить скомпиллированный класс: (из src/main)
  * java -classpath ./out org.example.Main //без расширения

* Утилита для документирования `javadoc` //находится в JDK
javadoc - создает веб-страницу с документацией из комментариев с индексом .html  
Пример: javadoc -d docs -sourcepath src -cp out -subpackages ru
  * javadoc -encoding utf-8 -d docs -sourcepath java -cp out -subpackages org //из папки ./src/main
Ключи:
  * -encoding utf-8 - чтобы небыло абракадабры вместо кириллицы
  * -d docs //директория, куда необходимо сложить готовую документацию
  * -sourcepath src //исходники кода - тут пишем комменты
  * -cp out (classpath) //где лежат готовые скомпиллированные классы
  * -subpackages //указание брать не только указанный основной пакет, но и все подпакеты рекурсивно
  * ru //пакет, в котором нужно создавать документацию
**Точка входа после создания** - ./docs/index.html

Особенности работы в Windows: //относительно javadoc
-local ru_RU
-encoding utf-8
-docencoding cp1251 //стандартная кодирофка в винде под США

Makefile - набор инструкций для программы make //утилита для С++
Код + Makefile -> make -> compiler -> programm
  * make clean - чтобы рекурсивно очистить папку out, очистить кэш
  * make run - запускает главный класс в папке out

* Добавить Docker в переменные среды:
C:\Program Files\Docker\Docker\resources\bin

`dockerfile - конфиг, который делал в Винде`

FROM bellsoft/liberica-openjdk-debian
LABEL maintainer = "Vadim Timofeev"
LABEL mt_telegram = "@vadim.yalta"
COPY ./java /src
ENV LANGUAGE ru_RU.UTF-8
ENV LANG ru_RU.UTF-8
ENV LC_ALL ru_RU.UTF-8
RUN locale-gen ru_RU.UTF-8 && dpkg-reconfigure locales
RUN javac -sourcepath ./src -d ./src/out ./src/org/example/Main.java
CMD java -classpath ./src/out org.example.Main

---



