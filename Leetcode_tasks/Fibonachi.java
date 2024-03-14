//Числа Фибоначчи
public class Fibonachi {
    public static void main(String[] args) {
        System.out.println(fibonacci(10));
        System.out.println(fibonacci_optimize(10));
    }
    
    //Классическое решение через рекурсию. Сложность O(2^n), а точнее 1.6^n (подсчет учеными)
    public static int fibonacci(int n){
        if (n == 0) return 0;
        if (n == 1) return 1;
        else return fibonacci(n-2) + fibonacci(n-1);
    }

    //оптимизированное решение через массив (меморизацию). Сложность O(n)
    public static int fibonacci_optimize(int n){
        int [] array = new int[n+1];
        array[0] = 0;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i-1] + array[i-2];
        }
        return array[n];
    }
}