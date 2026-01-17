
Создать стрим из файла
```java
        try (Stream<String> stream = Files.lines(Paths.get("example.txt"))) { // Создаем стрим из строк файла
            stream.forEach(System.out::println); // Выводим строки файла
        } catch (Exception e) {
            e.printStackTrace();
        }
```

`Основные функциональные интерфейсы в Java для использования в стримах`

Java предоставляет несколько стандартных функциональных интерфейсов в пакете java.util.function. Вот некоторые из них:

`Function`<T, R>

Метод: R apply(T t)

Описание: Представляет функцию, которая принимает аргумент типа T и возвращает результат типа R.

`Consumer`

Метод: void accept(T t)

Описание: Представляет операцию, которая принимает аргумент типа T и ничего не возвращает.

`Supplier`

Метод: T get()

Описание: Представляет функцию, которая не принимает аргументов и возвращает результат типа T.

`Predicate`

Метод: boolean test(T t)

Описание: Представляет предикат (логическую функцию) над одним аргументом типа T.

`UnaryOperator`

Метод: T apply(T t)

Описание: Представляет операцию над одним аргументом типа T и возвращает результат того же типа. Является подтипом Function<T, T>.

`BinaryOperator`

Метод: T apply(T t1, T t2)

Описание: Представляет операцию над двумя аргументами типа T и возвращает результат того же типа. Является подтипом BiFunction<T, T, T>.

`BiFunction`<T, U, R>

Метод: R apply(T t, U u)

Описание: Представляет функцию, которая принимает два аргумента типа T и U и возвращает результат типа R.

`BiConsumer`<T, U>

Метод: void accept(T t, U u)

Описание: Представляет операцию, которая принимает два аргумента типа T и U и ничего не возвращает.

`BiPredicate`<T, U>

Метод: boolean test(T t, U u)

Описание: Представляет предикат (логическую функцию) над двумя аргументами типа T и U.

```java
import java.util.function.*;

public class FunctionalInterfacesExample {
    public static void main(String[] args) {
        // Пример использования Function
        Function<String, Integer> stringLength = s -> s.length();
        System.out.println(stringLength.apply("Hello")); // Вывод: 5

        // Пример использования Consumer
        Consumer<String> printString = s -> System.out.println(s);
        printString.accept("World"); // Вывод: World

        // Пример использования Supplier
        Supplier<Double> randomValue = () -> Math.random();
        System.out.println(randomValue.get()); // Вывод: случайное число

        // Пример использования Predicate
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println(isEven.test(4)); // Вывод: true

        // Пример использования UnaryOperator
        UnaryOperator<Integer> square = n -> n * n;
        System.out.println(square.apply(5)); // Вывод: 25

        // Пример использования BinaryOperator
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        System.out.println(sum.apply(3, 4)); // Вывод: 7

        // Пример использования BiFunction
        BiFunction<Integer, Integer, String> concatNumbers = (a, b) -> a + "" + b;
        System.out.println(concatNumbers.apply(1, 2)); // Вывод: 12

        // Пример использования BiConsumer
        BiConsumer<String, String> printConcat = (s1, s2) -> System.out.println(s1 + s2);
        printConcat.accept("Hello, ", "Java"); // Вывод: Hello, Java

        // Пример использования BiPredicate
        BiPredicate<Integer, Integer> areEqual = (a, b) -> a.equals(b);
        System.out.println(areEqual.test(5, 5)); // Вывод: true
    }

```

Виды операторов:
- промежуточные
- терминальные

Промежуточные:
- filter (Predicate)
- map (Function)
- flatMap (Function<T, Stream>)
- distinct (использует equals())
- sorted
- peek (Consumer)
- limit
- skip

Терминальные:
- count
- forEach
- collect
- toArray
- toList
- anyMatch(Predicate)
- allMatch(Predicate)
- nonMatch
- findFirst возвращает Optional
- findAny возвращает Optional
- reduce (BinaryOperator)

`Stateless и Stateful операторы`
-  различие основано на том, сохраняют ли они состояние между обработками элементов потока.

Примеры `stateless операций`:

filter(Predicate<? super T> predicate)

map(Function<? super T, ? extends R> mapper)

flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)

peek(Consumer<? super T> action)

distinct()- (в некоторых случаях может быть stateful, но обычно считается stateless)

`Statefule операторы`
sorted()

distinct() - (в некоторых случаях может быть stateful, если требуется сохранить состояние для определения уникальности элементов)

limit(long maxSize)

skip(long n)

`Parallel Stream`
Parallel Stream (параллельные стримы) в Java представляют возможность обработки данных в параллельном режиме с использованием многопоточности. Оператор parallelStream() позволяет создать параллельный стрим из коллекции или другого источника данных. В параллельном стриме элементы обрабатываются одновременно несколькими потоками, что может улучшить производительность в определенных сценариях.

Важные моменты по использованию параллельных стримов:
Создание параллельного стрима:

Параллельный стрим можно создать вызовом метода parallelStream() на коллекции или вызовом метода parallel() на обычном стриме. Параллельные

`Операции:`

Многие операции стримов, такие как map, flatMap, filter, forEach и другие, могут быть автоматически выполнены параллельно в параллельном стриме.

`Thread Safety:`

Важно обеспечить, чтобы функции, передаваемые в операции стрима, были потокобезопасными. Также, следует быть осторожным при работе с изменяемыми данными в параллельных стримах, чтобы избежать состояния гонки (race conditions).

`Порядок Результата:`

Результаты операций параллельного стрима могут быть в случайном порядке, поскольку элементы обрабатываются параллельно.

`Parallel vs. Sequential:`

Использование параллельных стримов имеет смысл, когда объем данных велик и операции дорогостоящи. Для небольших объемов данных или легких операций, последовательные стримы могут быть более эффективными из-за накладных расходов на управление потоками.

`Управление Параллелизмом:`

Метод parallelStream() использует размер доступных ядер процессора для выполнения параллельных операций. Можно также управлять уровнем параллелизма с помощью метода parallel() и sequential().

Fork/Join Pool (пул разветвления/слияния) в Java представляет собой механизм выполнения параллельных задач, основанный на принципе "разделяй и властвуй". Этот механизм часто используется в контексте параллельных стримов.

Когда вы создаёте параллельный стрим с помощью parallelStream(), он использует под капотом Fork/Join Pool для управления параллельными операциями. Fork/Join Pool рекурсивно разделяет задачу на подзадачи, выполняет их параллельно и затем сливает результаты.

Важные моменты относительно Fork/Join Pool и параллельных стримов:

`Дефолтный Пул Разветвления/Слияния:`

Если вы используете parallelStream(), стрим будет использовать дефолтный экземпляр Fork/Join Pool, который создается в момент его первого использования.

`Управление Параллелизмом:`

Fork/Join Pool управляет уровнем параллелизма, определяя количество работающих потоков в системе. Вы можете настраивать его параметры, такие как ForkJoinPool.commonPool.parallelism, чтобы контролировать, сколько потоков используется.

`Предотвращение Deadlocks:`

Fork/Join Pool предотвращает потенциальные проблемы с блокировками, связанными с параллельной обработкой, чтобы избежать deadlock'ов.

`Nested Fork/Join:`

Если внутри задачи, выполняемой в Fork/Join Pool, используется еще один Fork/Join Pool, то он будет использовать общий пул разветвления/слияния, если не указан другой.


`Класс ForkJoinPoll`

### Основные особенности ForkJoinPool

1. Работа с задачами: 
   ForkJoinPool позволяет разбивать задачи на меньшие подзадачи с помощью методологии "разделяй и властвуй". Методы fork() и join() позволяют разработчикам легко реализовывать эту концепцию.

   - fork(): Запускает задачу асинхронно. Задача помещается в очередь для выполнения.
   - join(): Ожидает завершения задачи и возвращает результат. Если задача ещё не завершена, метод немедленно блокирует текущий поток.

2. Работа с RecursiveTask и RecursiveAction:
   - RecursiveTask<V>: Используется для задач, которые возвращают результат.
   - RecursiveAction: Используется для задач, которые не возвращают результат.

   Оба эти класса предоставляют метод compute(), который необходимо переопределить для определения логики выполнения задачи.

```java
public class ForkJoinFactorial {
    public static void main(String[] args) {
        int n = 10; // Вычисление факториала для числа 10

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
    }

    private static class FactorialTask extends RecursiveTask<Integer> {
        private int n;

        public FactorialTask(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n == 1) {
                return n;
            }

            FactorialTask task = new FactorialTask(n - 1);
            task.fork();
            return n * task.join();
        }
    }
}
```