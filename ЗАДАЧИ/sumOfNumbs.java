//Сумма чисел от 1 до N
public class sumOfNumbs {
    public static void main(String[] args) {
        System.out.println(sumOfNumb(10));
        System.out.println(sumOptimize(10));
    }

    //Классическое решение. Сложность O(n)
    public static int sumOfNumb(int n){
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum+=i;
        }
        return sum;
    }

    //Оптимизированное решение. Сложность O(1) - константная
    public static int sumOptimize(int n){
        return n * (n + 1) / 2;
    }
}
    

