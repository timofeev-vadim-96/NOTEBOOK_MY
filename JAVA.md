# JAVA - кроссплатформенный язык с обратной совместимостью всех версий  

Java - строготипизированный, объектноориентированный язык программирования общего назначения

Слоган/Дивиз - "Написано однажды, работает везде"

Преподаватель:
  * (лекции) - Сергей Камянецкий  
  * (семинары) - Александр Леонидов  

Самая удобная среда разработки - **IntelliJ IDEA**  

несколько **правила/rools, относительно java**:

1. Названия классов в java - существительные с большой буквы верблюжьей нотацией: CamelCase,   
2. названия методов - отглагольные, с маленькой буквы, верблюжьей нотацией: getUserById.  
3. Названия переменных - с маленькой буквы, верблюжьей нотацией: maxCount  
4. названия пакетов в java существительные, всегда с маленькой буквы и в одно слово.  
5. Если логика классов внутри пакета не позволяет назвать все в одно существительное, надо вложить один пакет в другой  
6. Называть классы по их функционалу  
7. Названия итерируемых объектов - с маленькой буквы в множественном числе без List, Set... типа ~illebleList~ - > 
illebles  
8. Main - в корневой папке программы, все остальное - по пакетам.  

Минимальная единица языка программирования JAVA - класс.   
100% случаев имя класса = имя файла. (по умолчанию)  
Место, с которого начинается выполнение программы - точка входа. Она обозначается функцией main.  

Базовой единицей в итоге является класс, который в себе содержит метод с названием **main**, который принимает в себя обязательно массив строк. 

Любой метод в JAVA должен быть частью класса.  


### Снипеты  

Чтобы быстро писать код - лучше учить **снипеты**:
sysout/sout = print;  
psvm - public static void main;  

Чтобы запустить скомпиллировать файл:
  * кнопка
  * в консоль: java filename.java 
  * Shift + F10 

Многострочный комментарий (документация Java):  
/**

В документации указывается:
@param - параметры/аргументы
@return - что возвращает
@throws - какие исключения бросает

Однострочный комментарий: (как в C#)  
//  

Типы данных:
1. Ссылочные (относятся к классам) //могут хранить Null в качестве значения
  * массивы  
  * и все остальное 
2. Примитивные (примитивы) всего 8 типов
  * byte (8 бит) значения в диапазоне от -127 до 127
  * boolean
  * int (занимает 32 бита или 4 байта)  
    * при объявлении больших чисел их разряды можно разделять нижним подчеркиванием: a = 123_123_123  
  * short (более мелкий тип, хз зачем) 16 бит, знаковый
  * long  // 64 бита = 8 Байт
    * 5_000_000_000L - L large
  * float - 32 бита (1 бит - на знак, 8 бит - на целую часть, 23 бита - на дробную часть)
    * объявление: float n = 2.7f; f - обязательно. почему так? потому что изначально вещественное число 
    воспринимается как тип double. Для double, соответственно, этого можно не делать(но можно, суффикс D)  
  * double - 64 бита бита (1 бит - на знак, 11 бит - на целую часть, 52 бита - на дробную часть)
  * char - отдельный символ, 16 бит, кодировка UTF-8
  * byte (хранит значение до 127)  считается, что занимает 1 Байт. Но можно постараться реализовать 1 бит
3. доп.  
* String - точно не примитивный тип данных, сколько занимет памяти на выходе не ясно(условно 2Б на символ, но
сколько символов в итоге?)
  * * Чтобы обратиться к элементу строки: s.charAt(0)   
* var - неявная типизация  
4. интерфейсные типы
5. параметризированные типы - дженерики, в которых уже произошло объявление типа

* В Java константу объявляют добавлением слова `final`
`final` - переменная с конечным значением/класс или метод с финальной реализацией (нельзя наследовать или переопределять)

В JAVA у типов данных есть **Классы-обертки**: 
* Integer и т.д. Вызов всего функционала проиходит через них. Integer.MAX_VALUE
* Short
* Long
* Byte
* Float
* Dobule
* Character
  * Character.isLetterOrDigit - проверка на число или букву
* Boolean

* Чтобы вывести тип данных: 
public static String getType(Object o) {
        return o.getClass().getSimpleName();
        } 

Логические операторы - как в C#. 
Также есть и обычные и побитовые. В общем случае используем:
  * && - и
  * || - или  
  * ^ - возвращает True когда **строго** одно из значений - истина. 2 - нельзя, 0 - нельзя. только 1.  
  * ! - оператор отрицания. как not in python  

Побитовые операции:  (вряд ли пригодится, мб для написания функций корней и т.д., но очень вряд ли)
  * побитовые сдвиги <<, >>, ^, |, &  
  << - сдвиг влево - каждый сдвиг это умножение числа на 2
  />> - сдвиг вправо - деление числа на 2
  Интересно: в отличие от бытрых логических операторов, побитовые в любом случае проверяют все условия, тогда
  когда быстрые - в случае не выполнения одно из условий - выдают сразу false(и это удобно)  
  Пример:  
  a = 8  (1000 - в бит виде)
  a << 1  
  10000 (16)  
  * побитовое или |
  Пример: 2 | 5
  Сравнивает
  101
  010
  111 - в итоге = 7  
  * побитовое и &  
  Пример: 2 | 5
  Сравнивает
  101
  010
  000 - в итоге = 0 
  * побитовое строго или ^
  Пример: 2 | 5
  Сравнивает
  101
  010
  111 - в итоге = 7  

Объявление и инициализация переменных:  
Можно просто объявить переменную:
  * string n
  Однако в том случае, если далее она не будет инициализирована, то вызовет ошибку

В общем случае лучше всегда сразу ее инициализировать
  * string n = null  

Операции Java:  
/ - нужно смотреть разницу для интовых и вещественных  
вспоминаем:
++ - инкремент
-- - декримент  
могут быть постфиксными a++, и префиксными ++a  

! По поводу выполнения операций - важно обращать внимание на **приоритет операции**:
например: print(a++) - выведет старое значение а, а уже после увеличит его значение  
        выход: print(++a) - мы поменяли приоритет операции  

* Чтобы объявить создать массив:  
    * int [] arr = new int [10]  
    * int [] arr = new int [] {1,2,3,4}  
    * int [] arr = {1,2,3,4}  
    * T [] temp = values;  
    * values = (T []) new Object[10];  
    * без инициализации:  
        * int [] arr;
        * int arr [];

* Чтобы **скопировать массив /copy**:  
System.arraycopy(temp,0, values,0, temp.length);  temp - откуда, 0 - c какого элемента копируем, values - куда копируем, 0 - начиная на какую позицию копируем, temp.lenght - сколько всего элементов копируем(указываем длину массива, из которого копируем)  

* Скопировать список:  
notes.addAll(notesFromJson);  

`Двумерный массив`
```java
//Чтобы объявить двумерный массив:  //массив массивов
    * int [] arr [] = new int [3] [5];  
    * int [][] arr = new int [3] [5]; //массивы массивов, хз что значит;  
    * int [][] arr = new int [3] []; //можно не объявлять размер второго измерения, но при этом будет происходить небольшая утечка памяти 
    * int [][] numbs = {
                {2, 44},
                {12, 42},
                {85, 95},
        };

//Размер двумерного массива. Но это при условии, что все вложенные массивы одного размера. 
//т.к. в java двумерный массив - это массив массивов, все вложенные массивы могут оказаться разных размеров и целесооразно проверить их размер в цикле.
int rows = arr.length; // Получение количества строк
int cols = arr[0].length; // Получение количества столбцов


//Обойти двумерный массив:
    public int getSumElem(int [][] inputArr){
        int result = 0;
        for (int[] a: inputArr) { //inputArr.length
            for (int b: a) { //inputArr[i].length
                result+=b;
            }
        }
        return result;
    }

//длины двумерного массива
public static void main(String[] args) {
    int[][] foo = new int[][] {
        new int[] { 1, 2, 3 },
        new int[] { 1, 2, 3, 4},
    };
    System.out.println(foo.length); //2 количество массивов в массиве
    System.out.println(foo[0].length); //3
    System.out.println(foo[1].length); //4
}

//Чтобы проверить на матрицу - нужно обойти циклом и убедиться, что длины всех входящих массивов = его величине
        for (int[] a: inputArr) { //проверка каждого внутреннего массива
            if (a.length != inputArr.length){
                throw new RuntimeException("Двумерный массив не является матрицей");
            }
        }

//Вывести двумерный массив с табуляцией:
for (int [] arr: newArray) {
            for (int numb: arr) {
                System.out.print(numb + "\t");
            }
            System.out.println();
        }
```

* Преобразования:  
    * неявное:  
    int i = 123; // i = 123
    double d = i; // d = 123.0  

    d = 3.141;
    i = (int)d; // 3  

* Распарсить строку в байты:  
  * byte b = Byte.parseByte("127");  

* Распарсить строку в дабл/int:  
  * Double.parseDouble(str);  
  * Scanner.nextDobule;  

* Проверка буфера на наличие инта (интовоего значения), для дальнейшего использования next.int()
scanner.hasNextInt();

* Для получения данных из терминала:  
  import java.util.Scanner;

* Чтобы получить текущую дату:  
this.year = LocalDate.now().getYear();  

* Вывести строку через маску (спецификатор):  
String S = String.format("%d + %d = %d \n", a, b, c);  
        System.out.printf("%d + %d = %d \n", a, b, c);  
        System.out.println(S);  

Виды спецификаторов:  
%d: целочисленных значений  
%x: для вывода шестнадцатеричных чисел  
%f: для вывода чисел с плавающей точкой  
%e: для вывода чисел в экспоненциальной форме,  
например, 3.1415e+01  
%c: для вывода одиночного символа  
%s: для вывода строковых значений  

* Количество знаков после запятой:  //округление дробных
float pi = 3.1415f;  
System.out.printf("%.2f\n", pi); // 3,14  

* Тернарный оператор:  
int min = a < b ? a : b; // если true - a, false - b; 

* Циклы:  
  * при работе с foreach мы работаем с item (как бы копия элементов i в массиве), так что присвоить новое значение  
  элементу массива с помощью foreach не получится.  

> package - пакет, у которого есть своя область видимости  

* **Static в объявлении** метода: *Если метод статик, то он принадлежит к классу, а если без статика, то к экземпляру класса.*  Статические вещи необходимо использовать по-минимуму, т.к. при запуске программы они как резервируют себе место в памяти до конца выполнения программы.  
+ внутри статики нельзя использовать нестатические аргументы и методы.  

Например:  
public class Main {  
    public static void main(String[] args) {  
        Task1 task1 = new Task1();  
        Task1.Hello();  // обращаемся к классу. без статики нужно будет обращаться к экземпляру класса task1  
    }  
}  

* Чтобы вывести текущее время и дату:  
System.out.println(LocalDateTime.now());  

* Изменить размер шрифта кода: Alt+Shift+./Ю  

* Чтобы считать текст с консоли:  
Scanner scanner = new Scanner(System.in); // переменная типа сканер  
String data = scanner.next();  
  * чтобы работать со сканнером в VSC:  
  import java.util.Scanner;  

* Чтобы запушить на GitHub:  
VCS на панели инструментов -> Share project... 

* Чтобы построить строку с помощью стринг-билдера:  
StringBuilder sb = new StringBuilder();  
for (int i = 0; i < 1_000_000; i++) {  
sb.append("+");  
}  

**Работа со строками** - при сборке строки лучше использовать StringBuilder, при разборке/поиске - String.  (От этого зависит скорость р-ты)  

* массив символов (чаров) в строку: (массив в строку)
new String(password.getPassword())

**Функции/методы для работы со строками:**  
.concat(): объединение строк  
.valueOf(): преобразует Object в строковое представление (завязан на toString())  
  * число в строку, char в строку и т.д.
.join(): объединяет набор строк в одну с учетом разделителя  //String.join("", arg)
.charAt(): получение символа по индексу  
.indexOf(): первый индекс вхождения подстроки  
.lastIndexOf(): последний индекс вхождения подстроки  
.startsWith()/endsWith(): определяет, начинается/заканчивается ли строка с подстроки  
.replace(): замена одной подстроки на другую  
.trim(): удаляет начальные и конечные пробелы  
.substring(): возвращает подстроку, см.аргументы  
.toLowerCase()/toUpperCase(): возвращает новую строку в нижнем/верхнем регистре  
.сompareTo(): сравнивает две строки  
.equals(): сравнивает строки с учетом регистра  
.equalsIgnoreCase(): **сравнивает строки без учета регистра**  
.regionMatches(): сравнивает подстроки в строках  
.getBytes(StandardCharsets.UTF_8): для перевода строки (особенно кириллицы, с указанием) в байты
.removeIf(лябда-выражение)
  * uniqueNames.removeIf(x->x.contains("A") || x.contains("a")); удалить все значения по условию

* Чтобы привести StringBuilder(стрингбилдер) в строку:  
newStroke.toString()  

* Чтобы привести строку в массив:  
String [] arr = inputString.split("");  


* Чтобы вывести (распечатать) массив:  
System.out.println(Arrays.toString(arr));  

* Чтобы вывести (распечатать) ArrayList:  в строку  
System.out.println(Arrays.toString(arr.toArray())); 

* Чтобы вывести (распечатать LinkedList): в строку
String joinedString = String.join(", ", list);

* вывести/распечатать LinkedList, а мб и все типы листов:  
list.forEach(System.out::println);  

* Чтобы сравнивать строки:  
stroke1.equals(stroke2)  

* Чтобы пробежаться/пройтись по строке:     
for (char c: arg.toCharArray()){   
  ...  
}  


* Метод для каскадного заполнения списка:  
public VetClinic addPet(Animal animal){  
        pets.add(animal);  
        return this;  
    }  
*Теперь можно будет добавлять животных через точку*


* Пройтись/перебрать/пробежаться по списку объектов:  
1. animals.forEach(a -> System.out.println(a));  


2.         for (Animal a: animals){  
            System.out.println(a);  
        }  

* С помощью перебора вызвать функцию у объекта  
3. animals.forEach(Animal::speak); //тоже самое, что animals.forEach(a -> a.speak());  

* С помощью streamAPI
4. data.stream().forEach(value -> System.out.println(value + "something"));

**Работа с файлами:**  

Загрузчики: 
MBR - main boot record (главная загрузочная запись) - для запуска операционной системы (загрузчик), часть системы BIOS
GPT - GUID partition table - разбивает ОС на ячейки, где первая под MBR, часть системы UEFI

BIOS - basic input-output system (х32)
UEFI - новая версия системы биос (х64)

`Файловая система` - один из ключевых компонентов операционной системы. Это структуризация, чтение, хранение и запись файлов. Выделяют Windows-подход и Linux-подход
`Файловая система` - архитектура хранения информации(способ разбиения диска на сегменты для хранения информации), размещенной на жестком диске и в оперативной памяти

Категории файловых систем:
журналируемые - сохраняют историю манипуляций пользователя (логи) (повышенная стойкость системы и сохранность данных)
не журналируемые (более быстрые)
Перечень файловых систем:
* Ext(extended)FS
* Ext2,3,4
* JFS, ReiseFS, XFS, Btrfs, F2FS
* EncFS, Aufs, NFS
* Tmpfs, Procfs, Sysfs

Файлы в `Linux`:
* Regular File - обычные - для хранения двоичной и символьной информации
* Device File - для физических устройств
* Sift link - мягкая ссылка - для доступа к информации, находящейся на любых носителях
* Directories - каталоги - файл с указателями на директории и файлы
* Block devices and sockets - блочные и символьные устройства - для сопряжения с аппаратной составляющей

Файловая система в `Windows`:
FAT(16) File Allocation Table (таблица размещения файлов) для ОС MS-DOS 3.0, Windows 3.x.; 95, 98, NT/2000...
FAT(32) с версии 95 и выше:
  * возможность перемещения корневого каталога
  * возможность хранения резервных копий
  * более высокая скорость запуска программ
NTFS - new technology file system - файловая система новой технологии
  * права доступа
  * шифрование данных
  * дисковые квоты
  * хранение разряженных файлов
  * журналирование (логирование)

`Утилитный класс` — это класс, имеющий набор статических методов, никак не связанных между собой и не обладающий состоянием. Характерной особенностью таких классов является использование множественного числа в их имени, например, Collections, Objects, а также слов Util и Helper, например, ArrayUtils.

//старый класс, после java 7.0 его разбили на 3 новых: Paths, Path, Files
`Class File`, методы:
file.getParentFile().mkdir() - создать верхнюю папку
file.createNewFile() - создать новый файл
file.listFiles() - список файлов в этой папке
file.getName() - имя файла
file.exists() - булевая проверка на существование
file.isDirectory() - узнать, является ли файл директорией
file.isFile() - узнать, является ли файл файлом
file.length() - размер файла
file.getAbsulutePath() - получить полный путь
file.getFreeSpace() - получить размер свободного диска в директории, где находится файл
file.delete() - удалить файл
file.getTotalSpace() - общий размер диска в директории, где находится файл

`Class Paths`, единственный **статический** метод:
//Path path = // `Paths`.get(file.Name()) - из строки URI получить объект типа Path

URI - Uniform Resource Identifier (унифицированный идентификатор ресурс)
URL - Uniform Resouce Locator (унифицированный определитель местонахождения ресурса)
URN - Uniform Resouce Name (унифицированне имя ресурса)

`Class Path`, методы: //экземпляр класса создается с помощью класса Paths
filePath.getFileName() //значение типа File или String
filePath.getParent() //возвращает имя родительской папки
filePath.endsWith("file.png") //путь (именно путь, а не имя) файла заканчивается на... возвращает boolean
filePath.startsWith("text") //имя файла начининается на ...
filePath.normalize() //нормализует путь, убирая точки . и две точки .., как Linux
  * to.toAbsolutePath().normalize() //использовать с абсолютным, т.к. точки не уходят без него

//пакет new input-output //буфероориетированный
import static java.nio.file.StandartCopyOption.REPLACE_EXISTING; //дословно выполнить перемещение, если файл существует

`Class Files`, все методы **статические**: //утилитный класс
Path file = **Files.createFile**(Paths.get("../pics/file.txt"));
Path directory = **Files.createDirectory**(Paths.get("./directory"));
**Files.exists**(Paths.get("../pics/file.txt"));
file = **Files.move**(file, Paths.get("./testing/file.txt"), REPLACE_EXISTING)
Path copyFile = **Files.copy**(file, Paths.get("../pics/file.txt"), REPLACE_EXISTING)
**Files.delete**(file)
**Files.write**(file, lines, StandartCharsets.UTF_8); //file - путь, lines - список
lines = **Files.readAllLines**(file, StandartCharsets.UTF_8); //file - путь //readAllLines возвращает список строк файла

`package io` //старый, но действующий //потокоориентированный
Содержит классы, определяющие потоки ввода-вывода: InputSteam и OutputStream - являются абстрактными, и определяют методы для работы с байтовыми массивами данных, файлами и строками
`InputStream` - это базовый абстрактный класс для потоков ввода, т.е. чтения
`OutputStream` - это базовый абстрактный класс для потоков вывода, т.е. записи

//Классы для работы с байтовыми потоками:

Их реализации: //вообще они для того, чтобы увидеть в каком представлении будут данные при передаче в байтовом потоке
ByteArrayOutputSteam out = new ByteArrayOutputSteam();
методы:
out.write(-1); //записать 1 байт
out.write(-1, true); //флаг чтобы дозаписывать, иначе все будет перезаписываться
out.close; //обязательно закрывать поток записи
...
ByteArrayInputSream in = new ByteArrayInputSream(out.toByteArray());
методы:
int value = in.read(); //прочтет только первый элемент. Если нужен в байтах, то он исказит int, добавив нули в конце, из-за чего мы можем получить положительный байт. хз короче
int bytesAvailable = in.available() - вернет кол-во доступных (записанных в файле) байтов для чтения (но только не заблокированных, хз)
in.close(); //обязательно закрываем поток чтения

**Другие потоковые классы для работы с байтами**: 
* PipedInputSteam и PipedOutputSteam - всегда привязываются друг к другу //используются для разработки многопоточных приложений
* StringBuilderInputStream //в конструктор передается строка. При этом, символы преобразуются в байты с потерей точности (старший байт отбрасывается - один из двух байтов в чаре, отвечающий за знак)
* SequenceInputStream //для нескольких потоков
* FileInputStream, FileOutputStream и их наследники //надстройки над стандартными потоками в виде адаптеров
* LineNumberInputStream - производит подсчет того, сколько строк было считано из потока //устаревший
  * getLineNumber() - для номера считываемой строки в данный момент
  * setLineNumber(strokeNumber) - для перехода к определенной строке по номеру
* LineNumberReader - аналогичный функционал классу LineNumberInputStream
* PushBackInputStream - позволяет вернуть в поток считанные из него данные
* PrintStream - для конвертации и записи строк в байтовый поток
* PrinterWriter - новый PrintStream
* **BuffedInputStream и BufferedOutputStream** //в 10 раз быстрее, чем InputSteam обычный

**Классы для работы с примитивными типами данных, отличных от байтов:**
//при их работе происходит конвертация в набор байтов и обратно
DataInputStream и DataOutputStream
их реализации:
ObjectInputSteam, ObjectOutputSteam //серриализация и дессериализация

## `Таблица классов для работы с input/output stream:`

| Байтовый поток | Символьный поток |
|----------------|------------------|
| InputStream | Reader |
| OutputStream | Writer |
| ByteArrayInputStream | CharArrayReader |
| ByteArrayOutputStream | CharArrayWriter | 
| Нет аналога | InputStreamReader |
| Нет аналога | OutputStreamWriter |
| FileInputStream | FileReader |
| FileOutputStream | FileWriter |
| BufferedInputStream | BufferedReader |
| BufferedOutputStream | BufferedWriter |
| PrintStream | PrintWriter |
| DataInputStream | Нет аналога |
| DataOutputStream | Нет аналога |

> Классы-мосты InputStreamReader и OutputStreamWriter

## `Работа с файлами (новая), с их содержимым:`

блок кода:
```java
List<String> lines = Arrays.asList(  //список для записи в файл
    "The cat wants to play with you",
    "But you don't want to play with him");
Path file = Files.createFile(Paths.get("cat.txt"));

if (Files.exists(file)){
    Files.write(file, lines, StandartCharsets.UTF_8);
    lines = Files.readAllLines(file, StandartCharsets.UTF_8); //readAllLines возвращает список строк файла

    for (String s: lines){
        System.out.println(s);
    }
}
```

### Жеванные тапки! Что выбрать при работе с файлами?

Бинарные: FileInputStream/FileOutputStream
read() будет возвращать int значение, а когда считываемые байты закончатся, вернет -1

```java
//байты пишет в строку
    private static void writeToFile(String fileName, int amount) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(generateSymbols(amount).getBytes());
        }
    }

//считывание и сразу запись байтов в строку //быстрее было бы через BufferedInputStream, ниже
    private static void concat(String inputFileName1, String inputFileName2, String outputFileName) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);
             FileInputStream fileInputStream = new FileInputStream(inputFileName1);
             FileInputStream fileInputStream2 = new FileInputStream(inputFileName2)){
            int c;
            while ((c = fileInputStream.read()) != -1){
                fileOutputStream.write(c);
            }
            while ((c = fileInputStream2.read()) != -1){
                fileOutputStream.write(c);
            }
        }
    }

//считка и запись НАМНОГО быстрее через буфер
    private static void bufferedConcat(String inputFileName1, String inputFileName2, String outputFileName) throws IOException {
        try (BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(outputFileName));
             BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(inputFileName1));
             BufferedInputStream fileInputStream2 = new BufferedInputStream(new FileInputStream(inputFileName2))){
            fileOutputStream.write(fileInputStream.readAllBytes());
            fileOutputStream.write(fileInputStream2.readAllBytes());
        }
    }

//сравнить два файла по содержимому: преобразуем их в байтовые массивы и сравниваем
        byte[] f1 = Files.readAllBytes(Paths.get("file_out1.txt"));
        byte[] f2 = Files.readAllBytes(Paths.get("file_out2.txt"));
        System.out.println(Arrays.equals(f1, f2));
```
Строковые: FileReader/FileWriter
```java
//Пример СЧИТКИ И ЗАПИСИ через блок try-catch resources //топ пример
try(
    FileReader reader = new FileReader("C:/Users/DELL/Desktop/Разработчик GB/Иключения и ошибки/Л3 " +
                        "Продвинутая обработка исключений/untitled/.gitignore");
    FileWriter writer = new FileWriter("test.txt"))
        {
            while (reader.ready()){
                writer.write(reader.read());
            }
        } catch (IOException | RuntimeException e){
            System.out.println("caught exception: " + e.getClass().getSimpleName());
        }
        System.out.printf("block try-catch resources end");

//`ПРИМЕР ДЛЯ ЗАПИСИ В ФАЙЛ: ` 
public void add(String text) throws IOException {  
        Writer logsWriter = new FileWriter(dataBase, true);  
        logsWriter.write(text + "\n");  
        logsWriter.close();  
    }  
```

---

## Старый метод работы с файлами

//`Создать файл (пустой):`
```java
File file = new File("myfile.txt"); // создаем объект File для файла myfile.txt
if (!file.exists()) { // проверяем, существует ли файл
jsonDataBase.getParentFile().mkdir(); //создаем папку верхнюю папку для файла
file.createNewFile(); // если нет, создаем новый файл
}

//Для работы нужно:  
import java.io.File;  //библиотека для работы с файлами.
File <имя> = new File(<полный путь к файлу>);
File f1 = new File("file.txt"); // относительный путь - маршрутизация от той папки, в рамках которой запущена программа. 
File f2 = new File("/Users/sk/vscode/java_projects/file.txt"); //абсолютный путь  

String pathProject = System.getProperty("user.dir") //предполагаю, что для получения пути к пользователю (мб разный в разных оп. сист) 
String pathFile = pathProject.concat("/file.txt"); //получить путь к файлу  
File f3 = new File(pathFile);  //создать новый файл  
System.out.println(f3.getAbsolutePath ());  //здесь очевидно абсолютный путь к файлу

//`ПРИМЕР СЧИТЫВАНИЯ С ФАЙЛА ПОСТРОЧНО В СПИСОК СТРОК`

//Для считывания файла построчно в Java можно использовать класс `BufferedReader`. Вот пример кода:
```java
public class FileReader {
public static void main(String[] args) throws IOException {
File file = new File("file.txt");
List<String> lines = new ArrayList<>();
BufferedReader reader = new BufferedReader(new FileReader(file));
String line;
while ((line = reader.readLine()) != null) {
lines.add(line);
}
reader.close();
for (String line : lines) {
System.out.println(line);
}
}
}

// В этом примере мы создаем объект `FileReader`, который открывает файл "file.txt" в режиме чтения построчно. Затем мы создаем список строк `lines` и используем `BufferedReader` для чтения каждой строки из файла. При чтении каждой строки мы добавляем ее в список `lines`.
После завершения чтения мы закрываем `BufferedReader`, а затем выводим каждую строку из списка `lines` на экран.

//Читать файл с помощью Scanner
    public static void reader(String filename){
        Scanner sc;
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); //оборот в анчект
        }
        while (sc.hasNext()){
            String nextLine = sc.next();
        }
    }

//Пример СЧИТКИ И ЗАПИСИ через блок try-catch resources //топ пример
try(
    FileReader reader = new FileReader("C:/Users/DELL/Desktop/Разработчик GB/Иключения и ошибки/Л3 " +
                        "Продвинутая обработка исключений/untitled/.gitignore");
    FileWriter writer = new FileWriter("test.txt"))
        {
            while (reader.ready()){
                writer.write(reader.read());
            }
        } catch (IOException | RuntimeException e){
            System.out.println("caught exception: " + e.getClass().getSimpleName());
        }
        System.out.printf("block try-catch resources end");

//`ПРИМЕР ДЛЯ ЗАПИСИ В ФАЙЛ: ` 
public void add(String text) throws IOException {  
        Writer logsWriter = new FileWriter(dataBase, true);  
        logsWriter.write(text + "\n");  
        logsWriter.close();  
    }  
/*
Основные ошибки при работе с файлами:  
1. Неправильный путь;  
2. Отсутствие какой-либо папки;  
3. Отсутствие файла;  
4. Неверный тип файла;  
5. Слишком большой файл, может не хватить памяти, чтобы весь файл одновременно выгрузить;  
6. Файл пустой;  
7. Битый/поврежденный файл (например, не докаченный из интернета);  
8. Файл открыт сторонней программой (вирус/антивирус)...    
*/

// * Для считывания текстового файла:   
FileReader fr = new FileReader(file); //? по идее считывает весь файл   
Чтобы построчно:  
BufferedReader reader = new BufferedReader(fr);  //работает на основе обычного ридера (здесь fr)  

//Узнать размер файла
    public static long getFileSize(File file) {
        if (!file.exists()) return -1L;
        return file.length();
    }

```
---

```java

**Обработка исключений/ошибок:**   
try {  // при записи файла - запись нужно останавливать. Чтобы этого не делать или не забыть об этом:  
пишем создание файла в скобках после try (код)   
Код, в котором может появиться ошибка  
} catch (Exception e) {  // блок catch, если есть какое-то проверяемое исключение  
// на разные ошибки могут быть разные catch  
  * catch (ClassCastException e){  // ошибка кастования, например, родительского класса Object к дочернему Cat  
            e.printStackTrace(); // метод вывода кода ошибки, есть у всех Exception  
        }  
  * catch (ClassCastException e){
            throw new RuntimeException(e); // время возникновения ошибки  
        }
  * } catch (NumberFormatException exception){  
            throw new NumberFormatException();  
        }  
  * catch (AripthemticException e){ //Не обязательно бросать
      System.out.println("Делить на ноль нельзя");
  }
// в скобках кетча можно прописть через | и другие ошибки, которые будут одинаково обрабатываться  
Обработка, если ошибка случилась  
 
finally {  // блок finally - не обязательный  
Код, который выполнится в любом случае  
}  

* **Свое исключение exception**:   
public class MathematicException extends Exception{  
    public MathematicException(String msg){  
        super(msg);  
    }    
}    

```
---

**Функции для работы с файлами:**  
isHidden(): возвращает истину, если каталог или файл является скрытым  
length(): возвращает размер файла в байтах  
lastModified(): возвращает время последнего изменения файла или каталога  
list(): возвращает массив файлов и подкаталогов, которые находятся в каталоге  
listFiles(): возвращает массив файлов и подкаталогов, которые находятся  
в определенном каталоге  
mkdir(): создает новый каталог  
renameTo(File dest): переименовывает файл или каталог  

Полное имя файла - это весь путь к нему, включая сам файл.  
Путь к файлу - путь до папки, в которой лежит файл. 

* Чтобы **создать новый файл**:  
File file = new File("test.txt");  
            file.createNewFile();  

Object  
Пример задания объекта:  
public class Ex01_object {
 public static void main(String[] args) {
 Object o = 1; GetType(o); // java.lang.Integer
 o = 1.2; GetType(o); // java.lang.Double
 }
 static void GetType(Object obj) {
 System.out.println(obj.getClass().getName());
 }
}

**Добавить элемент в список:**  
list.add(numb or smth else)  
list.add(index, data);  // по индексу  
  * если длина списка меньше, чем номер индекса, то этот список нужно, как минимум, заполнить пустыми значениями  
  .add(null); мб в цикле  

Создать список:  
ArrayList<Integer> list1 = new ArrayList<Integer>();  
ArrayList<Integer> list2 = new ArrayList<>();  
ArrayList<Integer> list3 = new ArrayList<>(10);  
ArrayList<Integer> list4 = new ArrayList<>(list3);  
List<Integer> list = List.of(1,2,3); - будет иммутабельным  (**нельзя сортировать или изменять!!!**)  

**Функции для коллекций** (напр. списков):  
add(args) – добавляет элемент в список ( в т.ч. на нужную позицию)  
get(pos) – возвращает элемент из списка по указанной позиции  
indexOf(item) – первое вхождение или -1  
lastIndexOf(item) – последнее вхождение или -1  
remove(pos) – удаление элемента на указанной позиции и его возвращение  
set(int pos, T item) – gjvtoftn значение item элементу, который находится на позиции pos  
void sort(Comparator) – сортирует набор данных по правилу  
subList(int start, int end) – получение набора данных от позиции start до end  
clear() – очистка списка  
toString() – «конвертация» списка в строку  
Arrays.asList – преобразует массив в список  
Arrays.fill(arr, '*') - заполнить массив каким-то одним значением
containsAll(col) – проверяет включение всех элементов из col  
removeAll(col) – удаляет элементы, имеющиеся в col  
retainAll(col) – оставляет элементы, имеющиеся в col  
toArray() – конвертация списка в массив Object’ов  
toArray(type array) – конвертация списка в массив type  
List.copyOf(col) – возвращает копию списка на основе имеющегося  
List.of(item1, item2,...) – возвращает неизменяемый список  
  * List.of('S', 'e', 'r', 'g', 'e', 'y');


StringBuilder пример:   
 StringBuilder day = new StringBuilder("28");  
 StringBuilder month = new StringBuilder("9");  
 StringBuilder year = new StringBuilder("1990");  
 StringBuilder[] date = { day, month, year };  
 List<StringBuilder> d = Arrays.asList(date);  
 System.out.println(d);  
 }  

* Чтобы развернуть строку:
StringBuilder sb = new StringBuilder(s1);
sb.reverse();

**Цикл foreach в JAVA:**  
for(int item: list){  
  sout..(item)  
}  


Итератор:  
Получение итератора с целью более гибкой работы с данными URL  
Интерфейс Iterator<E>. Итератор коллекцией. Iterator занимает место  
Enumeration в Java Collections Framework. Итераторы отличаются от  
перечислений двумя способами:  
Итераторы позволяют вызывающей стороне удалять элементы из  
базовой коллекции во время итерации с четко определенной  
семантикой.  
hasNext(), next(), remove()  # базовый функционал
ListIterator<E> URL  
hasPrevious(), E previous(), nextIndex(), previousIndex(), set(E e), add(E e)  




**Random рандом**:  
Random rd = new Random(); - переменная типа Rand..  
for...
list.app(rd.nextInt(1,11)); - добавляем рандомные числа в список от 1 до 10  

* Чтобы увеличить экран IndIdea:  Alt + Shift + .  

* Отсортировать список или массив:  
Collections.sort(list);    
  * отсортировать объекты по какому-то критерию (например, возраст):  
  wList.sort((w1, w2) -> Integer.compare(w1.age, w2.age)); // но поле "age" должно быть public  
  //2 пример
  products.sort(Comparator.comparingInt(Product::getCost));
  //3 пример
  list.stream().sorted(); //в потоке. Результат нужно возвращать. В самом списке ничего не поменяется

  * Отсортировать в том порядке, который определен уже
  list.sort(Comparator.naturalOrder()) //Тоже самое, что Collections.sort(list, null);

* Отсортировать в обратном порядке:
Collections.reverse(men); //TOP вариант
list.stream().sorted(Comparator.reverseOrder()).toList(); //возвращаем куда-то.



* Встроенный бинарный поиск Java:
Arrays.binarySearch / Collections.binarySearch;


* `Чтобы засечь / сравнить время выполнения`:  
1. Вариант
long startTime = System.currentTimeMillis();  
System.out.println(System.currentTimeMillis()-startTime);  
2. Вариант
Date start1 = new Date();
...функция...
Date end1 = new Date();
long time1 = end1.getTime() - start1.getTime();
System.out.println(time1);
3. Вариант
long startTime = System.nanoTime();
...
double deltaTime = (System.nanoTime() - startTime) * 0.000000001; //в секундах

* Чтобы удалить элемент списка:  
resList.remove(0);  

* Проверить на наличие типа данных:    
for (Object o : list){  
  if (o instanceof Integer)...  
}  

* Задать переменную на уровне класса:  
privat static final ...переменная... name = ...;  //final - значит, что переменную после объявления нельзя будет уже изменить. Пришло какое-то значение, и оно будет как константа.  

LinkedList  
Представляет собой двусвязный список.  
Список – гибкая структура данных, позволяющая легко менять свой  
размер. Элементы доступны для вставки или удаления в любой позиции.  

* Чтобы **создать LinkedList** (двусвязный список):  
 LinkedList<Integer> ll = new LinkedList<Integer>();  

* **Чтобы перевернуть коллекцию (список):**   
Collections.reverse(inputLinkList);  

* Чтобы создать очередь 
1. Queue<Integer> qu = new LinkedList<>();  //принцип FIFO - first in first out  (как очередь к кассе)   
2. PriorityQueue - Наибольший приоритет будет у наименьшего элемента  
  * Чтобы создать приоритетную очередь:  
  PriorityQueue<Integer> pq = new PriorityQueue<>();  
3. Deque - double ended queue - можно добавлять/извлекать данные как из начала, так с конца 
> Чтобы  данные выводились с конца, в Deque нужно не добавлять (add), а пушить!!!!(push) - кладем наверх   

* Чтобы создать Deque:  
ArrayDeque<String> dequeDataBase = new ArrayDeque<>();  

**Методы очереди Deque:**   
* deque.addFirst(1); deque.addLast(2);  
* deque.removeLast(); deque.removeLast();  
* deque.offerFirst(1); deque.offerLast(2);  
* deque.pollFirst(); deque.pollLast();  
* deque.getFirst(); deque.getLast();  
* deque.peekFirst(); deque.peekLast();  

* deque.peek - возвращает без удаления элемент из начала очереди. Если очередь пуста, возвращает значение null;  
* deque.poll - возвращает с удалением элемент из начала очереди. Если очередь пуста, возвращает значение null;
* deque.pop(): возвращает с удалением элемент из начала очереди. Если очередь пуста, генерирует исключение NoSuchElementException  
4. Stack - Lust in first out (как стопка книг) // не используется более, исп-ся ArrayDeque  
[работа со Stack](https://github.com/ZiganshinIB/GB_Seminar_1/blob/master/src/lesson4/task/GBStack.java "Stack")  
Методы `Stack`:
* peek () - вернуть верхний элемент без удаления
* pop () - вернуть верхний элемент с удалением
* push () - положить сверху

* Чтобы привести к числу: Integer.parseInt(argument);  

* Чтобы извлечь элемент из очереди:  
name.poll();  

* Чтобы вывести макс, мин, среднее значение из списка:  
int a = arr.stream().mapToInt(Integer::intValue).max().orElse(Integer.MAX_VALUE);  
double a = arr.stream().mapToDouble(Integer::doubleValue).average().orElse(Double.MAX_VALUE); //сработает с LinkedList

* Склеить строку из объекта другого типа
String s1 = l1.stream().map(Object::toString).collect(Collectors.joining())

* Алгоритм для работы с json:  
1. Создаем не обычный проект java, a mavan or gradle (сборщики проектов) gradle - более современный;  
2. В Browser - "json simple maven dependency"  
ссылка : https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple/1.1  
3. вкладка Gradle - и тупо копировать в файл ссылку, указанную в окошке.  
Если файл Maven:  
помещать внутри <dependencies> вначале и в конце кода 

* При работе с Gradle: 
чтобы импортировать библиотеку: в проекте Gradle в файл build.gradle в *Dependensies* добавить ссылку на нужную библиотеку  
Пример: (lambok gradle dependency)   compileOnly 'org.projectlombok:lombok:1.18.26'  
Когда ламбок подключен - сверху класса аннтоацию @Data or @Getter @Setter, для замены геттеров и сеттеров 

JSON зависимость:
    <dependencies>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.10.1</version>
        </dependency>
    </dependencies>

`Сюрреализация объект в json-формат и запись в файл.json`: 
Для записи объекта (класса с полями) в JSON-формате в файл на Java, вы можете использовать библиотеку Gson.
Вот пример кода для записи объекта в файл:
```
import com.google.gson.Gson;

MyObject obj = new MyObject(1, "John");
Gson gson = new Gson();
String json = gson.toJson(obj);
try (FileWriter writer = new FileWriter("object.json")) {
writer.write(json);
} catch (IOException e) {
e.printStackTrace();
}


`Дессюриелазиация json в объект:`  
public Note jsonToObj(String json) {
        return gson.fromJson(json, Note.class);
    }


```
В этом примере мы создаем класс `MyObject`, который имеет два поля: `id` и `name`. Затем мы создаем экземпляр этого объекта, используя конструктор с параметрами. Затем мы используем библиотеку Gson для преобразования объекта в строку в формате JSON.
Затем мы открываем файл "object.json" для записи строки в формате JSON в него. Если возникает исключение `IOException`, оно будет перехвачено и напечатано.


**Hotkeys/хоткеи**:  
* **Форматирование автоматическое:**  
ctrl + alt + l  
* **Переименовать переменную сразу везде**:  
Shift + F6  

**Map – это множество коллекций, работающих с данными** - на базе хэш-таблицы, реализация ассоциативных массивов (когда индекс не обязательно целочисленное число) по принципу <Ключ / Значение>.  // ключ - уникальное значение (как словари в питоне), сами "Значения" могут повторяться  


> хранятся парами в объектах Map.Entry - key+value

Ключевые особенности:  
● ускоренная обработка данных;  
* значения могут быть null - пустые ссылки. это так называемые "не валидные" данные  
● порядок добавления не запоминается.  

В HashMap элементы располагаются как угодно и могут менять свое положение.  
* ! очень не экономна по памяти, т.к. испльзует в своей основе хэш-таблицы(ничто иное, как массивы).  
Сильно раздвувает хэш-таблицу с целью резерва ячеек памяти для мудующих элементов  (+80% при приближении к заполняемости)  
* Но работает очень быстро (O(1))  

> Хранение данных в HashMap происходит как в классических списках/как в сбаланиросанных деревьях  

* Чтобы создать Map:  
Map<Integer, String> db = new HashMap<>(); //где Integer - тип ключа, String - тип значения  
Map<Integer, String> db = new HashMap<>(9);  // изначальный размер из 9 элементов  
Map<Integer,String> map3 = new HashMap<>(9, 1.0f); // конструктор, где 9 - количество элементов, 1.0 - это
количество процентов, при заполнении которых будет происходить условное удовение пространства ХэшМэпа  

* Чтобы что-то добавить в Map:  
db.put(1, "один");  
db.putIfAbsent(1, "один"); //проверяет свободен ли такой ключ и записывает 

* Чтобы создать HashMap измассива:  
var a = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7));  

* Чтобы сделать копию/скопировать HashMap:  
var u = new HashSet<Integer>(a)  

* Чтобы перебрать/пройтись/итерировать мапу(Map): нужно обратиться к энтрисет(entrySet):  //ХУЙНЯ
Iterator mapIterator = map.entrySet().iterator();  

* Пробежаться по мапе/map:
1 способ
for(var entry: map.entrySet()){
    System.out.println(entry.getKey() + " " + entry.getValue());
}

2 способ 
for(String s: map.keySet()){ //если ключи типа String
    System.out.println(s + " " + map.get(s));
}

3 способ
map.forEach((k,v) -> System.out.println(k + " " + v));

**Функции HashMap:** 
put(K,V) – добавить пару если или изменить значение,если ключ имеется.  
putIfAbsent(K,V) – произвести добавление если ключ не найден.  
get(K) - получение значения по указанному ключу.  
remove(K) – удаляет пару по указанному ключу.  
containsValue(V) – проверка наличия значения.  
containsKey(V) – проверка наличия ключа.  
keySet() – возвращает множество ключей.  
values() – возвращает набор значений.  

* Чтобы заполнить / добавить элементы в мапу Map:  
  * map = Map.of('{', '}', '<', '>', '[', ']','(', ')');  
  * map = Map.ofEntries(Map.entry('{', '}'), Map.entry('(', ')'));  
 
TreeMap - коллекция типа map (словарь), в которой происходит сортировка по значению ключа. При этом, 
необходимо как-то описывать логику сортировки, т.к. ключ не обязательно int... TODO Хранит данные структурой (в виде дерева). По памяти использует ровно столько ячеек, сколько имеет элементов.  Работает (O(log N)) 
* null быть не может   

* **Чтобы перевернуть TreeMap**:  
static Map<Integer, String> romanArabian = new TreeMap<>(Collections.reverseOrder());

* Высотой дерева - называетя количество его уровней  

* Чтобы создать TreeMap:  
TreeMap<Integer,String> tMap = new TreeMap<>();

**Функции TreeMap:**  
put(K,V) – добавить пару если или изменить значение,если ключ имеется.  
get(K) - получение значения по указанному ключу.  
remove(K) – удаляет пару по указанному ключу.  
descendingKeySet(V) возвращает множество ключей.  
descendingMap() показать в обратном порядке. 
tailMap() показать больше, чем. 
headMap() показать меньше, чем. 
lastEntry()  
firstEntry()  


> Hash-функции, это те же функции, под копотом которых имеются какие-то условия. 

* Чтобы пробежаться по значениями HashMap:  
for (var item : db.entrySet()) {  
 System.out.printf("[%d: %s]\n", item.getKey(), item.getValue());  
 }  

* Второй вариант пробежаться по значениям HashMap:
for (Map.Entry<String, String> entrySet: names.entrySet()) {
                writer.write(entrySet.getKey() + "=" + entrySet.getValue() + "\n");
            }

* for(Map.Entry<Integer, String> entry: db.entrySet()){  
 System.out.printf("[%d: %s]\n", item.getKey(), item.getValue());  
 }  

* LinkedHashMap - коллекция типа Map, которая помнит порядок добавления элементов. НО при этом работает  
значительно медленнее своих собратьев.  

* Чтобы создать **LinkedHashMap**:  
Map<Integer,String> linkmap = new LinkedHashMap<>();  

* HashTable - коллекция типа Map. Является **устаревшей!**. Не знает про null. 

* * Чтобы создать HashTable:  
Map<Integer,String> table = new Hashtable<>();  

**Коллекции Set** - практически все тоже самое, но без значению поключу, только ключи:  //используется для быстрого поиска O(n). + множество уникальных элементов
* HashSet  
* LinkedSet  
* SordedSet  
* NavigatableSet  
* TreeSet  

**TreeSet** - в основе лежит красно-черное дерево. Перебалансирует дерево для того, чтобы бинарный поиск по нему был O(n). Помещаемые объекты в обязательном порядке реализуют интерфейс Comparable для балансировки дерева (а она реализуется через сравнение элементов)

**Особенности Set**:  
● Коллекции, содержащие уникальные элементы.
● Быстрая работа с данными.
● «Основан» на Map’ах без пары.
● Порядок добавления не хранится.  
* Может содержать значение null.  

**Функции для работы с HashSet**:  
isEmpty() – проверка на пустоту.  
add(V) – добавление элемента в коллекцию.  
remove(V) – удаление элемента из коллекцию.  
contains(V) – проверка на включение элемента в коллекции.  
clear() – удаление всех элементов коллекции.  
size() – возвращает количество элементов коллекции.  
addAll(Coll) – объединение множеств.  // пример:  u.addAll(b)  
retainAll(Coll) – пересечение множеств.  
removeAll(Coll) – разность множеств.  
first()  
last()  
headSet(E)  
tailSet(E)  
subSet(E1, E2)   

* Функции для работы с LinkedHashSet:    
isEmpty() – проверка на пустоту.  
add(V) – добавление элемента в коллекцию.  
remove(V) – удаление элемента из коллекцию.  
contains(V) – проверка на включение элемента в коллекции.  
clear() – удаление всех элементов коллекции.  
size() – возвращает количество элементов коллекции.  


* Чтобы создать Set / HashSet:  
Set<Integer> set = new HashSet<>();  
* Создать Set из массива:  
Arrays.stream(array).collect(Collectors.toSet());  

* Чтобы создать TreeSet:  
var a = new TreeSet<>(Arrays.asList(1,7,2,3,6,4,5));  

# ООП  

> **Введение в создание собственных типов:**   
Java является объектно-ориентированным языком.  
Программа, написанная на Java, должна соответствовать  
парадигме объектно-ориентированного программирования.  
Следует понимать, что принципы ООП не просто определяют  
структуру программы. Это некий фундаментальный подход,  
с которым нам предстоит разобраться.  
**Спагетти-код** – код, в котором данные связаны с методами  
для их обработки и в итоге может получиться так, что отдельные  
ветви алгоритма переплетаются, образуя запутанный клубок,  
в котором невозможно разобраться  

Решение проблемы получило название **объектно-ориентированноепрограммирование**   
или объектно-ориентированное проектирование или ООП.  
При использовании данного подхода, упорядочивание кода базируется  
на объединении данных, с одной стороны, и методов для обработки этих  
данных, с другой стороны, в одно целое. Это «одно целое» в ООП называется  
**экземпляром класса.**  
Вся программа при этом имеет блочную структуру, что существенно упрощает  
анализ кода и внесение в него изменения.  
ООП – искусственный прием, в большинстве случаев не зависящий, от языка  
программирования.  

**промежуточные итоги:**  
* Если говорят, что разработка идет с использованием ООП – это говорит о том, что используются классы и экземпляры этих классов.  
* Каждый **экземпляр класса** определяется общим шаблоном, который называется **классом**.  
* В рамках класса задается общая структура, на основе которой затем создаются экземпляры.  
* Данные, относящиеся к классу, называются полями класса (ничто иное, как переменные, объявленные в теле класса), а код для их обработки — методами класса.  

**Примеры:**  
Автомобиль – Lada 2107 UIN 123123123, S/N 789789789  
Здание – Дом по адресу г.Москва ул. Ленина 21к1  
Ученик – Сергей Камянецкий, 51 МиИ, СмолГУ  
Мобильный телефон – Siemens CX60 IMEI 1234520032022  
Геометрическая фигура – додекаэдр  
Работник – Смиронова Т.В. 14.02.1994, ID 728, Компания GeekBrains  
Котики – Барсик 

* **Чтобы вывести форматированную строку:**  
return String.format("id: %d, sl: %s, fn: %n, ln: %l" id, salary, firstName, lastName);  

* **Чтобы поменять базовое поведение стандартных функций** - необходимо переписать их в теле класса. Тогда для его экземплятров поведение данной функции будет таким, как определили его мы.  

* **Переопределить hashCode** (HashCode - ссылка на объект в памяти) по 1 параметру: //переопределять для того, чтобы, например, в HashSet or HashMap не все объекты были уникальные. Тогда некоторые из них не смогут попасть в такие итерируемые объекты. Например, если сравнивать их по полю "id" и т.д.   
public int hashCode() {  
        return id;  
    }  

* **Переопределить equals:**  //переопределять для того, чтобы при вызове .equals объекты сравнивались по тем критерями, по которым надо нам... Имени, возрасту и т.д.  p.s. В базовом случае сравниваются ссылки на объекты. А так как при создании объекта мы пишем NEW - то ссылки будут всегда разные и объекты, соответственно, уникальными  
public boolean equals(Object obj) {  
        Worker t = (Worker) obj;  
        return id == t.id && firstName == t.firstName;  
    }  

public boolean equals(Object obj) {   
        if (obj instanceof Cat)  
            return ((Cat)object).name.equals(this.name)  
        else return false;  
    }  

* **Переопределить toString**:  
public String toString() {  
        return String.format("id: %d, sl: %s, fn: %n, ln: %l" id, salary, firstName, lastName);  
    }  

* **Вывести тип объекта (дочернего)**  
    public String getType(){  
        return super.getClass().getSimpleName();  
    }  



Модификаторы видимости(доступа) полей:  
* **public** - только в классе  
* **private** - во всей программе   
* **protecred** - в классе и у его наследников  
* **static** - переменная(поле) станет принадлежать только классу, но не его экземплярам. Это значит, что доступ к этой переменной будет по всей программе, даже не являющимся экземплярами классов. (**стараться избегать**, потому что под нее в любом случае выделяются ячейки оперативной памяти + считается нарушением инкапсуляции)

**Конструктор** - это метод без названия с таким же именем, как класс. Все, за что отвечает конструктор - построение экземпляра класса.  
* В теле класса может быть 2 и более конструкторов: пустой и заполненный(функциональный)  
  * пустой конструктор нужен для того, чтобы не прописывать каждый раз параметры, если эти параметры у экземпляров класса, по сути, одни и те же. Ниже пример.  
* Чтобы **создать конструктор автоматически**: alt + insert  
* super. - для вызова метода родительского класса  
* **экземпляр класса (объект) хранится в куче, ссылка на экзепляр класса (объект) хранится в стэке**.  
* минимальный конструктор: public Animal(){}; 

this - this.name - подсказывает принадлежность к вызвавшему объекту (не классу). 
* в конструкторе можно использовать для перегрузки:
  * Cat (String name, int age, string description){
    this(name, age); //ссылка на внутренний конструктор с двумя аргументами
    this.description = description;
  }
* также можно использовать для методов - this.method() - в случае, когда в родительском классе есть такой же метод
  * super.method() для вызова метода родительского класса

`Конструктор копирования` //создаст новый объект (в другом участке кучи)
Cat (Cat cat){
    this(cat.name, cat.age)
}

Пример **использования мин. конструктора** (когда параметры у всех экз. класса одинаковые). При этом произойдет неявная передача параметров в полнотелый конструктор из тела пустого конструктора:  
public Animal(){  
  this.legsCount = 4;     
  this.color = black;    
  this.type = Dog;  
};  

Можно создать **усеченный конструктор** (например здесь необх. передать цвет, ост. автоматически передастся в главный конструктор):  
public Animal(String color){  
  this.legsCount = 4;     
  this.color = color;    
  this.type = "Dog";  
}; 
public Animal(String color){  
  this(4, color, "Dog")  
}; 

* **Класс** - это шаблон для экземпляров класса.  

**Пример конструктора**:  
public class Animal {  
    int legsCount;  
    String color;  
    String type;  
public Animal(int legsCount, String color, String type) {  
        this.legsCount = legsCount;  // this - буквально - возьми входящее значение legsCount и присвой это значение этому параметру (объявленному в поле класса)  
        this.color = color;  
        this.type = type;  
    }  
}  



**ТОЛЬКО для private полей**!!:  
сеттр - отличается тем, что в нем можно реализовать логику проверки валидности принимаемого значения, тогда как при простом приваивании значения переменной значение может оказаться не валидным
геттр и сеттр: (вызываются через alt + insert):  
гетр для получения инфы, сетр для переопределения значения  
Пример:  
Animal cat = new Animal("cat");  
int legsCount = dog.getLegsCount; // в этом случае получаем значение конструктора по умолчанию (4);  
cat.setLegsCount(3); // изменяем стандартное значение на 3.  

**Пример создания экземпляра с помощью конструктора**:  
Animal dog = new Animal(4, "black", "Dog");  

* **Привести объект к экземпляру класса**:  
((Cat) object)  

* **Проверить принадлженость объекта к классу**:  
if (object instanceof Cat) // Cat - класс  

Как пишутся классы:  
1. поля  
2. коснтрукторы
3. методы

* Чтобы создать/сделать класс наследником:  
public class Cat extends Animal{ //где Cat - дочерний, Animal - родительский   
} 

* Унаследовать конструктор от родителя:  
public Cat(String name, int legs, int eyes, String color){  
        super(name,legs,eyes, color);/ключевое слово super - для обращения к конструктору род. класса  
    }  

* //**статический инициализатор** - для инициализации статических полей  
    static{  
        count = 0;  
    }  
  * **static** - такой модификатор доступа, который позволяет напрямую обращаться к полю или методу. Без него пришлось бы создавать экземпляр класса и обращаться к его типу. Program.method(). При этом нет никакого текущего и нет никакого базового экземпляра. (this/super)  

* **abstract** - модификатор доступа к классу, который следует использовать, когда мы хотим **ограничить(запретить) создание экземпляров этого класса**. Это значит, что это класс необходим для дальнейшего наследования и только. При этом в нем, конечно, могут быть поля, конструкторы и методы. 
  * если использовать применительно к методам, то эти методы **должны** будут переопределены в наследуемом классе. Кстати неплохой способ, чтобы не забыть переопределить методы. Пример: BasicHero может умирать, соответственно все его наследники тоже. В базовом классе метод можно не описывать, но в дочерник описать мы обязаны.  

* для индексации или подсчета количества экземпляров класса:  
public Foo(){  
        count++;  
    }  

* **Скастовать, кастануть** - привести объект к другому типу объекта. 
((Druid)(druid))   
values = (T []) new Object[values.length + 1];  

* @Override - значит, что в классе переопределяется метод суперкласса. Что делает? Проверяет, что мы действительно переопределяем метод, а не перегружаем //переопределять статические методы нельзя
  * для вызова методов для переопределения - ctrl + o  

**Интерфейс**  - interface предназначен для описания исключительно общего поведения сущностей. (способа взаимодействия с объектами)
Частный случай: для гарантии, реализации некоторого поведения.  
Интерфейс – это частный случай класса.  **Интерфейсы поддерживают множественное наследование**
Он представляет собой полностью абстрактный класс с абстрактными членами.
У интерфейса по умолчанию все public! Менять не надо. 
* Может может содержать только неизменяемые переменные.   

*и так - если мы **имплементируем** интерфейс, то обязаны! переопределить методы, которые в нем находятся (как в абстрактном классе)  
public class Foo extends Hero implements Healer, Warrior{}  

* чтобы реализовать метод в интерфейсе, необходимо добавить модификатор default:
default boolean add(T t){
    ...логика
}

* чтобы достучаться до полей из интерфейса, можно добавить контракты на поля - геттеры

* Интерфейс МОЖЕТ иметь поля! при этом, они по умолчанию public static final ...

*Абстрактный класс*  
Должен содержать 1 или более абстрактных методов.  

*Абстрактный метод* - метод без тела, и даже без фигурных скобок 

*Контракт* - условия, при которых чтобы пользоваться интерфейсом, необоходимо в обязательном порядке реализовать все его методы.  

* **Проверить объект на соответствие типа**:  
if (zooAnimals.get(i) instanceof Pets)  

* Чтобы **перезагрузить Идею**, когда видит ошибки в коде там, где их нет:  
File -> Invalidate Caches -> invalidate and restart  

> Вызывать методы в рамках текущего класса - через this, если нужно вызвать метод родительского класса - super:  
this.name (если метод описан в дочернем классе), super.name (если метод описан в родительском классе)   

Итератор<E>  
Итератор над «коллекцией». Iterator занимает место Enumeration’в Java Collections Framework.   
hasNext()  
next()  
Пример:   
Iterator<Integer> iterator = newList.iterator();  
        while (iterator.hasNext()){  
            System.out.println(iterator.next());  
        }   

**Важно!**  
Для итерации объекта: необходимо в классе имплементировать стантартный класс типа Iterator<String> or smth else  
public class Worker implements Iterator<String>...  
и **переопределить методы**:  
@Override  
    public boolean hasNext() {  
        return index++ < 4;  
    }  

    @Override  
    public String next() {  
        switch (index){  
            case 1:  
                return String.format("First name: %s", firstName);  
            case 2:  
                return String.format("Second name: %s", secondName);  
            case 3:  
                return String.format("Age: %s", age);  
            default:  
                return String.format("Salary: %s", salary);  
        }  
    }  
    

В **мейне напрямую объявляем**:  
Iterator<String> components = worker1; 

Чтобы **сравнить два объекта** (условно - **сравнить двух воркеров**):  
имплементируем Comparable<Worker>;  
реализуем метод toCompare: 
@Override  
    public int compareTo(Worker o) { 
      // 1 способ   
        if (o.age < this.age) return 0;  
        else if (o.age > this.age) return -1;  
        else return 1;  
      // 2 способ  
      return Integer.compare(o.age, this.age);  
    }  

Comparable<E> 
Списки (и массивы) с собственными типами позволяют автоматически сортироваться  (имплементировать в том
классе, который должен сравниваться. Например, Cat with Cat. В таком случа: Comparable<Cat>) при помощи   
Collections.sort  
Arrays.sort 
!!! Если мы наполняем не стандартный лист, а свой тип, внутри которого лист - то сорт просто так не сработает. Нужно внутри этого класс прописать еще один сорт, внутри которого будем сортировать внутренний список

Comparator<E>  
Предназначен для упорядочивания собственных типов. Но где? например, в .sort(здесь передаем new Comparator()) 
* ! В качестве компаратора можно передавать сортировку от Collections.sort()/.reverseOrder()  
А для этого необходимо создать новый класс, к примеру, AgeComparator, который иплементирует Comparator<Cat(кого будем сравнивать, какого типа объекты)>
* лайфхак для того, чтообы не создавать класс компаратор и не описывать отдельно логику для сравнения(целесообразно, если сравнить нужно только один раз. Если много, то лучше все-таки создать компаратор либо сделать класс Comparable) 
  * cats.sort((c1, c2) -> Integer.compare(c1.getAge(), c2.getAge()));   
  * cats.sort(Comparator.comparingInt(Cat::getAge)); //тоже самое   

> Очевидно, но я запишу: Итерируемым должен быть объект, компоненты которого мы будем перебирать. Так что
это объект должен имплементировать Iterator or be Iterable  

Iterable<E>  
Крч, имплементируя этот класс можно пользоваться foreach для перебора элементов класса. Применяется, когда
в основа класса лежит список, либо массив.  
Чтобы использовать - необходимо создать new Iterator<тип объекта внутри списка>, и описать все те же hasNext() и next(). Проще простого, когда в основе список. hasNext() ограждаем размером    списка (нужно поле с index), next() через .get(index).  

Iterator<E> (если это для вывода, то E = String), а в общем случае можно указать E=Object  
Итератор над «коллекцией». - чтобы пройтись по объекту (его полям). Для этого необходимо:  
1. добавить поле со счетчиком и ограничить в hasNext() перебор до количества полей класа (index++ <4 (если поля 4))  
2. в next() разбить на случаи (case 1...)/if index = 1,2...  **После каждого кейса !!! или return or break!!**    
Имплементируемые методы для Iterator:
hasNext()  
next()  
Вообще-то после имплементации можно даже не создавать экземпляр итератора, а просто перебирать по экземпляру класса Cat cat1 (in the Main)  
while(cat1.hasNext()){  
  sout(cat1.next())  
}  
* Если нужно перебрать по списку (листу) в классе, то не забываем добавить поле index. Его будем увеличивать ++
в next, и сравнивать с размером списка в hasNext()  

Iterator<Component> - обязательная реализации итератора (для Iterable объекта) Вариант исполнения - анонимный класс. Создавать в том классе, который хочу итерировать.  
Пример:  
int index = 0;  
@Override  
    public Iterator<Component> iterator() {  

        return new Iterator<Component>() {  
            @Override  
            public boolean hasNext() {  
                return index < components.size();  
            }  
            @Override  
            public Component next() {  
                return components.get(index++);  
            }  
        };  
    }  

Пример анонимного класса:
//MinitoringSystem - интерфейс. securityModule - анонимный класс (типо экземпляр интерфейса хз)
       MonitoringSystem securityModule = new MonitoringSystem() {
           @Override
           public void startMonitoring() {
               System.out.println("Мониторинг безопасности стартовал!");
           }
       };

* Как создать список с разными типами объектов:  
**создать интерфейс "Маркер"**, имплементировать его всем объектам, которые хотим добавить в список. И создать список с типом "Маркер"  



**Ассоциация. Аггрегация и композиция.** 
Ассоциация - когда два объекта как-то связаны между собой. (когда один класс содержит поле с типом другого класса)  
Аггрегация - частный случай ассоциации, когда в нем есть конструктор, принимающий поле другого класса.  
Композиция - частный случай ассоциации, когда в конструкторе используется создание типа другого класса. Оба экземпляра класса не могут существовать по отдельности (это жесткая сцепка)  

* Сериализация — это процесс сохранения состояния объекта в последовательность байт.  
Десериализация — это процесс восстановления объекта из этих байт.  
Serializable (**сериалайзбл**) - означает, что класс может быть переведен в бинарный код, и десиарайзбл - обратно. (ХЗ как использовать)  
implements **Serializable - интерфейс-маркер.** Он просто помечает машине, что эти данные разрешено переводить в бинарные код.  

**Пользователь** - в 80% случаев в комьюнити, подразумевается другой программист.  

* **Анонимный класс** - экземпляр класса без имени, реализующий какой-то интерфейс.
//использовать, когда класс нужен для единичного использования. (совет от Oracle) 


Создать "**Сырую строку**" 
List list = new LinkedList<>(); // без указания типа в дженерике. пример сырого списка. такой список может содержать как числа, так и 
// строки и т.п. 


---
**`Обобщение`**(Generic/дженерик) <E>/<T>, где E - entity, T - type (просто сокращения, по сути ничего не значат) (Параметрический физм) - продумывание иерархии без привязки к каким-то конкретным типам 

> Соглашение об именовании параметров типа:  
Е - элемент (Element, Entity, коллекции Java)  
K - ключ  
N - число  
T - тип  
V - значение  
S, U - 2-й, 3-й, 4-й типы   

`Дженерики` - это механизм языка, позволяющий создавать обобщеннные типа на языке Java. Это функция времени компляции. Что это значит? - то есть параметр типа стирается, а все дженерики реализуются как тип Object   
// класс с обобщенным типом. при объявлении экземпляра класса произойдет конкретизация типа параметра id, за исключением **случая <?>** - (wildcard/вайлдкард) когда мы объявляем коллекцию, но все еще не знаем какой тип в ней будет лежать. (имеет смысл при создании абстрактного класса.  его экземпляры могут возвращать разные мапы с разными типами.) В абстрактном классе Map <?, ?>, а в экземплярах уже конкретизируем...   

Дженерики работают только с ссылочными типами данных (для примитиво необходимо использовать обертки)
Дженерики появились в Java 5.

НЕВОЗМОЖНО: 
1. создавать внутри класса в дженериком <T> класс типа T:
T data = new T(); - так нельзя
2. создавать массив обобщенных типов (объявить можно, а значит можно подать его в метод извне в качестве входного аргумента). При создании своей коллекции создавать на базе массива Object
3. создавать статические поля типа T
4. создать исключения обобщенного типа (обобщенный класс не может расширять класс Throwable)
5. использовать приведение типа или проверку instanceOf для параметризованных типов

Пример `особеннотси` работы наследования:
Дан Box<Number>. Нельзя сделать Box<Integer>, а вот в <N super Number> будет возможно, или <? extends Number>

Пример: 
public class Multiparameterized <E1, E2, E3>{  
    public E1 value1;  
    public E2 value2;  
    public E3 value3;  

    public Multiparameterized(E1 value1, E2 value2, E3 value3) {  
        this.value1 = value1;  
        this.value2 = value2;  
        this.value3 = value3;  
    }  
}  

`Ограничение параметра типа`: 
<T extends Animal>, <N super Number>
НОВОЕ: <T extends Animal & Closeable & Runnable> //1 - класс, остальные - интерфейсы. Если указать 2 класса - будет ошибка

* **Чтобы решить проблему того, что в обобщенный класс можно положить теперь абсолютно любой тип**:  
делаем наследование обобщенного метода от какого-то верхнего класс всех будующих типов  

public class Repository <N extends Numbers> {  //при этом N может быть int, double, float, long etc...   

Далее- синтаксический сахар. N мы уже ограничили, а значит можно делать так:
public void someMethod(Number number){

}

public void repoTest(Repository r){} //уже без дженерика!
}  

//**метод для возвращения неизвестного на текущий момент типа**
//дженерик ПЕРЕД возвращаемым типом указывается
public class Methods {
    public static \<U\> U getElement(List\<U\> col, int index){
        return col.get(index);
    }
}

//**метод для возвращения неизвестного на текущий момент типа, ограниченного имплементацией интерфейса, например, Comparable\\<T\\> (тут без T в компарабл - будет сырой тип, так что нужно обязательно прописать)**  
public <T extends Comparable<T>> T getMax(T param1, T param2){  
        if (param1.compareTo(param2) >= 0 ) return param1;  
        else return param2;  
    }  

* `Ограничить wild`<?> сверху:  //никогда не испльзуется в качестве аргумента типа для вызова обобщенного метода, создания экземпляра класса или супертипа
//Но испольуется как параметр типа поля, локальная переменная, возвращаемый тип
\\<? super T/Cat\\>, тогда сюда войдет Cat и все родители

Пример использования wile:
public static <T> void copyTo(
    ArrayList<? extends T> src, ArrayList<? super T> dst){
        for (T o: src){
            dst.add(o);
        }
    }
    Пояснение: первый список принимает наследника T, а второй - родителя T. Так, складываем в список котов список животных. Типа можно животных сложить в котов, но не наоборот. T - в данном случае будет Animal

* Вообще, если класть в метод с <T t, T t2> параметрами, и они будут разных типов, благодаря автоматическому выведению типов - будет найден их общий родитель. Либо какой-то общий интерфейс, либо сам Object.

`Руводство по использованию подстановочного символа:`  
* Входная переменная определяется ограничением сверху (extends)
* Выходная переменная определяется с ограничением снизу (super)
* Если ко входной переменной можно обращаться только как к Object - то неограниченный подстановочный символ <?>
* Если переменная должна использоваться как входная и как выходная одновременно, НЕ исползьовать подстановочный символ <?>
* Не использовать подстановочные символы в возвращаемых типах

`Стирание типа` (Type Erasure) - процесс, просходящий во время компилляции с дженериками
* Заменяет все параметры типа их границами или Object
* Вставляет применение типа
* Генерирует связующие методы
* обеспечивает отсутствие новых типов для параметризованных типов

---

`Enum` - перечисление. Упоминание значений, объединенных по какому-либо логическому принципу. (классы, которые содержат в себе собственные статические экземпляры). Значение - объекты. Пишутся через запятую БОЛЬШИМИ БУКВАМИ, потому что это - константы, неизменяемые объекты.
Класс **Enum** - для создания некоторого ограниченного круга значений.. Например, цвет:  
public enum Color {  
    BLACK, WHITE;  
}  
Cat cat = new Cat(9, **Color.black**, "Barsik"); // чтобы вызвать такое значение  

* Проверить, содержит ли Enum(енам) значение строки:
    public boolean commandValidate(String command) {
        return Arrays.stream(Command.class.getEnumConstants()).anyMatch(e -> e.name().equals(command));
    }

* Количество элементов в енаме (Enum):
someEnum.values().length  

* Enum.values() - возвращает массив всех значений енама

* узнать порядковый номер значения в Enum - enumsomevalue.ordinal()

* Чтобы присвоить значению значение нового массива:  
this.values = (T []) new Object[0];  

**Пример advanced Enum**: 

public enum Color {
    RED("#FF0000"), GREEN("#00FF00"), BLUE("#0000FF");

    private String colorCode;

    Color(String colorCode) { //Конструктор в енаме всегда private, т.к. экземпляры енама уже хранятся в классе и не могут быть созданны иным способом
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }
}


---

* **Тесты**:  
создаются - по названию класса, который тестируем + Test. GBArrayListTest. Тоже самое с методами.  
Каждый тест должен быть изолирован. То есть внутри метода с тестом создаем экземпляр класса, и для этого экземпляра тестируем отдельный метод.  
**Чтобы добавить библиотеку для тестов** : @Test - и выбрать первое решение  
Пример:  
@Test  
    void getTest(){  
        GBArrayList<String> testList = new GBArrayList<>();  
        testList.add("1");  
        Assertions.assertEquals("1", testList.get(0));  
    } 

@Test  
    void getNegativeTest(){  
        GBArrayList<String> testList = new GBArrayList<>();  
        testList.add("1");  
        assertNotEquals("2", testList.get(0));  
    }  

@Transient - значит, что это переменная не будет участвовать в серриализации (в байт код и обратно) при компилляции. (значение этой переменной будет равно нулю). Также поля, к которым мы хотим закрыть доступ даже через байтовую систему, стоит указывать  Transient  

* Optional <Object or smth else> вернет объект, если не получится - эксепшн.  

* .orElseThrow() - если функция должна что-то вернуть, мы учитываем случай, если не получится вернуть - что делать  
В контексте:  
User user = repository.findById(id).orElseThrow(()->new RuntimeException("User not found"));  здесь findById - должно вернуть Юзера  

* Цикл **while(true)** - чтобы выйти из него - return, а в конце каждого условия - break
чтобы не выходить - continue

* Когда мы не сразу инициализируем переменную, а делаем это в конструкторе - инъекция (injection)  
private final Square square;

    public Painter(Square square) {
        this.square = square;
    }


**Logger (logger):**   

package logger; // в пакете

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {
    static {
        try(FileInputStream in = new FileInputStream("log.config")){ //сокр путь до файла с конфигами (из семинара 6 по ООП с Александром Леонидовым)  
            LogManager.getLogManager().readConfiguration(in);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Logger log(String className) {
        return Logger.getLogger(className);
    }
}

(Чтобы инициализировать Логгер в классе мейн: )  
private static final Logger log = Log.log(Main.class.getName());
в psvm main:  
log.log(Level.INFO, "Стартовал метод main в пакете model");

* switch case без break:
            switch (currentChar) {
                case '(' -> roundBracket--;
                case ')' -> roundBracket++;
                case '{' -> curlyBracket--;
                case '}' -> curlyBracket++;
                case '[' -> squareBracket--;
                case ']' -> squareBracket++;
            }

* **Чтобы заменить свичКейс (switch case) мапой**:  
    private static final Map<Operation, BinaryOperator<Integer>> operationMap = new HashMap<>();  

    public static void main(String[] args) {  
        fillMap();  
        System.out.println(calculateWithMap(Operation.SUM, 3,5));  
    }  


    private static void fillMap(){  
        operationMap.put(Operation.SUM, Integer::sum);   
        operationMap.put(Operation.DIFF, (a,b)->a-b);   
    }  

    private static int calculateWithMap(Operation operation, int a, int b){  
        return operationMap.get(operation).apply(a,b);  
    }  

* В Java есть два потока вывода: System.out and System.err(текст выделен красным цветом)  

`Чар/Char в int`
Character.getNumericValue(char ch)

`Строка в список`  (Разбить строку на слова)  
List<String> myList = new ArrayList<String>(Arrays.asList(s.split(",")));  

`Как подключить удаленную библиотеку в проекту Java?`  (зависимость, депенденси)
Project Structure -> Platform Settings -> Global Libraries -> название библиотеки типа com.google.code.gson 

`Date/Дата и время`  //в классе Date смещение на год на +1900, месяц на +1
Date now = new Date();
System.out.println(new SimpleDateFormat("dd.MM.yyyy.HH.mm").format(now));

`import java.sql.Date` - для нормального отображения даты вместо стандартной Date

**Мутабельный класс - AtomicInteger**
AtomicInteger counter.increment... //крч для счетчика, целесообразно передавать в рекурсию для подсчета шагов

`Стек` в java (LIFO принцип) - lust in - first out. //на основе LinkedList
  * Пример: стопка бумаги. Последний лист будет первым сверху
`Очередь` в java (FIFO принцип) - first in - first out //на основе LinkedList
  * Пример: очередь в магазине. Чем раньше встал в очередь, тем раньше обслужат

Заставить пользователя вводить данные до тех пор, пока не введет верные
```java
   public float getFloat() {
        String inputString = prompt("Enter a number: ");
        boolean isFloat = isFloat(inputString);
        while (!isFloat) {
            System.out.println("The argument entered is not a number");
            inputString = prompt("Enter a number: ");
            isFloat = isFloat(inputString);
        }
        return Float.parseFloat(inputString);
    }

    public boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String prompt(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.next();
    }

```
РЕГУЛЯРКИ СРАВНИВАТЬ ЧЕРЕЗ .matches!

* `REGEX для обработки строки в int`
private static final String NUMBER_REGEX =  "(\\d*\\.)?\\d+"; //для NumberFormatException, можно через .matches //мы как бы сравниваем с маской

* `REGEX для обработки строки в double`
private static String DOUBLE_REGEX = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";

* `REGEX для проверки на кириллицу (русский алфавит)`
private static final String CYRILLIC_REGEX = "[а-яёА-ЯЁ]+";

* `REGEX для проверки на английский алфавит`
String ENG_REGEX = "[a-zA-Z]+";

* `REGEX для английского и русского алфавита:` //для проверки символов char
String ALPHABET_ENG_RU = [^a-zA-Zа-яА-Я]

* `REGEX для проверки на Date/Дата`
String DATA_REGEX = "(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[0-2])[.](19[0-9][0-9]|20[0-9][0-9])";

`Модуль числа` = Math.abs()

`Возведение в степень` = Math.pow() //возвращает double
  * либо своя функция, где в цикле или рекурсии число будет умножаться на само себя n раз

**Auto_increment/автомат. инкремент**:
private static int counter = 0;  
и увеличивать его либо в конструкторе, либо в методе add() и др.

* Запуск jar файла из терминала
java -jar -Dfile.encoding=UTF-8 myapp.jar

---

Решение ошибки с отсутствием mainclass в манифесте:
засунуть в зависимости pom.xlm после properties...
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>Main</mainClass> //указать пакеты, в кот. лежит Main, если он вложен
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>

И проделать следующее:

For newer versions of IntelliJ, enable the use plugin registry option within the Maven settings as follows:

Click File 🡒 Settings.
Expand Build, Execution, Deployment 🡒 Build Tools 🡒 Maven.
Check Use plugin registry.
Click OK or Apply.
For IntelliJ 14.0.1, open the preferences---not settings---to find the plugin registry option:

Click File 🡒 Preferences.
Regardless of version, also invalidate the caches:

Click File 🡒 Invalidate Caches / Restart.
Click Invalidate and Restart.
When IntelliJ starts again the problem should be vanquished.

* Maven. После сборки с указанными зависимостями библиотеки отсутствуют...
https://stackoverflow.com/questions/16992255/how-can-i-make-intellij-idea-update-my-dependencies-from-maven
1. Open the project view in IntelliJ
2. Right click the pom.xml file and select Maven > Reimport or Maven > Reload (for newer versions of IntelliJ)
3. If this works for you IntelliJ will add the dependencies to the project
4. Check the if the dependencies you need are added in
5. File - Project Structure - Project Settings - Libraries
6. and File - Project Structure - Modules - Dependencies //ЗДЕСЬ галку напротив нужной библиотке !!!

---

* Чтобы собрать проект более старой версии: изменить номер в этом участке pom.xml (maven)  
    <properties>
        <maven.compiler.source>19</maven.compiler.source>
        <maven.compiler.target>19</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

    </properties>

* Чтобы изменить версию компилляции кода в java-проектах
настройки - compiler - java compiler - project bytecode version

* Чтобы изменить используемую версию java
свойства компьютера - расширенные настройки внизу - доп - параметры среды - путь к папке c/program files/java...19/bin

---

* Чтобы распарсить число, большее инта и лонга, можно использовать BigInteger:
BitInteger bi = new BigInteger();
add - суммировать

`Лямбда-выражение` - функция, которую можно передать в качестве параметра в метод. //работает для одной функции
(параметры) -> {тело метода}
самый простой: () -> System.out.println("Hello world!")
Arrays.stream(array)
    .flatMap(Arrays::stream) // преобразование двумерного массива в одномерный
    .forEach(System.out::println); // вывод элементов

Еще один пример лямбда-выражения через поток (stream) с фильтром
List<String> namesWithoutA = uniqueNames.stream().filter(s->!s.contains("A") && !s.contains("a")).toList();

`API` - способ взаимодействия с классом (его методы + документация к классу) = его интерфейс

`static` - модификатор, определяющий, что поле или метод будет относиться к самому классу, и для их вызова создавать объект не нужно. Такие методы можно вызывать через имя класса, не создавая его. Значения же полей хранятся в классах, а не в объектах.
  * к статическим переменным и методам необходимо обращаться по имени класса!


*UpCasting* - преобразование к родитеслькому классу
  * Object cat = new Cat("Мурзик");

* Преобразование вниз - downCasting (когда кастуем один объект к другому)
  * перед преобразованием данных извне рекомендуется исопользовать instanceOf - вернет буливое значение

* разбиение кода на регионы: //свойства(поля), методы, конструктор, геттеры и сеттеры
//region "name"
//endregion































`package-private` - модификатор доступа для пакета

`Виды внутренних классов:`
1. Не статический внутренний класс
2. Локальный класс (внутри метода внешнего класса)
3. Анонимный класс (пример - реализация Итератора при return new Iterator{...}) - это класс без названия

`Внутренний класс (non-static)` класс внутри другого класса. Испльзуется, когда внутренний класс нужен только для внешнего и никогда более. лучше делать его private в соответствии с принципом инкапсуляции.
**Особенности внутренних классов**  
* внутренний класс не существует без внешнего
* внутренний класс имеет доступ ко всем методам и полям внешнего (т.к. внутренний объект неявно получает ссылку на внешний объект при его создании)
* внешний экземпляр класса не имеет доступа к экземпляру внутреннего класса без явного создания объекта. (во втором примере ниже так и сделано)
* внутренний класс не может иметь static полей, за исключением static final (константы), т.к. статические поля никогда не связаны с экземплярами, тогда как внутренний класс всегда связан с экземпляром внешнего класса
* со внутренними классами работает наследование и полиморфизм

* Чтобы обратиться из объекта внутреннего класса к объекту внешнего класса
OuterClassName.this //имя внешнего класса там

> реализация 1 (далекая от жизни), после нее будет реализация, приближенная к жизни:

```java
public class Orange {
    public void squeezeJuice(){
        System.out.println("Squeeze juice...");
    }
    public class Juice{ //т.к. Сок публичный и не статичный, создать экземпляр такого класса можно лишь через эксземпляр внешнего класса Orange. ниже пример
        public void flow(){
            System.out.println("Juice dripped...");
        }
    }
}
```

> в Main

```java
    public static void main(String[] args) {
        Orange orange = new Orange();
        Orange.Juice juice = orange.new Juice(); //создания объекта типа Juice через созданный объект orange
        orange.squeezeJuice();
        juice.flow();
    }
```

> реализация 2 внутреннего класса, близкая к реальности:

```java
public class Orange {
    Juice juice; //внутренний объект будет создан при создании внешнего
    public Orange(){
        juice = new Juice();
    }
    public void squeezeJuice(){
        System.out.println("Squeeze juice...");
        juice.flow();
    }
    
    private class Juice{
        public void flow(){
            System.out.println("Juice dripped...");
        }
    }
}
```

//в main просто создаем объект апельсина и вызываем его единственный публичный метод - выдавить сок

`Локальный класс - класс внутри метода внешнего класса`

Особенности:
* описывается и объявляется только в теле одного конкретного метода
* метод имеет доступ к приватным историям внутреннего класса
* имеет доступ к внешнему классу и локальным полям метода (до java 8.0 - только к константам метода)
* иммет ссылку на внешний класс (как и в базовом случае внутреннего класса)

Пример локального класса внутри метода:
```java
public class Animal {
    public void performBehavior(boolean state){
        class Brain{ //внутренний класс в методе
            private void sleep(){
                if (state){
                    System.out.println("Sleeping");
                }
                else{
                    System.out.println("Not sleeping");
                }
            }
        }
        Brain brain = new Brain();
        brain.sleep();
    }
}
```

`Статический внутренний класс`. Когда нужен? когда, в теории, мы имеем признаки существования внешнего объекта без его создания. Так, мы можем слышать звучания кота из окна, однако не видеть его.
**Особенности**:
* объект статического внутреннго класса не хранит ссылку на конкретный экземпляр внешнего класса
* при создании экземпляра такого класса нужно указывать название внешнего класса (Cat.Voice voice = new Cat.Voice())
* статический вложенный класс может обращаться только к статическим полям внешнего класса (логично, т.к. экземпляр объекта может быть не создан)
* статический класс, в отличии от статической переменной, может существовать в нескольких экземплярах

Пример:
```java
public class Cat {
    String name, color;
    int age;

    public Cat(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    static class Voice{ //внутренний статический класс
        private final int volume;
        public Voice (int volume) {this.volume = volume;}; //создается без создания внешнего объекта, пример ниже
        public void sayMeow(){
            System.out.printf("A cat meowws with volume %d\n", volume);
        }
    }
}

//в Main
        Cat.Voice voice = new Cat.Voice(30);
        voice.sayMeow();
```

`Генерация случайных символов:`
(Связано с таблицей UNICODE), в которой у каждого символа есть свой код (16-я система исчисления). Таблица символов в панеле задач. Можно вызывать и по обычным 10-ричным - они будут преобразованы в 16-й.

```java
    private static final int CHAR_BOUND_LOW = 65; //Номер начального символа для латинского алф
    private static final int CHAR_BOUND_HIGH = 90; //Номер конечного символа для латинского алф

// main ...

    /**
     * Метод генерации случайной последовательности символов
     *
     * @param amount - кол-во символов
     * @return строку
     */
    private static String generateSymbols(int amount) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            sb.append((char) random.nextInt(CHAR_BOUND_LOW, CHAR_BOUND_HIGH + 1));
        }
        return sb.toString();
    }
```

* Содержит ли файл слово или байтовый массив:
```java
    private static boolean searchInFile(String fileName, String wordToSearch) throws IOException {
        byte[] f2 = wordToSearch.getBytes();
        int a;
        int counter = 0;
        try(FileInputStream fileInputStream = new FileInputStream(fileName)){
            while ((a = fileInputStream.read()) != -1){
                if (f2[counter] == a) counter++;
                else {
                    counter = 0;
                    if (f2[0] == a) counter++; //если не совпавший символ является началом новой последовательности
                }
                if (counter == f2.length) return true;
            }
            return false;
        }
    }
```

* Получить текущую директорию ".":
System.getProperty("user.dir")

`record` - класс (типа модификатор вместо слова class).
Содержит в себе конструктор с принимаемыми в рекорде параметрами и геттеры.
Пример:
public record Record <T1, T2> (T1 t1, T2 t2){ //можно переопределить методы, если необходимо
}

Сравнить строки по алфавиту и отсортировать:  
men.sort(String::compareTo);

---

`Многопоточность` - когда потоков больше 1. Первый поток в java - всегда main

Процесс - код + данные. Черный ящик! Процесс состоит из потоков.
  * процессы работают независимо друг от друга и не имеют прямого доступа к общим данным других процессов
  * опер. с-ма выделяет ресурсы на процесс - время и память
  * если один из процессов заблокирован, то ни один процесс не может выполняться, пока этот процесс не разблокируется
  * процесс может контролировать дочерние процессы, но не процессы того же уровня

`Статусы потоков:` (видно в stacktrace)
* New – экземпляр потока создан, но он еще не работает.
* Running — поток запущен и процессор начинает его выполнение.
* Suspended — запущенный поток приостанавливает свою работу, затем можно возобновить его выполнение.
* Blocked — поток ожидает высвобождения ресурсов или завершение операции ввода-вывода.
* Terminated — поток немедленно завершает свое выполнение.
* Dead — после того, как поток завершил свое выполнение, его состояние меняется на dead, то есть он завершает свой жизненный цикл. //из этого состояния поток перезапустить нельзя

* Для создания нескольких потоков выполнения методов:
        Thread th1 = new Thread(new Runnable(){
            public void run(){ //обязательно нужно реализовать внутри Runnable
                toAttackMonster1();
            }
        });

* Чтобы создать свой поток:  
1. Наследоваться от Thread и незабыть реализовать метод run()!! - любая реализация
2. Имплементировать Runnable. а потом создавать стандартный поток, передавая в него кастомный раннебл
  * new Thread(new MyRunnable).start();
3. Через анонимный класс:
            new Thread(()->{
                System.out.println("Current thread is: " + Thread.currentThread().getName());
            }).start();

`Методы потока Thread:`
* Thread.currentThread().getName() - имя текущего потока 
* Thread.sleep(16) - в цикле, выдаст нам 60fps
* Thread.interrupted(); //прерван ли поток, вернет bool. Можно использовать в условиях цикла работы потока, если таковой используем
* thread.start() - запустить поток. при этом выводится текущий поток в консоль формата: приоритет потока (от 1 до 10) - группа потока.
* thread.setDaemon(true); - сделать поток сервисным. Закончит свою деятельность тогда же, когда и main
* thread.interrupt(); //просим JVM прервать поток + переключает внутренний флаг. Если использовать, когда поток спит - будет **ошибка** прерывания!!! Лучше вообще использовать свои флаги для включения/отключения потока. 
* thread.join(); //анти-многопоточность. Пока этот поток не завершится, новый создать нельзя!


Потоки бывают: 
1. сервисные. Если main поток закончит свою деятельность, то и все сервисные потоки тоже.
2. не сервисные - не умрет, пока его кто-то ручками не убьет либо вообще до перезагрузки ОС. Либо до смерти процесса. //не делать так в цикле while(true)!

> Создание новых поток и освобождение ресурсов - дорогостоящие операции. Можно заранее зарезервировать необходимое количество потоков.

**Экзекьютер** - для выполнения в потоке runnable экземпляров классов.
ExecutorService executor = Executors.newFixedThreadPool(2); //класс экзекьютера, работающий с двумя потоками //здесь Executors - фабрика экзекьютеров. При этом, запустится 2 доп. потока для выполнения

**методы экзекьютеров:**
executor.shutdown(); //если поток не является сервисным (демоном)
executor.execute() - что-то там выполнить
executor.submit(здесь или Runneble - сделал и забыл ИЛИ Callable - сделал и вернул результат) - выполняет также, но и возвращает объект типа Future

**Проблема многопоточности - проблема консистентности**: race condition. Крч, потоки могут менять какие-то общие данные практически одновременно. Один обращается к старому значению, другой к новому и записывают до или после обращения новые значения в эту переменную. Из-за этого конечная модификация может отличаться от ожидаемой.
Может вызвать исключение: ConcurrentModificationException //возникает, когда в одном потоке происходит изменение коллекции, а в другом - итерирование по нему.

Самый простой способ для `синхронизации потоков` (т.е. определить их поведение при работе с общим ресурсом) - концепция "монитора" и использование ключевого слова `"synchronized"` - в месте, где происходит работа с общими данными. При этом, один из потоков как бы "бронирует" такой метод под себя пока с ним работает. ОЧЕНЬ ДОРОГОСТОЯЩАЯ ОПЕРАЦИЯ. Использовать во избежание `CuncurrentModificationException`
    public synchronized void inc(){ //синхронизация метода с остальными потоками на уровне класса. 
        i++;
    }

    public void inc(){ //так в 2 раза быстрее
    synchronized (Main.class){
        i++;
    }
}

Пример одной из гарантированно **синхронизированной коллекции** для одновременного добавления и итерации:
* Queue<Integer> queue = new ConcurrentLinkedDeque<>(); //топ, отлично работает с многопоточкой
* также ConcurrentHashMap
* List<Integer> list = new CopyOnWriteArrayList<>(); //здесь итерация происходит по копии списка. Проблема: к концу итерации другой поток исходный список может уже изменить. 

`CountDownLatch` - класс для отслеживания сосостояния потока. Счетчик для обработавших потоков. Так называемый "примитив синхронизации"

Методы каунтдауна:
countDownLatch.countDown(); //ставить после завершение в потоке задания (то, что в run)
countDownLatch.await(); //ставить в том месте, где в коде мы должны ждать завершения работы потока

CyclicBarrier - — аналог CountDownLatch, но можно использовать повторно

`ReentrantLock` — высокоуровневые механизмы блокировки, как более удобная альтернатива synchronized. //крч как работает. При входе в метод поток делает lock(), - теперь сюда не могут зайти другие потоки. Выполняет работу и вызывает unlock();


`Атомарность` - свойство переменной, гарантирующее ее неделимость. 

`Атомарные переменные`/типы данных: обертки над примитивами, которые можно использовать при работе с не синхронизированными потоками. Но! все равно работает так же медленно, как и синхронизированный метод.
* AtomicInteger
* AtomicLong
* AtomicBoolean
* AtomicReference<V> //обернуть любой объект в атомарную переменную

`Методы атомиков:`
incrementAndGet(); //i++

`ThreadLocal` - это переменные, которые создают свою КОПИЮ под каждый поток.


`Монитор`
* У любого не примитивного типа есть монитор
* состоянии (locked) — признак определяющий захвачен ли монитор каким либо потоком)
* какой поток в текущий момент захватил монитор (owner);
* перечень потоков, которые не смогли захватить монитор (blocked set);
* перечень потоков у которых был вызван метод wait (wait set).

В качестве **монитора** создается экземпляр Object monitor = MyClass.class;

// таким вот образом, как ниже, синхронизируются потоки по монитору на объекте
    synchronized (monitor){
        //здесь делаем что-то полезное
        monitor.notify(); //будит один из потоков, который лежит в wait-сете. ЛЮБОЙ
        try {
            monitor.wait(); //сам засыпает, пока не разбудят. При этом, попадает в wait-сет
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


Deadlock или взаимная блокировка — это ошибка, которая происходит когда threads имеют циклическую зависимость от пары
синхронизированных объектов. //крч, два потока в двух sinchronized методах ждут, пока и тот, и другой освободят свои потоки. Но этого не происходит, т.к. они блокируют захождение в метод друг другу. //или же проще - оба потока отправляем в wait-сет и нет потока, который может разбудить остальных.


`StackTrace` - стек вызовов всех функций в текущем потоке

`Semaphore` - один из примитивов синхронизации, позволяющий определить N потоков, которым позволено
исполнять критическую секцию кода. //крч. Пропускает к работе N потоков, и остальные будут ждать, пока эти N завершат работу. Если синхронайз пропускает только 1, то семафор - N.

Exchanger — точка синхронизации, позволяющая двум потокам обмениваться значениями //не юзабельная история


`volatile` модификатор - все потоки будут обращаться к одной переменной в куче
* Операции чтения/записи volatile переменной являются атомарными.
* Результат операции записи значения в volatile переменную одним потоком, становится виден всем другим потокам, которые используют эту переменную для чтения из нее значения.
Пример:
private volatile boolean flag = true;


`Semaphore` - класс для определения макс. кол-ва выполняемых потоков одновременно. В конструктор передается этот макс.
методы:
semaphore.acquire(); - запросить разрешение на выполнение
semaphore.release(); - сообщить о том, что мы закончили выполнение и можно пустить новый поток

---

` Библиотека Lambok` - для генерации геттеров и сеттеров во время компилляции, не оставляя следов в исходном коде

`Аннотации`:
@Data - генерирует конструктор, геттеры, сеттеры, методы equals, hashCode, toString.
@Value - генерирует конструктор, только геттеры, методы equals, hashCode, toString. А также делает все поля private и final.
  * С неизменяемыми классами хорошо сочетаются аннотации @With и @Builder.
@With - добавляет методы для каждого поля, которые делают клон объекта с одним измененным полем.
  * Cat anotherCat = cat.withName("Вася");
@Builder - генерирует методы, которыми мы инициализируем объект по цепочке. Это удобно когда мы не хотим использовать конструктор со всеми параметрами (Если у нас класс неизменяемый, то в нем единственный конструктор со всеми параметрами).
@Slf4j - добавляет в класс логгер log
@SneakyThrows - делает проверяемые исключения непроверяемыми
@NoArgsConstructor //добавляет конструктор без аргументов
@AllArgsConstructor //добавляет конструктор со всеми параметрами
@RequiredArgsConstructor //добавляет конструктор для final полей
@Getter //добавляет геттеры для всех параметров класса
@Setter //добавляет сеттеры для всех параметров класса
@EqualsAndHashCode //добавляет реализации методов equals и hashCode
@ToString //добавляет реализацию метода toString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) //делает все поля private и final

Пример работы с билдером:  
Cat cat = Cat.builder()
                .name("Мурка")
                .age(3)
                .person(person)
                .build();

Чтобы исключить поля:    
@ToString.Exclude
@EqualsAndHashCode.Exclude

Пример:
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

---














---
**Полезные ссылки:**  
1. канал для изучения JAVA - https://www.youtube.com/channel/UCK5d3n3kfkzlArMccS0TTXA
2. статья о LinkedList - https://habr.com/ru/articles/337558/ 

> как собрать проект с зависимостями в Intelliage Idea 
https://yandex.ru/video/preview/5410863555101948769

> как изменить тип проекта из обычного в Maven
https://skillbox.ru/media/base/kak-iz-obychnogo-ideaproekta-sdelat-maven/


**Проекты**
1. Достаточно полное консольное приложение для работы с реестром животных - в папке по контрольной работе по 
блоку программист  (здесь работа с входящими данными, их обработка, работа в ресурсном трае (например класс счетчик
с имплементацией AutoCloseable)),реализована работа с **json**-серриализацией и дессериализацией. Также, реализована проверка на сохранение данных в БД.
Кроме этого, достаточно полный конфиг ((pom.xml-файла)).
2. Немного про связь с удаленной БД MySql