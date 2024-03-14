----->> НАЧАЛО КОДА **C#**
___

\t - **табуляция** между элементами, к примеру, массива  

\n - **с новой строки** 

Console.WriteLine("Hello, World!"); - **вывод в консоль**

Console.ReadLine(); - **считывание вводимых пользователем данных**

переменная.ToLower - **игнорирует размер букв** (например: "МаШа")

Чтобы **возвести в степень**:  
math.pow(x,y) x - число, y - в какую степень его возвести  

Чтобы получить **квадратный корень:**   
Math.Sqrt(x)  
или * 1/2  

Чтобы **округлить**:  
Math.Round (d, 3), где d - переменная, 3 - количество знаков после запятой

**Чтобы конвертировать в int**  
Convert.ToInt32(arg) / int.Parse(arg) // 32 - в десятичное число  

Чтобы **задать/объявить пустую строковую переменную**:  
string arg = String.Empty; или = "";

Чтобы **создать массив:** 
   * одномерный: int [] arr = new int [size];  
   * вмумерный: int [,] arr = new int [rows,columns];  
   * трехмерный: int [,,] arr = new int [length,width, height];  


Чтобы **вывести массив:**  
  * Для **одномерного** массива:  
    * WriteLine(String.Join(", ", arr)); где ", " - сепаратор, arr - массив
    * for (int i = 0; i < arr.Length; i++)  
{  
    System.Console.Write(arr[i] + "\t");  
    // "t" - табуляция, Write - чтобы в одну строку  
}  
  * Для **двухмерного** массива:
    for (int i = 0; i < rows * columns; i++)  
{  
    if (i%columns == 0) System.Console.WriteLine();   
    // перенос на следующую строку, когда количество итераций будет кратно значению columns  
    System.Console.Write(arr[i / columns, i % columns] + "\t");   
    // "t" - табуляция, Write - печать в одну строку   
}

Чтобы **задать рандомное значение элементу**:  
  * **целое число:**  
  int arg = new Random().Next(1,10); 
  int arg = Random.Shared.Next(left,right);
  * **дробное число:**  
  arg = new Random().NextDouble(); //значения будут в промежутке от 0 до 1, для того, чтобы получить значения > 1, просто умножить на 10 или 100 и т.д.  
  *  **чтобы сократить рандомайзер**:  
  Random rand = new Random();  
  и далее используем переменную -> rand.Next();  
* Чтобы **создать массив из рандомных элементов**:  
int [] arr = new int [N].Select (r => rand.Next(0,5)).ToArray(); // r - объявлять не нужно

Чтобы **заполнить двухмерный массив с помощью одного цикла:**  
for (int i = 0; i < rows * columns; i++)  
{  
    arr[i / columns, i % columns] = new Random().Next(1, 10);   
}  

Чтобы **заполнить массив строк** при вводе с клавиатураы:  
string [] arr = console.ReadLine().Split(" "); // будет заполнять массив при вводе данных через **пробел**  

Чтобы **заменить символ**:  
arg.Replace("что заменить","на что");  

Чтобы создать документацию к методу:  /// 

>**Тернарный оператор**  
**Пример**:  
res += arr[i] > 0 ? arr [i] : 0;  
// arr [i] > 0 (**истина**): res += arr [i];  
// array [i] > 0 (**ложь**): res += 0;  

**MAX и MIN для сравнения чисел:**  
*принцип придания абсурдности*
  * min = int.MaxValue;  
  * max = int.MinValue.  

**Чтобы конвертировать в string**  
arg.ToString();  

Чтобы **сократить логические код**:  
  * На примере "**проверка на четность**"  
  bool IsEven (int value)  
  {  
    return value % 2 = 0; // true/false  
  }  

Чтобы **узнать тип переменной (данных)**:  
Console.WriteLine(arg.GetType().Name);  

Чтобы **вычленить из типа string данные** для дальнейшего использования:  
string text = "(1,2) (3,4) (5,6) (7,8)";  
var data = text.Split(" ").ToArray(); // теперь это массив данных ->   
Результат: string [] data = [(1,2),(3,4),(5,6),(7,8)]  

Чтобы **запустить файл программы:**  

1. **Вручную в папке**: ...\Знакомство с С#\Семинар 3\HW_sem3\bin\Debug\net6.exe  
2. **Из cmd**: cd C:\Users\...\bin\Debug\net6.0  
start file.exe    

Чтобы **засечь время выполнения** куска кода:   

using System.Diagnostic;  
Stopwatch sw = new();  
sw.Start();
...  
sw.Stop();  
Console.WriteLine.ElapsedMilliseconds; // вывод результата в миллисекундах   
sw.Reset(); // обнулить таймер  

Чтобы **не писать Console**....  
using static System.Console;  

Чтобы **не писать String**....  
using static System.String;  

Чтобы **вывести методы красиво:**  
перед типом переменной в () после объявления метода добавить "this"  
**Пример**: int Method (this int [] ...) // нельзя исп-ть с void типом  
**Итог**:  
10.CreateArray().Show().SortSelection().Show(); // 10 - размер массива

Чтобы **прописать исключение**, при котором заканчивается выполнение программы с выводом текста:  
if (...) throw new Exception ("...");

> Чтобы создать поток:  

int eachThreadCalc = N/THREADS_NUMB;//**количество элементов на поток**  
var threadsList = new List<Thread>();// **создаем поток**   
for (int i = 0; i < THREADS_NUMB; i++)  
{  
    int startpos = i*eachThreadCalc;  
    int endpos = (i+1)*eachThreadCalc;  
    if (i == THREADS_NUMB-1) endpos = N; //**для последнего потока остаток значений**  
    threadsList.Add(new Thread(() => ProductOfParallelMatrices(Matrix1,Matrix2, startpos, endpos)));  
    //**каждому потоку распределили что он должен делать** 
    threadsList[i].Start(); //**запускаем поток**  
}  
for (int i = 0; i < THREADS_NUMB; i++)  
{  
    threadsList[i].Join();// **ждем, пока все потоки будут готовы** (посчитают задание)  
}   

Чтобы заблокировать пуск кода для других потоков:  
object = locker = new object();
lock (locker) // locker - команда блокировки   
{  
    code  
}

**namespace** - пространство имен  

Чтобы вывести строку из рандомных элементов, букв, цифр:  

var arg = $"Name{Guid.NewGuid().ToString().Substring(0,5)}" 

Чтобы взять **модуль числа**:  

Abs(number) // не забыть System.Math;  

Чтобы **скопировать массив в другой** массив:  
arr.Copy(arr1, arr2, size);


> Клиент - сервер  

Потоки могут быть:  

1. < Thread > - поток исполнения
2. < Stream > - поток данных

Тип данных для поток записи - StreamWriter (данные с клиента на сервер)  
Тип данных для поток чтения - StreamReader  

Связь TCF - требует постоянное соединение, в случае разрыва - сервер упадет 

>Чтобы **хостить на своем компьютере IP - ip 127.0.0.1**  

Типы запросов: 
- get - получить, read (R)  
- put - обновить, update (U)  
- post - создать, create (C)  
- delite - удалить, delete (D)

___
----->> КОНЕЦ КОДА  **C#**
___

> Работа с директориями  
* Чтобы **получить информацию о папке**:    

string path = "C:/Users/DELL/Desktop/...; - **путь к папке**  
DirectoryInfo di = new DirectoryInfo(path);
Console.WriteLine(di.CreationTime); // **возвращает время создания папки**

void CatalogInfo(string path, string indent = " ")  
{  
DirectoryInfo catalogs = new DirectoryInfo(path);  
foreach (var currentCatalog in catalogs.GetDirectories()) // **возвращает список подпапок**
{  
Console.WriteLine($"{indent}{currentCatalog.Name}");  

CatalogInfo(currentCatalog.FullName, indent + " ");  
}  
foreach (var item in catalogs.GetFiles()) // **возвращает список папок текущей директории**  
{  
Console.WriteLine($"{indent}{item.Name}");  
}  
}    
CatalogInfo(path);  

> МЕТОДЫ 

**4 типа методов:**
1. *Ничего не принимает и ничего не возвращает*  
**Пример:**
void Method1()  
{  
    Console.WriteLine("Text");  
}  
Method1(); - **Чтобы вызвать метод** 
2. *Ничего не принимает, но что-то возвращает*  
**Пример:** 
void Method2(string msg)  
{  
Console.WriteLine(msg);  
}  
Method2(msg: "Текст сообщения"); - метод ничего не принимает, значение переменной msg мы прописываем при его вызове
3. *Ничего не принимает и ничего не возвращает*  
**Пример:**  
int Method3()  
{  
    return DateTime.Now.Year;  
}    
int year = Method3();
Console.WriteLine(year); 
4. Что-то принимает и что-то возвращает  
**Пример:**   
string Method4 (int count, string c)  
{  
    int i = 0;    
    string result = String.Empty; 
    while (i < count)  
    {  
        result = result + c;  
        i++;  
    }   
    return result;  
}  
string res = Method4(10, "qwerty");  
Console.WriteLine(res);

> **РЕКУРСИЯ** - функция, которая вызывает саму себя

У рекурсии 2 случая:  
1. базовый - условие для выхода из рекурсии  
2. рекурсивный - вызов функции самой себя  

**На примере чисел Фибоначчи**

// f(1) = 1  
// f(2) = 1  
// f(n) = f(n-1) + f(n-2)  
double Fibonacci(int n)  
{  
    if (n == 1 || n == 0) return 1;  
    else return Fibonacci(n-1) + Fibonacci(n-2);  
}  
for (int i = 0; i <= 45; i++)  
{  
    System.Console.WriteLine($"{i} = {Fibonacci(i)}");  
}  

**На примере программы для перевода из десятичного кода в бинарный**  

int N = 15;  
void ToBinary (int num)  
{  
    if (num == 0) // выход из рекурсии  
    {  
        return;  
    }  
    ToBinary(num/2);  
    System.Console.Write(num%2);  
}  
ToBinary(N); // - вызов метода  

**На примере Факториала**  
int Factorial (int N)  
{  
    if (N == 1) return 1; // базовый случай  
    return N*Factorial(N-1); //рекурсивный случай  
}

**Пример: Рекурсия**, в которой используются новые переменные:  
[task_47.py](./task_47.py) 

